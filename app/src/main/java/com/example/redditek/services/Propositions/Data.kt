package com.example.redditek.services.Propositions

data class Data(
    val after: Any,
    val before: Any,
    val children: List<Children>,
    val dist: Int,
    val geo_filter: String,
    val modhash: Any
)
