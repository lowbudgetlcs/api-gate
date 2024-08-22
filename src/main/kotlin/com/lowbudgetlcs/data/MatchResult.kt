package com.lowbudgetlcs.data

import kotlinx.serialization.*

@Serializable
data class MatchResult(
    val startTime: Long,
    val shortCode: String,
    val metaData: String,
    val gameId: Long?,
) {
    val gameName: String = ""
    val gameType: String = ""
    val gameMap: Int = -1
    val gameMode: String = ""
    val region: String = ""
}

@Serializable
data class MetaData(
    val gameNum: Int,
    val seriesId: Int,
)
