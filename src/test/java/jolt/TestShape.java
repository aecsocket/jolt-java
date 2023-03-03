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

    @Test
    void box() {
        Jolt.use(BoxShape.of(FVEC3_1, F1), obj -> {
            FVec3 halfExtent = FVec3.of(session);
            obj.getHalfExtent(halfExtent);
            assertEqualValue(FVEC3_1, halfExtent);

            convex(obj);
        });
    }

    @Test
    void triangle() {
        Jolt.use(TriangleShape.of(FVEC3_1, FVEC3_2, FVEC3_3, F1), obj -> {
            assertEquals(F1, obj.getConvexRadius());

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
        Jolt.use(CylinderShape.of(F1, F2, F3), obj -> {
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
