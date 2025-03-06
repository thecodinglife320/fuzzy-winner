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
include(":app")
include(":tipofwellness")
include(":dessertshop")
include(":unscrambleword")
include(":cupcake")
include(":luchtray")
include(":racetracker")
include(":marphoto")
include(":amphibians")
include(":bookshelf")
include(":inventoryyou")
include(":busschedule")
include(":playvideo")
include(":monngonmoingay")
include(":artspace")
include(":hero")
