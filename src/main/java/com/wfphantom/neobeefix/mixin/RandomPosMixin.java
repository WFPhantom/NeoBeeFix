package com.wfphantom.neobeefix.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.util.RandomPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RandomPos.class)
public class RandomPosMixin {
    @Redirect(method = "generateRandomDirectionWithinRadians", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/BlockPos;containing(DDD)Lnet/minecraft/core/BlockPos;"))
    private static BlockPos neobeefix$centerBlockPos(double x, double y, double z) {
        return BlockPos.containing(x + 0.5D, y, z + 0.5D);
    }
}