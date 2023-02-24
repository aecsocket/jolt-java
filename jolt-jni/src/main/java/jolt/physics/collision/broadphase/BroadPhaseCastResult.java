package jolt.physics.collision.broadphase;

public record BroadPhaseCastResult(
        int bodyId,
        float fraction
) {}
