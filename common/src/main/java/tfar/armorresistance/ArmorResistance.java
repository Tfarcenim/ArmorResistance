package tfar.armorresistance;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.CombatRules;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import tfar.armorresistance.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// This class is part of the common project meaning it is shared between all supported loaders. Code written here can only
// import and access the vanilla codebase, libraries used by vanilla, and optionally third party libraries that provide
// common compatible binaries. This means common code can not directly use loader specific concepts such as Forge events
// however it will be compatible with all supported mod loaders.
public class ArmorResistance {

    public static final String MOD_ID = "armorresistance";
    public static final String MOD_NAME = "ArmorResistance";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    // The loader specific projects are able to import and use any code from the common project. This allows you to
    // write the majority of your code here and load it from your loader specific projects. This example has some
    // code that gets invoked by the entry point of the loader specific projects.
    public static void init() {


        // It is common for all supported loaders to provide a similar feature that can not be used directly in the
        // common code. A popular way to get around this is using Java's built-in service loader feature to create
        // your own abstraction layer. You can learn more about this in our provided services class. In this example
        // we have an interface in the common code and use a loader specific implementation to delegate our call to
        // the platform specific approach.

    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MOD_ID,path);
    }

    public static float calc(LivingEntity entity,DamageSource source,float amount) {
        final float originalAmount = amount;
        float newAmount = CombatRules.getDamageAfterAbsorb(amount, (float)entity.getArmorValue(), (float)entity.getAttributeValue(Attributes.ARMOR_TOUGHNESS));
        float difference = originalAmount - newAmount;
        double armorPierce = 1;
        if (source.getEntity() instanceof LivingEntity living) {
            armorPierce = living.getAttributeValue(Init.ARMOR_PIERCE);
        }
        if (armorPierce > 0) {
            difference /= armorPierce;
            amount = Math.max(0, amount - difference);
        } else {
            amount = 0;
        }
       // System.out.println("Initial damage: "+originalAmount);
       // System.out.println("New reduction: "+difference);
       // System.out.println("New damage: "+amount);
        return amount;
    }

}