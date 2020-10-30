import dependencies.Dependencies
import dependencies.AnnotationProcessorsDependencies
import extensions.implementation
import extensions.kapt
import extensions.getLocalProperty
import extensions.buildConfigBooleanField
import extensions.buildConfigIntField
import extensions.buildConfigStringField

plugins {
    id("commons.android-library")
}

allOpen {
    // allows mocking for classes w/o directly opening them for release builds
    annotation("com.digitalkoi.core.annotations.OpenClass")
}

android {
    buildTypes.forEach {
        try {
            it.buildConfigStringField("POKEMON_API_BASE_URL", "https://pokeapi.co/")

            it.buildConfigBooleanField("POKEMON_DATABASE_EXPORT_SCHEMA", false)
            it.buildConfigStringField("POKEMON_DATABASE_NAME", "characters-db")
            it.buildConfigIntField("POKEMON_DATABASE_VERSION", 1)
        } catch (ignored: Exception) {
            throw InvalidUserDataException("Maybe you should define 'pokemon.key.public' and " +
                "'pokemon.key.private' in local.properties. Visit 'https://pokeapi.co' " +
                "to obtain them.")
        }
    }
}

dependencies {
    implementation(Dependencies.ROOM)
    implementation(Dependencies.ROOM_KTX)
    implementation(Dependencies.LIFECYCLE_EXTENSIONS)
    implementation(Dependencies.NAVIGATION_UI)
    implementation(Dependencies.FRAGMENT_KTX)
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER)
    implementation(Dependencies.LOGGING)
    implementation(Dependencies.MOSHI)
    implementation(Dependencies.MOSHI_KTX)

    kapt(AnnotationProcessorsDependencies.DATABINDING)
    kapt(AnnotationProcessorsDependencies.ROOM)
}
