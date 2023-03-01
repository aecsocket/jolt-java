package jolt;

import jolt.math.FVec3;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

final class Utils {
    private Utils() {}

    // arbitrary constants for testing, we don't care about their actual values
    static final Random RANDOM = ThreadLocalRandom.current();
    static final int RANDOM_POOL_SIZE = 8;

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

    static final FVec3 FVEC3_1 = new FVec3(RANDOM.nextFloat(), RANDOM.nextFloat(), RANDOM.nextFloat());
    static final FVec3 FVEC3_2 = new FVec3(RANDOM.nextFloat(), RANDOM.nextFloat(), RANDOM.nextFloat());
    static final FVec3 FVEC3_3 = new FVec3(RANDOM.nextFloat(), RANDOM.nextFloat(), RANDOM.nextFloat());
    static final FVec3 FVEC3_4 = new FVec3(RANDOM.nextFloat(), RANDOM.nextFloat(), RANDOM.nextFloat());
    static final FVec3 FVEC3_5 = new FVec3(RANDOM.nextFloat(), RANDOM.nextFloat(), RANDOM.nextFloat());
    static final FVec3 FVEC3_6 = new FVec3(RANDOM.nextFloat(), RANDOM.nextFloat(), RANDOM.nextFloat());
    static final FVec3 FVEC3_7 = new FVec3(RANDOM.nextFloat(), RANDOM.nextFloat(), RANDOM.nextFloat());
    static final FVec3 FVEC3_8 = new FVec3(RANDOM.nextFloat(), RANDOM.nextFloat(), RANDOM.nextFloat());
}
