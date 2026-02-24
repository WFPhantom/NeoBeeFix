package com.wfphantom.neobeefix.mixin;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(PathNavigation.class)
public class PathNavigationMixin {
    @Redirect(method = "followThePath", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Mob;getBbWidth()F", ordinal = 1))
    private float xWidth(Mob mob) {
        float w = mob.getBbWidth();
        return ((int) (w + 1.0F)) - 1.0F;
    }
    @Redirect(method = "followThePath", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Mob;getBbWidth()F", ordinal = 2))
    private float zWidth(Mob mob) {
        float w = mob.getBbWidth();
        return ((int) (w + 1.0F)) - 1.0F;
    }
}