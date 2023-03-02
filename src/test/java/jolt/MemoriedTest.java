package jolt;

import java.lang.foreign.MemorySession;

public class MemoriedTest {
    protected MemorySession session;

    protected void setUpMemory() {
        session = MemorySession.openConfined();
    }

    protected void tearDownMemory() {
        session.close();
    }
}
