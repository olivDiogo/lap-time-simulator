package lapTimeSimulator.utils.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TrackDTO {
    @NotNull(message = "The track ID cannot be null!")
    @NotBlank(message = "The track ID cannot be blank!")
    public final String trackID;
    @NotNull(message = "The track name cannot be null!")
    @NotBlank(message = "The track name cannot be blank!")
    public final String trackName;

}
