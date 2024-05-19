package lapTimeSimulator.domain.track;

import lapTimeSimulator.domain.valueObject.Description;
import lapTimeSimulator.domain.valueObject.TrackID;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;

@SpringBootTest
class TrackTest {

    @Test
    void shouldInstantiateTrack_whenDescriptionIsValid(){
        // Assert
        Description trackName = mock(Description.class);

        try (MockedConstruction<TrackID> deviceIDMocked = mockConstruction(TrackID.class)) {
            //Act
            Track track = new Track(trackName);

            //Assert
            assertNotNull(track);
        }
    }

    @Test
    void shouldThrowException_whenDescriptionIsNullFirstConstructor(){
        // Assert
        String expectedMessage = "Track name cannot be null.";

        // Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Track(null));

        // Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldInstantiateTrack_whenTrackIDAndDescriptionAreValid(){
        // Assert
        Description trackName = mock(Description.class);
        TrackID trackID = mock(TrackID.class);

        //Act
        Track track = new Track(trackID, trackName);

        //Assert
        assertNotNull(track);
    }

    @Test
    void shouldThrowException_whenDescriptionIsNullSecondConstructor(){
        // Assert
        TrackID trackID = mock(TrackID.class);
        String expectedMessage = "Track ID and track name cannot be null.";

        // Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Track(trackID, null));

        // Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowException_whenTrackIDIsNullSecondConstructor(){
        // Assert
        Description trackName = mock(Description.class);
        String expectedMessage = "Track ID and track name cannot be null.";

        // Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> new Track(null, trackName));

        // Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldReturnTrackID_whenGetIDIsCalled(){
        // Arrange
        Description trackName = mock(Description.class);
        TrackID trackID = mock(TrackID.class);
        Track track = new Track(trackID, trackName);

        // Act
        TrackID actualTrackID = track.getId();

        // Assert
        assertEquals(trackID, actualTrackID);
    }
}
