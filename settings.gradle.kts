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

rootProject.name = "Slice"
include(":app")
include(":ui-home")
include(":resources")
include(":ui-restaurantdetails")
include(":data")
include(":ui-search")
include(":ui-orders")
include(":ui-rewards")
include(":ui-account")
include(":compose")
include(":domain")
include(":ui-list")
