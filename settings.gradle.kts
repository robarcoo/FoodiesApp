pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "FoodiesApp"
include(":app")
include(":feature")
include(":core")
include(":feature:splash")
include(":core:common")
include(":feature:catalogue")
include(":feature:card")
include(":feature:shoppingcart")
include(":core:data")
