package com.digitalkoi.dynamicfeatures.pokemonlist.ui.detail.di

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import com.digitalkoi.commons.ui.extensions.viewModel
import com.digitalkoi.core.di.scopes.FeatureScope
import com.digitalkoi.core.network.repositories.PokemonRepository
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.detail.PokemonDetailFragment
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.detail.PokemonDetailViewModel
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.detail.model.PokemonDetailMapper
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the object graph [PokemonDetailComponent].
 *
 * @see Module
 */
@Module
class PokemonDetailModule(
    @VisibleForTesting(otherwise = PRIVATE)
    val fragment: PokemonDetailFragment
) {

    /**
     * Create a provider method binding for [PokemonDetailViewModel].
     *
     * @param pokemonRepository
     * @param pokemonDetailMapper mapper to parse view model
     *
     * @return instance of view model.
     */
    @FeatureScope
    @Provides
    fun providesCharacterDetailViewModel(
        pokemonRepository: PokemonRepository,
        pokemonDetailMapper: PokemonDetailMapper
    ) = fragment.viewModel {
        PokemonDetailViewModel(
            pokemonRepository = pokemonRepository,
            pokemonDetailMapper = pokemonDetailMapper
        )
    }

    /**
     * Create a provider method binding for [PokemonDetailMapper].
     *
     * @return instance of mapper.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesPokemonDetailMapper() = PokemonDetailMapper()

    /**
     * Create a provider method binding for [ProgressBarDialog].
     *
     * @return instance of dialog.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesProgressBarDialog() = ProgressBarDialog(fragment.requireContext())
}
