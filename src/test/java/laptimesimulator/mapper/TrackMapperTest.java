package laptimesimulator.mapper;

import laptimesimulator.ddd.IMapper;
import laptimesimulator.domain.track.Track;
import laptimesimulator.domain.valueObject.Name;
import laptimesimulator.domain.valueObject.TrackID;
import laptimesimulator.domain.valueObject.TrackLength;
import laptimesimulator.utils.dto.outputDataDTO.TrackDataOutDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TrackMapperTest {

    @Test
    void shouldMapTrackToTrackDataOutDTO_whenTrackIsValid(){
        // Arrange
        TrackID trackID = mock(TrackID.class);
        when(trackID.getId()).thenReturn("1");

        Name trackName = mock(Name.class);
        when(trackName.getStrName()).thenReturn("Test Track");

        TrackLength trackLength = mock(TrackLength.class);
        when(trackLength.getLength()).thenReturn(1000);

        Track track = mock(Track.class);
        when(track.getTrackID()).thenReturn(trackID);
        when(track.getTrackName()).thenReturn(trackName);
        when(track.getTrackLength()).thenReturn(trackLength);

        IMapper<Track, TrackDataOutDTO> trackMapper = new TrackMapper();

        // Act
        TrackDataOutDTO trackDataOutDTO = trackMapper.toDTO(track);

        // Assert
        assertEquals(trackID.getId(), trackDataOutDTO.trackID);
    }

    @Test
    void shouldThrowException_whenTrackIsNull(){
        // Arrange
        IMapper<Track, TrackDataOutDTO> trackMapper = new TrackMapper();
        Track track = null;

        String expectedMessage = "The track cannot be null.";

        // Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackMapper.toDTO(track));

        // Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldMapTrackListToTrackDataOutDTOList_whenTrackListIsValid(){
        // Arrange
        TrackID trackID = mock(TrackID.class);
        when(trackID.getId()).thenReturn("1");

        Name trackName = mock(Name.class);
        when(trackName.getStrName()).thenReturn("Test Track");

        TrackLength trackLength = mock(TrackLength.class);
        when(trackLength.getLength()).thenReturn(1000);

        Track track = mock(Track.class);
        when(track.getTrackID()).thenReturn(trackID);
        when(track.getTrackName()).thenReturn(trackName);
        when(track.getTrackLength()).thenReturn(trackLength);

        IMapper<Track, TrackDataOutDTO> trackMapper = new TrackMapper();

        List<Track> tracks = List.of(track);

        // Act
        List<TrackDataOutDTO> tracksDTO = trackMapper.toDTO(tracks);

        // Assert
        assertEquals(trackID.getId(), tracksDTO.get(0).trackID);
    }

    @Test
    void shouldThrowException_whenTrackListIsNull(){
        // Arrange
        IMapper<Track, TrackDataOutDTO> trackMapper = new TrackMapper();
        List<Track> tracks = null;

        String expectedMessage = "The list of tracks cannot be null.";

        // Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> trackMapper.toDTO(tracks));

        // Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
