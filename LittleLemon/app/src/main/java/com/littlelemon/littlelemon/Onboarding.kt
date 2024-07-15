package com.littlelemon.littlelemon


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Onboarding(context: Context, navController: NavHostController) {
    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .padding(20.dp)
                .height(50.dp)
                .fillMaxWidth()
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(Color(0xff495E57))
                .padding(30.dp),
            text = "Let's get to know you",
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            color = Color.White
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 50.dp, horizontal = 8.dp),
            text = "Personal Information",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 8.dp),
            value = firstName,
            onValueChange = {firstName = it},
            label = { Text(text = "First Name")}
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 8.dp),
            value = lastName,
            onValueChange = {lastName = it},
            label = { Text(text = "Last Name")}
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 8.dp),
            value = email,
            onValueChange = {email = it},
            label = { Text(text = "Email")}
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 170.dp, start = 8.dp, end = 8.dp)
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(Color(0xffF4CE14)),
            onClick = {
                if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty()){
                    makeToast(context, "Registration Unsuccessful! Please enter all data")
                } else {
                    saveData(context, firstName, lastName, email)
                    makeToast(context, "Registration successful!")
                    navController.navigate(Home.route)
                }
            }
        ) {
            Text(
                text = "Register",
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Normal
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OnboardingPreview(){
    Onboarding(context = LocalContext.current, navController = rememberNavController())
}

fun makeToast(context: Context, msg: String) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}
fun saveData(context: Context, firstName: String, lastName: String, email: String) {
    val sharedPref = context.getSharedPreferences("LittleLemon", Context.MODE_PRIVATE)
    sharedPref.edit()
        .putString("firstName", firstName)
        .putString("lastName", lastName)
        .putString("email", email)
        .apply()
}