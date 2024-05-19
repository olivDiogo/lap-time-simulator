package lapTimeSimulator.service;

import lapTimeSimulator.domain.track.ITrackFactory;
import lapTimeSimulator.domain.track.Track;
import lapTimeSimulator.domain.track.TrackFactory;
import lapTimeSimulator.domain.valueObject.Description;
import lapTimeSimulator.persistence.track.ITrackRepository;
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

    @Test
    void shouldInstantiateTrackService_whenParametersAreValid() {
        // Act
        TrackService trackService = new TrackService(trackRepository);

        // Assert
        assertNotNull(trackService);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenRepositoryIsNull() {
        // Arrange
        String expectedMessage = "Repository cannot be null.";

        // Act & Assert
        IllegalArgumentException exception =
            assertThrows(IllegalArgumentException.class, () -> {
                new TrackService(null);
            });

        // Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldGetTracks_whenTheRepositoryHasTracks() {
        // Arrange
        TrackService trackService = new TrackService(trackRepository);

        Description trackName = mock(Description.class);
        when(trackName.getDescription()).thenReturn("AIA");

        Track track = mock(Track.class);
        ITrackFactory trackFactory = mock(TrackFactory.class);
        when(trackFactory.createTrack(trackName)).thenReturn(track);

        when(trackRepository.findAll()).thenReturn(List.of(track));

        // Act
        List<Track> tracks = trackService.getTracks();

        // Assert
        assertEquals(track, tracks.get(0));
    }

    @Test
    void shouldGetEmptyList_whenNoTracksInRepository() {
        // Arrange
        TrackService trackService = new TrackService(trackRepository);

        when(trackRepository.findAll()).thenReturn(List.of());

        // Act
        List<Track> tracks = trackService.getTracks();

        // Assert
        assertTrue(tracks.isEmpty());
    }
}
