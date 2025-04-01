package com.hsmodel.rightnow.navigation

import android.annotation.SuppressLint
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.hsmodel.rightnow.viewmodels.GroupViewModel
import com.hsmodel.rightnow.views.AddGroup
import com.hsmodel.rightnow.views.AddMember
import com.hsmodel.rightnow.views.Group
import com.hsmodel.rightnow.views.Home
import com.hsmodel.rightnow.views.SelectGroup
import com.hsmodel.rightnow.views.Settings

@SuppressLint("UnrememberedMutableState")
@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    val groupViewModel: GroupViewModel = viewModel()

    NavHost(
        navController,
        startDestination = NavRoutes.Home.route,
        modifier = modifier
    ) {
        composable(NavRoutes.Home.route) {
            Home(groupViewModel)
        }

        composable(NavRoutes.Group.route) {
            Group(groupViewModel)
        }

        composable(NavRoutes.Settings.route) {
            Settings()
        }

        composable(NavRoutes.AddGroup.route) {
            val addGroupListener = {
                navController.navigate(NavRoutes.Group.route)
            }

            AddGroup(groupViewModel, addGroupListener)
        }

        composable(NavRoutes.AddMember.route) {
            val addMemberListener = { memberName: String ->
                navController.navigate(NavRoutes.SelectGroup.route + "/$memberName"/*.replace("member_name", memberName)*/)
            }
            AddMember(groupViewModel, addMemberListener)
        }

        composable(NavRoutes.SelectGroup.route + "/{member_name}") {
            val memberName = it.arguments?.getString("member_name") ?: ""

            val selectGroupListener = { navController.navigate(NavRoutes.Group.route) }
            SelectGroup(groupViewModel, memberName, selectGroupListener)
        }

        /*composable("groupDetail/{groupName}") { backStackEntry ->
            val groupName = backStackEntry.arguments?.getString("groupName")
            if (groupName != null) {
                GroupDetails(groupName)
            } else {
                Toast.makeText(LocalContext.current, "not found", Toast.LENGTH_SHORT).show()
            }
        }*/
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.barItems.forEach { barItem ->
            NavigationBarItem(
                selected = currentRoute == barItem.route,
                onClick = {
                    navController.navigate(barItem.route)
                },
                icon = {
                    Icon(
                        imageVector = barItem.image,
                        contentDescription = barItem.name
                    )
                },
                label = {
                    Text(text = barItem.name)
                }
            )
        }
    }
}