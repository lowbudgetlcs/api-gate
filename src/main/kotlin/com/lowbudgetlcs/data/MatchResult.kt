package com.lowbudgetlcs.data

import kotlinx.serialization.Serializable

@Serializable
data class MatchResult(
    val startTime: Long,
    val shortCode: String,
    val metaData: String,
    val gameId: Long = 0,
    val gameName: String = "",
    val gameType: String = "",
    val gameMap: Int = -1,
    val gameMode: String = "",
    val region: String = ""
)