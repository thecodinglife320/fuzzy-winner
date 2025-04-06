pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "FF2"
include(":bookshelf")
include(":inventoryyou")
include(":playvideo")
include(":monngonmoingay")
include(":dessertrelease")
include(":dessertrelease")
include(":flightsearch")
include(":restaurant")
