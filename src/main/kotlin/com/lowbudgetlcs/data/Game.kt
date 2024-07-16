package com.lowbudgetlcs.data

import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val id: Int,
    val series_id: Int,
)
