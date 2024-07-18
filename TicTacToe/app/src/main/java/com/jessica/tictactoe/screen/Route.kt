package com.jessica.tictactoe.screen

internal enum class Route {
    HOME,
    GAME_TICTACTOE,
    MATCH_HISTORY;

    companion object {
        fun getValue(route: String) = Route.entries.firstOrNull { it.name == route }
    }
}