/**
 * Configuration of build modules
 */
object BuildModules {
    const val APP = ":app"
    const val CORE = ":core"

    object Features {
    }

    object Commons {
        const val UI = ":commons:ui"
        const val VIEW = ":commons:views"
    }

    object Libraries {
        const val TEST_UTILS = ":libraries:test_utils"
    }
}
