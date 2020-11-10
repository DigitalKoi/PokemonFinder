package com.digitalkoi.core.network.services

import com.digitalkoi.core.network.responses.PokemonsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Representation interface of the Pokemon API endpoints.
 */
interface PokemonService {

    /**
     * Fetches list of pokemons
     *
     * @param offset Skip the specified number of resources in the result set.
     * @param limit Limit the result set to the specified number of resources.
     * @return Response for pokemon list resource.
     */
    @GET("api/v2/pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): PokemonsResponse

    /**
     * Fetches pokemon data
     *
     * @param id Id the result set to the specified number of resources.
     * @return Response for pokemon characters resource.
     */
    @GET("api/v2/pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id: Long
    ) : PokemonsResponse.PokemonResponse
}
