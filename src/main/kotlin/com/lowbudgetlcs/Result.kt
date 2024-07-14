package com.lowbudgetlcs

import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val startTime: Long,
    val shortCode: String,
    val metaData: Game,
    val gameId: Long,
    val gameName: String,
    val gameType: String,
    val gameMap: Int,
    val gameMode: String,
    val region: String,
)
