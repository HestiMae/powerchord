package garden.hestia.powerchord;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PowerChord implements ModInitializer {
    public static final String ID = "powerchord";
    public static final Logger LOGGER = LoggerFactory.getLogger(ID);

    public static final InstrumentItem HARMONICA = Registry.register(Registries.ITEM, Identifier.of(ID, "harmonica"), new InstrumentItem(new Item.Settings().maxCount(1)));

    @Override
    public void onInitialize() {
        LOGGER.info("[PowerChord] 1, 2, 3 - 123!");
    }
}
