# common-permissions-api

![GitHub License](https://img.shields.io/github/license/eclipseisoffline/commonpermissionsapi)
![Maven version](https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Fmaven.eclipseisoffline.xyz%2Freleases%2Fxyz%2Feclipseisoffline%2Fcommonpermissionsapi-common%2Fmaven-metadata.xml)

common-permissions-api is a library to abstract between Fabric's and NeoForge's permissions APIs.

**Please note that the Fabric implementation is currently not functional, pending a functional permission API on Minecraft 26.1.**

Artefacts are available at [maven.eclipseisoffline.xyz](https://maven.eclipseisoffline.xyz).

## Usage

Include this library in your project as follows:

```kotlin
repositories {
    maven {
        name = "eclipseisoffline"
        url = uri("https://maven.eclipseisoffline.xyz/releases")
    }
}

dependencies {
    // For your common module:
    implementation("xyz.eclipseisoffline:commonpermissionsapi-common:0.0.2-26.1")
    
    // For your Fabric module:
    implementation("xyz.eclipseisoffline:commonpermissionsapi-fabric:0.0.2-26.1")
    
    // For your NeoForge module:
    implementation("xyz.eclipseisoffline:commonpermissionsapi-neoforge:0.0.2-26.1")
}
```

On MultiMod setups, you can use and include the library in your mods as follows:

```kotlin
multimod {
    settings {
        repositories {
            maven {
                name = "eclipseisoffline"
                url = uri("https://maven.eclipseisoffline.xyz/releases")
            }
        }
    }

    sharedDependencies {
        multiModInclude(multiModImplementation("xyz.eclipseisoffline:commonpermissionsapi:0.0.2-26.1"))
    }
}
```

Once you've included the library in your project, depend on it in your `fabric.mod.json`:

```json
{
  "depends": {
    "commonpermissionsapi": "*"
  }
}
```

And in your `neoforge.mods.toml`:

```toml
[[dependencies]]
    modId="commonpermissionsapi"
    type="required"
    ordering="NONE"
    side="BOTH"
```

Once you've done that, you can use the library in your code. Create `CommonPermissionNode`s using `CommonPermissions#node`,
and then you can check for these nodes using the other methods in `CommonPermissions`.

Preferably create permission nodes once during mod initialisation, especially on NeoForge.
