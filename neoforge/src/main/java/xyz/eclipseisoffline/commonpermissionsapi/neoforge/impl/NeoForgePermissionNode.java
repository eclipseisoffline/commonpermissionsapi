package xyz.eclipseisoffline.commonpermissionsapi.neoforge.impl;

import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.server.permission.nodes.PermissionDynamicContext;
import net.neoforged.neoforge.server.permission.nodes.PermissionNode;
import net.neoforged.neoforge.server.permission.nodes.PermissionTypes;
import org.jspecify.annotations.Nullable;
import xyz.eclipseisoffline.commonpermissionsapi.api.CommonPermissionNode;

import java.util.UUID;

public record NeoForgePermissionNode(Identifier identifier, PermissionNode<Boolean> node, boolean registered) implements CommonPermissionNode {

    public NeoForgePermissionNode(Identifier identifier, boolean registered) {
        this(identifier, identifierToPermissionNode(identifier), registered);
    }

    public static PermissionNode<Boolean> identifierToPermissionNode(Identifier identifier) {
        return new PermissionNode<>(identifier, PermissionTypes.BOOLEAN, NullDefaultResolver.INSTANCE);
    }

    private static final class NullDefaultResolver implements PermissionNode.PermissionResolver<Boolean> {
        public static final PermissionNode.PermissionResolver<Boolean> INSTANCE = new NullDefaultResolver();

        private NullDefaultResolver() {}

        @Override
        public @Nullable Boolean resolve(@Nullable ServerPlayer player, UUID playerUUID, PermissionDynamicContext<?>... context) {
            return null;
        }
    }
}
