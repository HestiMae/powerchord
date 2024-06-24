package garden.hestia.powerchord.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.dynamic.Codecs;


public record AoeEffect(StatusEffectInstance status, int radius, boolean friendly) {
    public static final Codec<AoeEffect> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        StatusEffectInstance.CODEC.fieldOf("status").forGetter(AoeEffect::status),
        Codecs.POSITIVE_INT.fieldOf("radius").forGetter(AoeEffect::radius),
        Codec.BOOL.fieldOf("friendly").forGetter(AoeEffect::friendly)
    ).apply(instance, AoeEffect::new));

    public static final PacketCodec<ByteBuf, AoeEffect> PACKET_CODEC = PacketCodecs.codec(CODEC);
}
