package com.digitalkoi.dynamicfeatures.pokemonlist.ui.detail.di

import com.digitalkoi.core.di.CoreComponent
import com.digitalkoi.core.di.scopes.FeatureScope
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.detail.PokemonDetailFragment
import dagger.Component

/**
 * Class for which a fully-formed, dependency-injected implementation is to
 * be generated from [PokemonDetailModule].
 *
 * @see Component
 */
@FeatureScope
@Component(
    modules = [PokemonDetailModule::class],
    dependencies = [CoreComponent::class]
)
interface PokemonDetailComponent {

    /**
     * Inject dependencies on component.
     *
     * @param detailFragment Detail component.
     */
    fun inject(detailFragment: PokemonDetailFragment)
}
