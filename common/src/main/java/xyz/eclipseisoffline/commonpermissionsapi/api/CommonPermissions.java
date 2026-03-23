package xyz.eclipseisoffline.commonpermissionsapi.api;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.permissions.Permission;
import net.minecraft.server.permissions.PermissionLevel;
import net.minecraft.util.TriState;
import xyz.eclipseisoffline.commonpermissionsapi.impl.CommonPermissionsImpl;

import java.util.function.Predicate;

public interface CommonPermissions {

    static Predicate<CommandSourceStack> require(CommonPermissionNode node) {
        return source -> check(source, node);
    }

    static Predicate<CommandSourceStack> require(CommonPermissionNode node, PermissionLevel fallback) {
        return source -> check(source, node, fallback);
    }

    static Predicate<CommandSourceStack> require(CommonPermissionNode node, boolean defaultValue) {
        return source -> check(source, node, defaultValue);
    }

    static boolean check(CommandSourceStack source, CommonPermissionNode node) {
        return source.getPlayer() != null && check(source.getPlayer(), node);
    }

    static boolean check(ServerPlayer player, CommonPermissionNode node) {
        return check(player, node, false);
    }

    static boolean check(CommandSourceStack source, CommonPermissionNode node, PermissionLevel fallback) {
        return source.getPlayer() == null ? source.permissions().hasPermission(new Permission.HasCommandLevel(fallback)) : check(source.getPlayer(), node, fallback);
    }

    static boolean check(ServerPlayer player, CommonPermissionNode node, PermissionLevel fallback) {
        return getPermission(player, node).toBoolean(player.permissions().hasPermission(new Permission.HasCommandLevel(fallback)));
    }

    static boolean check(CommandSourceStack source, CommonPermissionNode node, boolean defaultValue) {
        return source.getPlayer() == null ? defaultValue : check(source.getPlayer(), node, defaultValue);
    }

    static boolean check(ServerPlayer player, CommonPermissionNode node, boolean defaultValue) {
        return getPermission(player, node).toBoolean(defaultValue);
    }

    static TriState getPermission(ServerPlayer player, CommonPermissionNode node) {
        return getInstance().getPermissionValue(player, node);
    }

    static CommonPermissionNode node(String namespace, String path) {
        return node(Identifier.fromNamespaceAndPath(namespace, path));
    }

    static CommonPermissionNode node(Identifier identifier) {
        return getInstance().createNode(identifier);
    }

    CommonPermissionNode createNode(Identifier identifier);

    TriState getPermissionValue(ServerPlayer player, CommonPermissionNode node);

    private static CommonPermissions getInstance() {
        return CommonPermissionsImpl.getImplementation();
    }
}
