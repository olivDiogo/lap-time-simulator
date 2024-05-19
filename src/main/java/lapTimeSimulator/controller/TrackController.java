package lapTimeSimulator.controller;

import lapTimeSimulator.ddd.IMapper;
import lapTimeSimulator.domain.track.Track;
import lapTimeSimulator.service.TrackService;
import lapTimeSimulator.utils.dto.TrackDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tracks")
public class TrackController {
    private final TrackService trackService;
    private final IMapper<Track, TrackDTO> trackAssembler;

    /**
     * Constructs a new TrackController instance with the specified track service.
     *
     * @param trackService The track service. Must not be null.
     */
    public TrackController(TrackService trackService, IMapper<Track, TrackDTO> trackAssembler) {
        if (trackService == null || trackAssembler == null) {
            throw new IllegalArgumentException("Track service and assembler cannot be null.");
        }
        this.trackService = trackService;
        this.trackAssembler = trackAssembler;
    }

    /**
     * Gets all tracks in the database.
     *
     * @return a list with all tracks as DTOs.
     */
    @GetMapping
    public List<TrackDTO> getTracks(){
        List<Track> tracks = trackService.getTracks();
        return trackAssembler.toDTO(tracks);
    }
}
