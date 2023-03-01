package jolt;

import jolt.physics.collision.shape.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static jolt.Utils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public final class TestShape {
    @BeforeAll
    public static void setUp() {
        Jolt.load();

        Jolt.registerDefaultAllocator();
        Jolt.createFactory();
        Jolt.registerTypes();
    }

    private void shape(Shape obj) {
        obj.setUserData(J1);
        assertEquals(J1, obj.getUserData());
    }

    private void convex(ConvexShape obj) {
        obj.setDensity(F8);
        assertEquals(F8, obj.getDensity());

        shape(obj);
    }

    @Test
    public void sphere() {
        Jolt.use(SphereShape.create(F1), obj -> {
            assertEquals(F1, obj.getRadius());

            convex(obj);
        });
    }

    @Test
    public void box() {
        Jolt.use(BoxShape.create(FVEC3_1, F1), obj -> {
            assertEquals(FVEC3_1, obj.getHalfExtent());

            convex(obj);
        });
    }

    @Test
    public void triangle() {
        Jolt.use(TriangleShape.create(FVEC3_1, FVEC3_2, FVEC3_3, F1), obj -> {
            assertEquals(F1, obj.getConvexRadius());

            convex(obj);
        });
    }

    @Test
    public void capsule() {
        Jolt.use(CapsuleShape.create(F1, F2), obj -> {
            assertEquals(F1, obj.getHalfHeight());
            assertEquals(F2, obj.getRadius());

            convex(obj);
        });
    }

    /* nothing to test
    @Test
    public void taperedCylinder() {}
     */

    @Test
    public void cylinder() {
        Jolt.use(CylinderShape.create(F1, F2, F3), obj -> {
            assertEquals(F1, obj.getHalfHeight());
            assertEquals(F2, obj.getRadius());

            convex(obj);
        });
    }

    /* nothing to test
    @Test
    public void convexHull() {
    }
     */
}
