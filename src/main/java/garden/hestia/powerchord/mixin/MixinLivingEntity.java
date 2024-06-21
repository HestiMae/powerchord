package garden.hestia.powerchord.mixin;

import garden.hestia.powerchord.InstrumentItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("UnreachableCode")
@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity {
    @Shadow
    protected abstract int getHandSwingDuration();

    @Inject(method = "swingHand(Lnet/minecraft/util/Hand;Z)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getWorld()Lnet/minecraft/world/World;", ordinal = 0))
    public void swingHand(Hand hand, boolean fromServerPlayer, CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object) this;
        if (self instanceof PlayerEntity player && player.getStackInHand(hand).getItem() instanceof InstrumentItem instrument && player.getAttackCooldownProgress(0.0f) == 0.0f) {
            instrument.swing(player, hand);
            self.handSwingTicks = getHandSwingDuration();
        }
    }
}
