package laptimesimulator.service;

import laptimesimulator.ddd.IMapper;
import laptimesimulator.domain.track.ITrackFactory;
import laptimesimulator.domain.track.Track;
import laptimesimulator.domain.track.TrackFactory;
import laptimesimulator.domain.valueObject.Name;
import laptimesimulator.domain.valueObject.TrackID;
import laptimesimulator.domain.valueObject.TrackLength;
import laptimesimulator.persistence.track.ITrackRepository;
import laptimesimulator.utils.dto.outputDataDTO.TrackDataOutDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class TrackServiceTest {

    @MockBean
    ITrackRepository trackRepository;

    @MockBean
    IMapper<Track, TrackDataOutDTO> trackMapper;

    @Test
    void shouldInstantiateTrackService_whenParametersAreValid() {
        // Act
        TrackService trackService = new TrackService(trackRepository, trackMapper);

        // Assert
        assertNotNull(trackService);
    }

    @Test
    void shouldGetTracks_whenTheRepositoryHasTracks() {
        // Arrange
        TrackService trackService = new TrackService(trackRepository, trackMapper);

        Name trackName = mock(Name.class);
        TrackLength trackLength = mock(TrackLength.class);

        Track track = mock(Track.class);
        when(track.getTrackID()).thenReturn(mock(TrackID.class));
        when(track.getTrackID().getId()).thenReturn("1");

        ITrackFactory trackFactory = mock(TrackFactory.class);
        when(trackFactory.createTrack(trackName, trackLength)).thenReturn(track);

        when(trackRepository.findAll()).thenReturn(List.of(track));
        when(trackMapper.toDTO(List.of(track))).thenReturn(List.of(new TrackDataOutDTO("1", "AIA", 1000)));

        TrackDataOutDTO expected = new TrackDataOutDTO("1", "AIA", 1000);

        // Act
        List<TrackDataOutDTO> tracks = trackService.getTracks();

        // Assert
        assertEquals(expected.trackID, tracks.get(0).trackID);
    }

    @Test
    void shouldGetEmptyList_whenNoTracksInRepository() {
        // Arrange
        TrackService trackService = new TrackService(trackRepository, trackMapper);

        when(trackRepository.findAll()).thenReturn(List.of());

        // Act
        List<TrackDataOutDTO> tracks = trackService.getTracks();

        // Assert
        assertTrue(tracks.isEmpty());
    }
}
