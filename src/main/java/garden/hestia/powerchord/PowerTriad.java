package garden.hestia.powerchord;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.text.Text;
import net.minecraft.text.TextCodecs;
import net.minecraft.util.dynamic.Codecs;

import java.util.Collection;
import java.util.List;

public record PowerTriad(int root, int third, int fifth, Text name) implements PowerableChord {
    public static final Codec<PowerTriad> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        Codecs.NONNEGATIVE_INT.fieldOf("root").forGetter(PowerTriad::root),
        Codecs.NONNEGATIVE_INT.fieldOf("third").forGetter(PowerTriad::third),
        Codecs.NONNEGATIVE_INT.fieldOf("fifth").forGetter(PowerTriad::fifth),
        TextCodecs.CODEC.fieldOf("name").forGetter(PowerTriad::name)
    ).apply(instance, PowerTriad::new));

    @Override
    public Collection<Integer> notes() {
        return List.of(root, third, fifth);
    }
}
