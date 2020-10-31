package com.digitalkoi.core.network.services

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt


object MockResponses {
    object GetPokemons {
        const val STATUS_200 = "mock-responses/get-pokemons-status200.json"
    }
}

class PokemonServiceTest  {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var service: PokemonService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun requestGetPokemons() = runBlocking {
        enqueueResponse(MockResponses.GetPokemons.STATUS_200)
        val limit = 30
        val offset = 0
        service.getPokemons(
            offset = offset,
            limit = limit
        )

        val request = mockWebServer.takeRequest()
        Assert.assertEquals("GET", request.method)
        Assert.assertEquals(
            "/api/v2/pokemon?limit=$limit&offset=$offset",
            request.path
        )
    }

    @Test
    fun responseGetCharacters_StatusCode200() = runBlocking {
        enqueueResponse(MockResponses.GetPokemons.STATUS_200)
        val limit = 30
        val offset = 0
        val response = service.getPokemons(
            offset = offset,
            limit = limit
        )

        Assert.assertEquals(1050, response.count)
        Assert.assertEquals(limit, response.results.size)
        MatcherAssert.assertThat(response.results, CoreMatchers.instanceOf(List::class.java))
    }

    private fun enqueueResponse(filePath: String) {
        val inputStream = javaClass.classLoader?.getResourceAsStream(filePath)
        val bufferSource = inputStream?.source()?.buffer()
        val mockResponse = MockResponse()

        mockWebServer.enqueue(
            mockResponse.setBody(
                bufferSource!!.readString(Charsets.UTF_8)
            )
        )
    }
}