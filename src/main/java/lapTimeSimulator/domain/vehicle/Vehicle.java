package lapTimeSimulator.domain.vehicle;

import lapTimeSimulator.ddd.IAggregateRoot;
import lapTimeSimulator.domain.valueObject.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.util.UUID;

@Getter
@EqualsAndHashCode
public class Vehicle implements IAggregateRoot<VehicleID> {
    private final VehicleID vehicleID;
    private final Name vehicleName;
    private final AeroModel aeroModel;
    private final BrakeModel brakeModel;
    private final ChassisModel chassisModel;
    private final PowertrainModel powertrainModel;
    private final TransmissionModel transmissionModel;
    private final TyreModel tyreModel;

    /**
     * Constructor of the class Vehicle
     *
     * @param vehicleParameters is the parameters of the vehicle.
     */
    Vehicle(VehicleParameters vehicleParameters) {
        this.vehicleID = new VehicleID(UUID.randomUUID().toString());
        this.vehicleName = vehicleParameters.getVehicleName();
        this.aeroModel = vehicleParameters.getAeroModel();
        this.brakeModel = vehicleParameters.getBrakeModel();
        this.chassisModel = vehicleParameters.getChassisModel();
        this.powertrainModel = vehicleParameters.getPowertrainModel();
        this.transmissionModel = vehicleParameters.getTransmissionModel();
        this.tyreModel = vehicleParameters.getTyreModel();
    }

    /**
     * Constructor of the class Vehicle with a given ID
     *
     * @param vehicleID         is the ID of the vehicle.
     * @param vehicleParameters is the parameters of the vehicle.
     */
    Vehicle(VehicleID vehicleID, VehicleParameters vehicleParameters) {
        this.vehicleID = vehicleID;
        this.vehicleName = vehicleParameters.getVehicleName();
        this.aeroModel = vehicleParameters.getAeroModel();
        this.brakeModel = vehicleParameters.getBrakeModel();
        this.chassisModel = vehicleParameters.getChassisModel();
        this.powertrainModel = vehicleParameters.getPowertrainModel();
        this.transmissionModel = vehicleParameters.getTransmissionModel();
        this.tyreModel = vehicleParameters.getTyreModel();
    }
}
