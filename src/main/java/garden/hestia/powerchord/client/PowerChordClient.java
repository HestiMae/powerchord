package garden.hestia.powerchord.client;

import garden.hestia.powerchord.InstrumentItem;
import garden.hestia.powerchord.PowerKeyComponent;
import garden.hestia.powerchord.PowerTriad;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class PowerChordClient implements ClientModInitializer {
    public static final Identifier HARMONICA_FLAT = Identifier.of("powerchord", "item/harmonica_flat");

    @Override
    public void onInitializeClient() {
        ModelLoadingPlugin.register(pluginContext -> pluginContext.addModels(HARMONICA_FLAT));
        HudRenderCallback.EVENT.register(PowerChordClient::render);
    }

    private static void render(DrawContext drawContext, RenderTickCounter renderTickCounter) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;
        if (player == null) return;
        PowerKeyComponent key = InstrumentItem.getPlayableKey(player);
        if (key == null) return;
        PowerTriad chord = key.chords().get(InstrumentItem.getChordIndex(player, key));
        StatusEffect effect = chord.effect().status().getEffectType().value();
        Text chordName = chord.name();
        drawContext.drawText(MinecraftClient.getInstance().textRenderer, (player.isSneaking() && InstrumentItem.isMagical(player)) ? Text.literal("").append(chordName).append("   (").append(Text.translatable(effect.getTranslationKey()).withColor(effect.getColor())).append(")") : chordName, drawContext.getScaledWindowWidth() / 2 + 8, drawContext.getScaledWindowHeight() / 2 - 4, 0xFFFFFFFF, true);
    }

}
