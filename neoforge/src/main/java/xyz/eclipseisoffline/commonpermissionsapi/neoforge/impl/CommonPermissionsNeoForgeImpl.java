package xyz.eclipseisoffline.commonpermissionsapi.neoforge.impl;

import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.TriState;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.server.permission.PermissionAPI;
import net.neoforged.neoforge.server.permission.events.PermissionGatherEvent;
import net.neoforged.neoforge.server.permission.nodes.PermissionNode;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.Nullable;
import xyz.eclipseisoffline.commonpermissionsapi.api.CommonPermissionNode;
import xyz.eclipseisoffline.commonpermissionsapi.api.CommonPermissions;
import xyz.eclipseisoffline.commonpermissionsapi.impl.CommonPermissionsImpl;
import xyz.eclipseisoffline.commonpermissionsapi.neoforge.mixin.PermissionAPIAccessor;

import java.util.ArrayList;
import java.util.List;

@ApiStatus.Internal
@Mod("commonpermissionsapi")
public final class CommonPermissionsNeoForgeImpl implements CommonPermissions {
    private final List<NeoForgePermissionNode> nodesToRegister = new ArrayList<>();
    private boolean nodesHaveBeenRegistered = false;

    public CommonPermissionsNeoForgeImpl() {
        NeoForge.EVENT_BUS.addListener(PermissionGatherEvent.Nodes.class, event -> {
            nodesToRegister.stream()
                    .map(NeoForgePermissionNode::node)
                    .forEach(event::addNodes);
            nodesHaveBeenRegistered = true;
        });
        CommonPermissionsImpl.setImplementation(this);
    }

    @Override
    public CommonPermissionNode createNode(Identifier identifier) {
        NeoForgePermissionNode node = new NeoForgePermissionNode(identifier, !nodesHaveBeenRegistered);
        if (node.registered()) {
            nodesToRegister.add(node);
        }
        return node;
    }

    @Override
    public TriState getPermissionValue(ServerPlayer player, CommonPermissionNode node) {
        if (node instanceof NeoForgePermissionNode neoForgeNode) {
            if (neoForgeNode.registered()) {
                return nullBooleanToTriState(PermissionAPI.getPermission(player, neoForgeNode.node()));
            }
            return getUnregisteredNodeSafely(player, neoForgeNode.node());
        }
        return getUnregisteredNodeSafely(player, NeoForgePermissionNode.identifierToPermissionNode(node.identifier()));
    }

    private static TriState getUnregisteredNodeSafely(ServerPlayer player, PermissionNode<Boolean> node) {
        try {
            return nullBooleanToTriState(PermissionAPIAccessor.getActiveHandler().getPermission(player, node));
        } catch (RuntimeException exception) {
            // Assuming the handler couldn't handle our unregistered nodes
            return TriState.DEFAULT;
        }
    }

    private static TriState nullBooleanToTriState(@Nullable Boolean b) {
        return b == null ? TriState.DEFAULT : TriState.from(b);
    }
}
