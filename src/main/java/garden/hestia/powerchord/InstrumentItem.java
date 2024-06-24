package garden.hestia.powerchord;

import net.minecraft.block.NoteBlock;
import net.minecraft.entity.LivingEntity;
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

    public static PowerKeyComponent getPlayableKey(PlayerEntity player)
    {
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
        return stack.getComponents().contains(PowerChord.KEY);
    }

    public static PowerKeyComponent getKey(LivingEntity user, ItemStack usingStack) {
        if (user instanceof PlayerEntity player) {
            ItemStack otherStack = player.getStackInHand(Hand.MAIN_HAND) == usingStack ? player.getStackInHand(Hand.OFF_HAND) : player.getStackInHand(Hand.MAIN_HAND);
            if (hasKey(otherStack) && !(otherStack.getItem() instanceof InstrumentItem)) return otherStack.getComponents().get(PowerChord.KEY);
        }
        return hasKey(usingStack) ? usingStack.getComponents().get(PowerChord.KEY) : null;
    }

    public static boolean hasState(ItemStack stack)
    {
        return stack.getComponents().contains(PowerChord.STATE);
    }

    public static InstrumentStateComponent getState(ItemStack stack)
    {
        return hasState(stack) ? stack.getComponents().get(PowerChord.STATE) : null;
    }

    public static void setState(ItemStack stack, int index)
    {
        stack.set(PowerChord.STATE, new InstrumentStateComponent(index));
    }

    public static SoundEvent getSoundEvent(PowerKeyComponent key) {   
        return key == null ? SoundEvents.INTENTIONALLY_EMPTY : key.sound();
    }

    public static int getChordIndex(LivingEntity user, PowerKeyComponent key, ItemStack instrumentStack) {
        if (hasState(instrumentStack)) return getState(instrumentStack).chordIndex();
        return key == null ? 0 : (int) Math.floor((90.0f - user.getPitch()) * key.chords().size() / 180.05f);
    }

    public static void playRoot(LivingEntity user, PowerKeyComponent key, int chordIndex) {
        user.playSound(getSoundEvent(key), 1.0f, NoteBlock.getNotePitch(key.chords().get(chordIndex).root()));
    }

    public static void playChord(LivingEntity user, PowerKeyComponent key, int chordIndex) {
        user.playSound(getSoundEvent(key), 0.5f, NoteBlock.getNotePitch(key.chords().get(chordIndex).root()));
        user.playSound(getSoundEvent(key), 0.3f, NoteBlock.getNotePitch(key.chords().get(chordIndex).third()));
        user.playSound(getSoundEvent(key), 0.7f, NoteBlock.getNotePitch(key.chords().get(chordIndex).fifth()));
    }

    public void swing(PlayerEntity user, Hand hand) {
        PowerKeyComponent key = getKey(user, user.getStackInHand(hand));
        playChord(user, key, getChordIndex(user, key, user.getStackInHand(hand)));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stackInHand = user.getStackInHand(hand);
        PowerKeyComponent key = getKey(user, stackInHand);
        if (key != null) {
            setState(stackInHand, getChordIndex(user, key, stackInHand));
            playRoot(user, key, getChordIndex(user, key, stackInHand));
            user.setCurrentHand(hand);
            return TypedActionResult.pass(stackInHand);
        }
        return super.use(world, user, hand);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        PowerKeyComponent key = getKey(user, stack);
        if (key != null)
        {
            if (remainingUseTicks == 50) {
                user.playSound(getSoundEvent(key), 1.0f, NoteBlock.getNotePitch(key.chords().get(getChordIndex(user, key, stack)).third()));
            } else if (remainingUseTicks == 40) {
                user.playSound(getSoundEvent(key), 1.0f, NoteBlock.getNotePitch(key.chords().get(getChordIndex(user, key, stack)).fifth()));
            }
        }
        super.usageTick(world, user, stack, remainingUseTicks);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (remainingUseTicks < 40) {
            PowerKeyComponent key = getKey(user, stack);
            if (key != null && user instanceof PlayerEntity player) {
                playChord(user, key, getChordIndex(user, key, stack));
                AoeEffect effect = key.chords().get(getChordIndex(user, key, stack)).effect();
                List<LivingEntity> targets;
                if (effect.friendly())
                {
                     targets = new ArrayList<>(world.getNonSpectatingEntities(PlayerEntity.class, Box.of(user.getPos(), effect.radius(), effect.radius(), effect.radius())));

                }
                else
                {
                    targets = new ArrayList<>(world.getNonSpectatingEntities(HostileEntity.class, Box.of(user.getPos(), effect.radius(), effect.radius(), effect.radius())));
                }

                for (LivingEntity target : targets)
                {
                    target.addStatusEffect(effect.status());
                }
                player.getItemCooldownManager().set(stack.getItem(), 60);
            }
        }
        stack.remove(PowerChord.STATE);
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
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
        return 60;
    }
}
