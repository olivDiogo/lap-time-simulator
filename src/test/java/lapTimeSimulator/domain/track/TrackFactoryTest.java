package lapTimeSimulator.domain.track;

import lapTimeSimulator.domain.valueObject.Name;
import lapTimeSimulator.domain.valueObject.TrackID;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;

@SpringBootTest
class TrackFactoryTest {

    @Test
    void shouldInstantiateTrack_whenNameIsValid(){
        // Assert
        Name trackName = mock(Name.class);
        ITrackFactory trackFactory = new TrackFactory();

        try (MockedConstruction<TrackID> trackIDMocked = mockConstruction(TrackID.class)) {
            //Act
            Track result = trackFactory.createTrack(trackName);

            //Assert
            assertNotNull(result);
        }
    }

    @Test
    void shouldThrowException_whenNameIsNull(){
        // Assert
        Name trackName = null;
        ITrackFactory trackFactory = new TrackFactory();

        String expectedMessage = "Track name cannot be null.";

        //Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackFactory.createTrack(trackName));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldInstantiateTrack_whenTrackIDAndNameAreValid(){
        // Assert
        Name trackName = mock(Name.class);
        TrackID trackID = mock(TrackID.class);
        ITrackFactory trackFactory = new TrackFactory();

        //Act
        Track result = trackFactory.createTrack(trackID, trackName);

        //Assert
        assertNotNull(result);
    }

    @Test
    void shouldThrowException_whenNameIsNullAndTrackIDIsValid(){
        // Assert
        Name trackName = null;
        TrackID trackID = mock(TrackID.class);
        ITrackFactory trackFactory = new TrackFactory();

        String expectedMessage = "Track ID and track name cannot be null.";

        //Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackFactory.createTrack(trackID, trackName));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowException_whenTrackIDIsNullAndNameIsValid(){
        // Assert
        Name trackName = mock(Name.class);
        TrackID trackID = null;
        ITrackFactory trackFactory = new TrackFactory();

        String expectedMessage = "Track ID and track name cannot be null.";

        //Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackFactory.createTrack(trackID, trackName));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

}
