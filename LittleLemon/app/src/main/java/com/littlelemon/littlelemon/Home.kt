package com.littlelemon.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController



@Composable
fun Home(context: Context, navController: NavHostController){
    var searchPhrase by remember {
        mutableStateOf("")
    }

    var buttonPhrase by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 0.dp, top = 20.dp, bottom = 20.dp, end = 0.dp)
                    .height(50.dp)
                    .fillMaxWidth(0.8F)
            )
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Profile Icon",
                modifier = Modifier
                    .size(65.dp)
                    .clickable(onClick = { navController.navigate(Profile.route) })
                    .padding(start = 0.dp, top = 18.dp, bottom = 2.dp, end = 0.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xff495e57))
        ) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    text = "Little Lemon",
                    style = TextStyle(
                        color = Color(0xfff4ce14),
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold
                    ),
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column{
                        Text(
                            text = "Chicago",
                            style = TextStyle(
                                color = Color(0xffffffff),
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold
                            ),
                        )
                        Text(
                            text = "We are a family owned\n" + "Mediterranean restaurant,\n" + "focused on traditional\n" + "recipes served with a\n" + "modern twist.",
                            style = TextStyle(
                                color = Color(0xffffffff),
                                fontSize = 20.sp,
                            ),
                            modifier = Modifier.padding(top = 25.dp)
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.hero),
                            contentDescription = "Hero Image",
                            modifier = Modifier.clip(shape = RoundedCornerShape(10.dp)),
                        )
                    }
                }
                OutlinedTextField(
                    value = searchPhrase,
                    onValueChange = {searchPhrase = it},
                    placeholder = { Text(
                        text = "Enter Search Phrase",
                        color = Color(0xff767171)
                    )},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                        .background(color = Color(0xffeaeaea)),
                    leadingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "") },
                )
            }
        }
        Text(
            "ORDER FOR DELIVERY!",
            fontWeight = FontWeight.Bold,
            fontSize = 27.sp,
            modifier = Modifier.padding(top = 30.dp, start = 10.dp, bottom = 10.dp)
        )
        Row(
            modifier = Modifier.horizontalScroll(rememberScrollState())
        ) {
            Button(
                onClick = {
                    buttonPhrase = if(buttonPhrase=="starters"){
                        ""
                    } else {
                        "starters"
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color(0xff495e57),
                    containerColor = if(buttonPhrase=="starters"){
                        Color(0xff333333)
                    }else{
                        Color(0xffedefee)
                    }
                ),
                modifier = Modifier.padding(start = 10.dp, bottom = 20.dp)
                ) {
                Text(
                    text = "Starters",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Button(
                onClick = {
                    buttonPhrase = if(buttonPhrase=="mains"){
                        ""
                    } else {
                        "mains"
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color(0xff495e57),
                    containerColor = if(buttonPhrase=="mains"){
                        Color(0xff333333)
                    }else{
                        Color(0xffedefee)
                    }
                ),
                modifier = Modifier.padding(start = 10.dp, bottom = 20.dp)
            ) {
                Text(
                    text = "Mains",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Button(
                onClick = {
                    buttonPhrase = if(buttonPhrase=="desserts"){
                        ""
                    } else {
                        "desserts"
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color(0xff495e57),
                    containerColor = if(buttonPhrase=="desserts"){
                        Color(0xff333333)
                    }else{
                        Color(0xffedefee)
                    }
                ),
                modifier = Modifier.padding(start = 10.dp, bottom = 20.dp)
            ) {
                Text(
                    text = "Desserts",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Button(
                onClick = {
                    buttonPhrase = if(buttonPhrase=="drinks"){
                        ""
                    } else {
                        "drinks"
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color(0xff495e57),
                    containerColor = if(buttonPhrase=="drinks"){
                        Color(0xff333333)
                    }else{
                        Color(0xffedefee)
                    }
                ),
                modifier = Modifier.padding(start = 10.dp, bottom = 20.dp)
            ) {
                Text(
                    text = "Drinks",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
        HorizontalDivider(color = Color.Black)
        MenuItemColumn(context = context).MenuItemsList(searchPhrase, buttonPhrase)
    }
}