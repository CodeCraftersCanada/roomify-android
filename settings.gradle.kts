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

rootProject.name = "Roomify"
include(":app")
include(":features:landlord")
include(":features:student")
include(":features:common:utils")
include(":features:common:networking")
include(":features:messaging")
