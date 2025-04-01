@file:OptIn(ExperimentalMaterial3Api::class)

package com.hsmodel.rightnow

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hsmodel.rightnow.components.ExpandableFloatingButton
import com.hsmodel.rightnow.navigation.BottomNavigationBar
import com.hsmodel.rightnow.navigation.NavRoutes
import com.hsmodel.rightnow.navigation.NavigationHost
import com.hsmodel.rightnow.ui.theme.RightNowTheme
import com.hsmodel.rightnow.utils.LOG_HEADER

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RightNowTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    Scaffold(
        topBar = { CenterAlignedTopAppBar(
            title = { Text(text = stringResource(R.string.app_topbar_name)) },
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary,
            ),
            actions = {
                IconButton(onClick = {  navController.navigate(NavRoutes.Settings.route) }) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = stringResource(R.string.msg_navigate_to_setting)
                    )
                }
            },
        )},
        floatingActionButton = {
            val context = LocalContext.current
            val onClickListener = { text: String ->
                when (text) {
                    context.getString(R.string.add_group) -> navController.navigate(NavRoutes.AddGroup.route)
                    context.getString(R.string.add_member) -> navController.navigate(NavRoutes.AddMember.route)
                    else -> System.err.println("$LOG_HEADER something wrong action happens")
                }
            }
            if (currentRoute != null && currentRoute == NavRoutes.Group.route) {
                ExpandableFloatingButton(onClickListener)
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        NavigationHost(navController = navController, Modifier.padding(innerPadding))
    }
}