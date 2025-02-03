package tfar.armorresistance.mixin;

import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import tfar.armorresistance.ArmorResistance;
import tfar.armorresistance.Init;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Shadow protected abstract void hurtArmor(DamageSource $$0, float $$1);

    @Inject(method = "createLivingAttributes",at = @At("RETURN"))
    private static void extraAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.getReturnValue().add(Init.ARMOR_PIERCE);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    protected float getDamageAfterArmorAbsorb(DamageSource source, float amount) {
        if (!source.is(DamageTypeTags.BYPASSES_ARMOR)) {
            this.hurtArmor(source, amount);
            amount = ArmorResistance.calc((LivingEntity) (Object)this,source,amount);
        }

        return amount;
    }

}