package jolt;

import jolt.geometry.AABox;
import jolt.geometry.AABoxCast;
import jolt.geometry.OrientedBox;
import jolt.math.FMat44;
import jolt.math.FVec3;
import jolt.physics.collision.BroadPhaseCastResult;
import jolt.physics.collision.FRayCast;
import jolt.physics.collision.ObjectLayerFilter;
import jolt.physics.collision.broadphase.BroadPhaseLayerFilter;
import jolt.physics.collision.broadphase.CollideShapeBodyCollector;
import jolt.physics.collision.broadphase.RayCastBodyCollector;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static jolt.Utils.*;
import static org.junit.jupiter.api.Assertions.*;

final class TestBroadPhaseQuery extends PhysicsSystemTest {
    // TODO test the return values of these types, rather than just running them

    @BeforeAll
    static void beforeAll() {
        setUpAll();
    }

    @AfterAll
    static void afterAll() {
        tearDownAll();
    }

    @BeforeEach
    void setUp() {
        setUpMemory();
        setUpPhysics();
    }

    @AfterEach
    void afterEach() {
        tearDownPhysics();
        tearDownMemory();
    }

    @Test
    void castRay() {
        var results = new ArrayList<BroadPhaseCastResult>();
        physics.getBroadPhaseQuery().castRay(
                new FRayCast(FVec3.ZERO, new FVec3(1.0f, 0.0f, 0.0f)),
                RayCastBodyCollector.into(session, results),
                BroadPhaseLayerFilter.passthrough(),
                ObjectLayerFilter.passthrough()
        );
        assertEquals(0, results.size());
    }

    @Test
    void collideAABox() {
        var results = new ArrayList<Integer>();
        physics.getBroadPhaseQuery().collideAABox(
                AABox.UNIT,
                CollideShapeBodyCollector.into(session, results),
                BroadPhaseLayerFilter.passthrough(),
                ObjectLayerFilter.passthrough()
        );
        assertEquals(0, results.size());
    }

    @Test
    void collideSphere() {
        var results = new ArrayList<Integer>();
        physics.getBroadPhaseQuery().collideSphere(
                FVec3.ZERO,
                1.0f,
                CollideShapeBodyCollector.into(session, results),
                BroadPhaseLayerFilter.passthrough(),
                ObjectLayerFilter.passthrough()
        );
        assertEquals(0, results.size());
    }

    @Test
    void collidePoint() {
        var results = new ArrayList<Integer>();
        physics.getBroadPhaseQuery().collideSphere(
                FVec3.ZERO,
                1.0f,
                CollideShapeBodyCollector.into(session, results),
                BroadPhaseLayerFilter.passthrough(),
                ObjectLayerFilter.passthrough()
        );
        assertEquals(0, results.size());
    }

    @Test
    void collideOrientedBox() {
        var results = new ArrayList<Integer>();
        physics.getBroadPhaseQuery().collideOrientedBox(
                new OrientedBox(FMat44.IDENTITY, FVec3.ONE),
                CollideShapeBodyCollector.into(session, results),
                BroadPhaseLayerFilter.passthrough(),
                ObjectLayerFilter.passthrough()
        );
        assertEquals(0, results.size());
    }

    @Test
    void castAABox() {
        var results = new ArrayList<Integer>();
        physics.getBroadPhaseQuery().castAABox(
                new AABoxCast(AABox.UNIT, new FVec3(1.0f, 0.0f, 0.0f)),
                CollideShapeBodyCollector.into(session, results),
                BroadPhaseLayerFilter.passthrough(),
                ObjectLayerFilter.passthrough()
        );
        assertEquals(0, results.size());
    }
}
