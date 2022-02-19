package services;

import model.Movie;
import model.Ring;
import model.TolkienCharacter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static model.Race.*;
import static org.junit.jupiter.api.Assertions.*;

class DataServiceTest {

    DataService dataService;

    @BeforeEach
    void setup(){
        dataService = new DataService();
    }

    @Test
    void ensureThatInitializationOfTolkienCharactersWork(){
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);
        assertAll ("Should be Frodo 33 years old",
                () -> assertEquals(33, frodo.age),
                () -> assertEquals("Frodo", frodo.getName()),
                () -> assertNotEquals("Frodon", frodo.getName())
        );
    }

    @Test
    void ensureThatEqualsIsWorksForCharacters(){
        Object jack = new TolkienCharacter("Jake", 43, HOBBIT);
        Object samejack = jack;
        Object jakeClone = new TolkienCharacter("Jake", 12, HOBBIT);
        assertAll("Jack should be Jack",
                () -> assertEquals(jack, samejack),
                () -> assertNotEquals(jack, jakeClone)
        );
    }

    @Test
    void checkInheritance(){
        TolkienCharacter tolkienCharacter =
                dataService.getFellowship().get(0);
        assertFalse(Movie.class.isAssignableFrom(tolkienCharacter.getClass()));
    }

    @Test
    void ensureFellowShipCharacterAccessByNameReturnsNullForUnknown(){
        TolkienCharacter tolkienCharacter = dataService.getFellowshipCharacter("Lars");
        assertNull(tolkienCharacter);
    }

    @Test
    void ensureFellowShipCharacterAccessByNameWorksGivenCorrectNameIsGiven(){
        TolkienCharacter tolkienCharacter = dataService.getFellowshipCharacter("Frodo");
        assertNotNull(tolkienCharacter);
    }

    @Test
    void ensureThatFrodoAndGandalfArePartOfTheFellowship(){
        List<TolkienCharacter> fellowship = dataService.getFellowship();
        TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);
        TolkienCharacter gandalf = new TolkienCharacter("Gandalf", 2020, MAIA);
        assertTrue(fellowship.contains(frodo));
        assertTrue(fellowship.contains(gandalf));
    }

    @RepeatedTest(3)
    void ensureThatOneRingBearerIsPatOfTheFellowship(){
        List<TolkienCharacter> fellowship = dataService.getFellowship();
        Map<Ring, TolkienCharacter> ringBearers = dataService.getRingBearers();
        //assertTrue(fellowship.contains(ringBearers.get(Ring.oneRing)));
        assertTrue(ringBearers.values().stream().anyMatch(fellowship::contains));
    }

    @Test
    void ensureOrdering(){
        List<TolkienCharacter> fellowship = dataService.getFellowship();
        assertAll(
                () -> assertEquals(dataService.getFellowshipCharacter("Frodo"),fellowship.get(0)),
        () -> assertEquals(dataService.getFellowshipCharacter("Sam"),fellowship.get(1)),
        () -> assertEquals(dataService.getFellowshipCharacter("Merry"),fellowship.get(2)),
        () -> assertEquals(dataService.getFellowshipCharacter("Pippin"),fellowship.get(3)),
        () -> assertEquals(dataService.getFellowshipCharacter("Gandalf"),fellowship.get(4)),
        () -> assertEquals(dataService.getFellowshipCharacter("Legolas"),fellowship.get(5)),
        () -> assertEquals(dataService.getFellowshipCharacter("Gimli"),fellowship.get(6)),
        () -> assertEquals(dataService.getFellowshipCharacter("Aragorn"),fellowship.get(7)),
        () -> assertEquals(dataService.getFellowshipCharacter("Boromir"),fellowship.get(8))
        );
    }

    @Test
    void ensureAge(){
        List<TolkienCharacter> fellowship = dataService.getFellowship();
//        assertFalse(fellowship.stream()
//                .filter(fellow -> (fellow.getRace().equals(HOBBIT)
//                        || fellow.getRace().equals(MAN)))
//                .anyMatch(ages -> ages.age >= 100));
        assertTrue(fellowship.stream()
                .filter(fellow -> (fellow.getRace().equals(HOBBIT)
                        || fellow.getRace().equals(MAN)))
                .allMatch(ages -> ages.age < 100));
//        assertFalse(fellowship.stream()
//                .filter(fellow -> fellow.getRace().equals(ELF)
//                        || fellow.getRace().equals(DWARF)
//                        || fellow.getRace().equals(MAIA))
//                .anyMatch(ages -> ages.age <= 100));
        assertTrue(fellowship.stream()
                .filter(fellow -> fellow.getRace().equals(ELF)
                        || fellow.getRace().equals(DWARF)
                        || fellow.getRace().equals(MAIA))
                .allMatch(ages -> ages.age >= 100));
    }

    @Test
    void ensureThatFellowsStayASmallGroup(){
        List<TolkienCharacter> fellowship = dataService.getFellowship();
        assertThrows(IndexOutOfBoundsException.class, () -> fellowship.get(20));
    }

    @Test
    void ensureThatTestDoesNotRunMoreThenFewSeconds(){
        assertTimeout(Duration.ofSeconds(3),() -> dataService.update());
    }





}