package jolt.jni;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
@Inherited
public @interface JniNative {
    String value();
}
