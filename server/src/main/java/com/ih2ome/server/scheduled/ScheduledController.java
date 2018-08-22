package com.ih2ome.server.scheduled;

import ch.qos.logback.classic.spi.LoggerRemoteView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Sky
 * create 2018/08/21
 * email sky.li@ixiaoshuidi.com
 **/
@Component
@EnableScheduling
public class ScheduledController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScheduledController.class);

}
