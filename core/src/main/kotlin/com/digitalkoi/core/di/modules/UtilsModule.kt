package com.digitalkoi.core.di.modules

import com.digitalkoi.core.utils.ThemeUtils
import com.digitalkoi.core.utils.ThemeUtilsImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

/**
 * Class that contributes to the object graph [CoreComponent].
 *
 * @see Module
 */
@Module
abstract class UtilsModule {

    /**
     * Create a provider method binding for [ThemeUtilsImpl].
     *
     * @return Instance of theme utils.
     * @see Binds
     */
    @Singleton
    @Binds
    abstract fun bindThemeUtils(themeUtilsImpl: ThemeUtilsImpl): ThemeUtils
}
