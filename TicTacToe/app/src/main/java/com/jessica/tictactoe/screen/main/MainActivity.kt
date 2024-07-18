package com.jessica.tictactoe.screen.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.jessica.tictactoe.screen.Route
import com.jessica.tictactoe.screen.gametictactoe.navigationGameTicTacToe
import com.jessica.tictactoe.screen.home.navigationHomeScreen
import com.jessica.tictactoe.screen.matchhistory.navigationMatchHistory
import com.jessica.tictactoe.ui.theme.TicTacToeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TicTacToeTheme {
                AppContent()
            }
        }
    }
}

@Composable
fun AppContent() {
    val navController = rememberNavController()
    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = Route.HOME.name
        ) {
            navigationHomeScreen(navController)
            navigationGameTicTacToe(navController)
            navigationMatchHistory(navController)
        }
    }
}