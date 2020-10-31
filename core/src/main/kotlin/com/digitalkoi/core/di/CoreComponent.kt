package com.digitalkoi.core.di

import android.content.Context
import com.digitalkoi.core.di.modules.ContextModule
import com.digitalkoi.core.di.modules.DatabaseModule
import com.digitalkoi.core.di.modules.NetworkModule
import com.digitalkoi.core.di.modules.UtilsModule
import com.digitalkoi.core.network.repositories.PokemonRepository
import com.digitalkoi.core.network.services.PokemonService
import com.digitalkoi.core.utils.ThemeUtils
import dagger.Component
import javax.inject.Singleton

/**
 * Core component that all module's components depend on.
 *
 * @see Component
 */
@Singleton
@Component(
    modules = [
        ContextModule::class,
        NetworkModule::class,
        DatabaseModule::class,
        UtilsModule::class
    ]
)
interface CoreComponent {

    /**
     * Provide dependency graph Context
     *
     * @return Context
     */
    fun context(): Context


    /**
     * Provide dependency graph PokemonService
     *
     * @return PokemonService
     */
    fun pokemonService(): PokemonService


    /**
     * Provide dependency graph PokemonRepository
     *
     * @return PokemonRepository
     */
    fun pokemonRepository(): PokemonRepository

    /**
     * Provide dependency graph ThemeUtils
     *
     * @return ThemeUtils
     */
    fun themeUtils(): ThemeUtils
}
