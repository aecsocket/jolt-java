package jolt;

import jolt.math.FVec3;
import jolt.physics.collision.shape.*;
import org.junit.jupiter.api.*;

import static jolt.Utils.*;
import static org.junit.jupiter.api.Assertions.*;

final class TestShapeSettings extends MemoriedTest {
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

    private void shape(ShapeSettings obj) {
        obj.setUserData(J1);
        assertEquals(J1, obj.getUserData());
    }

    private void convex(ConvexShapeSettings obj) {
        obj.setDensity(F8);
        assertEquals(F8, obj.getDensity());

        shape(obj);
    }

    @Test
    void sphere() {
        Jolt.use(SphereShapeSettings.of(F1), obj -> {
            assertEquals(F1, obj.getRadius());

            obj.setRadius(F2);
            assertEquals(F2, obj.getRadius());

            convex(obj);
        });
    }

    @Test
    void box() {
        Jolt.use(BoxShapeSettings.of(FVEC3_1, F1), obj -> {
            FVec3 halfExtent = FVec3.of(session);
            obj.getHalfExtent(halfExtent);
            assertEqualValue(FVEC3_1, halfExtent);
            assertEquals(F1, obj.getConvexRadius());

            obj.setHalfExtent(FVEC3_2);
            obj.getHalfExtent(halfExtent);
            assertEqualValue(FVEC3_2, halfExtent);
            obj.setConvexRadius(F2);
            assertEquals(F2, obj.getConvexRadius());

            convex(obj);
        });
    }

    @Test
    void triangle() {
        Jolt.use(TriangleShapeSettings.of(FVEC3_1, FVEC3_2, FVEC3_3, F1), obj -> {
            FVec3 v1 = FVec3.of(session);
            FVec3 v2 = FVec3.of(session);
            FVec3 v3 = FVec3.of(session);
            obj.getVertices(v1, v2, v3);
            assertEqualValue(FVEC3_1, v1);
            assertEqualValue(FVEC3_2, v2);
            assertEqualValue(FVEC3_3, v3);

            obj.setVertices(FVEC3_4, FVEC3_5, FVEC3_6);
            obj.getVertices(v1, v2, v3);
            assertEqualValue(FVEC3_4, v1);
            assertEqualValue(FVEC3_5, v2);
            assertEqualValue(FVEC3_6, v3);
            obj.setConvexRadius(F2);
            assertEquals(F2, obj.getConvexRadius());

            convex(obj);
        });
    }

    @Test
    void capsule() {
        Jolt.use(CapsuleShapeSettings.of(F1, F2), obj -> {
            assertEquals(F1, obj.getHalfHeight());
            assertEquals(F2, obj.getRadius());

            obj.setHalfHeight(F3);
            assertEquals(F3, obj.getHalfHeight());
            obj.setRadius(F4);
            assertEquals(F4, obj.getRadius());

            convex(obj);
        });
    }

    @Test
    void taperedCapsule() {
        Jolt.use(TaperedCapsuleShapeSettings.of(F1, F2, F3), obj -> {
            assertEquals(F1, obj.getHalfHeight());
            assertEquals(F2, obj.getTopRadius());
            assertEquals(F3, obj.getBottomRadius());

            obj.setHalfHeight(F4);
            assertEquals(F4, obj.getHalfHeight());
            obj.setTopRadius(F5);
            assertEquals(F5, obj.getTopRadius());
            obj.setBottomRadius(F6);
            assertEquals(F6, obj.getBottomRadius());

            convex(obj);
        });
    }

    @Test
    void cylinder() {
        Jolt.use(CylinderShapeSettings.of(F1, F2, F3), obj -> {
            assertEquals(F1, obj.getHalfHeight());
            assertEquals(F2, obj.getRadius());
            assertEquals(F3, obj.getConvexRadius());

            obj.setHalfHeight(F4);
            assertEquals(F4, obj.getHalfHeight());
            obj.setRadius(F5);
            assertEquals(F5, obj.getRadius());
            obj.setConvexRadius(F6);
            assertEquals(F6, obj.getConvexRadius());

            convex(obj);
        });
    }

    @Test
    void convexHull() {
        Jolt.use(ConvexHullShapeSettings.of(new FVec3[] { FVEC3_1, FVEC3_2, FVEC3_3, FVEC3_4 }, F1), obj -> {
            // TODO test point access
            assertEquals(F1, obj.getMaxConvexRadius());

            obj.setMaxConvexRadius(F2);
            assertEquals(F2, obj.getMaxConvexRadius());
            obj.setMaxErrorConvexRadius(F3);
            assertEquals(F3, obj.getMaxErrorConvexRadius());
            obj.setHullTolerance(F4);
            assertEquals(F4, obj.getHullTolerance());
        });
    }
}
