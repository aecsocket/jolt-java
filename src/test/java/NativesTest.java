import jolt.TestNatives;
import org.junit.jupiter.api.Test;

public class NativesTest {
    @Test
    public void testNatives() {
        var i = new TestNatives().addTen(5);
        System.out.println("val = " + i);
    }
}
