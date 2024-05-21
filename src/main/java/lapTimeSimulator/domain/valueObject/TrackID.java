package lapTimeSimulator.domain.valueObject;

import lapTimeSimulator.ddd.IDomainID;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class TrackID implements IDomainID {
    private final String id;

    /**
     * Constructor of the class TrackID.
     *
     * @param id is the ID of the track.
     */
    public TrackID(String id) {
        if(id == null || id.isBlank()) {
            throw new IllegalArgumentException("Track ID must be a non-empty string.");
        }
        this.id = id;
    }
}
