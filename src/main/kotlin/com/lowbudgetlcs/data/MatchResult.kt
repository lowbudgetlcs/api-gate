package com.lowbudgetlcs.data

import kotlinx.serialization.*

@Serializable
data class MatchResult(
    val startTime: Long,
    val shortCode: String,
    val metaData: Game,
    val gameId: Long?,

    ) {
    val gameName: String = ""
    val gameType: String = ""
    val gameMap: Int = -1
    val gameMode: String = ""
    val region: String = ""
}