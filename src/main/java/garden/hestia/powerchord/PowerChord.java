package garden.hestia.powerchord;

import net.fabricmc.api.ModInitializer;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PowerChord implements ModInitializer {
    public static final String ID = "powerchord";
    public static final Logger LOGGER = LoggerFactory.getLogger(ID);

    public static final ComponentType<PowerKeyComponent> KEY = Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(ID, "key"),
        ComponentType.<PowerKeyComponent>builder().codec(PowerKeyComponent.CODEC).packetCodec(PowerKeyComponent.PACKET_CODEC).build()
    );
    public static final ComponentType<AoeEffect> AOE_EFFECT = Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(ID, "effect"),
        ComponentType.<AoeEffect>builder().codec(AoeEffect.CODEC).packetCodec(AoeEffect.PACKET_CODEC).build()
    );
    public static final ComponentType<InstrumentStateComponent> STATE = Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(ID, "state"),
        ComponentType.<InstrumentStateComponent>builder().packetCodec(InstrumentStateComponent.PACKET_CODEC).build());

    public static final InstrumentItem HARMONICA = Registry.register(Registries.ITEM, Identifier.of(ID, "harmonica"), new InstrumentItem(new Item.Settings().maxCount(1).component(KEY,
        new PowerKeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_DIDGERIDOO.value(), List.of(
            new PowerTriad(0, 4, 7, Text.of("F#"), new AoeEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 100, 1), 10, true)),
            new PowerTriad(2, 5, 9, Text.of("G#m"), new AoeEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 40, 2), 10, true)),
            new PowerTriad(4, 7, 11, Text.of("A#m"), new AoeEffect(new StatusEffectInstance(StatusEffects.SPEED, 60, 1), 10, true))
        ))))
    );
    public static final Item SPIRIT_BUNNY = Registry.register(Registries.ITEM, Identifier.of(ID, "spirit_bunny"), new Item(new Item.Settings().maxCount(1).component(KEY,
        new PowerKeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_CHIME.value(), List.of(
            new PowerTriad(4, 7, 10, Text.of("A#dim"), new AoeEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 30, 2), 10, true))
        ))))
    );

    @Override
    public void onInitialize() {
        LOGGER.info("[PowerChord] 1, 2, 3 - 123!");
    }
}
