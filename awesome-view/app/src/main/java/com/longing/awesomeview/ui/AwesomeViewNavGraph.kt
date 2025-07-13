package com.longing.awesomeview.ui

import android.R
import android.R.attr.title
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.longing.awesomeview.ui.main.NavigationItems

@Composable
fun AwesomeViewNavGraph(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationItems.Home.route,
        modifier = modifier
    ) {
        composable(NavigationItems.Home.route) {
            ScreenContent(NavigationItems.Home.title)
        }

        composable(NavigationItems.Feature1.route) {
            ScreenContent(NavigationItems.Home.title)
        }
    }
}

@Composable
fun ScreenContent(title: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "这是 $title 页面",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}