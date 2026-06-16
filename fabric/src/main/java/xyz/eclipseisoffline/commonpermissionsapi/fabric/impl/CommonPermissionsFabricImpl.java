package xyz.eclipseisoffline.commonpermissionsapi.fabric.impl;

import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.TriState;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.Nullable;
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
        FabricPermissionNode permissionNode = node instanceof FabricPermissionNode fabricNode ? fabricNode : new FabricPermissionNode(node.identifier());
        return booleanToTriState(player.getPermissionContext().checkPermission(permissionNode.node()));
    }

    private static TriState booleanToTriState(@Nullable Boolean bool) {
        return bool == null ? TriState.DEFAULT : TriState.from(bool);
    }
}
