package com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.di

import com.digitalkoi.core.di.CoreComponent
import com.digitalkoi.core.di.scopes.FeatureScope
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.PokemonListFragment
import dagger.Component

/**
 * Class for which a fully-formed, dependency-injected implementation is to
 * be generated from [PokemonListModule].
 *
 * @see Component
 */
@FeatureScope
@Component(
    modules = [PokemonListModule::class],
    dependencies = [CoreComponent::class]
)
interface PokemonListComponent {

    /**
     * Inject dependencies on component.
     *
     * @param listFragment Pokemon list component.
     */
    fun inject(listFragment: PokemonListFragment)
}
