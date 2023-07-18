import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestHippodrome {
    @Test
    void whenHorsesNullThenException () {
        IllegalArgumentException thown = Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", thown.getMessage());
    }

    @Test
    void whenHorsesEmptyThenException () {
        List<Horse> horses = Collections.emptyList();
        IllegalArgumentException thown = Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(horses));
        assertEquals("Horses cannot be empty.", thown.getMessage());
    }

    @Test
    void getHorsesReturnRightList () {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("A", 1, 1));
        }
        assertEquals(horses, new Hippodrome(horses).getHorses());
    }

    @Test
    void moveRunningRight () {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (int i = 0; i < 50; i++) {
            Mockito.verify(horses.get(i)).move();
        }
    }

   @Test
    void getWinnerRight () {
        Horse mockHorse1 = Mockito.mock(Horse.class);
        Mockito.when(mockHorse1.getDistance()).thenReturn(2.0);
        Horse mockHorse2 = Mockito.mock(Horse.class);
        Mockito.when(mockHorse2.getDistance()).thenReturn(3.0);
        List<Horse> horses = new ArrayList<>();
        horses.add(mockHorse1);
        horses.add(mockHorse2);
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        assertEquals(mockHorse2, hippodrome.getWinner());
    }
}
