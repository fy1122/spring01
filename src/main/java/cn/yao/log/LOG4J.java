package cn.yao.log;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

public class LOG4J {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("log4j");
        logger.info("log4J");
    }
}

