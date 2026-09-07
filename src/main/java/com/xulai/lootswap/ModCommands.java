package com.xulai.lootswap;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(modid = LootSwap.MODID)
public class ModCommands {

    public static boolean isDebugEnabled = false;

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        register(event.getDispatcher());
    }

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            Commands.literal("lootswap")
                .then(Commands.literal("debug")
                    .executes(ModCommands::toggleDebug))
        );
    }

    private static int toggleDebug(CommandContext<CommandSourceStack> context) {
        isDebugEnabled = !isDebugEnabled;
        String status = isDebugEnabled ? "ENABLED (开启)" : "DISABLED (关闭)";

        context.getSource().sendSuccess(() -> Component.literal("Loot Swap Debug Mode: " + status), false);
        return 1;
    }
}
