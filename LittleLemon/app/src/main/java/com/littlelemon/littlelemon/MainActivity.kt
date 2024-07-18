package com.littlelemon.littlelemon

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.littlelemon.littlelemon.ui.theme.LittleLemonTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(contentType = ContentType("text", "plain"))
        }
    }

    private val database by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database").build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LittleLemonTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()
                    NavigationComposable(context = applicationContext, navController)
                }
            }
        }

        lifecycleScope.launch(Dispatchers.IO) {
            if (database.MenuItemDao().isEmpty()) {
                saveMenuToDatabase(fetchMenu())
            }
        }
    }

    private suspend fun fetchMenu(): List<MenuItemNetwork> {
        val response = httpClient
            .get("https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json")
            .body<MenuNetwork>()
        return response.menu
    }

    private fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
        database.MenuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }
}

class MenuItemColumn(context: Context) {

    private val database by lazy {
        Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
    }


    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun MenuItemsList(searchPhrase : String) {
        val databaseMenuItems by database.MenuItemDao().getAll().observeAsState(emptyList())
        var menuItems = databaseMenuItems

        if (searchPhrase != "") {
            menuItems = menuItems.filter { searchPhrase.lowercase() in (it.title).lowercase() }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxHeight()
                .padding(10.dp)
        ) {
            items(
                items = menuItems,
                itemContent = { menuItem ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(0.65F)
                        ) {
                            Text(
                                menuItem.title,
                                style = TextStyle(
                                    fontSize = 25.sp
                                ),
                                modifier = Modifier.padding(vertical = 5.dp)
                            )
                            Text(
                                text = menuItem.description,
                                style = TextStyle(
                                    color = Color(0xff495e57),
                                    fontSize = 18.sp
                                ),
                                modifier = Modifier.padding(vertical = 5.dp)
                            )
                            Text(
                                text = "$" + "%.2f".format(menuItem.price),
                                style = TextStyle(
                                    color = Color(0xff495e57),
                                    fontSize = 23.sp
                                ),
                                modifier = Modifier.padding(vertical = 5.dp)
                            )
                        }
                        Column {
                            GlideImage(model = menuItem.image, contentDescription = "MenuItem Image")
                        }
                    }
                    HorizontalDivider()
                }
            )
        }
    }
}