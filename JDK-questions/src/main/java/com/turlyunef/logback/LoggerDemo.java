package com.turlyunef.logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Класс для экспериментов с настройкой логгера через файл logback.xml в resources
 */
public class LoggerDemo {
    private static final Logger logger = LoggerFactory.getLogger(LoggerDemo.class);

    public static void main(String[] args) {
        // Что из это будет логироваться и сколько раз в зависимости от настроек в logback.xml?
        logger.trace("TEST TRACE LEVEL");
        logger.debug("TEST DEBUG LEVEL");
        logger.info("TEST INFO LEVEL");
        logger.warn("TEST WARN LEVEL");
        logger.error("TEST ERROR LEVEL");
    }
}
