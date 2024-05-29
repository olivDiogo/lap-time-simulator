package laptimesimulator.controller;

import laptimesimulator.service.TrackService;
import laptimesimulator.utils.dto.outputDataDTO.TrackDataOutDTO;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

        List<TrackDataOutDTO> tracksDTO;
        try {
            tracksDTO = trackService.getTracks();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        List<EntityModel<TrackDataOutDTO>> resources = new ArrayList<>();

        for (TrackDataOutDTO trackDataOutDTO : tracksDTO) {
            EntityModel<TrackDataOutDTO> resource = EntityModel.of(trackDataOutDTO);
            resources.add(resource);
        }

        return ResponseEntity.ok().body(resources);
    }
}
