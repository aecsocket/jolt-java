package jolt.physics.collision;

import jolt.JoltNative;
import jolt.jni.JniInclude;

@JniInclude("<Jolt/Physics/Collision/ObjectLayer.h>")
public class ObjectLayerPairFilter extends JoltNative {
    private ObjectLayerPairFilter(long address) { super(address); }
    public static ObjectLayerPairFilter ref(long address) { return address == 0 ? null : new ObjectLayerPairFilter(address); }

    public ObjectLayerPairFilter() { address = _ctor(); }
    private native long _ctor();

    public boolean shouldCollide(int layer1, int layer2) { throw unimplemented(); }
    private boolean _shouldCollide(int layer1, int layer2) { return shouldCollide(layer1, layer2); }
}
