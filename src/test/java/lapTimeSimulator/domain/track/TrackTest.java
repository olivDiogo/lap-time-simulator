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
class TrackTest {

    @Test
    void shouldInstantiateTrack_whenNameIsValid(){
        // Assert
        Name trackName = mock(Name.class);

        try (MockedConstruction<TrackID> trackIDMocked = mockConstruction(TrackID.class)) {
            //Act
            Track track = new Track(trackName);

            //Assert
            assertNotNull(track);
        }
    }


    @Test
    void shouldInstantiateTrack_whenTrackIDAndNameAreValid(){
        // Assert
        Name trackName = mock(Name.class);
        TrackID trackID = mock(TrackID.class);

        //Act
        Track track = new Track(trackID, trackName);

        //Assert
        assertNotNull(track);
    }

    @Test
    void shouldUpdateTrackName_whenUpdateTrackNameIsCalled(){
        // Arrange
        Name trackName = mock(Name.class);
        Name newTrackName = mock(Name.class);
        TrackID trackID = mock(TrackID.class);
        Track track = new Track(trackID, trackName);

        // Act
        Name result = track.updateTrackName(newTrackName);

        // Assert
        assertEquals(newTrackName, result);
    }

    @Test
    void shouldThrowException_whenUpdateTrackNameIsNull(){
        // Arrange
        Name trackName = mock(Name.class);
        TrackID trackID = mock(TrackID.class);
        Track track = new Track(trackID, trackName);

        String expectedMessage = "Track name cannot be null.";

        // Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> track.updateTrackName(null));

        // Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
