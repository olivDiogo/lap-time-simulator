package lapTimeSimulator.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lapTimeSimulator.ddd.IMapper;
import lapTimeSimulator.domain.track.Track;
import lapTimeSimulator.service.TrackService;
import lapTimeSimulator.utils.dto.TrackDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/tracks")
public class TrackController {
    @NotNull
    private final TrackService trackService;
    @NotNull
    private final IMapper<Track, TrackDTO> trackMapper;

    /**
     * Constructs a new TrackController instance with the specified track service.
     *
     * @param trackService The track service. Must not be null.
     */
    public TrackController(@Valid TrackService trackService, @Valid IMapper<Track, TrackDTO> trackMapper) {
        if (trackService == null || trackMapper == null) {
            throw new IllegalArgumentException("Track service and mapper cannot be null.");
        }
        this.trackService = trackService;
        this.trackMapper = trackMapper;
    }

    /**
     * Gets all tracks in the database.
     *
     * @return a list with all tracks as DTOs.
     */
    @GetMapping
    public ResponseEntity<List<EntityModel<TrackDTO>>> getTracks() {
        List<Track> tracks = trackService.getTracks();
        List<TrackDTO> tracksDTO = trackMapper.toDTO(tracks);

        Link selfLink = linkTo(methodOn(TrackController.class).getTracks()).withSelfRel();

        List<EntityModel<TrackDTO>> resources = new ArrayList<>();

        for (TrackDTO trackDTO : tracksDTO) {
            EntityModel<TrackDTO> resource = EntityModel.of(trackDTO, selfLink);
            resources.add(resource);
        }

        return ResponseEntity.ok().body(resources);
    }
}
