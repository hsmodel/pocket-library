package com.hsmodel.rightnow.navigation

sealed class NavRoutes(val route: String) {

    object Home: NavRoutes("home")
    object Group: NavRoutes("group")
    object Settings: NavRoutes("settings")

    //object GroupDetail: NavRoutes("groupDetail")

    object AddGroup: NavRoutes("addGroup")
    object AddMember: NavRoutes("addMember")
    object SelectGroup: NavRoutes("selectGroup")
    
}