package lapTimeSimulator.controller;

import lapTimeSimulator.ddd.IMapper;
import lapTimeSimulator.domain.track.Track;
import lapTimeSimulator.service.TrackService;
import lapTimeSimulator.utils.dto.outputDataDTO.TrackDataOutDTO;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class TrackController {
    private TrackService trackService;
    private IMapper<Track, TrackDataOutDTO> trackMapper;

//    /**
//     * Constructs a new TrackController instance with the specified track service.
//     *
//     * @param trackService The track service. Must not be null.
//     */
//    public TrackController(@Valid TrackService trackService, @Valid IMapper<Track, TrackDataOutDTO> trackMapper) {
//        if (trackService == null || trackMapper == null) {
//            throw new IllegalArgumentException("Track service and mapper cannot be null.");
//        }
//        this.trackService = trackService;
//        this.trackMapper = trackMapper;
//    }

    /**
     * Gets all tracks in the database.
     *
     * @return a list with all tracks as DTOs.
     */
    @GetMapping
    public ResponseEntity<List<EntityModel<TrackDataOutDTO>>> getTracks() {
        List<Track> tracks = trackService.getTracks();
        List<TrackDataOutDTO> tracksDTO = trackMapper.toDTO(tracks);

        Link selfLink = linkTo(methodOn(TrackController.class).getTracks()).withSelfRel();

        List<EntityModel<TrackDataOutDTO>> resources = new ArrayList<>();

        for (TrackDataOutDTO trackDataOutDTO : tracksDTO) {
            EntityModel<TrackDataOutDTO> resource = EntityModel.of(trackDataOutDTO, selfLink);
            resources.add(resource);
        }

        return ResponseEntity.ok().body(resources);
    }
}
