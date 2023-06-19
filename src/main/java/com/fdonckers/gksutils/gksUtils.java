package com.fdonckers.gksutils;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fdonckers.gksutils.util.gksUtilsRegistries;

public class gksUtils implements ModInitializer {
    public static final String MOD_ID = "gksutils";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		gksUtilsRegistries.registerGksUtilsStuff();
	}
}