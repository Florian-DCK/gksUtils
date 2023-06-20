package com.fdonckers.gksutils.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import me.lucko.fabric.api.permissions.v0.Permissions;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class EnderChestCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess registryAccess, CommandManager.RegistrationEnvironment environment) {
        dispatcher.register(CommandManager.literal("enderchest")
        .requires(Permissions.require("gksutils.command.enderchest.self"))
        .executes(context -> run(context, null) )
            .then((ArgumentBuilder<ServerCommandSource, ?>)CommandManager.argument("target", EntityArgumentType.players())
            .requires(Permissions.require("gksutils.command.enderchest.others"))
            .executes(context -> run(context, EntityArgumentType.getPlayer(context, "target")))));
    }

    public static int run(CommandContext<ServerCommandSource> context, ServerPlayerEntity target) throws CommandSyntaxException {
        PlayerEntity sourcePlayer = context.getSource().getPlayer();
        EnderChestInventory enderChestInventory;
        Text containerName;
        if (target != null) {
            enderChestInventory = target.getEnderChestInventory();
            containerName = Text.literal(target.getDisplayName().getString() + "'s enderchest");
        }
        else {
            enderChestInventory = sourcePlayer.getEnderChestInventory(); 
            containerName = Text.literal(sourcePlayer.getDisplayName().getString() + "'s enderchest");
        }
        
        sourcePlayer.sendMessage(Text.literal("Opening " + containerName.getString()));

        sourcePlayer.openHandledScreen(new SimpleNamedScreenHandlerFactory((syncId, inventory, player) -> GenericContainerScreenHandler.createGeneric9x3(syncId, inventory, enderChestInventory), containerName));
        return 1;   

    }
    
}

