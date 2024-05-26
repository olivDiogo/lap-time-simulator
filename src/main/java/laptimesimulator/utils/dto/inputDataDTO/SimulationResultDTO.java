package laptimesimulator.utils.dto.inputDataDTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor(force = true)
@AllArgsConstructor
public class SimulationResultDTO {
    public final String simulationID;
    public final String vehicleID;
    public final String vehicleName;
}
