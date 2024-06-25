package garden.hestia.powerchord.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.dynamic.Codecs;

public record InstrumentComponent(int noteTicks, int cooldownBeats) {
    public static final Codec<InstrumentComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        Codecs.POSITIVE_INT.fieldOf("noteTicks").forGetter(InstrumentComponent::noteTicks),
        Codecs.POSITIVE_INT.fieldOf("cooldownBeats").forGetter(InstrumentComponent::cooldownBeats)
    ).apply(instance, InstrumentComponent::new));
}
