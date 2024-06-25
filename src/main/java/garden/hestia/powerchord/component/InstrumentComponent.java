package garden.hestia.powerchord.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.dynamic.Codecs;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public record InstrumentComponent(int noteTicks, int cooldownBeats) {
    public static final Codec<InstrumentComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        Codecs.POSITIVE_INT.fieldOf("noteTicks").forGetter(InstrumentComponent::noteTicks),
        Codecs.POSITIVE_INT.fieldOf("cooldownBeats").forGetter(InstrumentComponent::cooldownBeats)
    ).apply(instance, InstrumentComponent::new));

    public void appendTooltip(ItemStack stack, Item.TooltipContext context, Consumer<Text> tooltip, @Nullable PlayerEntity player, TooltipType type) {
        tooltip.accept(Text.literal("♩ = %s".formatted(1200 / noteTicks)).formatted(Formatting.GOLD));
        tooltip.accept(Text.literal(cooldownBeats == 0 ? "No Rest" : "%s %s Rest".formatted(cooldownBeats % 4 == 0 ? cooldownBeats / 4 : cooldownBeats, cooldownBeats % 4 == 0 ? "Bar" : "Beat")).formatted(Formatting.GOLD));
        tooltip.accept(Text.literal("%s triads/min".formatted(1200 / ((cooldownBeats + 4) * noteTicks))).formatted(Formatting.GOLD));
    }
}
