package com.digitalkoi.dynamicfeatures.pokemonlist.ui.detail

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalkoi.core.network.repositories.PokemonRepository
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.detail.model.PokemonDetail
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.detail.model.PokemonDetailMapper
import javax.inject.Inject
import kotlinx.coroutines.launch

/**
 * View model responsible for preparing and managing the data for [PokemonDetailFragment].
 *
 * @see ViewModel
 */
class PokemonDetailViewModel @Inject constructor(
    @VisibleForTesting(otherwise = PRIVATE)
    val pokemonRepository: PokemonRepository,
    @VisibleForTesting(otherwise = PRIVATE)
    val pokemonDetailMapper: PokemonDetailMapper
) : ViewModel() {

    private val _data = MutableLiveData<PokemonDetail>()
    val data: LiveData<PokemonDetail>
        get() = _data

    private val _state = MutableLiveData<PokemonDetailViewState>()
    val state: LiveData<PokemonDetailViewState>
        get() = _state

    // region Public methods

    /**
     * Fetch selected pokemon detail info.
     *
     * @param pokemonId Pokemon identifier.
     */
    fun loadPokemonDetail(pokemonId: Long) {
        _state.postValue(PokemonDetailViewState.Loading)
        viewModelScope.launch {
            try {
                val result = pokemonRepository.getPokemon(characterId)
                _data.postValue(pokemonDetailMapper.map(result))
            } catch (e: Exception) {
                _state.postValue(PokemonDetailViewState.Error)
            }
        }
    }

    /**
     * Send interaction event for dismiss pokemon detail view.
     */
    fun dismissPokemonDetail() {
        _state.postValue(PokemonDetailViewState.Dismiss)
    }

    // endregion
}
