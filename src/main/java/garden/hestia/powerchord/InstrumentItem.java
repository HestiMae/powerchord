package garden.hestia.powerchord;

import com.google.common.collect.Lists;
import garden.hestia.powerchord.component.AoeEffect;
import garden.hestia.powerchord.component.InstrumentStateComponent;
import garden.hestia.powerchord.component.KeyComponent;
import garden.hestia.powerchord.component.PowerableChord;
import net.minecraft.block.NoteBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class InstrumentItem extends Item {
    public InstrumentItem(Settings settings) {
        super(settings);
    }

    public static KeyComponent getPlayableKey(PlayerEntity player) {
        ItemStack instrumentStack = getPlayableInstrument(player);
        if (instrumentStack != null && instrumentStack.getItem() instanceof InstrumentItem) return getKey(player, instrumentStack);
        return null;
    }

    public static ItemStack getPlayableInstrument(PlayerEntity player)
    {
        ItemStack mainHandStack = player.getStackInHand(Hand.MAIN_HAND);
        ItemStack offHandStack = player.getStackInHand(Hand.OFF_HAND);
        if (mainHandStack.getItem() instanceof InstrumentItem) return mainHandStack;
        if (offHandStack.getItem() instanceof InstrumentItem) return offHandStack;
        return null;
    }

    public static boolean hasKey(ItemStack stack) {
        return stack.contains(PowerChord.KEY);
    }

    public static KeyComponent getKey(LivingEntity user, ItemStack usingStack) {
        if (user instanceof PlayerEntity player) {
            ItemStack otherStack = player.getStackInHand(Hand.MAIN_HAND) == usingStack ? player.getStackInHand(Hand.OFF_HAND) : player.getStackInHand(Hand.MAIN_HAND);
            if (hasKey(otherStack) && !(otherStack.getItem() instanceof InstrumentItem)) return otherStack.get(PowerChord.KEY);
        }
        return hasKey(usingStack) ? usingStack.get(PowerChord.KEY) : null;
    }

    private int getNoteTicks(ItemStack stack) {
        return stack.contains(PowerChord.INSTRUMENT) ? stack.get(PowerChord.INSTRUMENT).noteTicks() : 8;
    }

    private int getCooldownBeats(ItemStack stack) {
        return stack.contains(PowerChord.INSTRUMENT) ? stack.get(PowerChord.INSTRUMENT).cooldownBeats() : (getNoteTicks(stack) * 4);
    }

    public static boolean hasState(ItemStack stack)
    {
        return stack.contains(PowerChord.STATE);
    }

    public static InstrumentStateComponent getState(ItemStack stack)
    {
        return hasState(stack) ? stack.get(PowerChord.STATE) : null;
    }

    public static void setState(ItemStack stack, int index, boolean reversed)
    {
        stack.set(PowerChord.STATE, new InstrumentStateComponent(index, reversed));
    }

    public static SoundEvent getSoundEvent(KeyComponent key) {
        return key == null ? SoundEvents.INTENTIONALLY_EMPTY : key.sound();
    }

    public static int getChordIndex(LivingEntity user, KeyComponent key, ItemStack instrumentStack) {
        if (hasState(instrumentStack)) return getState(instrumentStack).chordIndex();
        return key == null ? 0 : (int) Math.floor((90.0f - user.getPitch()) * key.chords().size() / 180.05f);
    }

    private static boolean getReversed(LivingEntity user, ItemStack instrumentStack) {
        if (hasState(instrumentStack)) return getState(instrumentStack).reversed();
        return user.isSneaking();
    }

    public static void playRoot(LivingEntity user, KeyComponent key, int chordIndex, boolean reversed) {
        user.playSound(getSoundEvent(key), 0.5f, NoteBlock.getNotePitch(key.chords().get(chordIndex).root(reversed)));
    }

    public static void playChord(LivingEntity user, KeyComponent key, int chordIndex, boolean reversed) {
        List<Integer> notes = reversed ? Lists.reverse(key.chords().get(chordIndex).notes()) : key.chords().get(chordIndex).notes();
        for (int i = 0; i < notes.size(); i++) {
            user.playSound(getSoundEvent(key), i == 0 ? 0.4f : i == notes.size() - 1 ? 0.7f : 0.3f, NoteBlock.getNotePitch(notes.get(i)));
        }
    }

    public static Hand handBlockingSwing(PlayerEntity player, Hand hand) {
        if (player.getStackInHand(hand).getItem() instanceof InstrumentItem) {
            return hand;
        } else if (InstrumentItem.hasKey(player.getStackInHand(hand)) && player.getStackInHand(hand == Hand.MAIN_HAND ? Hand.OFF_HAND : Hand.MAIN_HAND).getItem() instanceof InstrumentItem) {
            return hand == Hand.MAIN_HAND ? Hand.OFF_HAND : Hand.MAIN_HAND;
        }
        return null;
    }

    public static boolean swing(PlayerEntity user, Hand hand) {
        Hand swingHand = handBlockingSwing(user, hand);
        if (swingHand !=  null) {
            KeyComponent key = getKey(user, user.getStackInHand(hand));
            playChord(user, key, getChordIndex(user, key, user.getStackInHand(hand)), getReversed(user, user.getStackInHand(hand)));
            return true;
        }
        return false;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stackInHand = user.getStackInHand(hand);
        KeyComponent key = getKey(user, stackInHand);
        if (key != null) {
            setState(stackInHand, getChordIndex(user, key, stackInHand), getReversed(user, stackInHand));
            playRoot(user, key, getChordIndex(user, key, stackInHand), getReversed(user, stackInHand));
            user.setCurrentHand(hand);
            return TypedActionResult.pass(stackInHand);
        }
        return super.use(world, user, hand);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        KeyComponent key = getKey(user, stack);
        int noteTicks = getNoteTicks(stack);
        if (key != null) {
            if (remainingUseTicks >= 2 * noteTicks) { // Release Zone
                if (remainingUseTicks % noteTicks == 0) {
                    PowerableChord chord = key.chords().get(getChordIndex(user, key, stack));
                    int noteIndex = (getMaxUseTime(stack, user) - remainingUseTicks) / noteTicks;
                    int actualNoteIndex = getReversed(user, stack) ? chord.notes().size() - noteIndex - 1 : noteIndex;
                    user.playSound(getSoundEvent(key), 0.5f, NoteBlock.getNotePitch(chord.notes().get(actualNoteIndex)));
                }
            }
        }
        super.usageTick(world, user, stack, remainingUseTicks);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        int noteTicks = getNoteTicks(stack);
        if (remainingUseTicks < noteTicks * 2) {
            KeyComponent key = getKey(user, stack);
            if (key != null && user instanceof PlayerEntity player) {
                playChord(user, key, getChordIndex(user, key, stack), getReversed(user, stack));
                if (isMagical(user)) {
                    AoeEffect effect = key.chords().get(getChordIndex(user, key, stack)).effect();
                    List<LivingEntity> targets;
                    if (effect.friendly()) {
                        targets = new ArrayList<>(world.getNonSpectatingEntities(PlayerEntity.class, Box.of(user.getPos(), effect.radius(), effect.radius(), effect.radius())));
                    } else {
                        targets = new ArrayList<>(world.getNonSpectatingEntities(HostileEntity.class, Box.of(user.getPos(), effect.radius(), effect.radius(), effect.radius())));
                    }

                    for (LivingEntity target : targets) {
                        target.addStatusEffect(new StatusEffectInstance(effect.status()));
                    }
                    player.getItemCooldownManager().set(stack.getItem(), noteTicks * (1 + getCooldownBeats(stack)));
                }
            }
        }
        stack.remove(PowerChord.STATE);
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    public static boolean isMagical(LivingEntity user) {
        return (!(user.getStackInHand(Hand.MAIN_HAND).getItem() instanceof InstrumentItem) || !(user.getStackInHand(Hand.OFF_HAND).getItem() instanceof InstrumentItem)) && hasKey(user.getStackInHand(Hand.MAIN_HAND)) && hasKey(user.getStackInHand(Hand.OFF_HAND));
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity player) {
            user.playSound(SoundEvents.BLOCK_CANDLE_EXTINGUISH, 0.5f, 1.0f);
            player.getItemCooldownManager().set(stack.getItem(), 20);
        }
        stack.remove(PowerChord.STATE);
        return super.finishUsing(stack, world, user);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        KeyComponent key = getKey(user, stack);
        if (key != null) {
            return (key.chords().get(getChordIndex(user, key, stack)).notes().size() + 1 /* release window */) * getNoteTicks(stack);
        }
        return 0;
    }
}
