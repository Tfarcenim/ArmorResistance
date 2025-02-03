package tfar.armorresistance;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;

public class Init {
    public static final Attribute ARMOR_PIERCE = new RangedAttribute("armorresistance.attribute.name.armor_pierce", 1, 0, 1024).setSyncable(true);

}
