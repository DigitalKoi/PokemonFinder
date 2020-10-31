package com.digitalkoi.core.network

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.digitalkoi.core.network.repositories.PokemonRepository
import com.digitalkoi.core.network.services.PokemonService
import io.mockk.MockKAnnotations
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PokemonRepositoryTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK(relaxed = true)
    lateinit var pokemonService: PokemonService
    private lateinit var pokemonRepository: PokemonRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        pokemonRepository = PokemonRepository(pokemonService)
    }

    @Test
    fun getPokemons() = runBlocking {
        val itemsOffset = 0
        val itemsLimit = 30
        val offset = slot<Int>()
        val limit = slot<Int>()

        pokemonRepository.getPokemons(
            page = itemsOffset
        )

        coVerify {
            pokemonService.getPokemons(
                offset = capture(offset),
                limit = capture(limit)
            )
        }

        Assert.assertEquals(itemsOffset, offset.captured)
        Assert.assertEquals(itemsLimit, limit.captured)
    }
}