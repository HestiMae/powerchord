package garden.hestia.powerchord;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PowerChord implements ModInitializer {
    public static final String ID = "powerchord";
    public static final Logger LOGGER = LoggerFactory.getLogger(ID);

    @Override
    public void onInitialize() {
        LOGGER.info("[PowerChord] 1, 2, 3 - 123!");
    }
}
