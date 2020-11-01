package com.digitalkoi.dynamicfeatures.home.ui.di

import com.digitalkoi.core.di.CoreComponent
import com.digitalkoi.core.di.scopes.FeatureScope
import com.digitalkoi.dynamicfeatures.home.ui.HomeFragment
import dagger.Component

/**
 * Class for which a fully-formed, dependency-injected implementation is to
 * be generated from [HomeModule].
 *
 * @see Component
 */
@FeatureScope
@Component(
    modules = [HomeModule::class],
    dependencies = [CoreComponent::class]
)
interface HomeComponent {

    /**
     * Inject dependencies on component.
     *
     * @param homeFragment Home component.
     */
    fun inject(homeFragment: HomeFragment)
}
