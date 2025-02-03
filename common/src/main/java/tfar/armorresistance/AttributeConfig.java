package tfar.armorresistance;

import com.mojang.serialization.Codec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.HashMap;
import java.util.Map;

public class AttributeConfig {
    public static final Codec<Map<Item,Double>> CODEC = Codec.unboundedMap(BuiltInRegistries.ITEM.byNameCodec(),Codec.DOUBLE);

    public static final AttributeConfig SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;


    static {
        final Pair<AttributeConfig, ForgeConfigSpec> specPair2 = new ForgeConfigSpec.Builder().configure(AttributeConfig::new);
        SERVER_SPEC = specPair2.getRight();
        SERVER = specPair2.getLeft();
    }


    public final ConfigHelper.ConfigObject<Map<Item,Double>> mapConfig;

    public AttributeConfig(ForgeConfigSpec.Builder builder) {
        builder.push("general");
        Map<Item,Double> map = new HashMap<>();
        map.put(Items.WOODEN_SWORD,-.5);
        mapConfig = ConfigHelper.defineObject(builder,"map", CODEC,map);
        builder.pop();
    }

}
