package garden.hestia.powerchord.music;

import garden.hestia.powerchord.component.PowerableChord;

import java.util.List;

public class Progressions {
    public static final List<PowerableChord> CHROMATIC = List.of(Chords.Fs, Chords.G, Chords.Gs, Chords.A, Chords.As, Chords.B, Chords.C, Chords.Cs, Chords.D, Chords.Ds, Chords.E, Chords.F);
    public static final List<PowerableChord> CHROMATIC_MINOR = List.of(Chords.Fsm, Chords.Gm, Chords.Gsm, Chords.Am, Chords.Asm, Chords.Bm, Chords.Cm, Chords.Csm, Chords.Dm, Chords.Dsm, Chords.Em, Chords.Fm);

    public static final List<PowerableChord> A1A546 = List.of(Chords.A, Chords.Aaug, Chords.E, Chords.D, Chords.Fsm);
    public static final List<PowerableChord> B1A546 = List.of(Chords.B, Chords.Baug, Chords.Fs, Chords.E, Chords.Gsm);
    public static final List<PowerableChord> Bb1A547 = List.of(Chords.Bb, Chords.Bbaug, Chords.Gm, Chords.F, Chords.Adim);
    public static final List<PowerableChord> C1A546 = List.of(Chords.C, Chords.Caug, Chords.G, Chords.F, Chords.Am);
    public static final List<PowerableChord> D1A546 = List.of(Chords.D, Chords.Daug, Chords.A, Chords.G, Chords.Bm);
    public static final List<PowerableChord> E1A546 = List.of(Chords.E, Chords.Eaug, Chords.B, Chords.A, Chords.Csm);
    public static final List<PowerableChord> F1A546 = List.of(Chords.F, Chords.Faug, Chords.C, Chords.Bb, Chords.Dm);
    public static final List<PowerableChord> G1A546 = List.of(Chords.G, Chords.Gaug, Chords.D, Chords.C, Chords.Em);

    public static final List<PowerableChord> Am1546 = List.of(Chords.Am, Chords.Em, Chords.Dm, Chords.F);
    public static final List<PowerableChord> Bm1546 = List.of(Chords.Bm, Chords.Fsm, Chords.Em, Chords.G);
    public static final List<PowerableChord> Cm1546 = List.of(Chords.Cm, Chords.Gm, Chords.Fm, Chords.Ab);
    public static final List<PowerableChord> Dm1546 = List.of(Chords.Dm, Chords.Am, Chords.Gm, Chords.Bb);
    public static final List<PowerableChord> Em1546 = List.of(Chords.Em, Chords.Bm, Chords.Am, Chords.C);
    public static final List<PowerableChord> Fm1546 = List.of(Chords.Fm, Chords.Cm, Chords.Bbm, Chords.Db);
    public static final List<PowerableChord> Gm1546 = List.of(Chords.Gm, Chords.Dm, Chords.Cm, Chords.Eb);
}
