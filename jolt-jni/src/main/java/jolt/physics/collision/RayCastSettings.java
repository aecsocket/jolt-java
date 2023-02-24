package jolt.physics.collision;

public record RayCastSettings(
        BackFaceMode backFaceMode,
        boolean treatConvexAsSolid
) {
    public RayCastSettings() {
        this(BackFaceMode.IGNORE_BACK_FACES, true);
    }
}
