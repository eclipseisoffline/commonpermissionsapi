package xyz.eclipseisoffline.commonpermissionsapi.fabric.impl;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.TriState;
import org.jetbrains.annotations.ApiStatus;
import xyz.eclipseisoffline.commonpermissionsapi.api.CommonPermissionNode;
import xyz.eclipseisoffline.commonpermissionsapi.api.CommonPermissions;
import xyz.eclipseisoffline.commonpermissionsapi.impl.CommonPermissionsImpl;

@ApiStatus.Internal
public final class CommonPermissionsFabricImpl implements CommonPermissions, PreLaunchEntrypoint {

    @Override
    public void onPreLaunch() {
        CommonPermissionsImpl.setImplementation(this);
    }

    @Override
    public CommonPermissionNode createNode(Identifier identifier) {
        return new FabricPermissionNode(identifier);
    }

    @Override
    public TriState getPermissionValue(ServerPlayer player, CommonPermissionNode node) {
        String permissionString = node instanceof FabricPermissionNode fabricNode ? fabricNode.node() : FabricPermissionNode.nodeToPermissionString(node.identifier());
        //return Permissions.getPermissionValue(player, permissionString);
        return TriState.DEFAULT;
    }
}
