package garden.hestia.powerchord;

import net.minecraft.block.NoteBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class InstrumentItem extends Item {
    public InstrumentItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        user.playSound(SoundEvents.BLOCK_NOTE_BLOCK_DIDGERIDOO.value(), 1.0f, NoteBlock.getNotePitch(0));
        return TypedActionResult.pass(user.getStackInHand(hand));
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        if (remainingUseTicks == 50) {
            user.playSound(SoundEvents.BLOCK_NOTE_BLOCK_DIDGERIDOO.value(), 1.0f, NoteBlock.getNotePitch(4));
        } else if (remainingUseTicks == 40) {
            user.playSound(SoundEvents.BLOCK_NOTE_BLOCK_DIDGERIDOO.value(), 1.0f, NoteBlock.getNotePitch(7));
        }
        super.usageTick(world, user, stack, remainingUseTicks);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        if (remainingUseTicks < 40) {
            user.playSound(SoundEvents.BLOCK_NOTE_BLOCK_DIDGERIDOO.value(), 0.5f, NoteBlock.getNotePitch(0));
            user.playSound(SoundEvents.BLOCK_NOTE_BLOCK_DIDGERIDOO.value(), 0.3f, NoteBlock.getNotePitch(4));
            user.playSound(SoundEvents.BLOCK_NOTE_BLOCK_DIDGERIDOO.value(), 0.7f, NoteBlock.getNotePitch(7));
            if (user instanceof PlayerEntity player) {
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
