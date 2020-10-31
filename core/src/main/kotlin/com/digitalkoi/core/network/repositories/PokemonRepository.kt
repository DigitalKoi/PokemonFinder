package com.digitalkoi.core.network.repositories

import androidx.annotation.VisibleForTesting
import com.digitalkoi.core.network.responses.BaseResponse
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
     * @param page A page count for offset
     * @return Response for pokemon's resource.
     */
    suspend fun getPokemons(page: Int): PokemonsResponse =
        service.getPokemons(
            limit = POKEMON_LIMIT_ITEMS_COUNT,
            offset = calculateOffset(page)
        )

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