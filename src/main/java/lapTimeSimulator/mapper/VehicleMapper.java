package lapTimeSimulator.mapper;

import lapTimeSimulator.ddd.IMapper;
import lapTimeSimulator.domain.valueObject.*;
import lapTimeSimulator.domain.vehicle.IVehicleFactory;
import lapTimeSimulator.domain.vehicle.Vehicle;
import lapTimeSimulator.utils.dto.VehicleDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
//@AllArgsConstructor
public class VehicleMapper implements IMapper<Vehicle, VehicleDTO> {
//    private final IVehicleFactory vehicleFactory;

    /**
     * Method to convert a Vehicle into a VehicleDTO.
     *
     * @param vehicle is the vehicle object to be converted.
     * @return the VehicleDTO.
     */
    @Override
    public VehicleDTO toDTO(Vehicle vehicle) {
        if(vehicle == null)
            throw new IllegalArgumentException("The vehicle cannot be null.");

        String vehicleID = vehicle.getVehicleID().getId();
        String vehicleName = vehicle.getVehicleName().getStrName();
        double downforce = vehicle.getAeroModel().getDownforce();
        double drag = vehicle.getAeroModel().getDrag();
        double pressureToTorqueRatio = vehicle.getBrakeModel().getPressureToTorqueRatio();
        double mass = vehicle.getChassisModel().getMass();
        double power = vehicle.getPowertrainModel().getPower();
        double torque = vehicle.getPowertrainModel().getTorque();
        int numberOfGears = vehicle.getTransmissionModel().getNumberOfGears();
        List<Double> gears = vehicle.getTransmissionModel().getGears();
        double finalDriveRatio = vehicle.getTransmissionModel().getFinalDriveRatio();
        double longitudinalGrip = vehicle.getTyreModel().getLongitudinalGrip();
        double lateralGrip = vehicle.getTyreModel().getLateralGrip();

        return new VehicleDTO(vehicleID, vehicleName, downforce, drag, pressureToTorqueRatio, mass, power, torque,
                numberOfGears, gears, finalDriveRatio, longitudinalGrip, lateralGrip);
    }

    /**
     * Method to convert a list of Vehicles into a list of VehicleDTOs.
     *
     * @param vehicles is the list of domain entities to be converted.
     * @return the list of DTOs.
     */
    @Override
    public List<VehicleDTO> toDTO(List<Vehicle> vehicles) {
        if(vehicles == null)
            throw new IllegalArgumentException("The list of vehicles cannot be null.");

        return vehicles.stream().map(this::toDTO).toList();
    }

//    /**
//     * Method to convert a VehicleDTO into a Vehicle.
//     *
//     * @param vehicleDTO is the DTO object to be converted.
//     * @return the Vehicle.
//     */
//    public Vehicle toDomain(VehicleDTO vehicleDTO){
//        if(vehicleDTO == null)
//            throw new IllegalArgumentException("The vehicle data DTO cannot be null.");
//
//        AeroModel aeroModel = new AeroModel(vehicleDTO.downforce, vehicleDTO.drag);
//        BrakeModel brakeModel = new BrakeModel(vehicleDTO.pressureToTorqueRatio);
//        ChassisModel chassisModel = new ChassisModel(vehicleDTO.mass);
//        Name vehicleName = new Name(vehicleDTO.vehicleName);
//        PowertrainModel powertrainModel = new PowertrainModel(vehicleDTO.power, vehicleDTO.torque);
//        TransmissionModel transmissionModel = new TransmissionModel(vehicleDTO.numberOfGears, vehicleDTO.gears, vehicleDTO.finalDriveRatio);
//        TyreModel tyreModel = new TyreModel(vehicleDTO.longitudinalGrip, vehicleDTO.lateralGrip);
//
//        VehicleParameters vehicleParameters = new VehicleParameters(aeroModel, brakeModel, chassisModel, vehicleName,powertrainModel, transmissionModel, tyreModel);
//        return vehicleFactory.createVehicle(vehicleParameters);
//    }
}
