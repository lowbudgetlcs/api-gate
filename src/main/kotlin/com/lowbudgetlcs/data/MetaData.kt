package com.lowbudgetlcs.data

import kotlinx.serialization.Serializable

@Serializable
data class MetaData(
    val gameNum: Int,
    val seriesId: Int,
)
