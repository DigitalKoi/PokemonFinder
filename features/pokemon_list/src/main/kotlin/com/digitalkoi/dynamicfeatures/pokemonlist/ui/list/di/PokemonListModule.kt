package com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.di

import androidx.annotation.VisibleForTesting
import androidx.annotation.VisibleForTesting.PRIVATE
import androidx.lifecycle.viewModelScope
import com.digitalkoi.commons.ui.extensions.viewModel
import com.digitalkoi.core.di.scopes.FeatureScope
import com.digitalkoi.core.network.repositories.PokemonRepository
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.PokemonListFragment
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.PokemonListViewModel
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.adapter.PokemonListAdapter
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.model.PokemonItemMapper
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.paging.PokemonPageDataSource
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.paging.PokemonPageDataSourceFactory
import dagger.Module
import dagger.Provides

/**
 * Class that contributes to the object graph [PokemonListComponent].
 *
 * @see Module
 */
@Module
class PokemonListModule(
    @VisibleForTesting(otherwise = PRIVATE)
    val fragment: PokemonListFragment
) {

    /**
     * Create a provider method binding for [PokemonListViewModel].
     *
     * @param dataFactory Data source factory for characters.
     * @return Instance of view model.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesPokemonListViewModel(
        dataFactory: PokemonPageDataSourceFactory
    ) = fragment.viewModel {
        PokemonListViewModel(dataFactory)
    }

    /**
     * Create a provider method binding for [PokemonPageDataSource].
     *
     * @return Instance of data source.
     * @see Provides
     */
    @Provides
    fun providesPokemonPageDataSource(
        viewModel: PokemonListViewModel,
        repository: PokemonRepository,
        mapper: PokemonItemMapper
    ) = PokemonPageDataSource(
        repository = repository,
        scope = viewModel.viewModelScope,
        mapper = mapper
    )

    /**
     * Create a provider method binding for [PokemonItemMapper].
     *
     * @return Instance of mapper.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesPokemonItemMapper() = PokemonItemMapper()

    /**
     * Create a provider method binding for [PokemonListAdapter].
     *
     * @return Instance of adapter.
     * @see Provides
     */
    @FeatureScope
    @Provides
    fun providesPokemonListAdapter(
        viewModel: PokemonListViewModel
    ) = PokemonListAdapter(viewModel)
}
