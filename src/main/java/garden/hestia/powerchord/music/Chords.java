package garden.hestia.powerchord.music;

import garden.hestia.powerchord.component.AoeEffect;
import garden.hestia.powerchord.component.PowerableChord;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.text.Text;

import java.util.List;

public class Chords {
    public static final PowerableChord Fs = new PowerableChord(List.of(Notes.Fs, Notes.As, Notes.Cs), Text.literal("F#"), new AoeEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200), 20, true));
    public static final PowerableChord G = new PowerableChord(List.of(Notes.G, Notes.B, Notes.D), Text.literal("G"), new AoeEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 200), 20, true));
    public static final PowerableChord Gs = new PowerableChord(List.of(Notes.Gs, Notes.C, Notes.Ds), Text.literal("G#"), new AoeEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 60, 2), 20, true));
    public static final PowerableChord A = new PowerableChord(List.of(Notes.A, Notes.Cs, Notes.E), Text.literal("A"), new AoeEffect(new StatusEffectInstance(StatusEffects.SATURATION, 10), 20, true));
    public static final PowerableChord As = new PowerableChord(List.of(Notes.As, Notes.D, Notes.F), Text.literal("A#"), new AoeEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 400), 20, true));
    public static final PowerableChord B = new PowerableChord(List.of(Notes.B, Notes.Ds, Notes.Fs2), Text.literal("B"), new AoeEffect(new StatusEffectInstance(StatusEffects.SPEED, 200), 20, true));
    public static final PowerableChord C = new PowerableChord(List.of(Notes.C, Notes.E, Notes.G2), Text.literal("C"), new AoeEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200), 20, true));
    public static final PowerableChord Cs = new PowerableChord(List.of(Notes.Cs, Notes.F, Notes.Gs2), Text.literal("C#"), new AoeEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 200), 20, true));
    public static final PowerableChord D = new PowerableChord(List.of(Notes.D, Notes.Fs2, Notes.A2), Text.literal("D"), new AoeEffect(new StatusEffectInstance(StatusEffects.HASTE, 200), 20, true));
    public static final PowerableChord Ds = new PowerableChord(List.of(Notes.Ds, Notes.G2, Notes.As2), Text.literal("D#"), new AoeEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1), 20, true));
    public static final PowerableChord E = new PowerableChord(List.of(Notes.E, Notes.Gs2, Notes.B2), Text.literal("E"), new AoeEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 200), 20, true));
    public static final PowerableChord F = new PowerableChord(List.of(Notes.F, Notes.A2, Notes.C2), Text.literal("F"), new AoeEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 200), 20, true));

    public static PowerableChord Gb = Fs.named(Text.literal("Gb"));
    public static PowerableChord Ab = Gs.named(Text.literal("Ab"));
    public static PowerableChord Bb = As.named(Text.literal("Bb"));
    public static PowerableChord Db = Cs.named(Text.literal("Db"));
    public static PowerableChord Eb = Ds.named(Text.literal("Eb"));

    public static final PowerableChord Gaug = new PowerableChord(List.of(Notes.G, Notes.B, Notes.D), Text.literal("G+"), new AoeEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 200), 10, true));
    public static final PowerableChord Aaug = new PowerableChord(List.of(Notes.A, Notes.Cs, Notes.F), Text.literal("A+"), new AoeEffect(new StatusEffectInstance(StatusEffects.LUCK, 400), 10, true));
    public static final PowerableChord Asaug = new PowerableChord(List.of(Notes.As, Notes.D, Notes.Fs2), Text.literal("A#+"), new AoeEffect(new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 200), 10, true));
    public static final PowerableChord Caug = new PowerableChord(List.of(Notes.C, Notes.E, Notes.Gs2), Text.literal("C+"), new AoeEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 200, 1), 10, true));
    public static final PowerableChord Baug = new PowerableChord(List.of(Notes.B, Notes.Ds, Notes.G2), Text.literal("B+"), new AoeEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 200), 10, true));
    public static final PowerableChord Daug = new PowerableChord(List.of(Notes.D, Notes.Fs2, Notes.As2), Text.literal("D+"), new AoeEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 200), 10, true));
    public static final PowerableChord Eaug = new PowerableChord(List.of(Notes.E, Notes.Gs2, Notes.C2), Text.literal("E+"), new AoeEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 400), 10, true));
    public static final PowerableChord Faug = new PowerableChord(List.of(Notes.F, Notes.A2, Notes.Cs2), Text.literal("F+"), new AoeEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 200), 10, true));

    public static PowerableChord Bbaug = Asaug.named(Text.literal("Bb+"));

    public static final PowerableChord Fsm = new PowerableChord(List.of(Notes.Fs, Notes.A, Notes.Cs), Text.literal("F#m"), new AoeEffect(new StatusEffectInstance(StatusEffects.HUNGER, 200), 20, false));
    public static final PowerableChord Gm = new PowerableChord(List.of(Notes.G, Notes.As, Notes.D), Text.literal("Gm"), new AoeEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 200), 20, false));
    public static final PowerableChord Gsm = new PowerableChord(List.of(Notes.Gs, Notes.B, Notes.Ds), Text.literal("G#m"), new AoeEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20, 1), 20, false));
    public static final PowerableChord Am = new PowerableChord(List.of(Notes.A, Notes.C, Notes.E), Text.literal("Am"), new AoeEffect(new StatusEffectInstance(StatusEffects.UNLUCK, 200), 20, false));
    public static final PowerableChord Asm = new PowerableChord(List.of(Notes.As, Notes.D, Notes.F), Text.literal("A#m"), new AoeEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 200), 20, false));
    public static final PowerableChord Bm = new PowerableChord(List.of(Notes.B, Notes.D, Notes.Fs2), Text.literal("Bm"), new AoeEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200), 20, false));
    public static final PowerableChord Cm = new PowerableChord(List.of(Notes.C, Notes.Ds, Notes.G2), Text.literal("Cm"), new AoeEffect(new StatusEffectInstance(StatusEffects.POISON, 160), 20, false));
    public static final PowerableChord Csm = new PowerableChord(List.of(Notes.Cs, Notes.E, Notes.Gs2), Text.literal("C#m"), new AoeEffect(new StatusEffectInstance(StatusEffects.WITHER, 80), 20, false));
    public static final PowerableChord Dm = new PowerableChord(List.of(Notes.D, Notes.F, Notes.A2), Text.literal("Dm"), new AoeEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 400), 20, false));
    public static final PowerableChord Dsm = new PowerableChord(List.of(Notes.Ds, Notes.Fs2, Notes.As2), Text.literal("D#m"), new AoeEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1), 20, false));
    public static final PowerableChord Em = new PowerableChord(List.of(Notes.E, Notes.G2, Notes.B2), Text.literal("Em"), new AoeEffect(new StatusEffectInstance(StatusEffects.GLOWING, 400), 20, false));
    public static final PowerableChord Fm = new PowerableChord(List.of(Notes.F, Notes.Gs2, Notes.C2), Text.literal("Fm"), new AoeEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 200), 20, false));

    public static PowerableChord Gbm = Fsm.named(Text.literal("Gbm"));
    public static PowerableChord Abm = Gsm.named(Text.literal("Abm"));
    public static PowerableChord Bbm = Asm.named(Text.literal("Bbm"));
    public static PowerableChord Dbm = Csm.named(Text.literal("Dbm"));
    public static PowerableChord Ebm = Dsm.named(Text.literal("Ebm"));

    public static final PowerableChord Adim = new PowerableChord(List.of(Notes.A, Notes.C, Notes.Ds), Text.literal("Adim"), new AoeEffect(new StatusEffectInstance(StatusEffects.OOZING, 400), 10, false));
}
