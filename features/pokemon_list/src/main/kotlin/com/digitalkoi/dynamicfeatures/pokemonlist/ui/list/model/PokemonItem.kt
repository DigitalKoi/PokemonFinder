package com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.model

import com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.PokemonListFragment

/**
 * Model view to display on the screen [PokemonListFragment].
 */
data class PokemonItem(
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
}
