package garden.hestia.powerchord.component;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

public record InstrumentStateComponent(int chordIndex, boolean reversed) {
    public static final PacketCodec<ByteBuf, InstrumentStateComponent> PACKET_CODEC = PacketCodec.tuple(
        PacketCodecs.INTEGER, InstrumentStateComponent::chordIndex,
        PacketCodecs.BOOL, InstrumentStateComponent::reversed,
        InstrumentStateComponent::new
    );
}
