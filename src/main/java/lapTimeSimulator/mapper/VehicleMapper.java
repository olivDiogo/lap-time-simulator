package lapTimeSimulator.mapper;

import lapTimeSimulator.ddd.IMapper;
import lapTimeSimulator.domain.valueObject.*;
import lapTimeSimulator.domain.vehicle.Vehicle;
import lapTimeSimulator.utils.dto.outputDataDTO.VehicleDataOutDTO;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class VehicleMapper implements IMapper<Vehicle, VehicleDataOutDTO> {

    /**
     * Method to convert a Vehicle into a VehicleDTO.
     *
     * @param vehicle is the vehicle object to be converted.
     * @return the VehicleDTO.
     */
    @Override
    public VehicleDataOutDTO toDTO(Vehicle vehicle) {
        if(vehicle == null)
            throw new IllegalArgumentException("The vehicle cannot be null.");

        String vehicleID = vehicle.getVehicleID().getId();

        /* Getting the vehicle parameters objects */
        AeroModel aeroModel = vehicle.getAeroModel();
        BrakeModel brakeModel = vehicle.getBrakeModel();
        ChassisModel chassisModel = vehicle.getChassisModel();
        Name vehicleName = vehicle.getVehicleName();
        PowertrainModelCombustion powertrainModelCombustion = vehicle.getPowertrainModelCombustion();
        TransmissionModel transmissionModel = vehicle.getTransmissionModel();
        TyreModel tyreModel = vehicle.getTyreModel();

        /* Parsing the vehicle parameters objects to primitives*/
        String vehicleNameString = vehicleName.getStrName();
        double downforceCoefficient = aeroModel.getDownforceCoefficient();
        double dragCoefficient = aeroModel.getDragCoefficient();
        double pressureToTorqueRatio = brakeModel.getPressureToTorqueRatio();
        double mass = chassisModel.getMass();
        double powerMax = powertrainModelCombustion.getPowerMax();
        double torqueMax = powertrainModelCombustion.getTorqueMax();
        double rpmPowerMax = powertrainModelCombustion.getRpmPowerMax();
        double rpmTorqueMax = powertrainModelCombustion.getRpmTorqueMax();
        int numberOfGears = transmissionModel.getNumberOfGears();
        List<Double> gears = transmissionModel.getGears();
        double finalDriveRatio = transmissionModel.getFinalDriveRatio();
        double longitudinalGrip = tyreModel.getLongitudinalGrip();
        double lateralGrip = tyreModel.getLateralGrip();
        double tyreRadius = tyreModel.getTyreRadius();

        return new VehicleDataOutDTO(vehicleID, vehicleNameString, downforceCoefficient, dragCoefficient, pressureToTorqueRatio, mass,
                powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, numberOfGears, gears, finalDriveRatio,
                longitudinalGrip, lateralGrip, tyreRadius);
    }

    /**
     * Method to convert a list of Vehicles into a list of VehicleDTOs.
     *
     * @param vehicles is the list of domain entities to be converted.
     * @return the list of DTOs.
     */
    @Override
    public List<VehicleDataOutDTO> toDTO(List<Vehicle> vehicles) {
        if(vehicles == null)
            throw new IllegalArgumentException("The list of vehicles cannot be null.");

        return vehicles.stream().map(this::toDTO).toList();
    }
}
