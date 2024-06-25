package garden.hestia.powerchord.mixin.client;

import garden.hestia.powerchord.client.PowerChordClient;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@SuppressWarnings("UnreachableCode")
@Mixin(ItemRenderer.class)
public class MixinItemRenderer {
    @ModifyVariable(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0), index = 8, argsOnly = true)
    public BakedModel renderFlatOverrides(BakedModel model, ItemStack stack) {
        ItemRenderer self = (ItemRenderer) (Object) this;
        if (PowerChordClient.FLAT_MODELS.containsKey(stack.getItem())) return self.getModels().getModelManager().getModel(PowerChordClient.FLAT_MODELS.get(stack.getItem()));
        return model;
    }
}
