package com.xulai.lootswap;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@EventBusSubscriber(modid = LootSwap.MODID)
public class LootSwapHandler {

    private static final Random random = new Random();

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getLevel().isClientSide() || event.getEntity() == null) return;

        BlockEntity targetBe = event.getLevel().getBlockEntity(event.getPos());

        if (targetBe instanceof RandomizableContainerBlockEntity targetContainer) {
            ResourceKey<LootTable> targetLootTable = targetContainer.getLootTable();

            if (targetLootTable == null) return;

            if (random.nextDouble() > Config.swapChance) return;

            ServerLevel level = (ServerLevel) event.getLevel();
            BlockPos center = event.getPos();
            int radius = Config.radius;
            List<RandomizableContainerBlockEntity> candidates = new ArrayList<>();

            for (int x = -radius; x <= radius; x++) {
                for (int y = -radius; y <= radius; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        if (x == 0 && y == 0 && z == 0) continue;

                        BlockPos searchPos = center.offset(x, y, z);
                        BlockEntity be = level.getBlockEntity(searchPos);

                        if (be instanceof RandomizableContainerBlockEntity candidate) {
                            ResourceKey<LootTable> candidateLoot = candidate.getLootTable();

                            if (candidateLoot != null && !candidateLoot.equals(targetLootTable)) {
                                candidates.add(candidate);
                            }
                        }
                    }
                }
            }

            if (!candidates.isEmpty()) {
                RandomizableContainerBlockEntity otherContainer = candidates.get(random.nextInt(candidates.size()));
                ResourceKey<LootTable> otherLootTable = otherContainer.getLootTable();

                swapLootTables(targetContainer, otherContainer);

                if (ModCommands.isDebugEnabled) {
                    event.getEntity().sendSystemMessage(Component.literal(
                        "§a[LootSwap]§r Swapped DIFFERENT loot: " +
                        targetLootTable.location() + " <-> " + otherLootTable.location()
                    ));
                }
            }
        }
    }

    private static void swapLootTables(RandomizableContainerBlockEntity box1, RandomizableContainerBlockEntity box2) {
        ResourceKey<LootTable> loot1 = box1.getLootTable();
        long seed1 = box1.getLootTableSeed();

        ResourceKey<LootTable> loot2 = box2.getLootTable();
        long seed2 = box2.getLootTableSeed();

        box1.setLootTable(loot2);
        box1.setLootTableSeed(seed2);

        box2.setLootTable(loot1);
        box2.setLootTableSeed(seed1);

        box1.setChanged();
        box2.setChanged();
    }
}
