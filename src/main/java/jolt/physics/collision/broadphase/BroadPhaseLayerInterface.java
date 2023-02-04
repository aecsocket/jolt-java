package jolt.physics.collision.broadphase;

import jolt.JoltNative;
import jolt.jni.JniInclude;

@JniInclude("<Jolt/Physics/Collision/BroadPhase/BroadPhaseLayer.h>")
public class BroadPhaseLayerInterface extends JoltNative {
    private BroadPhaseLayerInterface(long address) { super(address); }
    public static BroadPhaseLayerInterface ref(long address) { return address == 0 ? null : new BroadPhaseLayerInterface(address); }

    public BroadPhaseLayerInterface() { address = _ctor(); }
    private native long _ctor();

    public int getNumBroadPhaseLayers() { throw unimplemented(); }
    private int _getNumBroadPhaseLayers() { return getNumBroadPhaseLayers(); }

    public BroadPhaseLayer getBroadPhaseLayer(int layer) { throw unimplemented(); }
    private long _getBroadPhaseLayer(int layer) { return getBroadPhaseLayer(layer).getAddress(); }

    public String getBroadPhaseLayerName(BroadPhaseLayer layer) { throw unimplemented(); }
    private String _getBroadPhaseLayerName(long layer) { return getBroadPhaseLayerName(BroadPhaseLayer.ref(layer)); }
}
