package xyz.eclipseisoffline.commonpermissionsapi.neoforge.mixin;

import net.neoforged.neoforge.server.permission.PermissionAPI;
import net.neoforged.neoforge.server.permission.handler.IPermissionHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(PermissionAPI.class)
public interface PermissionAPIAccessor {

    @Accessor("activeHandler")
    static IPermissionHandler getActiveHandler() {
        throw new AssertionError();
    }
}
