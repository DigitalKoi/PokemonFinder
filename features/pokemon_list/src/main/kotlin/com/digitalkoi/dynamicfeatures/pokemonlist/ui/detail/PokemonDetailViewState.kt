package com.digitalkoi.dynamicfeatures.pokemonlist.ui.detail

import com.digitalkoi.commons.ui.base.BaseViewState

/**
 * Different states for [PokemonDetailFragment].
 *
 * @see BaseViewState
 */
sealed class PokemonDetailViewState : BaseViewState {

    /**
     * Loading character detail info.
     */
    object Loading : PokemonDetailViewState()

    /**
     * Error on loading character detail info.
     */
    object Error : PokemonDetailViewState()

    /**
     * Dismiss the detail view.
     */
    object Dismiss : PokemonDetailViewState()

    // region Public helpers methods

    /**
     * Check if current view state is [Loading].
     *
     * @return True if is loading state, otherwise false.
     */
    fun isLoading() = this is Loading

    /**
     * Check if current view state is [Error].
     *
     * @return True if is error state, otherwise false.
     */
    fun isError() = this is Error

    /**
     * Check if current view state is [Dismiss].
     *
     * @return True if is delete state, otherwise false.
     */
    fun isDismiss() = this is Dismiss

    // endregion
}
