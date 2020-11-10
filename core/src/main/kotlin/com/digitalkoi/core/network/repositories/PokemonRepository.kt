package com.digitalkoi.core.network.repositories

import androidx.annotation.VisibleForTesting
import com.digitalkoi.core.network.responses.PokemonsResponse
import com.digitalkoi.core.network.services.PokemonService


/**
 * Repository module for handling pokemon API network operations [PokemonService].
 */
class PokemonRepository(
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal val service: PokemonService
) {

    /**
     * Get list of Pokemon.
     *
     * @param offset A page count for offset
     * @return Response for pokemon's resource.
     */
    suspend fun getPokemons(offset: Int, limit: Int): PokemonsResponse =
        service.getPokemons(
            limit = limit,
            offset = offset
        )

    suspend fun getPokemon(id: Long): PokemonsResponse.PokemonResponse =
        service.getPokemon(id)

    /**
     * Calculate offset position.
     *
     * @param page A page count for offset.
     * @return offset position.
     */
    private fun calculateOffset(page: Int) =
        if (page == 0) page
        else POKEMON_LIMIT_ITEMS_COUNT * page


    companion object {
        private const val POKEMON_LIMIT_ITEMS_COUNT = 30
    }
}