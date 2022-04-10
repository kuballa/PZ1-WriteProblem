package writerReaderProblem;

import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WriterPreferenceTest {

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("TEST 1 - checking if the number of readers is increasing when envoked")
    void testAcquireRead() {
        List<Integer> numbers = List.of(2,3,4,6);
        WriterPreference wp = new WriterPreference();
        List<Integer> check = new ArrayList<>();

        //this lines above adding readers to and adding it to list
        for(int i = 1; i < 8; i++){
            wp.acquireRead();
            if(i == 2 || i == 3 || i == 4 || i == 6){
                check.add(wp.readActiveReaders());
                System.out.println(wp.readActiveReaders());
            }
        }
        Assertions.assertAll(   () -> assertEquals(numbers.get(0),check.get(0)),
                () -> assertEquals(numbers.get(1),check.get(1)),
                () -> assertEquals(numbers.get(2),check.get(2)),
                () -> assertEquals(numbers.get(3),check.get(3)));
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("TEST 2 - testing if the numbers of readers changing corectly when acquiring and releasing")
    void testReleaseRead() {
        List<Integer> numbers = List.of(2,3,5,7);
        WriterPreference wp = new WriterPreference();
        List<Integer> check = new ArrayList<>();

        //this lines above adding readers to and adding it to list
        for(int i = 1; i < 8; i++){
            wp.acquireRead();
            if(i == 2 || i == 3 || i == 5 || i == 7){
                check.add(wp.readActiveReaders());
                System.out.println(wp.readActiveReaders());
            }
        }
        Assertions.assertAll(   () -> assertEquals(numbers.get(0),check.get(0)),
                                () -> assertEquals(numbers.get(1),check.get(1)),
                                () -> assertEquals(numbers.get(2),check.get(2)),
                                () -> assertEquals(numbers.get(3),check.get(3)));

        // this lines above decrementing the readers and adding them to the list
        for(int i = 7; i > 0; i--){
            if(i == 2 || i == 3 || i == 5 || i == 7){
                check.add(wp.readActiveReaders());
                System.out.println(wp.readActiveReaders());
            }
            wp.releaseRead();
        }
        Assertions.assertAll(   () -> assertEquals(numbers.get(3),check.get(4)),
                                () -> assertEquals(numbers.get(2),check.get(5)),
                                () -> assertEquals(numbers.get(1),check.get(6)),
                                () -> assertEquals(numbers.get(0),check.get(7)));
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("TEST 3 - checking if status of the writer is changing to true when acquired")
    void testAcquireWrite() {
        WriterPreference wp = new WriterPreference();
        System.out.println(wp.readActiveWriter());
        wp.acquireWrite();
        assertTrue(wp.readActiveWriter());
        System.out.println(wp.readActiveWriter());
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("TEST 4 - checking if status of the writer is changing to false when released when function was envoked")
    void testReleaseWrite() {
        WriterPreference wp = new WriterPreference();
        wp.acquireWrite();
        assertTrue(wp.readActiveWriter());
        System.out.println(wp.readActiveWriter());
        wp.releaseWrite();
        assertFalse(wp.readActiveWriter());
        System.out.println(wp.readActiveWriter());
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("TEST 5 - Testing if read int works properly")
    void testReadInt() {
        WriterPreference wp = new WriterPreference();
        for(int i = 1; i < 10; i++){
            wp.incrementInt();
            assertEquals(i,wp.readInt());
            System.out.println(wp.readInt());
        }
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("TEST 6 - Just a basic incrementation of value")
    void testIncrementInt() {
        List<Integer> numbers = List.of(2,3,4,9);
        WriterPreference wp = new WriterPreference();
        List<Integer> check = new ArrayList<>();
        for(int i = 1; i < 10; i++){
            wp.incrementInt();
            if(i == 2 || i == 3 || i == 4 || i == 9){
                check.add(wp.readInt());
            }
        }
        Assertions.assertAll(   () -> assertEquals(numbers.get(0),check.get(0)),
                                () -> assertEquals(numbers.get(1),check.get(1)),
                                () -> assertEquals(numbers.get(2),check.get(2)),
                                () -> assertEquals(numbers.get(3),check.get(3)));
    }
}