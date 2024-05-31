package com.juliaosystem.utils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Log4j2
@AllArgsConstructor
@NoArgsConstructor
@Component
public  class AbtractError extends  Exception  {
    protected transient  Logger logger = LogManager.getLogger(getClass());

    public void logError(Throwable throwable ) {
        logger.error("Error occurred in %%s:%s--%s".formatted(getClass().getName(), getLocalizedMessage()), getClass().getName() + getLocalizedMessage() , throwable);
    }

    public void logInfo(String message) {
        logger.info(message, getClass().getName());
    }

}
