package com.digitalkoi.core.network.responses

import com.google.gson.annotations.SerializedName


data class PokemonsResponse(
    val count: Long,
    val next: String,
    val previous: String,
    val results: List<PokemonResponse>
) {

    data class PokemonResponse(
        val id: Long,
        val name: String,
        val height: Int,
        val weight: Int,
        val types: List<TypeResponse>,
        val stats: List<StatResponse>
    ) {
        fun getImageUrl() = "https://pokeres.bastionbot.org/images/pokemon/$id.png"
    }

    data class TypeResponse(
        val slot: Int,
        val type: TypeDetailsResponse
    ) {
        data class TypeDetailsResponse(
            val name: String,
            val url: String
        )
    }

    /**
     * Stats of pokemon
     *
     * Basically is six base stats
     * @property stat is a value of defense[3], attack[4], hp[5] where number is a element of list
     */
    data class StatResponse(
        @SerializedName("base_stat")
        val stat: Int,
        val effort: Int
    )
}
