package garden.hestia.powerchord.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;

public record InstrumentComponent(int notePlayTicks, int noteCooldownTicks) {
    public static final Codec<InstrumentComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        Codecs.POSITIVE_INT.fieldOf("sound").forGetter(InstrumentComponent::notePlayTicks),
        Codecs.POSITIVE_INT.fieldOf("chords").forGetter(InstrumentComponent::noteCooldownTicks)
    ).apply(instance, InstrumentComponent::new));
}
