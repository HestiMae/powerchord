package garden.hestia.powerchord.mixin;

import garden.hestia.powerchord.PowerChord;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.function.Consumer;

@SuppressWarnings("UnreachableCode")
@Mixin(ItemStack.class)
public abstract class MixinItemStack {
    @ModifyVariable(method = "getTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;appendAttributeModifiersTooltip(Ljava/util/function/Consumer;Lnet/minecraft/entity/player/PlayerEntity;)V"), ordinal = 0)
    public Consumer<Text> getTooltip(Consumer<Text> textConsumer, Item.TooltipContext context, @Nullable PlayerEntity player, TooltipType type) {
        ItemStack self = (ItemStack) (Object) this;
        if (self.contains(PowerChord.INSTRUMENT)) self.get(PowerChord.INSTRUMENT).appendTooltip(self, context, textConsumer, player, type);
        if (self.contains(PowerChord.KEY)) self.get(PowerChord.KEY).appendTooltip(self, context, textConsumer, player, type);
        return textConsumer;
    }
}
