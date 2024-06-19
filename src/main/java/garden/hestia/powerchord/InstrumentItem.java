package garden.hestia.powerchord;

import net.minecraft.block.NoteBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.List;

public class InstrumentItem extends Item {
    public InstrumentItem(Settings settings) {
        super(settings);
    }

    public static boolean hasKey(ItemStack stack) {
        return stack.getComponents().contains(PowerChord.KEY);
    }

    public static ItemStack getKeyStack(LivingEntity user, ItemStack usingStack) {
        if (user instanceof PlayerEntity player) {
            ItemStack otherStack = player.getStackInHand(Hand.MAIN_HAND) == usingStack ? player.getStackInHand(Hand.OFF_HAND) : player.getStackInHand(Hand.MAIN_HAND);
            if (hasKey(otherStack) && !(otherStack.getItem() instanceof InstrumentItem)) return otherStack;
        }
        return usingStack;
    }

    public static SoundEvent getSoundEvent(ItemStack stack) {
        PowerKeyComponent component = stack.getComponents().getOrDefault(PowerChord.KEY, null);
        return component == null ? SoundEvents.INTENTIONALLY_EMPTY : component.sound();
    }

    public static List<PowerTriad> getChords(ItemStack stack) {
        PowerKeyComponent component = stack.getComponents().getOrDefault(PowerChord.KEY, null);
        return component == null ? List.of() : List.copyOf(component.chords());
    }

    public static int getChordIndex(LivingEntity user, ItemStack keyStack) {
        PowerKeyComponent component = keyStack.getComponents().getOrDefault(PowerChord.KEY, null);
        return component == null ? 0 : (int) Math.floor((user.getPitch() + 90.0f) * component.chords().size() / 180.05f);
    }

    public static boolean playRoot(LivingEntity user, ItemStack keyStack, int chordIndex) {
        if (hasKey(keyStack)) {
            user.playSound(getSoundEvent(keyStack), 1.0f, NoteBlock.getNotePitch(getChords(keyStack).get(chordIndex).root()));
            return true;
        }
        return false;
    }

    public static boolean playChord(LivingEntity user, ItemStack keyStack, int chordIndex) {
        if (hasKey(keyStack)) {
            user.playSound(getSoundEvent(keyStack), 0.5f, NoteBlock.getNotePitch(getChords(keyStack).get(chordIndex).root()));
            user.playSound(getSoundEvent(keyStack), 0.3f, NoteBlock.getNotePitch(getChords(keyStack).get(chordIndex).third()));
            user.playSound(getSoundEvent(keyStack), 0.7f, NoteBlock.getNotePitch(getChords(keyStack).get(chordIndex).fifth()));
            return true;
        }
        return false;
    }

    public boolean swing(PlayerEntity user, Hand hand) {
        ItemStack keyStack = getKeyStack(user, user.getStackInHand(hand));
        return playChord(user, keyStack, getChordIndex(user, keyStack));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack keyStack = getKeyStack(user, user.getStackInHand(hand));
        if (playRoot(user, keyStack, getChordIndex(user, keyStack))) {
            user.setCurrentHand(hand);
            return TypedActionResult.pass(user.getStackInHand(hand));
        }
        return super.use(world, user, hand);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (remainingUseTicks == 50) {
            ItemStack keyStack = getKeyStack(user, stack);
            user.playSound(getSoundEvent(keyStack), 1.0f, NoteBlock.getNotePitch(getChords(keyStack).get(getChordIndex(user, keyStack)).third()));
        } else if (remainingUseTicks == 40) {
            ItemStack keyStack = getKeyStack(user, stack);
            user.playSound(getSoundEvent(keyStack), 1.0f, NoteBlock.getNotePitch(getChords(keyStack).get(getChordIndex(user, keyStack)).fifth()));
        }
        super.usageTick(world, user, stack, remainingUseTicks);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (remainingUseTicks < 40) {
            ItemStack keyStack = getKeyStack(user, stack);
            if (playChord(user, keyStack, getChordIndex(user, keyStack)) && user instanceof PlayerEntity player) {
                player.getItemCooldownManager().set(stack.getItem(), 60);
            }
        }
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof PlayerEntity player) {
            user.playSound(SoundEvents.BLOCK_CANDLE_EXTINGUISH, 0.5f, 1.0f);
            player.getItemCooldownManager().set(stack.getItem(), 20);
        }
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
