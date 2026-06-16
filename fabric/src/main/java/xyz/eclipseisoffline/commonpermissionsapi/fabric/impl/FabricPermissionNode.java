package xyz.eclipseisoffline.commonpermissionsapi.fabric.impl;

import net.fabricmc.fabric.api.permission.v1.PermissionNode;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.ApiStatus;
import xyz.eclipseisoffline.commonpermissionsapi.api.CommonPermissionNode;

@ApiStatus.Internal
public record FabricPermissionNode(PermissionNode<Boolean> node) implements CommonPermissionNode {

    public FabricPermissionNode(Identifier identifier) {
        this(PermissionNode.of(identifier));
    }

    @Override
    public Identifier identifier() {
        return node.key();
    }
}
