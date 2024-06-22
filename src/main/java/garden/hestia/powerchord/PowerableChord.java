package garden.hestia.powerchord;

import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface PowerableChord extends Comparable<PowerableChord> {
    Collection<Integer> notes();
    int root();
    Text name();
    AoeEffect effect();

    @Override
    default int compareTo(@NotNull PowerableChord o) {
        return Integer.compare(root(), o.root());
    }
}
