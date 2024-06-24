package garden.hestia.powerchord.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.sound.SoundEvent;

import java.util.List;

public record KeyComponent(SoundEvent sound, List<PowerableChord> chords) {
    public static final Codec<KeyComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        SoundEvent.CODEC.fieldOf("sound").forGetter(KeyComponent::sound),
        Codec.list(PowerableChord.CODEC).fieldOf("chords").forGetter(KeyComponent::chords)
    ).apply(instance, KeyComponent::new));
}
