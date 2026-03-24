package xyz.eclipseisoffline.commonpermissionsapi.impl;

import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.eclipseisoffline.commonpermissionsapi.api.CommonPermissions;

import java.lang.reflect.InvocationTargetException;

@ApiStatus.Internal
public final class CommonPermissionsImpl {
    private static final String FABRIC_IMPLEMENTATION = "xyz.eclipseisoffline.commonpermissionsapi.fabric.impl.CommonPermissionsFabricImpl";
    private static final String NEOFORGE_IMPLEMENTATION = "xyz.eclipseisoffline.commonpermissionsapi.neoforge.impl.CommonPermissionsNeoForgeImpl";
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonPermissionsImpl.class);
    private static @Nullable CommonPermissions implementation = null;

    private CommonPermissionsImpl() {}

    public static CommonPermissions getImplementation() {
        if (implementation == null) {
            try {
                implementation = (CommonPermissions) Class.forName(FABRIC_IMPLEMENTATION).getConstructor().newInstance();
                LOGGER.info("Using Fabric implementation");
            } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException fabricException) {
                try {
                    implementation = (CommonPermissions) Class.forName(NEOFORGE_IMPLEMENTATION).getConstructor().newInstance();
                    LOGGER.info("Using NeoForge implementation");
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException neoforgeException) {
                    throw new IllegalStateException("No CommonPermissions implementation found");
                }
            }
        }
        return implementation;
    }
}
