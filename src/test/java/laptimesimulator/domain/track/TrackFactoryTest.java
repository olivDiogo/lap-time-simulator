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
        String trackIconPath = "path";
        String trackLocation = "location";
        String trackRaceLapRecord = "record";
        String numberOfCorners = "13";
        String trackLayout = "layout";
        ITrackFactory trackFactory = new TrackFactory();

        try (MockedConstruction<TrackID> trackIDMocked = mockConstruction(TrackID.class)) {
            //Act
            Track result = trackFactory.createTrack(trackName, trackLength, trackIconPath, trackLocation, trackRaceLapRecord, numberOfCorners, trackLayout);

            //Assert
            assertNotNull(result);
        }
    }

    @Test
    void shouldThrowException_whenNameIsNull(){
        // Assert
        TrackLength trackLength = mock(TrackLength.class);
        String trackIconPath = "path";
        String trackLocation = "location";
        String trackRaceLapRecord = "record";
        String numberOfCorners = "13";
        String trackLayout = "layout";
        ITrackFactory trackFactory = new TrackFactory();

        String expectedMessage = "Track parameters cannot be null.";

        //Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackFactory.createTrack(null, trackLength, trackIconPath, trackLocation, trackRaceLapRecord, numberOfCorners, trackLayout));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowException_whenTrackLengthIsNull(){
        // Assert
        Name trackName = mock(Name.class);
        String trackIconPath = "path";
        String trackLocation = "location";
        String trackRaceLapRecord = "record";
        String numberOfCorners = "13";
        String trackLayout = "layout";
        ITrackFactory trackFactory = new TrackFactory();

        String expectedMessage = "Track parameters cannot be null.";

        //Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackFactory.createTrack(trackName, null, trackIconPath, trackLocation, trackRaceLapRecord, numberOfCorners, trackLayout));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowException_whenTrackIconPathIsNull(){
        // Assert
        Name trackName = mock(Name.class);
        TrackLength trackLength = mock(TrackLength.class);
        String trackLocation = "location";
        String trackRaceLapRecord = "record";
        String numberOfCorners = "13";
        String trackLayout = "layout";
        ITrackFactory trackFactory = new TrackFactory();

        String expectedMessage = "Track parameters cannot be null.";

        //Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackFactory.createTrack(trackName, trackLength, null, trackLocation, trackRaceLapRecord, numberOfCorners, trackLayout));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowException_whenTrackLocationIsNull(){
        // Assert
        Name trackName = mock(Name.class);
        TrackLength trackLength = mock(TrackLength.class);
        String trackIconPath = "path";
        String trackRaceLapRecord = "record";
        String numberOfCorners = "13";
        String trackLayout = "layout";
        ITrackFactory trackFactory = new TrackFactory();

        String expectedMessage = "Track parameters cannot be null.";

        //Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackFactory.createTrack(trackName, trackLength, trackIconPath, null, trackRaceLapRecord, numberOfCorners, trackLayout));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowException_whenTrackRaceLapRecordIsNull(){
        // Assert
        Name trackName = mock(Name.class);
        TrackLength trackLength = mock(TrackLength.class);
        String trackIconPath = "path";
        String trackLocation = "location";
        String numberOfCorners = "13";
        String trackLayout = "layout";
        ITrackFactory trackFactory = new TrackFactory();

        String expectedMessage = "Track parameters cannot be null.";

        //Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackFactory.createTrack(trackName, trackLength, trackIconPath, trackLocation, null, numberOfCorners, trackLayout));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowException_whenNumberOfCornersIsNull(){
        // Assert
        Name trackName = mock(Name.class);
        TrackLength trackLength = mock(TrackLength.class);
        String trackIconPath = "path";
        String trackLocation = "location";
        String trackRaceLapRecord = "record";
        String trackLayout = "layout";
        ITrackFactory trackFactory = new TrackFactory();

        String expectedMessage = "Track parameters cannot be null.";

        //Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackFactory.createTrack(trackName, trackLength, trackIconPath, trackLocation, trackRaceLapRecord, null, trackLayout));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowException_whenTrackLayoutIsNull(){
        // Assert
        Name trackName = mock(Name.class);
        TrackLength trackLength = mock(TrackLength.class);
        String trackIconPath = "path";
        String trackLocation = "location";
        String trackRaceLapRecord = "record";
        String numberOfCorners = "13";
        ITrackFactory trackFactory = new TrackFactory();

        String expectedMessage = "Track parameters cannot be null.";

        //Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackFactory.createTrack(trackName, trackLength, trackIconPath, trackLocation, trackRaceLapRecord, numberOfCorners, null));

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
        String trackIconPath = "path";
        String trackLocation = "location";
        String trackRaceLapRecord = "record";
        String numberOfCorners = "13";
        String trackLayout = "layout";

        //Act
        Track result = trackFactory.createTrack(trackID, trackName, trackLength, trackIconPath, trackLocation, trackRaceLapRecord, numberOfCorners, trackLayout);

        //Assert
        assertNotNull(result);
    }

    @Test
    void shouldThrowException_whenNameIsNullAndTrackIDIsValid(){
        // Assert
        TrackLength trackLength = mock(TrackLength.class);
        TrackID trackID = mock(TrackID.class);
        ITrackFactory trackFactory = new TrackFactory();
        String trackIconPath = "path";
        String trackLocation = "location";
        String trackRaceLapRecord = "record";
        String numberOfCorners = "13";
        String trackLayout = "layout";

        String expectedMessage = "Track parameters cannot be null.";

        //Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackFactory.createTrack(trackID, null, trackLength, trackIconPath, trackLocation, trackRaceLapRecord, numberOfCorners, trackLayout));

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
        String trackIconPath = "path";
        String trackLocation = "location";
        String trackRaceLapRecord = "record";
        String numberOfCorners = "13";
        String trackLayout = "layout";

        String expectedMessage = "Track parameters cannot be null.";

        //Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackFactory.createTrack(trackID,trackName, null, trackIconPath, trackLocation, trackRaceLapRecord, numberOfCorners, trackLayout));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowException_whenTrackIDIsNullAndNameIsValid(){
        // Assert
        Name trackName = mock(Name.class);
        TrackLength trackLength = mock(TrackLength.class);
        String trackIconPath = "path";
        String trackLocation = "location";
        String trackRaceLapRecord = "record";
        String numberOfCorners = "13";
        String trackLayout = "layout";

        ITrackFactory trackFactory = new TrackFactory();

        String expectedMessage = "Track parameters cannot be null.";

        //Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackFactory.createTrack(null, trackName, trackLength, trackIconPath, trackLocation, trackRaceLapRecord, numberOfCorners, trackLayout));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowException_whenTrackIconPathIsNullAndTrackIDIsValid(){
        // Assert
        Name trackName = mock(Name.class);
        TrackLength trackLength = mock(TrackLength.class);
        TrackID trackID = mock(TrackID.class);
        ITrackFactory trackFactory = new TrackFactory();
        String trackLocation = "location";
        String trackRaceLapRecord = "record";
        String numberOfCorners = "13";
        String trackLayout = "layout";

        String expectedMessage = "Track parameters cannot be null.";

        //Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackFactory.createTrack(trackID, trackName, trackLength, null, trackLocation, trackRaceLapRecord, numberOfCorners, trackLayout));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowException_whenTrackLocationIsNullAndTrackIDIsValid(){
        // Assert
        Name trackName = mock(Name.class);
        TrackLength trackLength = mock(TrackLength.class);
        TrackID trackID = mock(TrackID.class);
        ITrackFactory trackFactory = new TrackFactory();
        String trackIconPath = "path";
        String trackRaceLapRecord = "record";
        String numberOfCorners = "13";
        String trackLayout = "layout";

        String expectedMessage = "Track parameters cannot be null.";

        //Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackFactory.createTrack(trackID, trackName, trackLength, trackIconPath, null, trackRaceLapRecord, numberOfCorners, trackLayout));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowException_whenTrackRaceLapRecordIsNullAndTrackIDIsValid(){
        // Assert
        Name trackName = mock(Name.class);
        TrackLength trackLength = mock(TrackLength.class);
        TrackID trackID = mock(TrackID.class);
        ITrackFactory trackFactory = new TrackFactory();
        String trackIconPath = "path";
        String trackLocation = "location";
        String numberOfCorners = "13";
        String trackLayout = "layout";

        String expectedMessage = "Track parameters cannot be null.";

        //Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackFactory.createTrack(trackID, trackName, trackLength, trackIconPath, trackLocation, null, numberOfCorners, trackLayout));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowException_whenNumberOfCornersIsNullAndTrackIDIsValid(){
        // Assert
        Name trackName = mock(Name.class);
        TrackLength trackLength = mock(TrackLength.class);
        TrackID trackID = mock(TrackID.class);
        ITrackFactory trackFactory = new TrackFactory();
        String trackIconPath = "path";
        String trackLocation = "location";
        String trackRaceLapRecord = "record";
        String trackLayout = "layout";

        String expectedMessage = "Track parameters cannot be null.";

        //Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackFactory.createTrack(trackID, trackName, trackLength, trackIconPath, trackLocation, trackRaceLapRecord, null, trackLayout));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowException_whenTrackLayoutIsNullAndTrackIDIsValid(){
        // Assert
        Name trackName = mock(Name.class);
        TrackLength trackLength = mock(TrackLength.class);
        TrackID trackID = mock(TrackID.class);
        ITrackFactory trackFactory = new TrackFactory();
        String trackIconPath = "path";
        String trackLocation = "location";
        String trackRaceLapRecord = "record";
        String numberOfCorners = "13";

        String expectedMessage = "Track parameters cannot be null.";

        //Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackFactory.createTrack(trackID, trackName, trackLength, trackIconPath, trackLocation, trackRaceLapRecord, numberOfCorners, null));

        //Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
