package com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.model

import com.digitalkoi.core.mapper.Mapper
import com.digitalkoi.core.network.responses.PokemonsResponse

/**
 * Helper class to transforms network response to visual model, catching the necessary data.
 *
 * @see Mapper
 */
class PokemonItemMapper : Mapper<List<PokemonsResponse.PokemonResponse>, List<PokemonItem>> {

    /**
     * Transform network response to [PokemonItem].
     *
     * @param from Network pokemons response.
     * @return List of parsed pokemons items.
     */
    override suspend fun map(from: List<PokemonsResponse.PokemonResponse>) =
        from.map { pokemonResponse ->
            PokemonItem(
                id = pokemonResponse.id,
                name = pokemonResponse.name,
                height = pokemonResponse.height,
                weight = pokemonResponse.weight,
                stats = PokemonItem.Stat(
                    hp = pokemonResponse.stats[5].stat,
                    attack = pokemonResponse.stats[4].stat,
                    defense = pokemonResponse.stats[3].stat
                ),
                types = pokemonResponse.types.map { typeResponse ->
                    PokemonItem.Type(
                        slot = typeResponse.slot,
                        type = PokemonItem.Type.TypeDetails(
                            name = typeResponse.type.name,
                            url = typeResponse.type.url
                        )
                    )
                },
                imageUrl = pokemonResponse.getImageUrl()
            )
        }
}
