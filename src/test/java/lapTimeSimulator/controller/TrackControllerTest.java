package lapTimeSimulator.controller;

import lapTimeSimulator.ddd.IMapper;
import lapTimeSimulator.domain.track.ITrackFactory;
import lapTimeSimulator.domain.track.Track;
import lapTimeSimulator.domain.track.TrackFactory;
import lapTimeSimulator.domain.valueObject.Description;
import lapTimeSimulator.mapper.TrackMapper;
import lapTimeSimulator.persistence.track.ITrackRepository;
import lapTimeSimulator.service.TrackService;
import lapTimeSimulator.utils.dto.TrackDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class TrackControllerTest {

    @MockBean
    ITrackRepository trackRepository;

    @Test
    void shouldInstantiateTrackController_whenParametersAreValid() {
        // Arrange
        TrackService trackService = new TrackService(trackRepository);
        IMapper<Track, TrackDTO> trackMapper = new TrackMapper();

        // Act
        TrackController trackController = new TrackController(trackService, trackMapper);

        // Assert
        assertNotNull(trackController);
    }

    @Test
    void shouldThrowException_whenTrackServiceIsNull() {
        // Arrange
        IMapper<Track, TrackDTO> trackMapper = new TrackMapper();

        String expectedMessage = "Track service and assembler cannot be null.";

        // Act & Assert
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new TrackController(null, trackMapper));

        // Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowException_whenTrackMapperIsNull() {
        // Arrange
        TrackService trackService = new TrackService(trackRepository);

        String expectedMessage = "Track service and assembler cannot be null.";

        // Act & Assert
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new TrackController(trackService, null));

        // Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldGetTracks_whenTracksExist() {
        // Arrange
        ITrackFactory trackFactory = new TrackFactory();
        Description trackName = new Description("Laguna Seca");
        Track track = trackFactory.createTrack(trackName);
        when(trackRepository.findAll()).thenReturn(List.of(track));

        TrackService trackService = new TrackService(trackRepository);
        IMapper<Track, TrackDTO> trackMapper = new TrackMapper();

        TrackDTO trackDTO = trackMapper.toDTO(track);

        TrackController trackController = new TrackController(trackService, trackMapper);

        List<TrackDTO> expectedTracks = List.of(trackDTO);

        // Act
        List<TrackDTO> tracks = trackController.getTracks();

        // Assert
        assertEquals(expectedTracks.get(0).trackID, tracks.get(0).trackID);
    }

    @Test
    void shouldReturnEmptyList_whenNoTracksExist() {
        // Arrange
        when(trackRepository.findAll()).thenReturn(List.of());

        TrackService trackService = new TrackService(trackRepository);
        IMapper<Track, TrackDTO> trackMapper = new TrackMapper();

        TrackController trackController = new TrackController(trackService, trackMapper);

        // Act
        List<TrackDTO> tracks = trackController.getTracks();

        // Assert
        assertTrue(tracks.isEmpty());
    }
}
