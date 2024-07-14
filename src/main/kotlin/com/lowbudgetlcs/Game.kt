package com.lowbudgetlcs

import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val id: Int,
    val series_id: Int,
)
