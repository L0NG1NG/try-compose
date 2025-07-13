package com.longing.awesomeview.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.longing.awesomeview.ui.AwesomeViewNavGraph
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    var selectedItem by remember { mutableStateOf(NavigationItems.Home) }

    val scope = rememberCoroutineScope()

    val drawerItems = listOf(
        NavigationItems.Home,
        NavigationItems.Feature1
    )

    ModalNavigationDrawer(
        drawerContent = {
            NavigationDrawer(
                navController = navController,
                drawerItems = drawerItems,
                onItemClick = { item ->
                    selectedItem = item
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }

                    scope.launch { drawerState.close() }
                }
            )
        }) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = selectedItem.title) },
                    navigationIcon = {
                        androidx.compose.material3.Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "菜单",
                            modifier = Modifier.clickable { scope.launch { drawerState.open() } },
                        )
                    }
                )
            }
        ) { paddingValues ->
            AwesomeViewNavGraph(Modifier.padding(paddingValues), navController)
        }
    }
}


object NavigationItems {
    val Home = NavigationItem(
        title = "首页",
        icon = Icons.Default.Home,
        route = "home"
    )

    val Feature1 = NavigationItem(
        title = "todo",
        icon = Icons.Default.FavoriteBorder,
        route = "todo"
    )
}