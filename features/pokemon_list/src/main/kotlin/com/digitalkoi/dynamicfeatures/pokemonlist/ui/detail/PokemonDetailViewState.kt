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
     * Add current character to favorite list.
     */
    object AddToFavorite : PokemonDetailViewState()

    /**
     * Dismiss the detail view.
     */
    object Dismiss : PokemonDetailViewState()

    /**
     * Added current character to favorite list.
     */
    object AddedToFavorite : PokemonDetailViewState()

    /**
     * Already added character to favorite list.
     */
    object AlreadyAddedToFavorite : PokemonDetailViewState()

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
     * Check if current view state is [AddToFavorite].
     *
     * @return True if is add to favorite state, otherwise false.
     */
    fun isAddToFavorite() = this is AddToFavorite

    /**
     * Check if current view state is [AddedToFavorite].
     *
     * @return True if is added to favorite state, otherwise false.
     */
    fun isAddedToFavorite() = this is AddedToFavorite

    /**
     * Check if current view state is [AlreadyAddedToFavorite].
     *
     * @return True if is already added to favorite state, otherwise false.
     */
    fun isAlreadyAddedToFavorite() = this is AlreadyAddedToFavorite
    /**
     * Check if current view state is [Dismiss].
     *
     * @return True if is delete state, otherwise false.
     */
    fun isDismiss() = this is Dismiss

    // endregion
}
