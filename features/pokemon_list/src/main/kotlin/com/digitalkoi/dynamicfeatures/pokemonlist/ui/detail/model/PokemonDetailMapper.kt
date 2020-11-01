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
        id = 0,
        name = from.name,
        description = "",
        imageUrl = from.url
    )
}
