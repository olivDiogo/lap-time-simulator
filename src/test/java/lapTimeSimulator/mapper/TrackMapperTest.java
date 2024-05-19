package lapTimeSimulator.mapper;

import lapTimeSimulator.ddd.IMapper;
import lapTimeSimulator.domain.track.Track;
import lapTimeSimulator.domain.valueObject.Description;
import lapTimeSimulator.domain.valueObject.TrackID;
import lapTimeSimulator.utils.dto.TrackDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.format.DecimalStyle;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class TrackMapperTest {

    @Test
    void shouldMapTrackToTrackDTO_whenTrackIsValid(){
        // Arrange
        TrackID trackID = mock(TrackID.class);
        when(trackID.getId()).thenReturn("1");

        Description trackName = mock(Description.class);
        when(trackName.getDescription()).thenReturn("Test Track");

        Track track = mock(Track.class);
        when(track.getId()).thenReturn(trackID);
        when(track.getTrackName()).thenReturn(trackName);

        IMapper<Track, TrackDTO> trackMapper = new TrackMapper();

        // Act
        TrackDTO trackDTO = trackMapper.toDTO(track);

        // Assert
        assertEquals(trackID.getId(), trackDTO.trackID);
    }

    @Test
    void shouldThrowException_whenTrackIsNull(){
        // Arrange
        IMapper<Track, TrackDTO> trackMapper = new TrackMapper();
        Track track = null;

        String expectedMessage = "The track cannot be null.";

        // Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackMapper.toDTO(track));

        // Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldMapTrackListToTrackDTOList_whenTrackListIsValid(){
        // Arrange
        TrackID trackID = mock(TrackID.class);
        when(trackID.getId()).thenReturn("1");

        Description trackName = mock(Description.class);
        when(trackName.getDescription()).thenReturn("Test Track");

        Track track = mock(Track.class);
        when(track.getId()).thenReturn(trackID);
        when(track.getTrackName()).thenReturn(trackName);

        IMapper<Track, TrackDTO> trackMapper = new TrackMapper();

        List<Track> tracks = List.of(track);

        // Act
        List<TrackDTO> tracksDTO = trackMapper.toDTO(tracks);

        // Assert
        assertEquals(trackID.getId(), tracksDTO.get(0).trackID);
    }

    @Test
    void shouldThrowException_whenTrackListIsNull(){
        // Arrange
        IMapper<Track, TrackDTO> trackMapper = new TrackMapper();
        List<Track> tracks = null;

        String expectedMessage = "The list of tracks cannot be null.";

        // Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackMapper.toDTO(tracks));

        // Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
