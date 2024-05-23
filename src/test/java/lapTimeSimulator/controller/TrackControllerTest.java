package lapTimeSimulator.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lapTimeSimulator.ddd.IMapper;
import lapTimeSimulator.domain.track.ITrackFactory;
import lapTimeSimulator.domain.track.Track;
import lapTimeSimulator.domain.track.TrackFactory;
import lapTimeSimulator.domain.valueObject.Name;
import lapTimeSimulator.mapper.TrackMapper;
import lapTimeSimulator.persistence.track.ITrackRepository;
import lapTimeSimulator.service.TrackService;
import lapTimeSimulator.utils.dto.outputDataDTO.TrackDataOutDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TrackControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    ITrackRepository trackRepository;

    @Test
    void shouldInstantiateTrackController_whenParametersAreValid() {
        // Arrange
        TrackService trackService = new TrackService(trackRepository);
        IMapper<Track, TrackDataOutDTO> trackMapper = new TrackMapper();

        // Act
        TrackController trackController = new TrackController(trackService, trackMapper);

        // Assert
        assertNotNull(trackController);
    }

//    @Test
//    void shouldThrowException_whenTrackServiceIsNull() {
//        // Arrange
//        IMapper<Track, TrackDataOutDTO> trackMapper = new TrackMapper();
//
//        String expectedMessage = "Track service and mapper cannot be null.";
//
//        // Act & Assert
//        Exception e = assertThrows(IllegalArgumentException.class, () ->
//                new TrackController(null, trackMapper));
//
//
//        // Assert
//        String actualMessage = e.getMessage();
//        assertEquals(expectedMessage, actualMessage);
//    }
//
//    @Test
//    void shouldThrowException_whenTrackMapperIsNull() {
//        // Arrange
//        TrackService trackService = new TrackService(trackRepository);
//
//        String expectedMessage = "Track service and mapper cannot be null.";
//
//        // Act & Assert
//        Exception e = assertThrows(IllegalArgumentException.class, () ->
//                new TrackController(trackService, null));
//
//        // Assert
//        String actualMessage = e.getMessage();
//        assertEquals(expectedMessage, actualMessage);
//    }

    @Test
    void shouldGetTracks_whenTracksExist() throws Exception {
        // Arrange
        ITrackFactory trackFactory = new TrackFactory();

        Name trackName = new Name("Laguna Seca");
        Track track = trackFactory.createTrack(trackName);

        when(trackRepository.findAll()).thenReturn(List.of(track));

        IMapper<Track, TrackDataOutDTO> trackMapper = new TrackMapper();
        TrackDataOutDTO trackDataOutDTO = trackMapper.toDTO(track);

        List<TrackDataOutDTO> expectedTracks = List.of(trackDataOutDTO);

        // Act + Assert
        MvcResult result = mockMvc.perform(get("/tracks")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String actualResponseBody = result.getResponse().getContentAsString();
        List<TrackDataOutDTO> actualTracks = objectMapper.readValue(actualResponseBody, new TypeReference<>() {});
        assertEquals(expectedTracks.get(0).trackID, actualTracks.get(0).trackID);
    }

    @Test
    void shouldReturnEmptyList_whenNoTracksExist() throws Exception {
        // Arrange
        when(trackRepository.findAll()).thenReturn(List.of());

        // Act + Assert
        MvcResult result = mockMvc.perform(get("/tracks")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String actualResponseBody = result.getResponse().getContentAsString();
        List<TrackDataOutDTO> actualTracks = objectMapper.readValue(actualResponseBody, new TypeReference<>() {});
        assertTrue(actualTracks.isEmpty());
    }
}
