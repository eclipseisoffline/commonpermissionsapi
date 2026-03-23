package xyz.eclipseisoffline.commonpermissionsapi.fabric.impl;

import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.ApiStatus;
import xyz.eclipseisoffline.commonpermissionsapi.api.CommonPermissionNode;

@ApiStatus.Internal
public record FabricPermissionNode(Identifier identifier, String node) implements CommonPermissionNode {

    public FabricPermissionNode(Identifier identifier) {
        this(identifier, nodeToPermissionString(identifier));
    }

    public static String nodeToPermissionString(Identifier identifier) {
        return identifier.getNamespace() + "." + identifier.getPath();
    }
}
