package com.digitalkoi.dynamicfeatures.pokemonlist.ui.detail.model

import com.digitalkoi.dynamicfeatures.pokemonlist.ui.detail.PokemonDetailFragment

/**
 * Model view to display on the screen [PokemonDetailFragment].
 */
data class PokemonDetail(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String
)
