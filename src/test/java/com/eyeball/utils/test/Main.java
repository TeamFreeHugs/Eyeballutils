package com.eyeball.utils.test;

import com.eyeball.utils.logging.MultiLogger;
import com.eyeball.utils.reflect.ReflectionHelper;

public class Main {

	private static MultiLogger LOGGER;

	public static void main(String[] args) throws NoSuchFieldException,
			SecurityException, ClassNotFoundException,
			IllegalArgumentException, IllegalAccessException,
			NullPointerException {
		// How to print? Everything is private.
		// Use ReflectionHelper!
		LOGGER = ReflectionHelper.getField(References.class, /* static */null, "LOGGER");
		LOGGER.info("Hi?");
		LOGGER.info("Yes! this worked!");
	}

}
