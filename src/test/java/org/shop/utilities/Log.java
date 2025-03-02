package org.shop.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    public static Logger logger = LogManager.getLogger(Log.class);

    public static void info(String message) {
        logger.info(message);
    }

    public static void error(String message) {
        logger.info(message);
    }
}
