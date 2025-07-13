package com.longing.awesomeview.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)

@Composable
fun NavigationDrawer(
    drawerState: DrawerState,
    navController:NavController,
    drawerItems: List<NavigationItem>,
    onItemClick: (NavigationItem) -> Unit,
) {
    ModalDrawerSheet(
        drawerState = drawerState,
    ) {
        LazyColumn {
            items(drawerItems.size) { index ->
                val item = drawerItems[index]
                DrawerItem(
                    item = item,
                    selected = navController.currentDestination?.route == item.route,
                    onItemClick = onItemClick
                )
            }
        }
    }

}

@Composable
fun DrawerItem(
    item: NavigationItem,
    selected: Boolean,
    onItemClick: (NavigationItem) -> Unit
){
    val background = if (selected) {
        MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)
    } else {
        Color.Transparent
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick(item) }
            .background(background)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Icon(
            imageVector = item.icon,
            contentDescription = item.title,
            tint = if (selected) MaterialTheme.colorScheme.secondary else Color.White
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = item.title,
            color = if (selected) MaterialTheme.colorScheme.secondary else Color.White,
            fontSize = 16.sp
        )

    }
}