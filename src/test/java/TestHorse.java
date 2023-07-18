import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TestHorse {


    @Test
    void whenNameNullthenException() {
        IllegalArgumentException thown = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
        assertEquals("Name cannot be null.", thown.getMessage());
    }

    @ParameterizedTest
    @ValueSource (strings = {"", "  ", "  " })
    void whenSpaceThenException(String arg) {
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse(arg, 1, 1));
        assertEquals("Name cannot be blank.", thrown.getMessage());
    }

    @Test
    void whenSpeedNegativeThenException() {
        IllegalArgumentException thown = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("A", -1, 1));
        assertEquals("Speed cannot be negative.", thown.getMessage());
    }

    @Test
    void whenDistanceNegativeThenException() {
        IllegalArgumentException thown = Assertions.assertThrows(IllegalArgumentException.class, () -> new Horse("A", 1, -1));
        assertEquals("Distance cannot be negative.", thown.getMessage());
    }

    @Test
    void getNameReturnRightName () {
        Horse horse = new Horse("A" , 1, 1);
        assertEquals("A", horse.getName());
    }

    @Test
    void getSpeedReturnRightSpeed () {
        Horse horse = new Horse("A" , 1, 1);
        assertEquals(1, horse.getSpeed());
    }

    @Test
    void getDistanceReturnRightDistance () {
        Horse horse = new Horse("A" , 1, 1);
        assertEquals(1, horse.getDistance());
    }

    @Test
    void getDistanceThenTwoParametrs () {
        Horse horse = new Horse("A" , 1);
        assertEquals(0, horse.getDistance());
    }
    @Test
    void getRandomDoubleCall() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("A", 1);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }


    @ParameterizedTest
    @ValueSource(doubles = {0.5, 0.7, 0.9})
    void getDistanceTest (double doubles) {
        try (MockedStatic<Horse> theMock = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("A", 1);
            double rightDistance = horse.getDistance() + horse.getSpeed() * doubles;
            theMock.when(()-> Horse.getRandomDouble(0.2, 0.9)).thenReturn(doubles);
            horse.move();
            assertEquals(rightDistance, horse.getDistance());
        }
    }
}



