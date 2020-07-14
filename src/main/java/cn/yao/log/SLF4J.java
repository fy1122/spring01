package cn.yao.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SLF4J {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger("slf4j");
        logger.info("slf4j");
    }
}
