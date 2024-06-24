package garden.hestia.powerchord.mixin.client;

import garden.hestia.powerchord.InstrumentItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GameRenderer.class)
public class MixinGameRenderer {
    @Inject(method = "findCrosshairTarget", at = @At("HEAD"), cancellable = true)
    private void instrumentNoTarget(Entity camera, double blockInteractionRange, double entityInteractionRange, float tickDelta, CallbackInfoReturnable<HitResult> cir) {
        if (MinecraftClient.getInstance().player != null && InstrumentItem.handBlockingSwing(MinecraftClient.getInstance().player, Hand.MAIN_HAND) != null) {
            cir.setReturnValue(BlockHitResult.createMissed(MinecraftClient.getInstance().player.getPos(), Direction.EAST, MinecraftClient.getInstance().player.getBlockPos()));
            cir.cancel();
        }
    }
}
