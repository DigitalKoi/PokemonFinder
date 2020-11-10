package com.digitalkoi.dynamicfeatures.pokemonlist.ui.list

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import com.digitalkoi.commons.ui.livedata.SingleLiveData
import com.digitalkoi.core.network.NetworkState
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.paging.PokemonPageDataSourceFactory
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.paging.PAGE_MAX_ELEMENTS
import javax.inject.Inject

/**
 * View model responsible for preparing and managing the data for [PokemonListFragment].
 *
 * @see ViewModel
 */
class PokemonListViewModel
@Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE)
    val dataSourceFactory: PokemonPageDataSourceFactory
) : ViewModel() {

    @VisibleForTesting(otherwise = PRIVATE)
    val networkState = Transformations.switchMap(dataSourceFactory.sourceLiveData) {
        it.networkState
    }

    val event = SingleLiveData<PokemonListViewEvent>()
    val data = LivePagedListBuilder(dataSourceFactory, PAGE_MAX_ELEMENTS).build()
    val state = Transformations.map(networkState) {
        when (it) {
            is NetworkState.Success ->
                if (it.isAdditional && it.isEmptyResponse) {
                    PokemonListViewState.NoMoreElements
                } else if (it.isEmptyResponse) {
                    PokemonListViewState.Empty
                } else {
                    PokemonListViewState.Loaded
                }
            is NetworkState.Loading ->
                if (it.isAdditional) {
                    PokemonListViewState.AddLoading
                } else {
                    PokemonListViewState.Loading
                }
            is NetworkState.Error ->
                if (it.isAdditional) {
                    PokemonListViewState.AddError
                } else {
                    PokemonListViewState.Error
                }
        }
    }

    // region Public methods

    /**
     * Refresh pokemons fetch them again and update the list.
     */
    fun refreshLoadedPokemonList() {
        dataSourceFactory.refresh()
    }

    /**
     * Retry last fetch operation to add pokemons into list.
     */
    fun retryAddPokemonList() {
        dataSourceFactory.retry()
    }

    /**
     * Send interaction event for open pokemon detail view from selected character.
     *
     * @param pokemonId Pokemon identifier.
     */
    fun openPokemonDetail(pokemonId: Long) {
        event.postValue(PokemonListViewEvent.OpenCharacterDetail(pokemonId))
    }

    // endregion
}
