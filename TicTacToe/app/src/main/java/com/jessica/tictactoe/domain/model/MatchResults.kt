package com.jessica.tictactoe.domain.model

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

data class MatchResults(
    val playerX: String,
    val playerO: String,
    val result: String,
    val timestamp: String = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"))
        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))
)