package jolt;

// doesn't implement AutoCloseable because it would cause a lot of static analysis warnings
// alternative: Jolt.use
public interface Deletable {
    boolean isDeleted();

    void delete();
}
