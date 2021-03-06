package com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.paging

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.digitalkoi.core.annotations.OpenForTesting
import com.digitalkoi.core.extensions.asyncAll
import com.digitalkoi.core.network.NetworkState
import com.digitalkoi.core.network.repositories.PokemonRepository
import com.digitalkoi.core.network.responses.PokemonsResponse
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.model.PokemonItem
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.model.PokemonItemMapper
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

const val PAGE_INIT_ELEMENTS = 1
const val PAGE_MAX_ELEMENTS = 30

/**
 * Incremental data loader for page-keyed content, where requests return keys for next/previous
 * pages. Obtaining paginated the Marvel characters.
 *
 * @see PageKeyedDataSource
 */
@OpenForTesting
class PokemonPageDataSource @Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE)
    val repository: PokemonRepository,
    @VisibleForTesting(otherwise = PRIVATE)
    val scope: CoroutineScope,
    @VisibleForTesting(otherwise = PRIVATE)
    val mapper: PokemonItemMapper
) : PageKeyedDataSource<Int, PokemonItem>() {

    val networkState = MutableLiveData<NetworkState>()

    @VisibleForTesting(otherwise = PRIVATE)
    var retry: (() -> Unit)? = null

    /**
     * Load initial data.
     *
     * @param params Parameters for initial load, including requested load size.
     * @param callback Callback that receives initial load data.
     * @see PageKeyedDataSource.loadInitial
     */
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PokemonItem>
    ) {
        networkState.postValue(NetworkState.Loading())
        scope.launch(
            CoroutineExceptionHandler { _, _ ->
                retry = {
                    loadInitial(params, callback)
                }
                networkState.postValue(NetworkState.Error())
            }
        ) {

            val response = mutableListOf<PokemonsResponse.PokemonResponse>()

            asyncAll((PAGE_INIT_ELEMENTS..PAGE_MAX_ELEMENTS.toLong()).toList()) { id ->
                repository.getPokemon(id)
            }.awaitAll().forEach {
                response.add(it)
            }

            val data = mapper.map(response)

            callback.onResult(data, null, PAGE_MAX_ELEMENTS)
            networkState.postValue(NetworkState.Success(isEmptyResponse = data.isEmpty()))
        }
    }

    /**
     * Append page with the key specified by [LoadParams.key].
     *
     * @param params Parameters for the load, including the key for the new page, and requested
     * load size.
     * @param callback Callback that receives loaded data.
     * @see PageKeyedDataSource.loadAfter
     */
    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, PokemonItem>
    ) {
        networkState.postValue(NetworkState.Loading(true))
        scope.launch(
            CoroutineExceptionHandler { _, _ ->
                retry = {
                    loadAfter(params, callback)
                }
                networkState.postValue(NetworkState.Error(true))
            }
        ) {

            val response = mutableListOf<PokemonsResponse.PokemonResponse>()

            asyncAll((params.key.toLong() until params.key + PAGE_MAX_ELEMENTS).toList()) {
                repository.getPokemon(it)
            }.awaitAll().forEach {
                response.add(it)
            }

            val data = mapper.map(response)

            callback.onResult(data, params.key + PAGE_MAX_ELEMENTS)
            networkState.postValue(NetworkState.Success(true, data.isEmpty()))
        }
    }

    /**
     * Prepend page with the key specified by [LoadParams.key]
     *
     * @param params Parameters for the load, including the key for the new page, and requested
     * load size.
     * @param callback Callback that receives loaded data.
     * @see PageKeyedDataSource.loadBefore
     */
    override fun loadBefore(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, PokemonItem>
    ) {
        // Ignored, since we only ever append to our initial load
    }

    /**
     * Force retry last fetch operation in case it has ever been previously executed.
     */
    fun retry() {
        retry?.invoke()
    }
}
