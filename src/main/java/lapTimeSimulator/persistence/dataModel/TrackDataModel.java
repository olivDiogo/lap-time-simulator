package lapTimeSimulator.persistence.dataModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lapTimeSimulator.domain.track.Track;
import lombok.Getter;

@Entity
@Table(name = "Track")
@Getter
public class TrackDataModel {
    @Id
    private String trackID;
    private String trackName;

    @Version
    private long version;

    /**
     * Empty class constructor
     */
    public TrackDataModel() {
    }

    /**
     * Class constructor with parameter track
     *
     * @param track is the track domain object to be converted to a data model.
     */
    public TrackDataModel(Track track) {
        if (track == null) {
            throw new IllegalArgumentException("Track cannot be null.");
        }
        this.trackID = track.getTrackID().getId();
        this.trackName = track.getTrackName().getStrName();
    }
}
