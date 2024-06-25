package garden.hestia.powerchord.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TextCodecs;
import net.minecraft.util.Formatting;
import net.minecraft.util.dynamic.Codecs;

import java.util.List;

public record PowerableChord(List<Integer> notes, Text name, AoeEffect effect) {
    public static final Codec<PowerableChord> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        Codecs.nonEmptyList(Codec.list(Codecs.NONNEGATIVE_INT)).fieldOf("notes").forGetter(PowerableChord::notes),
        TextCodecs.CODEC.fieldOf("name").forGetter(PowerableChord::name),
        AoeEffect.CODEC.fieldOf("effect").forGetter(PowerableChord::effect)
    ).apply(instance, PowerableChord::new));

    public int root() {
        return notes.get(0);
    }

    public Text toText(boolean withEffect) {
        MutableText nameText = name.copy().formatted(effect.friendly() ? Formatting.GREEN : Formatting.RED);
        MutableText effectText = Text.translatable(effect.status().getTranslationKey()).append(Text.literal(effect().status().getAmplifier() == 0 ? "" : " ")).append(Text.translatable("potion.potency." + effect().status().getAmplifier())).withColor(effect().status().getEffectType().value().getColor());
        return withEffect ? Text.literal("").append(nameText).append(" (").append(effectText).append(")") : nameText;
    }
}
