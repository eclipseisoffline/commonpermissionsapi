plugins {
    alias(libs.plugins.multimod)
}

group = properties["maven_group"] as String
version = properties["version"] as String

multimod {
    id = properties["mod_id"] as String
    name = properties["mod_name"] as String
    description = properties["mod_description"] as String

    archivesBaseName = properties["archives_base_name"] as String

    // TODO remove when NeoForge finishes 26.2
    settings {
        repositories {
            maven {
                name = "Maven for PR #3198" // https://github.com/neoforged/NeoForge/pull/3198
                url = uri("https://prmaven.neoforged.net/NeoForge/pr3198")
                content {
                    includeModule("net.neoforged", "neoforge")
                    includeModule("net.neoforged", "testframework")
                }
            }
        }
    }
    // TODO end

    minecraft {
        minecraft = libs.minecraft
        supportedMinecraftVersions = ">=26.2"
        neoForgeSupportedMinecraftVersions = "26.2"
    }

    fabricApi = libs.fabric.api
    neoForgeVersion = libs.versions.neoforge

    publishing {
        maven {
            name = "eclipseisoffline"
            url = uri("https://maven.eclipseisoffline.xyz/releases")
            credentials(PasswordCredentials::class)
        }
    }
}
