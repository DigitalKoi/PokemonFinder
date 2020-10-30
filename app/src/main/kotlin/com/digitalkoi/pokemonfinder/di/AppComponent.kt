package com.digitalkoi.pokemonfinder.di

import com.digitalkoi.core.di.CoreComponent
import com.digitalkoi.core.di.scopes.AppScope
import com.digitalkoi.pokemonfinder.PokemonApp
import dagger.Component

/**
 * App component that application component's components depend on.
 *
 * @see Component
 */
@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {

    /**
     * Inject dependencies on application.
     *
     * @param application The sample application.
     */
    fun inject(application: PokemonApp)
}