package xyz.eclipseisoffline.commonpermissionsapi.impl;

import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.Nullable;
import xyz.eclipseisoffline.commonpermissionsapi.api.CommonPermissions;

@ApiStatus.Internal
public final class CommonPermissionsImpl {
    private static @Nullable CommonPermissions implementation = null;

    private CommonPermissionsImpl() {}

    public static CommonPermissions getImplementation() {
        if (implementation == null) {
            throw new IllegalStateException("CommonPermissions not yet initialised");
        }
        return implementation;
    }

    public static void setImplementation(CommonPermissions permissions) {
        if (implementation != null) {
            throw new IllegalStateException("CommonPermissions implementation already set");
        }
        implementation = permissions;
    }
}
