package tfar.armorresistance;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ItemAttributeModifierEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import tfar.armorresistance.datagen.ModDatagen;

import java.util.UUID;

@Mod(ArmorResistance.MOD_ID)
public class ArmorResistanceForge {
    
    public ArmorResistanceForge() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER,AttributeConfig.SERVER_SPEC);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.
        bus.addListener(this::register);
        bus.addListener(ModDatagen::gather);
        MinecraftForge.EVENT_BUS.addListener(this::modifiers);
        // Use Forge to bootstrap the Common mod.
        ArmorResistance.init();
        
    }

    void register(RegisterEvent event) {
        event.register(Registries.ATTRIBUTE,ArmorResistance.id("armor_pierce"),() -> Init.ARMOR_PIERCE);
    }

    static UUID uuid = new UUID(ArmorResistance.MOD_ID.hashCode(),"armor_resistance".hashCode());

    void modifiers(ItemAttributeModifierEvent event) {
        if (event.getSlotType() == EquipmentSlot.MAINHAND) {
            Double d = AttributeConfig.SERVER.mapConfig.get().get(event.getItemStack().getItem());
            if (d != null) {
                event.addModifier(Init.ARMOR_PIERCE,new AttributeModifier(uuid,"armor resistance",d, AttributeModifier.Operation.MULTIPLY_BASE));
            }
        }
    }

}