package com.fdonckers.gksutils.util;

import com.fdonckers.gksutils.gksUtils;
import com.fdonckers.gksutils.commands.EnderChestCommand;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class gksUtilsRegistries {
    public static void registerGksUtilsStuff() {
        registerCommands();
    }

    private static void registerCommands() {
        gksUtils.LOGGER.info("Registering commands for " + gksUtils.MOD_ID);

        CommandRegistrationCallback.EVENT.register(EnderChestCommand::register);
    }
}
