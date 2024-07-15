package com.littlelemon.littlelemon

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun Profile(context: Context, navController: NavHostController) {
    val sharedPrefs = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    val firstName = sharedPrefs.getString("firstName", "")
    val lastName = sharedPrefs.getString("lastName", "")
    val email = sharedPrefs.getString("email", "")

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 70.dp)
                .height(50.dp)
                .fillMaxWidth()
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 50.dp, horizontal = 8.dp),
            text = "Profile Information",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 8.dp),
            value = firstName.toString(),
            readOnly = true,
            onValueChange = {},
            label = { Text(text = "First Name")}
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 8.dp),
            value = lastName.toString(),
            readOnly = true,
            onValueChange = {},
            label = { Text(text = "Last Name")}
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 8.dp),
            value = email.toString(),
            readOnly = true,
            onValueChange = {},
            label = { Text(text = "Email")}
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 225.dp, start = 8.dp, end = 8.dp)
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Color(0xffF4CE14)),
            onClick = {
                sharedPrefs.edit()
                    .clear()
                    .apply()
                navController.navigate(Onboarding.route)
            }
        ) {
            Text(
                text = "Log out",
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Normal
            )
        }
    }
}