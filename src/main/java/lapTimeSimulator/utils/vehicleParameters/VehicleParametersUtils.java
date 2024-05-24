package lapTimeSimulator.utils.vehicleParameters;

import lapTimeSimulator.domain.valueObject.*;
import lapTimeSimulator.utils.dto.inputDataDTO.VehicleDataInDTO;

public class VehicleParametersUtils {

    private VehicleParametersUtils() {
    }

    /**
     * Method to get the vehicle parameters from the vehicle data input transfer object.
     *
     * @param vehicleDataInDTO is the vehicle data input transfer object.
     * @return the vehicle parameters object.
     */
    public static VehicleParameters getVehicleParameters(VehicleDataInDTO vehicleDataInDTO) {
        if (vehicleDataInDTO == null) {
            throw new IllegalArgumentException("Vehicle data input transfer object cannot be null.");
        }

        AeroModel aeroModel = new AeroModel(vehicleDataInDTO.downforceCoefficient, vehicleDataInDTO.dragCoefficient);
        BrakeModel brakeModel = new BrakeModel(vehicleDataInDTO.pressureToTorqueRatio);
        ChassisModel chassisModel = new ChassisModel(vehicleDataInDTO.mass);
        Name vehicleName = new Name(vehicleDataInDTO.vehicleName);
        TransmissionModel transmissionModel = new TransmissionModel(vehicleDataInDTO.numberOfGears, vehicleDataInDTO.gears, vehicleDataInDTO.finalDriveRatio);
        TyreModel tyreModel = new TyreModel(vehicleDataInDTO.longitudinalGrip, vehicleDataInDTO.lateralGrip, vehicleDataInDTO.tyreRadius);

        PowertrainModel powertrainModel;

        if (vehicleDataInDTO.rpmPowerMax == null || vehicleDataInDTO.rpmTorqueMax == null || vehicleDataInDTO.rpmPowerMax == 0 || vehicleDataInDTO.rpmTorqueMax == 0) {
            // Electric powertrain
            powertrainModel = new PowertrainModel(vehicleDataInDTO.powerMax, vehicleDataInDTO.torqueMax);
        } else {
            // Combustion powertrain
            powertrainModel = new PowertrainModel(vehicleDataInDTO.powerMax, vehicleDataInDTO.torqueMax, vehicleDataInDTO.rpmPowerMax, vehicleDataInDTO.rpmTorqueMax);
        }

        return new VehicleParameters(aeroModel, brakeModel, chassisModel, vehicleName, powertrainModel, transmissionModel, tyreModel);
    }
}
