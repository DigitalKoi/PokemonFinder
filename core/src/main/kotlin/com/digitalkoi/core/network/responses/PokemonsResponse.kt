package com.digitalkoi.core.network.responses


data class PokemonsResponse(
    val count: Long,
    val next: String,
    val previous: String,
    val results: List<PokemonResponse>
) {

    data class PokemonResponse(
        val name: String,
        val url: String
    )
}