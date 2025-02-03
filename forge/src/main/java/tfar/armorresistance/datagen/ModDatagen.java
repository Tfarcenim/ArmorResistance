package tfar.armorresistance.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import tfar.armorresistance.ArmorResistance;
import tfar.armorresistance.Init;

public class ModDatagen {

    public static void gather(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        generator.addProvider(event.includeClient(),new Lang(output));
    }

    static class Lang extends LanguageProvider {

        public Lang(PackOutput output) {
            super(output, ArmorResistance.MOD_ID,"en_us");
        }

        @Override
        protected void addTranslations() {
            add(Init.ARMOR_PIERCE.getDescriptionId(),"Armor Pierce");
        }
    }

}
