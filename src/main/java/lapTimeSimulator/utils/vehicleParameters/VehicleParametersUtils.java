package lapTimeSimulator.utils.vehicleParameters;

import lapTimeSimulator.domain.valueObject.*;
import lapTimeSimulator.utils.dto.inputDataDTO.VehicleDataInDTO;

public class VehicleParametersUtils {

    private VehicleParametersUtils() {
    }

//    /**
//     * Method to get the vehicle parameters from the vehicle data transfer object.
//     *
//     * @param vehicleDataDTO is the vehicle data transfer object.
//     * @return the vehicle parameters object.
//     */
//    public static VehicleParameters getVehicleParameters(VehicleDTO vehicleDataDTO) {
//        AeroModel aeroModel = new AeroModel(vehicleDataDTO.downforceCoefficient, vehicleDataDTO.dragCoefficient);
//        BrakeModel brakeModel = new BrakeModel(vehicleDataDTO.pressureToTorqueRatio);
//        ChassisModel chassisModel = new ChassisModel(vehicleDataDTO.mass);
//        Name vehicleName = new Name(vehicleDataDTO.vehicleName);
//        PowertrainModel powertrainModel = new PowertrainModel(vehicleDataDTO.powerMax, vehicleDataDTO.torqueMax, vehicleDataDTO.rpmPowerMax, vehicleDataDTO.rpmTorqueMax);
//        TransmissionModel transmissionModel = new TransmissionModel(vehicleDataDTO.numberOfGears, vehicleDataDTO.gears, vehicleDataDTO.finalDriveRatio);
//        TyreModel tyreModel = new TyreModel(vehicleDataDTO.longitudinalGrip, vehicleDataDTO.lateralGrip, vehicleDataDTO.tyreRadius);
//
//        return new VehicleParameters(aeroModel, brakeModel, chassisModel, vehicleName, powertrainModel, transmissionModel, tyreModel);
//    }

    /**
     * Method to get the vehicle parameters from the vehicle data input transfer object.
     *
     * @param vehicleDataInDTO is the vehicle data input transfer object.
     * @return the vehicle parameters object.
     */
    public static VehicleParameters getVehicleParameters(VehicleDataInDTO vehicleDataInDTO) {
        AeroModel aeroModel = new AeroModel(vehicleDataInDTO.downforceCoefficient, vehicleDataInDTO.dragCoefficient);
        BrakeModel brakeModel = new BrakeModel(vehicleDataInDTO.pressureToTorqueRatio);
        ChassisModel chassisModel = new ChassisModel(vehicleDataInDTO.mass);
        Name vehicleName = new Name(vehicleDataInDTO.vehicleName);
        PowertrainModelCombustion powertrainModelCombustion = new PowertrainModelCombustion(vehicleDataInDTO.powerMax, vehicleDataInDTO.torqueMax, vehicleDataInDTO.rpmPowerMax, vehicleDataInDTO.rpmTorqueMax);
        PowertrainModelElectric powertrainModelElectric = new PowertrainModelElectric(vehicleDataInDTO.powerMax, vehicleDataInDTO.torqueMax);
        TransmissionModel transmissionModel = new TransmissionModel(vehicleDataInDTO.numberOfGears, vehicleDataInDTO.gears, vehicleDataInDTO.finalDriveRatio);
        TyreModel tyreModel = new TyreModel(vehicleDataInDTO.longitudinalGrip, vehicleDataInDTO.lateralGrip, vehicleDataInDTO.tyreRadius);

        return new VehicleParameters(aeroModel, brakeModel, chassisModel, vehicleName, powertrainModelCombustion, powertrainModelElectric, transmissionModel, tyreModel);
    }


}
