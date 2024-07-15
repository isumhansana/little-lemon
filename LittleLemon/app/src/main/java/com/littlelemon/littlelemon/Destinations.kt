package com.littlelemon.littlelemon

interface Destinations {
    val route: String
}

object Home: Destinations{
    override val route = "com.littlelemon.littlelemon.Home"
}

object Profile: Destinations{
    override val route = "com.littlelemon.littlelemon.Profile"
}

object Onboarding: Destinations{
    override val route = "com.littlelemon.littlelemon.Onboarding"
}