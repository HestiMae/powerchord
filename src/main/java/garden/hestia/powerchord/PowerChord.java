package garden.hestia.powerchord;

import garden.hestia.powerchord.component.InstrumentComponent;
import garden.hestia.powerchord.component.InstrumentStateComponent;
import garden.hestia.powerchord.component.KeyComponent;
import garden.hestia.powerchord.music.Progressions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.ComponentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
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

    public static final ComponentType<KeyComponent> KEY = Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(ID, "key"),
        ComponentType.<KeyComponent>builder().codec(KeyComponent.CODEC).build()
    );

    public static final ComponentType<InstrumentComponent> INSTRUMENT = Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(ID, "instrument"),
        ComponentType.<InstrumentComponent>builder().codec(InstrumentComponent.CODEC).build()
    );

    public static final ComponentType<InstrumentStateComponent> STATE = Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(ID, "state"),
        ComponentType.<InstrumentStateComponent>builder().packetCodec(InstrumentStateComponent.PACKET_CODEC).build());

    public static final InstrumentItem PAN_FLUTE = Registry.register(Registries.ITEM, Identifier.of(ID, "pan_flute"), new InstrumentItem(new Item.Settings().maxCount(1).rarity(Rarity.COMMON)
        .component(KEY, new KeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_FLUTE.value(), Progressions.CHROMATIC))
        .component(INSTRUMENT, new InstrumentComponent(8, 4))
    ));

    public static final InstrumentItem HARMONICA = Registry.register(Registries.ITEM, Identifier.of(ID, "harmonica"), new InstrumentItem(new Item.Settings().maxCount(1).rarity(Rarity.COMMON).component(KEY,
            new KeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_BASS.value(), Progressions.CHROMATIC_MINOR))
        .component(INSTRUMENT, new InstrumentComponent(4, 12))
    ));

    public static final InstrumentItem WAWA = Registry.register(Registries.ITEM, Identifier.of(ID, "wawa"), new InstrumentItem(new Item.Settings().maxCount(1).rarity(Rarity.COMMON).component(KEY,
            new KeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_DIDGERIDOO.value(), Progressions.CHROMATIC))
        .component(INSTRUMENT, new InstrumentComponent(12, 0))
    ));

    public static final Item SPIRIT_ARMADILLO = Registry.register(Registries.ITEM, Identifier.of(ID, "spirit_armadillo"), new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).component(KEY,
        new KeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_BANJO.value(), Progressions.A1546)))
    );

    public static final Item SPIRIT_BUNNY = Registry.register(Registries.ITEM, Identifier.of(ID, "spirit_bunny"), new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).component(KEY,
        new KeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_BELL.value(), Progressions.B1546)))
    );

    public static final Item SPIRIT_BAT = Registry.register(Registries.ITEM, Identifier.of(ID, "spirit_bat"), new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).component(KEY,
        new KeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_CHIME.value(), Progressions.Bb1547)))
    );

    public static final Item SPIRIT_CAT = Registry.register(Registries.ITEM, Identifier.of(ID, "spirit_cat"), new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).component(KEY,
        new KeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_BASS.value(), Progressions.C1546)))
    );

    public static final Item SPIRIT_DOG = Registry.register(Registries.ITEM, Identifier.of(ID, "spirit_dog"), new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).component(KEY,
        new KeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_BASS.value(), Progressions.D1546)))
    );

    public static final Item SPIRIT_ECHIDNA = Registry.register(Registries.ITEM, Identifier.of(ID, "spirit_echidna"), new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).component(KEY,
        new KeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_XYLOPHONE.value(), Progressions.E1546)))
    );

    public static final Item SPIRIT_FROG = Registry.register(Registries.ITEM, Identifier.of(ID, "spirit_frog"), new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).component(KEY,
        new KeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE.value(), Progressions.F1546)))
    );

    public static final Item SPIRIT_GOPHER = Registry.register(Registries.ITEM, Identifier.of(ID, "spirit_gopher"), new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).component(KEY,
        new KeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_COW_BELL.value(), Progressions.G1546)))
    );

    public static final Item SPIRIT_ANTEATER = Registry.register(Registries.ITEM, Identifier.of(ID, "spirit_anteater"), new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).component(KEY,
        new KeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_DIDGERIDOO.value(), Progressions.Am1546)))
    );

    public static final Item SPIRIT_BUTTERFLY = Registry.register(Registries.ITEM, Identifier.of(ID, "spirit_butterfly"), new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).component(KEY,
        new KeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_HARP.value(), Progressions.Bm1546)))
    );

    public static final Item SPIRIT_CHAMELEON = Registry.register(Registries.ITEM, Identifier.of(ID, "spirit_chameleon"), new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).component(KEY,
        new KeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_GUITAR.value(), Progressions.Cm1546)))
    );

    public static final Item SPIRIT_DEER = Registry.register(Registries.ITEM, Identifier.of(ID, "spirit_deer"), new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).component(KEY,
        new KeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_FLUTE.value(), Progressions.Dm1546)))
    );

    public static final Item SPIRIT_EMU = Registry.register(Registries.ITEM, Identifier.of(ID, "spirit_emu"), new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).component(KEY,
        new KeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_BANJO.value(), Progressions.Em1546)))
    );

    public static final Item SPIRIT_FLAMINGO = Registry.register(Registries.ITEM, Identifier.of(ID, "spirit_flamingo"), new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).component(KEY,
        new KeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_PLING.value(), Progressions.Fm1546)))
    );

    public static final Item SPIRIT_GRASSHOPPER = Registry.register(Registries.ITEM, Identifier.of(ID, "spirit_grasshopper"), new Item(new Item.Settings().maxCount(1).rarity(Rarity.RARE).component(KEY,
        new KeyComponent(SoundEvents.BLOCK_NOTE_BLOCK_BIT.value(), Progressions.Gm1546)))
    );

    @Override
    public void onInitialize() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register((entries -> entries.addBefore(Items.MUSIC_DISC_13,
            PAN_FLUTE,
            HARMONICA,
            WAWA,
            SPIRIT_ARMADILLO,
            SPIRIT_ANTEATER,
            SPIRIT_BUNNY,
            SPIRIT_BAT,
            SPIRIT_BUTTERFLY,
            SPIRIT_CAT,
            SPIRIT_CHAMELEON,
            SPIRIT_DOG,
            SPIRIT_DEER,
            SPIRIT_ECHIDNA,
            SPIRIT_EMU,
            SPIRIT_FROG,
            SPIRIT_FLAMINGO,
            SPIRIT_GOPHER,
            SPIRIT_GRASSHOPPER
        )));
        LOGGER.info("[PowerChord] 1, 2, 3 - 123!");
    }
}
