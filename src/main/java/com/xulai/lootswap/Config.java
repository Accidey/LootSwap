package com.xulai.lootswap;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = LootSwap.MODID)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue RADIUS;
    public static final ModConfigSpec.DoubleValue SWAP_CHANCE;

    static {
        BUILDER.push("Loot Swap Settings");

        RADIUS = BUILDER.comment("Swap search radius (in blocks) / 互换搜索半径(格)")
                .defineInRange("radius", 10, 1, 64);

        SWAP_CHANCE = BUILDER.comment("Probability of swapping (0.0 to 1.0, where 0.5 is 50%) / 互换概率(0.0到1.0)")
                .defineInRange("swapChance", 0.5, 0.0, 1.0);

        BUILDER.pop();
    }

    public static final ModConfigSpec SPEC = BUILDER.build();

    public static int radius;
    public static double swapChance;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        radius = RADIUS.get();
        swapChance = SWAP_CHANCE.get();
    }
}
