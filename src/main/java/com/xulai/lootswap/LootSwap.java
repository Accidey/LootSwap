package com.xulai.lootswap;

import com.mojang.logging.LogUtils;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(LootSwap.MODID)
public class LootSwap {
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MODID = "lootswap";

    public LootSwap(ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
