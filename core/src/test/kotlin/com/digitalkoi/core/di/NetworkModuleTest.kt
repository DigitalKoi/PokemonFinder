package com.digitalkoi.core.di

import com.digitalkoi.core.BuildConfig
import com.digitalkoi.core.di.modules.NetworkModule
import com.digitalkoi.core.network.services.PokemonService
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

class NetworkModuleTest {

    private lateinit var networkModule: NetworkModule

    @Before
    fun setUp() {
        networkModule = NetworkModule()
    }

    @Test
    fun verifyProvidedHttpLoggingInterceptor() {
        val interceptor = networkModule.provideHttpLoggingInterceptor()
        assertEquals(HttpLoggingInterceptor.Level.BODY, interceptor.level)
    }

    @Test
    fun verifyProvidedHttpClient() {
        val interceptor = mockk<HttpLoggingInterceptor>()
        val httpClient = networkModule.provideHttpClient(interceptor)

        assertEquals(1, httpClient.interceptors.size)
        assertEquals(interceptor, httpClient.interceptors.first())
    }

    @Test
    fun verifyProvidedRetrofitBuilder() {
        val retrofit = networkModule.provideRetrofitBuilder()

        assertEquals(BuildConfig.POKEMON_API_BASE_URL, retrofit.baseUrl().toUrl().toString())
    }

    @Test
    fun verifyProvidedPokemonService() {
        val retrofit = mockk<Retrofit>()
        val pokemonService = mockk<PokemonService>()
        val serviceClassCaptor = slot<Class<*>>()

        every { retrofit.create<PokemonService>(any()) } returns pokemonService

        networkModule.providePokemonService(retrofit)

        verify { retrofit.create(capture(serviceClassCaptor)) }
        assertEquals(PokemonService::class.java, serviceClassCaptor.captured)
    }


    @Test
    fun verifyProvidedPokemonRepository() {
        val pokemonService = mockk<PokemonService>()
        val pokemonRepository = networkModule.providePokemonRepository(pokemonService)

        assertEquals(pokemonService, pokemonRepository.service)
    }
}

