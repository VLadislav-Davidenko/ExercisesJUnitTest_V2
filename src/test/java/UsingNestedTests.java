import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UsingNestedTests {
    private List<String> list;

    @BeforeEach
    void setup(){
        list = Arrays.asList("JUnit 5", "Mockito");
    }

    @Test
    void listTests(){
        assertEquals(2, list.size());
    }
    @Nested
    @DisplayName("Grouped tests for checking members")
    class CheckElements{
        @Test
        void checkFirstElement(){
            assertEquals("JUnit 5", list.get(0));
        }
        @Test
        void checkSecondElement(){
            assertEquals("Mockito", list.get(1));
        }
    }
}
