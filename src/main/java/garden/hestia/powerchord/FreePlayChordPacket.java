package garden.hestia.powerchord;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record FreePlayChordPacket() implements CustomPayload {
    public static final Id<FreePlayChordPacket> ID = new Id<>(Identifier.of(PowerChord.ID, "play_free_chord"));
    public static final PacketCodec<PacketByteBuf, FreePlayChordPacket> CODEC = PacketCodec.unit(new FreePlayChordPacket());

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
