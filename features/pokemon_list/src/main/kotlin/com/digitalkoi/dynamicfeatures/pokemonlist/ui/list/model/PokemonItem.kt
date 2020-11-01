package com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.model

import com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.PokemonListFragment

/**
 * Model view to display on the screen [PokemonListFragment].
 */
data class PokemonItem(
    val id: Long,
    val name: String,
    val description: String,
    val imageUrl: String
)
