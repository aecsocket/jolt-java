JoltJNI
===

[JoltPhysics](https://github.com/jrouwe/JoltPhysics) bindings for Java using JNI

## Features

### Coverage

- [ ] Geometry types
  - [x] Primitives
  - [ ] Meshes
- [x] Rigid bodies
- [ ] Joints
- [ ] Vehicles
- [ ] Characters

### Variants

Platforms:
- Linux (x86_64)
- Windows (x86_64)
- MacOS (x86_64, arm64)

Build types (change with Gradle flag `-PbuildType=` or property `buildType`):
- `debug`
- `release`
- `distribution`

Flavors (change with Gradle flag `-Pflavor=` or property `flavor`):
- `sp` (single-precision floating point)
- `dp` (double-precision floating point)

## Usage

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.aecsocket", "jolt-jni", "VERSION")
    // implementation("io.github.aecsocket", "jolt-jni-kotlin", "VERSION")
    runtimeOnly("io.github.aecsocket", "jolt-jni", "VERSION", classifier = "natives-linux")
    runtimeOnly("io.github.aecsocket", "jolt-jni", "VERSION", classifier = "natives-windows")
    runtimeOnly("io.github.aecsocket", "jolt-jni", "VERSION", classifier = "natives-macos")
    runtimeOnly("io.github.aecsocket", "jolt-jni", "VERSION", classifier = "natives-macos-arm64")
}
```

See the example [HelloJolt.java](src/test/java/jolt/HelloJolt.java) file for a minimal working example.

## Build

```sh
git clone https://github.com/aecsocket/jolt-jni
cd jolt-jni
git submodule update --init
# You must run `generateNatives` to build the Jolt native library
# After changing any build settings like buildType, you must rerun generateNatives
# You must use the same build settings in `assemble` task as you generated the natives with
./gradlew -PbuildType=debug -Pflavor=sp generateNatives build
```
