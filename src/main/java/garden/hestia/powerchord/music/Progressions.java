package garden.hestia.powerchord.music;

import garden.hestia.powerchord.component.PowerableChord;

import java.util.List;

public class Progressions {
    public static final List<PowerableChord> CHROMATIC = List.of(Chords.Fs, Chords.G, Chords.Gs, Chords.A, Chords.As, Chords.B, Chords.C, Chords.Cs, Chords.D, Chords.Ds, Chords.E, Chords.F);
    public static final List<PowerableChord> CHROMATIC_MINOR = List.of(Chords.Fsm, Chords.Gm, Chords.Gsm, Chords.Am, Chords.Asm, Chords.Bm, Chords.Cm, Chords.Csm, Chords.Dm, Chords.Dsm, Chords.Em, Chords.Fm);

    public static final List<PowerableChord> A1546 = List.of(Chords.A, Chords.E, Chords.D, Chords.Fsm);
    public static final List<PowerableChord> B1546 = List.of(Chords.B, Chords.Fs, Chords.E, Chords.Gsm);
    public static final List<PowerableChord> Bb1547 = List.of(Chords.Bb, Chords.Gm, Chords.F, Chords.Adim);
    public static final List<PowerableChord> C1546 = List.of(Chords.C, Chords.G, Chords.F, Chords.Am);
    public static final List<PowerableChord> D1546 = List.of(Chords.D, Chords.A, Chords.G, Chords.Bm);

    public static final List<PowerableChord> Cm1546 = List.of(Chords.Cm, Chords.Gm, Chords.Fm, Chords.Ab);
}
