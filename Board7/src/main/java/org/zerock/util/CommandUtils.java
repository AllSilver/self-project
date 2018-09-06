package org.zerock.util;

import java.util.UUID;

public class CommandUtils {

		public static String getRandomString() {
			return UUID.randomUUID().toString().replaceAll("-", "");
			
		}
}
