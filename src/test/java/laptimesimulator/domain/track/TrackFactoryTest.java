package laptimesimulator.domain.track;

import laptimesimulator.domain.valueObject.Name;
import laptimesimulator.domain.valueObject.TrackID;
import laptimesimulator.domain.valueObject.TrackLength;
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
        TrackLength trackLength = mock(TrackLength.class);
        ITrackFactory trackFactory = new TrackFactory();

        try (MockedConstruction<TrackID> trackIDMocked = mockConstruction(TrackID.class)) {
            //Act
            Track result = trackFactory.createTrack(trackName, trackLength);

            //Assert
            assertNotNull(result);
        }
    }

    @Test
    void shouldThrowException_whenNameIsNull(){
        // Assert
        TrackLength trackLength = mock(TrackLength.class);
        ITrackFactory trackFactory = new TrackFactory();

        String expectedMessage = "Track parameters cannot be null.";

        //Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackFactory.createTrack(null, trackLength));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowException_whenTrackLengthIsNull(){
        // Assert
        Name trackName = mock(Name.class);
        ITrackFactory trackFactory = new TrackFactory();

        String expectedMessage = "Track parameters cannot be null.";

        //Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackFactory.createTrack(trackName, null));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldInstantiateTrack_whenTrackParametersAreValid(){
        // Assert
        Name trackName = mock(Name.class);
        TrackLength trackLength = mock(TrackLength.class);
        TrackID trackID = mock(TrackID.class);
        ITrackFactory trackFactory = new TrackFactory();

        //Act
        Track result = trackFactory.createTrack(trackID, trackName, trackLength);

        //Assert
        assertNotNull(result);
    }

    @Test
    void shouldThrowException_whenNameIsNullAndTrackIDIsValid(){
        // Assert
        TrackLength trackLength = mock(TrackLength.class);
        TrackID trackID = mock(TrackID.class);
        ITrackFactory trackFactory = new TrackFactory();

        String expectedMessage = "Track parameters cannot be null.";

        //Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackFactory.createTrack(trackID, null, trackLength));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowException_whenTrackLengthIsNullAndTrackIDIsValid(){
        // Assert
        Name trackName = mock(Name.class);
        TrackID trackID = mock(TrackID.class);
        ITrackFactory trackFactory = new TrackFactory();

        String expectedMessage = "Track parameters cannot be null.";

        //Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackFactory.createTrack(trackID,trackName, null));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowException_whenTrackIDIsNullAndNameIsValid(){
        // Assert
        Name trackName = mock(Name.class);
        TrackLength trackLength = mock(TrackLength.class);

        ITrackFactory trackFactory = new TrackFactory();

        String expectedMessage = "Track parameters cannot be null.";

        //Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackFactory.createTrack(null, trackName, trackLength));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

}
