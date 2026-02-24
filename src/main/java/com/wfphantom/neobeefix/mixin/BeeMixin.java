package com.wfphantom.neobeefix.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.animal.bee.Bee;
import net.minecraft.world.level.storage.ValueInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Bee.class)
public class BeeMixin {
    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void noGravityOnLoad(ValueInput in, CallbackInfo ci) {
        if (in.read("NoGravity", Codec.BOOL).isEmpty()) ((Bee) (Object) this).setNoGravity(true);
    }
    @Inject(method = "getBreedOffspring(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/AgeableMob;)Lnet/minecraft/world/entity/AgeableMob;", at = @At("RETURN"))
    private void noGravityOnBreed(ServerLevel level, AgeableMob otherParent, CallbackInfoReturnable<AgeableMob> cir) {
        AgeableMob child = cir.getReturnValue();
        if (child instanceof Bee beeChild) beeChild.setNoGravity(true);
    }
}