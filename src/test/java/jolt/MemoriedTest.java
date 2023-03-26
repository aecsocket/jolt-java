package jolt;

import java.lang.foreign.MemorySession;

public class MemoriedTest {
    protected MemorySession arena;

    protected void setUpMemory() {
        arena = MemorySession.openConfined();
    }

    protected void tearDownMemory() {
        arena.close();
    }
}
