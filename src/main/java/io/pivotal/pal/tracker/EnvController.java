package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvController {

    String PORT;
    String MEMORY_LIMIT;
    String CF_INSTANCE_INDEX;
    String CF_INSTANCE_ADDR;

    public EnvController(@Value("${server.port:8080}") final String PORT,
                         @Value("${memory:NOT SET}") final String MEMORY_LIMIT,
                         @Value("${cf.instance.index:NOT SET}") final String CF_INSTANCE_INDEX,
                         @Value("${cf.instance.addr:NOT SET}") final String CF_INSTANCE_ADDR) {
        this.PORT = PORT;
        this.CF_INSTANCE_ADDR = CF_INSTANCE_ADDR;
        this.CF_INSTANCE_INDEX = CF_INSTANCE_INDEX;
        this.MEMORY_LIMIT = MEMORY_LIMIT;
    }

    @GetMapping("/env")
    public Map<String, String> getEnv() {
        Map<String, String> propertyMap = new HashMap<>();
        propertyMap.put("PORT" , this.PORT);
        propertyMap.put("CF_INSTANCE_INDEX",this.CF_INSTANCE_INDEX);
        propertyMap.put("MEMORY_LIMIT" , this.MEMORY_LIMIT);
        propertyMap.put("CF_INSTANCE_ADDR" , this.CF_INSTANCE_ADDR);
        return propertyMap;
    }
}
