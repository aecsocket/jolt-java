package jolt;

import org.junit.jupiter.api.Test;

public final class HelloJolt {
    @Test
    public void helloJolt() {
        Jolt.load();

        Jolt.registerDefaultAllocator();
        Jolt.createFactory();
        Jolt.registerTypes();
    }
}
