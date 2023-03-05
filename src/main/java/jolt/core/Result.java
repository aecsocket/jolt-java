package jolt.core;

import jolt.SegmentedJoltNative;

import javax.annotation.Nullable;
import java.lang.foreign.MemorySegment;

public abstract class Result<T> extends SegmentedJoltNative {
    //region Jolt-Value-Protected
    protected Result(MemorySegment handle) {
        super(handle);
    }
    //endregion Jolt-Value-Protected

    public abstract boolean hasError();

    public abstract @Nullable T or();

    public T orThrow() {
        var result = or();
        if (result == null) throwError();
        return result;
    }

    public abstract String getError();

    public void throwError() {
        throw new RuntimeException(getError());
    }
}
