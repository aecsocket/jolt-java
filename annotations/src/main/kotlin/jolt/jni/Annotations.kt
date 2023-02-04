package jolt.jni

import java.lang.annotation.Inherited

object IncludePriority {
    const val NORMAL = 0
    const val EARLY = -100
    const val EARLIEST = -1000
    const val LATE = 100
    const val LATEST = 1000
}

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
@Inherited
annotation class JniNative(val value: String)

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class JniInclude(val value: String, val priority: Int = IncludePriority.NORMAL)

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class JniHeader(val value: String)

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.SOURCE)
annotation class JniType(val value: String)


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class JniBind(val value: String)

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class JniSelfBind(val value: String)

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.SOURCE)
annotation class JniCallback
