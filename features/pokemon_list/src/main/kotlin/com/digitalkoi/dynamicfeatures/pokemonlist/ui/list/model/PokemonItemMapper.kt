package com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.model

import com.digitalkoi.core.mapper.Mapper
import com.digitalkoi.core.network.responses.PokemonsResponse

/**
 * Helper class to transforms network response to visual model, catching the necessary data.
 *
 * @see Mapper
 */
class PokemonItemMapper : Mapper<PokemonsResponse, List<PokemonItem>> {

    /**
     * Transform network response to [PokemonItem].
     *
     * @param from Network pokemons response.
     * @return List of parsed pokemons items.
     */
    override suspend fun map(from: PokemonsResponse) =
        from.results.map {
            PokemonItem(
                id = 1,
                name = it.name,
                description = "",
                imageUrl = it.url
            )
        }
}
