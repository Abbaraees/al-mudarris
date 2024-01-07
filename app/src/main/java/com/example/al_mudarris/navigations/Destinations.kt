package com.example.al_mudarris.navigations

interface Destinations {
    val route: String
    val name: String
}

object Setup:Destinations {
    override val name = "setup"
    override val route = "setup"
}

object Login:Destinations {
    override val name = "login"
    override val route = "login"
}

object Dashboard:Destinations {
    override val name = "dashboard"
    override val route = "dashboard"
}

object Students:Destinations {
    override val name = "students"
    override val route = "students"
}
