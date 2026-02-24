package com.wfphantom.neobeefix.mixin;

import net.minecraft.client.renderer.entity.BeeRenderer;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntityRenderer.class)
public class BeeRendererMixin {
    @Inject(method = "getFlipDegrees", at = @At("HEAD"), cancellable = true)
    private void beeFlips180(CallbackInfoReturnable<Float> cir) {
        if ((Object) this instanceof BeeRenderer) cir.setReturnValue(180.0F);
    }
}