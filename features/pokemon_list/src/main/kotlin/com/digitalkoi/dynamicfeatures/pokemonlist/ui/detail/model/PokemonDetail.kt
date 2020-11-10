package com.digitalkoi.dynamicfeatures.pokemonlist.ui.detail.model

import com.digitalkoi.dynamicfeatures.pokemonlist.ui.detail.PokemonDetailFragment

/**
 * Model view to display on the screen [PokemonDetailFragment].
 */
data class PokemonDetail(
    val id: Long,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<Type>,
    val stats: Stat,
    val imageUrl: String
) {

    data class Type(
        val slot: Int,
        val type: TypeDetails
    ) {
        data class TypeDetails(
            val name: String,
            val url: String
        )
    }

    data class Stat(
        val hp: Int,
        val attack: Int,
        val defense: Int,
    )

    fun getTypesString() = types.joinToString(separator = ", ") { it.type.name }
}

