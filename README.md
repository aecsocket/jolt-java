<div align="center">

# JoltJava
[![License](https://img.shields.io/github/license/aecsocket/jolt-java)](LICENSE)
[![CI](https://img.shields.io/github/actions/workflow/status/aecsocket/jolt-java/build.yml)](https://github.com/aecsocket/jolt-java/actions/workflows/build.yml)
![Release](https://img.shields.io/maven-central/v/io.github.aecsocket/jolt-java?label=release)
![Snapshot](https://img.shields.io/nexus/s/io.github.aecsocket/jolt-java?label=snapshot&server=https%3A%2F%2Fs01.oss.sonatype.org)

Java bindings for [JoltPhysics](https://github.com/jrouwe/JoltPhysics) with C bindings based on
[zig-gamedev JoltPhysicsC](https://github.com/michal-z/zig-gamedev/tree/main/libs/zphysics/libs)

</div>

These bindings are still feature-incomplete and unstable.

## Coverage

Features:
- [ ] Rigid bodies
  - [x] Body interface
  - [x] Body locking
  - [ ] Motion properties
- [x] Shapes
  - [x] Convex
  - [x] Compound
  - [x] Decorator
  - [x] Mesh/height field
- [x] Queries
  - [x] Broad phase
  - [x] Narrow phase
- [x] Listeners
  - [x] Step
  - [x] Contact
- [x] Double precision
- [ ] Constraints
- [ ] Vehicles
- [ ] Characters

Platforms:
- [x] Linux (x86_64)
- [ ] Windows (x86_64)
- [ ] MacOS (x86_64)

Build types (change with Gradle flag `-PbuildType=` or property `buildType`):
- `debug`
- `release`
- `distribution` (default)

Flavors (change with Gradle flag `-PbuildFlavor=` or property `buildFlavor`):
- `sp` (single-precision floating point, default)
- `dp` (double-precision floating point)

## Usage

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.aecsocket", "jolt-java", "VERSION")
    runtimeOnly("io.github.aecsocket", "jolt-java-natives-linux-x86", "VERSION")
    runtimeOnly("io.github.aecsocket", "jolt-java-natives-windows-x86", "VERSION")
    runtimeOnly("io.github.aecsocket", "jolt-java-natives-macos-x86", "VERSION")
}
```

Usage is very similar to JoltPhysics. See [HelloJolt.java](src/test/java/jolt/HelloJolt.java) to get a
minimal implementation.

**Note:** these bindings are fairly low-level. For some classes such as the vector and matrix classes, you are expected
to write your own wrappers around them.

### Setup

```java
// Load the native libraries from the jar (in `jolt/`)
Jolt.load();
// Or load them manually
// System.load("name-of-lib-file");

// Do some Jolt setup
Jolt.registerDefaultAllocator();
Jolt.createFactory();
Jolt.registerTypes();
```

## Building from source

On Windows, you need [Ninja](https://ninja-build.org/manual.html) installed.

```sh
git clone https://github.com/aecsocket/jolt-java
cd jolt-java
./gradlew build
```
