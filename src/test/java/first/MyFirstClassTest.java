package first;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyFirstClassTest {

    MyFirstClass myFirstClass;

    @BeforeEach
    void setup(){
       myFirstClass = new MyFirstClass();
    }

    @Test
    @DisplayName("Checking a multiplying")
    void multiply() {
        assertEquals(20,myFirstClass.multiply(5,4));
    }

    @Test
    @DisplayName("Checking Exceptions")
    void testExceptionIsThrown(){
        assertThrows(IllegalArgumentException.class, () -> myFirstClass.multiply(1000,5));
    }

    @Test
    @DisplayName("Checking a work thanks to Asserts All")
    void testingAll(){
        assertAll(
                () -> assertEquals(20, myFirstClass.multiply(5,4),
                        "Must be 20"),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> myFirstClass.multiply(1000,5)));
    }
}