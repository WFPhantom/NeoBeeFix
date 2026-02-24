package com.wfphantom.neobeefix.mixin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.animal.Bee;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Bee.class)
public class BeeMixin {
    @Redirect(method = "wantsToEnterHive", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;isNight()Z"))
    private boolean noSkylight_isNotNight(Level level) {
        return level.dimensionType().hasSkyLight() && level.isNight();
    }
    @Redirect(method = "wantsToEnterHive", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;isRaining()Z"))
    private boolean noSkylight_isNotRaining(Level level) {
        return level.dimensionType().hasSkyLight() && level.isRaining();
    }
    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void noGravityOnLoad(CompoundTag tag, CallbackInfo ci) {
        if (!tag.contains("NoGravity")) ((Bee) (Object) this).setNoGravity(true);
    }
    @Inject(method = "getBreedOffspring(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/AgeableMob;)Lnet/minecraft/world/entity/animal/Bee;", at = @At("RETURN"))
    private void noGravityOnBreed(ServerLevel level, AgeableMob otherParent, CallbackInfoReturnable<AgeableMob> cir) {
        AgeableMob child = cir.getReturnValue();
        if (child instanceof Bee beeChild) beeChild.setNoGravity(true);
    }
}