package com.wfphantom.neobeefix.mixin;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BeehiveBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BeehiveBlockEntity.class)
public class BeehiveBlockEntityMixin {
    @Redirect(method = "releaseOccupant", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;isNight()Z"))
    private static boolean noSkyLight_isNotNight(Level level) {
        return level.dimensionType().hasSkyLight() && level.isNight();
    }
    @Redirect(method = "releaseOccupant", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;isRaining()Z"))
    private static boolean noSkyLight_isNotRaining(Level level) {
        return level.dimensionType().hasSkyLight() && level.isRaining();
    }
}
