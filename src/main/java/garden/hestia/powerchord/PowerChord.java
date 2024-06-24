package garden.hestia.powerchord;

import garden.hestia.powerchord.music.Progressions;
import net.fabricmc.api.ModInitializer;
import net.minecraft.component.ComponentType;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    public static final InstrumentItem HARMONICA = Registry.register(Registries.ITEM, Identifier.of(ID, "harmonica"), new InstrumentItem(new Item.Settings().maxCount(1).rarity(Rarity.UNCOMMON).component(KEY,
        new PowerKeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_DIDGERIDOO.value(), Progressions.CHROMATIC)))
    );

    public static final Item SPIRIT_ARMADILLO = Registry.register(Registries.ITEM, Identifier.of(ID, "spirit_armadillo"), new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).component(KEY,
        new PowerKeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_BANJO.value(), Progressions.A1546)))
    );

    public static final Item SPIRIT_BUNNY = Registry.register(Registries.ITEM, Identifier.of(ID, "spirit_bunny"), new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).component(KEY,
        new PowerKeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_BELL.value(), Progressions.B1546)))
    );

    public static final Item SPIRIT_BAT = Registry.register(Registries.ITEM, Identifier.of(ID, "spirit_bat"), new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).component(KEY,
        new PowerKeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_CHIME.value(), Progressions.Bb1547)))
    );

    public static final Item SPIRIT_CAT = Registry.register(Registries.ITEM, Identifier.of(ID, "spirit_cat"), new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).component(KEY,
        new PowerKeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_BASS.value(), Progressions.C1546)))
    );

    public static final Item SPIRIT_DOG = Registry.register(Registries.ITEM, Identifier.of(ID, "spirit_dog"), new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).component(KEY,
        new PowerKeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_BASS.value(), Progressions.D1546)))
    );

    public static final Item SPIRIT_CHAMELEON = Registry.register(Registries.ITEM, Identifier.of(ID, "spirit_chameleon"), new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).component(KEY,
        new PowerKeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_GUITAR.value(), Progressions.Cm1546)))
    );

    @Override
    public void onInitialize() {
        LOGGER.info("[PowerChord] 1, 2, 3 - 123!");
    }
}
