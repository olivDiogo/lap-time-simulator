package laptimesimulator.utils.dto.outputDataDTO.simulationData;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class SimulationVehicleDataOutDTO {
    public final String vehicleID;
    public final String vehicleName;
    public final double sCz; // downforce coefficient
    public final double sCx; // drag coefficient
    public final double rBrkF2P; // brake pressure to torque ratio
    public final double mCar; // vehicle mass
    public final double PEngMax; // maximum power
    public final double MEngMax; // maximum torque
    public final double nEngPMax; // rpm at maximum power
    public final double nEngMMax; // rpm at maximum torque
    public final List<Double> gears;
    public final double finalDriveRatio;
    public final double mux0; // longitudinal grip coefficient
    public final double muy0; // lateral grip coefficient
    public final double rrTyre; // tyre radius
}
