package com.digitalkoi.pokemonfinder

import android.content.Context
import com.digitalkoi.core.di.CoreComponent
import com.digitalkoi.core.di.DaggerCoreComponent
import com.digitalkoi.core.di.modules.ContextModule
import com.digitalkoi.pokemonfinder.di.DaggerAppComponent
import com.google.android.play.core.splitcompat.SplitCompatApplication
import timber.log.Timber

class PokemonApp : SplitCompatApplication() {

    lateinit var coreComponent: CoreComponent


    companion object {

        /**
         * Obtain core dagger component.
         *
         * @param context The application context
         */
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as? PokemonApp)?.coreComponent
    }

    /**
     * Called when the application is starting, before any activity, service, or receiver objects
     * (excluding content providers) have been created.
     *
     * @see SplitCompatApplication.onCreate
     */
    override fun onCreate() {
        super.onCreate()
        initTimber()
        initCoreDependencyInjection()
        initAppDependencyInjection()
    }

    // region init methods

    /**
     * Initialize app dependency injection component.
     */
    private fun initAppDependencyInjection() {
        DaggerAppComponent
            .builder()
            .coreComponent(coreComponent)
            .build()
            .inject(this)
    }

    /**
     * Initialize core dependency injection component.
     */
    private fun initCoreDependencyInjection() {
        coreComponent = DaggerCoreComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }

    /**
     * Initialize log library Timber only on debug build.
     */
    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    // endregion
}