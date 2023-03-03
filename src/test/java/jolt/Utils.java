package jolt;

import jolt.math.DMat44;
import jolt.math.DVec3;
import jolt.math.FMat44;
import jolt.math.FVec3;

import java.lang.foreign.MemorySession;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

final class Utils {
    private Utils() {}

    // arbitrary constants for testing, we don't care about their actual values
    static final Random RANDOM = ThreadLocalRandom.current();

    static final float F1 = RANDOM.nextFloat();
    static final float F2 = RANDOM.nextFloat();
    static final float F3 = RANDOM.nextFloat();
    static final float F4 = RANDOM.nextFloat();
    static final float F5 = RANDOM.nextFloat();
    static final float F6 = RANDOM.nextFloat();
    static final float F7 = RANDOM.nextFloat();
    static final float F8 = RANDOM.nextFloat();

    static final long J1 = RANDOM.nextLong();
    static final long J2 = RANDOM.nextLong();

    static final FVec3 FVEC3_1 = FVec3.of(MemorySession.global(), RANDOM.nextFloat(), RANDOM.nextFloat(), RANDOM.nextFloat());
    static final FVec3 FVEC3_2 = FVec3.of(MemorySession.global(), RANDOM.nextFloat(), RANDOM.nextFloat(), RANDOM.nextFloat());
    static final FVec3 FVEC3_3 = FVec3.of(MemorySession.global(), RANDOM.nextFloat(), RANDOM.nextFloat(), RANDOM.nextFloat());
    static final FVec3 FVEC3_4 = FVec3.of(MemorySession.global(), RANDOM.nextFloat(), RANDOM.nextFloat(), RANDOM.nextFloat());
    static final FVec3 FVEC3_5 = FVec3.of(MemorySession.global(), RANDOM.nextFloat(), RANDOM.nextFloat(), RANDOM.nextFloat());
    static final FVec3 FVEC3_6 = FVec3.of(MemorySession.global(), RANDOM.nextFloat(), RANDOM.nextFloat(), RANDOM.nextFloat());
    static final FVec3 FVEC3_7 = FVec3.of(MemorySession.global(), RANDOM.nextFloat(), RANDOM.nextFloat(), RANDOM.nextFloat());
    static final FVec3 FVEC3_8 = FVec3.of(MemorySession.global(), RANDOM.nextFloat(), RANDOM.nextFloat(), RANDOM.nextFloat());

    static void setUpAll() {
        Jolt.load();

        Jolt.registerDefaultAllocator();
        Jolt.createFactory();
        Jolt.registerTypes();
    }

    static void tearDownAll() {
        Jolt.destroyFactory();
    }

    static void assertEqualValue(FVec3 expected, FVec3 actual) {
        assertTrue(expected.equalValue(actual));
    }

    static void assertEqualValue(DVec3 expected, DVec3 actual) {
        assertTrue(expected.equalValue(actual));
    }

    static void assertEqualValue(FMat44 expected, FMat44 actual) {
        assertTrue(expected.equalValue(actual));
    }

    static void assertEqualValue(DMat44 expected, DMat44 actual) {
        assertTrue(expected.equalValue(actual));
    }
}
