plugins {
    alias(libs.plugins.multimod)
}

dependencies {
    implementation(libs.fabric.permissions.api)
    modInclude(libs.fabric.permissions.api)
}

multimod.fabric(project(":common"))
