package com.eyeball.utils.test;

<<<<<<< HEAD
import java.io.File;
import java.io.IOException;

import com.eyeball.utils.io.FileTextList;
import com.eyeball.utils.logging.Logger;
=======
>>>>>>> 96edfa4cb1cddbd0710eedf27589a184de3b8888
import com.eyeball.utils.logging.MultiLogger;
import com.eyeball.utils.reflect.ReflectionHelper;

public class Main {

	private static MultiLogger LOGGER;

	public static void main(String[] args) throws NoSuchFieldException,
			SecurityException, ClassNotFoundException,
			IllegalArgumentException, IllegalAccessException,
<<<<<<< HEAD
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

=======
			NullPointerException {
		// How to print? Everything is private.
		// Use ReflectionHelper!
		LOGGER = ReflectionHelper.getField(References.class, /* static */null, "LOGGER");
		LOGGER.info("Hi?");
		LOGGER.info("Yes! this worked!");
>>>>>>> 96edfa4cb1cddbd0710eedf27589a184de3b8888
	}

}
