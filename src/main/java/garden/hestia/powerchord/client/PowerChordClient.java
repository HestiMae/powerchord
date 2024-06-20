package garden.hestia.powerchord.client;

import garden.hestia.powerchord.InstrumentItem;
import garden.hestia.powerchord.PowerKeyComponent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.text.Text;

public class PowerChordClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(PowerChordClient::render);
    }

    private static void render(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;
        PowerKeyComponent key = InstrumentItem.getPlayableKey(player);
        if (key == null) return;
        Text chordName = key.chords().get(InstrumentItem.getChordIndex(player, key)).name();
        drawContext.drawText(MinecraftClient.getInstance().textRenderer, chordName, drawContext.getScaledWindowWidth() / 2 + 8, drawContext.getScaledWindowHeight() / 2 - 4, 0xffffffff, true);
    }

}
