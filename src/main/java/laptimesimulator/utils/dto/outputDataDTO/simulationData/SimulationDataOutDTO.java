package laptimesimulator.utils.dto.outputDataDTO.simulationData;

import laptimesimulator.utils.simulationStarter.SimulationStarter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SimulationDataOutDTO {
    public final SimulationStarter.SimJson sim;
    public final SimulationStarter.VehicleJson vehicle;
    public final SimulationStarter.TrackJson track;
}
