package laptimesimulator.controller;

import laptimesimulator.service.TrackService;
import laptimesimulator.utils.dto.outputDataDTO.TrackDataOutDTO;
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


    /**
     * Gets all tracks in the database.
     *
     * @return a list with all tracks as DTOs.
     */
    @GetMapping
    public ResponseEntity<List<EntityModel<TrackDataOutDTO>>> getTracks() {
        List<TrackDataOutDTO> tracksDTO = trackService.getTracks();

        Link selfLink = linkTo(methodOn(TrackController.class).getTracks()).withSelfRel();

        List<EntityModel<TrackDataOutDTO>> resources = new ArrayList<>();

        for (TrackDataOutDTO trackDataOutDTO : tracksDTO) {
            EntityModel<TrackDataOutDTO> resource = EntityModel.of(trackDataOutDTO, selfLink);
            resources.add(resource);
        }

        return ResponseEntity.ok().body(resources);
    }
}
