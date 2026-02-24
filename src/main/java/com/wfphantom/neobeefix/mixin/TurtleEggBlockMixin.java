package com.wfphantom.neobeefix.mixin;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.bee.Bee;
import net.minecraft.world.level.block.TurtleEggBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TurtleEggBlock.class)
public class TurtleEggBlockMixin {
    @Inject(method = "canDestroyEgg", at = @At("HEAD"), cancellable = true)
    private void beesCannotDestroyEggs(ServerLevel level, Entity entity, CallbackInfoReturnable<Boolean> cir) {
        if (entity instanceof Bee) cir.setReturnValue(false);
    }
}