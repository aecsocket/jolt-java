<div align="center">

# JoltJni
[![License](https://img.shields.io/github/license/aecsocket/jolt-jni)](LICENSE)
[![CI](https://img.shields.io/github/actions/workflow/status/aecsocket/jolt-jni/build.yml)](https://github.com/aecsocket/jolt-jni/actions/workflows/build.yml)
![Release](https://img.shields.io/maven-central/v/io.github.aecsocket/jolt-jni?label=release)
![Snapshot](https://img.shields.io/nexus/s/io.github.aecsocket/jolt-jni?label=snapshot&server=https%3A%2F%2Fs01.oss.sonatype.org)

Java bindings for [JoltPhysics](https://github.com/jrouwe/JoltPhysics)

</div>

These bindings are still feature-incomplete and unstable.

## Coverage

Features:
- [ ] Geometry types
  - [x] Primitives
  - [ ] Meshes
- [x] Rigid bodies
- [ ] Joints
- [ ] Vehicles
- [ ] Characters

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
  runtimeOnly("io.github.aecsocket", "jolt-jni-natives-linux", "VERSION")
  runtimeOnly("io.github.aecsocket", "jolt-jni-natives-windows", "VERSION")
  runtimeOnly("io.github.aecsocket", "jolt-jni-natives-macos", "VERSION")
  runtimeOnly("io.github.aecsocket", "jolt-jni-natives-macos-arm64", "VERSION")
}
```

Usage is very similar to JoltPhysics. See [HelloJolt.java](jolt-jni-test/src/test/java/jolt/HelloJolt.java) to get a
minimal implementation.

### Entry point

Use `jolt.JoltEnvironment.load()` to load the native libraries from the jar (in `jolt/`), or load them yourself using
`System.load`. Afterwards, run some Jolt setup:

```java
JoltEnvironment.registerDefaultAllocator();
RTTIFactory.setInstance(new RTTIFactory());
JoltEnvironment.registerTypes();
```

## Building from source

```sh
git clone https://github.com/aecsocket/jolt-jni
cd jolt-jni
git submodule update --init
./gradlew build
```
