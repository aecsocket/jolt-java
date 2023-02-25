package jolt.physics.body;

public sealed interface BodyLockBase permits BodyLockRead, BodyLockWrite {
    boolean succeeded();

    boolean succeededAndIsInBroadPhase();

    BodyImpl getBody();
}
