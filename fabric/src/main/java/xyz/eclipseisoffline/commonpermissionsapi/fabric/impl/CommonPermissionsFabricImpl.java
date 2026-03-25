package xyz.eclipseisoffline.commonpermissionsapi.fabric.impl;

import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.TriState;
import org.jetbrains.annotations.ApiStatus;
import xyz.eclipseisoffline.commonpermissionsapi.api.CommonPermissionNode;
import xyz.eclipseisoffline.commonpermissionsapi.api.CommonPermissions;

@ApiStatus.Internal
public final class CommonPermissionsFabricImpl implements CommonPermissions {

    @Override
    public CommonPermissionNode createNode(Identifier identifier) {
        return new FabricPermissionNode(identifier);
    }

    @Override
    public TriState getPermissionValue(ServerPlayer player, CommonPermissionNode node) {
        String permissionString = node instanceof FabricPermissionNode fabricNode ? fabricNode.node() : FabricPermissionNode.nodeToPermissionString(node.identifier());
        return fabricTriStateToMinecraft(Permissions.getPermissionValue(player, permissionString));
    }

    private static TriState fabricTriStateToMinecraft(net.fabricmc.fabric.api.util.TriState triState) {
        return switch (triState) {
            case FALSE -> TriState.FALSE;
            case DEFAULT -> TriState.DEFAULT;
            case TRUE -> TriState.TRUE;
        };
    }
}
