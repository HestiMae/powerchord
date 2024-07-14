package garden.hestia.powerchord.client;

import garden.hestia.powerchord.InstrumentItem;
import garden.hestia.powerchord.PowerChord;
import garden.hestia.powerchord.component.KeyComponent;
import garden.hestia.powerchord.component.PowerableChord;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.Map;

public class PowerChordClient implements ClientModInitializer {
    public static final Map<Item, Identifier> FLAT_MODELS = Map.of(
        PowerChord.HARMONICA, Identifier.of("powerchord", "item/harmonica_flat"),
        PowerChord.PAN_FLUTE, Identifier.of("powerchord", "item/pan_flute_flat"),
        PowerChord.WAWA, Identifier.of("powerchord", "item/wawa_flat")
    );

    @Override
    public void onInitializeClient() {
        ModelLoadingPlugin.register(pluginContext -> pluginContext.addModels(FLAT_MODELS.values()));
        HudRenderCallback.EVENT.register(PowerChordClient::render);
    }

    private static void render(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;
        KeyComponent key = InstrumentItem.getPlayableKey(player);
        ItemStack instrumentItemStack = InstrumentItem.getPlayableInstrument(player);
        if (key == null || instrumentItemStack == null) return;
        int chordIndex = InstrumentItem.getChordIndex(player, key, instrumentItemStack);
        if (chordIndex >= key.chords().size()) return;
        PowerableChord chord = key.chords().get(chordIndex);
        drawContext.drawText(MinecraftClient.getInstance().textRenderer, chord.toText((player.isSneaking() && InstrumentItem.isMagical(player))), drawContext.getScaledWindowWidth() / 2 + 8, drawContext.getScaledWindowHeight() / 2 - 4, 0xFFFFFFFF, true);
    }

}
