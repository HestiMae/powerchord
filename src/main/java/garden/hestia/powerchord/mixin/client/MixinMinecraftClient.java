package garden.hestia.powerchord.mixin.client;

import garden.hestia.powerchord.InstrumentItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    @Inject(method = "doAttack", at = @At("HEAD"), cancellable = true)
    public void freePlayChord(CallbackInfoReturnable<Boolean> cir) {
        if (MinecraftClient.getInstance().player != null && InstrumentItem.swing(MinecraftClient.getInstance().player, Hand.MAIN_HAND)) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}
