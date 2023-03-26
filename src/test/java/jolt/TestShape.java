package jolt;

import jolt.math.FVec3;
import jolt.physics.collision.shape.*;
import org.junit.jupiter.api.*;

import static jolt.Utils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

final class TestShape extends MemoriedTest {
    @BeforeAll
    static void beforeAll() {
        setUpAll();
    }

    @AfterAll
    static void afterAll() {
        tearDownAll();
    }

    @BeforeEach
    void beforeEach() {
        setUpMemory();
    }

    @AfterEach
    void afterEach() {
        tearDownMemory();
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
    void sphere() {
        Jolt.use(SphereShape.of(F1), obj -> {
            assertEquals(F1, obj.getRadius());

            convex(obj);
        });
    }

    // convex radii must be smaller than some other values, so we can't test them with random values
    @Test
    void box() {
        Jolt.use(BoxShape.of(FVEC3_1, 0.0f), obj -> {
            FVec3 halfExtent = FVec3.of(arena);
            obj.getHalfExtent(halfExtent);
            assertEqualValue(FVEC3_1, halfExtent);

            convex(obj);
        });
    }

    @Test
    void triangle() {
        Jolt.use(TriangleShape.of(FVEC3_1, FVEC3_2, FVEC3_3, 0.0f), obj -> {
            convex(obj);
        });
    }

    @Test
    void capsule() {
        Jolt.use(CapsuleShape.of(F1, F2), obj -> {
            assertEquals(F1, obj.getHalfHeight());
            assertEquals(F2, obj.getRadius());

            convex(obj);
        });
    }

    /* nothing to test
    @Test
    void taperedCylinder() {}
     */

    @Test
    void cylinder() {
        Jolt.use(CylinderShape.of(F1, F2, 0.0f), obj -> {
            assertEquals(F1, obj.getHalfHeight());
            assertEquals(F2, obj.getRadius());

            convex(obj);
        });
    }

    /* nothing to test
    @Test
    void convexHull() {
    }
     */
}
