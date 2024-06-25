package garden.hestia.powerchord.component;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import garden.hestia.powerchord.PowerChord;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import net.minecraft.util.Formatting;
import net.minecraft.util.dynamic.Codecs;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public record KeyComponent(SoundEvent sound, List<PowerableChord> chords) {
    public static final Codec<KeyComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        SoundEvent.CODEC.fieldOf("sound").forGetter(KeyComponent::sound),
        Codecs.nonEmptyList(Codec.list(PowerableChord.CODEC)).fieldOf("chords").forGetter(KeyComponent::chords)
    ).apply(instance, KeyComponent::new));

    public void appendTooltip(ItemStack stack, Item.TooltipContext context, Consumer<Text> tooltip, @Nullable PlayerEntity player, TooltipType type) {
        boolean isInstrument = stack.contains(PowerChord.INSTRUMENT);
        if (isInstrument) {
            tooltip.accept(Text.literal("Free Play Chords:").formatted(Formatting.AQUA));
            for (List<PowerableChord> line : Lists.partition(chords, 6)) {
                tooltip.accept(Texts.join(line.stream().map(c -> c.toText(false)).toList(), Text.literal(", ")).copy().formatted(Formatting.GRAY));
            }
        } else {
            tooltip.accept(Text.literal("Chord Progression:").formatted(Formatting.AQUA));
            for (PowerableChord chord : chords) {
                tooltip.accept(Text.literal("- ").append(chord.toText(true)).formatted(Formatting.GRAY));
            }
        }
    }
}
