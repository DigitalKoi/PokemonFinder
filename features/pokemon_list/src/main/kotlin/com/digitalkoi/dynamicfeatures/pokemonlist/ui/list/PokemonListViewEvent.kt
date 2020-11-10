package com.digitalkoi.dynamicfeatures.pokemonlist.ui.list

/**
 * Different interaction events for [PokemonListFragment].
 */
sealed class PokemonListViewEvent {

    /**
     * Open pokemon detail view.
     *
     * @param id Pokemons identifier
     */
    data class OpenCharacterDetail(val id: Long) : PokemonListViewEvent()
}
