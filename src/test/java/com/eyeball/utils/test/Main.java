package com.eyeball.utils.test;

import java.io.File;
import java.io.IOException;

import com.eyeball.utils.io.FileTextList;
import com.eyeball.utils.logging.Logger;
import com.eyeball.utils.logging.MultiLogger;
import com.eyeball.utils.reflect.ReflectionHelper;

public class Main {

	private static MultiLogger LOGGER;

	public static void main(String[] args) throws NoSuchFieldException,
			SecurityException, ClassNotFoundException,
			IllegalArgumentException, IllegalAccessException,
			NullPointerException, IOException {
		// How to print? Everything is private.
		// Use ReflectionHelper!
		LOGGER = ReflectionHelper.getField(References.class, /* static */null,
				"LOGGER");
		LOGGER.info("Hi?");
		LOGGER.info("Yes! this worked!");

		for (String s : new FileTextList(new File(
				System.getProperty("user.home") + "/options.txt"))) {
			new Logger("File-Reader").info(s);
		}

		// How to print? Everything is private.
		// Use ReflectionHelper!
		LOGGER = ReflectionHelper.getField(References.class, /* static */null,
				"LOGGER");
		LOGGER.info("Hi?");
		LOGGER.info("Yes! this worked!");
	}

}
