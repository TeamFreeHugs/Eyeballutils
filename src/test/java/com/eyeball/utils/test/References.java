package com.eyeball.utils.test;

import com.eyeball.utils.logging.Logger;
import com.eyeball.utils.logging.MultiLogger;

public class References {

	// All private? Must be a mistake in the code.
	private static final Logger MAIN = new Logger("MainLogger");
	private static final Logger OTHER = new Logger("Misc");
	@SuppressWarnings("unused")
	private static final MultiLogger LOGGER = new MultiLogger(MAIN, OTHER);

}
