package org.agm.sbtest.api.ping;

import lombok.Getter;

@Getter
public class PingResult {

    private final boolean running = true;
    private final String message = "Spring Boot test application running";

}