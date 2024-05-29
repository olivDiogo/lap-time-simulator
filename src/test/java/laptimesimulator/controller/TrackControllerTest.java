package laptimesimulator.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import laptimesimulator.ddd.IMapper;
import laptimesimulator.domain.track.ITrackFactory;
import laptimesimulator.domain.track.Track;
import laptimesimulator.domain.track.TrackFactory;
import laptimesimulator.domain.valueObject.Name;
import laptimesimulator.domain.valueObject.TrackLength;
import laptimesimulator.mapper.TrackMapper;
import laptimesimulator.persistence.track.ITrackRepository;
import laptimesimulator.service.TrackService;
import laptimesimulator.utils.dto.outputDataDTO.TrackDataOutDTO;
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
    private ITrackRepository trackRepository;

    @Test
    void shouldInstantiateTrackController_whenParametersAreValid() {
        // Arrange
        IMapper<Track, TrackDataOutDTO> trackMapper = new TrackMapper();
        TrackService trackService = new TrackService(trackRepository, trackMapper);

        // Act
        TrackController trackController = new TrackController(trackService);

        // Assert
        assertNotNull(trackController);
    }

    @Test
    void shouldGetTracks_whenTracksExist() throws Exception {
        // Arrange
        ITrackFactory trackFactory = new TrackFactory();

        Name trackName = new Name("Laguna Seca");
        TrackLength trackLength = new TrackLength(3600);
        Track track = trackFactory.createTrack(trackName, trackLength);

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

    @Test
    void shouldReturnInternalServerError_whenListOfTracksIsNull() throws Exception {
        // Arrange
        when(trackRepository.findAll()).thenReturn(null);

        // Act + Assert
        mockMvc.perform(get("/tracks")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());
    }
}
