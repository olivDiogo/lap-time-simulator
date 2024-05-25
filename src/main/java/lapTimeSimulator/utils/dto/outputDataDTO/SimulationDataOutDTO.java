package lapTimeSimulator.utils.dto.outputDataDTO;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SimulationDataOutDTO {
    public final String simulationID;
    public final String simulationName;
    public final String vehicleID;
    public final String vehicleName;
    public final double sCz; // downforce coefficient
    public final double sCx; // drag coefficient
    public final double rBrkF2P; // brake pressure to torque ratio
    public final double mCar; // vehicle mass
    public final double pEngMax; // maximum power
    public final double tEngMax; // maximum torque
    public final double nEngPMax; // rpm at maximum power
    public final double nEngTMax; // rpm at maximum torque
    public final int numberOfGears;
    public final List<Double> gears;
    public final double finalDriveRatio;
    public final double mux; // longitudinal grip coefficient
    public final double muy; // lateral grip coefficient
    public final double rrTyre; // tyre radius
    public final String trackID;
    public final String trackName;
}
