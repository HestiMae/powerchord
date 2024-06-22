package garden.hestia.powerchord.music;

import garden.hestia.powerchord.AoeEffect;
import garden.hestia.powerchord.PowerTriad;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.text.Text;

public class Chords {
    public static final PowerTriad Fs = new PowerTriad(Notes.Fs, Notes.As, Notes.Cs, Text.literal("F#"), new AoeEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 40), 10, true));
    public static final PowerTriad G = new PowerTriad(Notes.G, Notes.B, Notes.D, Text.literal("G"), new AoeEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 40), 10, true));
    public static final PowerTriad Gs = new PowerTriad(Notes.Gs, Notes.C, Notes.Ds, Text.literal("G#"), new AoeEffect(new StatusEffectInstance(StatusEffects.JUMP_BOOST, 40), 10, true));
    public static final PowerTriad A = new PowerTriad(Notes.A, Notes.Cs, Notes.E, Text.literal("A"), new AoeEffect(new StatusEffectInstance(StatusEffects.SATURATION, 10), 10, true));
    public static final PowerTriad As = new PowerTriad(Notes.As, Notes.D, Notes.F, Text.literal("A#"), new AoeEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 60), 10, true));
    public static final PowerTriad B = new PowerTriad(Notes.B, Notes.Ds, Notes.Fs2, Text.literal("B"), new AoeEffect(new StatusEffectInstance(StatusEffects.SPEED, 40), 10, true));
    public static final PowerTriad C = new PowerTriad(Notes.C, Notes.E, Notes.G2, Text.literal("C"), new AoeEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 40), 10, true));
    public static final PowerTriad Cs = new PowerTriad(Notes.Cs, Notes.F, Notes.Gs2, Text.literal("C#"), new AoeEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 40), 10, true));
    public static final PowerTriad D = new PowerTriad(Notes.D, Notes.Fs2, Notes.A2, Text.literal("D"), new AoeEffect(new StatusEffectInstance(StatusEffects.HASTE, 80), 10, true));
    public static final PowerTriad Ds = new PowerTriad(Notes.Ds, Notes.G2, Notes.As2, Text.literal("D#"), new AoeEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1), 10, true));
    public static final PowerTriad E = new PowerTriad(Notes.E, Notes.Gs2, Notes.B2, Text.literal("E"), new AoeEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 40), 10, true));
    public static final PowerTriad F = new PowerTriad(Notes.F, Notes.A2, Notes.C2, Text.literal("F"), new AoeEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 60), 10, true));

    public static PowerTriad Gb = Fs;
    public static PowerTriad Ab = Gs;
    public static PowerTriad Bb = As;
    public static PowerTriad Db = Cs;
    public static PowerTriad Eb = Ds;

    public static final PowerTriad Fsaug = new PowerTriad(Notes.Fs, Notes.As, Notes.D, Text.literal("F#+"), new AoeEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 60), 3, true));
    public static final PowerTriad Amaug = new PowerTriad(Notes.A, Notes.Cs, Notes.F, Text.literal("A+"), new AoeEffect(new StatusEffectInstance(StatusEffects.LUCK, 40), 3, true));
    public static final PowerTriad Asaug = new PowerTriad(Notes.As, Notes.D, Notes.Fs, Text.literal("A#+"), new AoeEffect(new StatusEffectInstance(StatusEffects.CONDUIT_POWER, 60), 3, true));
    public static final PowerTriad Baug = new PowerTriad(Notes.B, Notes.Ds, Notes.G2, Text.literal("B+"), new AoeEffect(new StatusEffectInstance(StatusEffects.DOLPHINS_GRACE, 60), 3, true));
    public static final PowerTriad Dsaug = new PowerTriad(Notes.Ds, Notes.G2, Notes.B2, Text.literal("D#+"), new AoeEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 60), 3, true));
    public static final PowerTriad Eaug = new PowerTriad(Notes.E, Notes.Gs2, Notes.C2, Text.literal("E+"), new AoeEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 120), 3, true));

    public static PowerTriad Gba = Fsaug;
    public static PowerTriad Bba = Asaug;
    public static PowerTriad Eba = Dsaug;

    public static final PowerTriad Fsm = new PowerTriad(Notes.Fs, Notes.A, Notes.Cs, Text.literal("F#m"), new AoeEffect(new StatusEffectInstance(StatusEffects.HUNGER, 40), 10, false));
    public static final PowerTriad Gm = new PowerTriad(Notes.G, Notes.As, Notes.D, Text.literal("Gm"), new AoeEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 40), 10, false));
    public static final PowerTriad Gsm = new PowerTriad(Notes.Gs, Notes.B, Notes.Ds, Text.literal("G#m"), new AoeEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20, 1), 10, false));
    public static final PowerTriad Am = new PowerTriad(Notes.A, Notes.C, Notes.E, Text.literal("Am"), new AoeEffect(new StatusEffectInstance(StatusEffects.UNLUCK, 40), 10, false));
    public static final PowerTriad Asm = new PowerTriad(Notes.As, Notes.D, Notes.F, Text.literal("A#"), new AoeEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 60), 10, false));
    public static final PowerTriad Bm = new PowerTriad(Notes.B, Notes.D, Notes.Fs2, Text.literal("B"), new AoeEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 40), 10, false));
    public static final PowerTriad Cm = new PowerTriad(Notes.C, Notes.Ds, Notes.G2, Text.literal("Cm"), new AoeEffect(new StatusEffectInstance(StatusEffects.POISON, 40), 10, false));
    public static final PowerTriad Csm = new PowerTriad(Notes.Cs, Notes.E, Notes.Gs2, Text.literal("C#m"), new AoeEffect(new StatusEffectInstance(StatusEffects.WITHER, 20), 10, false));
    public static final PowerTriad Dm = new PowerTriad(Notes.D, Notes.F2, Notes.A2, Text.literal("Dm"), new AoeEffect(new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 80), 10, false));
    public static final PowerTriad Dsm = new PowerTriad(Notes.Ds, Notes.Fs2, Notes.As2, Text.literal("D#m"), new AoeEffect(new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE, 1), 10, false));
    public static final PowerTriad Em = new PowerTriad(Notes.E, Notes.G2, Notes.B2, Text.literal("Em"), new AoeEffect(new StatusEffectInstance(StatusEffects.GLOWING, 80), 10, false));
    public static final PowerTriad Fm = new PowerTriad(Notes.F, Notes.Gs2, Notes.C2, Text.literal("Fm"), new AoeEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 60), 10, false));

    public static PowerTriad Gbm = Fsm;
    public static PowerTriad Abm = Gsm;
    public static PowerTriad Bbm = Asm;
    public static PowerTriad Dbm = Csm;
    public static PowerTriad Ebm = Dsm;

    public static final PowerTriad Fsdim = new PowerTriad(Notes.Fs, Notes.A, Notes.C, Text.literal("F#dim"), new AoeEffect(new StatusEffectInstance(StatusEffects.WITHER, 60), 3, false));
    public static final PowerTriad Adim = new PowerTriad(Notes.A, Notes.C, Notes.Ds, Text.literal("Adim"), new AoeEffect(new StatusEffectInstance(StatusEffects.OOZING, 80), 3, false));

    public static PowerTriad Gbdim = Fsdim;
}
