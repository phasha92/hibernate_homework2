package org.example.handler;

import java.util.HashMap;
import java.util.Map;


public class HandlerRegistry {

    private final Map<String, Handler> registry = new HashMap<>();

    public Handler getHandler(String command) {
        return registry.get(command);
    }

    public void register(String command, Handler handler) {
        registry.put(command, handler);
    }
}
