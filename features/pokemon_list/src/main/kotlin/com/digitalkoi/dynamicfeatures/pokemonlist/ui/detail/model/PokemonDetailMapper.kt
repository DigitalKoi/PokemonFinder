package com.digitalkoi.dynamicfeatures.pokemonlist.ui.detail.model

import com.digitalkoi.core.mapper.Mapper
import com.digitalkoi.core.network.responses.PokemonsResponse

private const val IMAGE_URL_FORMAT = "%s.%s"

/**
 * Helper class to transforms network response to visual model, catching the necessary data.
 *
 * @see Mapper
 */
class PokemonDetailMapper : Mapper<PokemonsResponse.PokemonResponse, PokemonDetail> {

    /**
     * Transform network response to [PokemonDetail].
     *
     * @param from Network characters response.
     * @return List of parsed characters items.
     * @throws NoSuchElementException If the response results are empty.
     */
    @Throws(NoSuchElementException::class)
    override suspend fun map(from: PokemonsResponse.PokemonResponse): PokemonDetail = PokemonDetail(
        id = from.id,
        name = from.name,
        height = from.height,
        weight = from.weight,
        stats = PokemonDetail.Stat(
            hp = from.stats[5].stat,
            attack = from.stats[4].stat,
            defense = from.stats[3].stat
        ),
        types = from.types.map { typeResponse ->
            PokemonDetail.Type(
                slot = typeResponse.slot,
                type = PokemonDetail.Type.TypeDetails(
                    name = typeResponse.type.name,
                    url = typeResponse.type.url
                )
            )
        },
        imageUrl = from.getImageUrl()
    )
}
