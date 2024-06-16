package garden.hestia.powerchord;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.sound.SoundEvent;

import java.util.List;

public record PowerKeyComponent(SoundEvent sound, List<PowerTriad> chords) {
    public static final Codec<PowerKeyComponent> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        SoundEvent.CODEC.fieldOf("sound").forGetter(PowerKeyComponent::sound),
        Codec.list(PowerTriad.CODEC).fieldOf("chords").forGetter(PowerKeyComponent::chords)
    ).apply(instance, PowerKeyComponent::new));
    public static final PacketCodec<ByteBuf, PowerKeyComponent> PACKET_CODEC = PacketCodecs.codec(CODEC);
}
