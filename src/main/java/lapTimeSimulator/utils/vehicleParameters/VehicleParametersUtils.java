package lapTimeSimulator.utils.vehicleParameters;

import lapTimeSimulator.domain.valueObject.*;
import lapTimeSimulator.utils.dto.VehicleDTO;

public class VehicleParametersUtils {

    private VehicleParametersUtils() {
    }

    /**
     * Method to get the vehicle parameters from the vehicle data transfer object.
     *
     * @param vehicleDataDTO is the vehicle data transfer object.
     * @return the vehicle parameters object.
     */
    public static VehicleParameters getVehicleParameters(VehicleDTO vehicleDataDTO) {
        AeroModel aeroModel = new AeroModel(vehicleDataDTO.downforce, vehicleDataDTO.drag);
        BrakeModel brakeModel = new BrakeModel(vehicleDataDTO.pressureToTorqueRatio);
        ChassisModel chassisModel = new ChassisModel(vehicleDataDTO.mass);
        Name vehicleName = new Name(vehicleDataDTO.vehicleName);
        PowertrainModel powertrainModel = new PowertrainModel(vehicleDataDTO.power, vehicleDataDTO.torque);
        TransmissionModel transmissionModel = new TransmissionModel(vehicleDataDTO.numberOfGears, vehicleDataDTO.gears, vehicleDataDTO.finalDriveRatio);
        TyreModel tyreModel = new TyreModel(vehicleDataDTO.longitudinalGrip, vehicleDataDTO.lateralGrip);

        return new VehicleParameters(aeroModel, brakeModel, chassisModel, vehicleName, powertrainModel, transmissionModel, tyreModel);
    }
}
