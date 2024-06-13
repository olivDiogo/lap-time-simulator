package laptimesimulator.persistence.dataModel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import laptimesimulator.domain.vehicle.Vehicle;
import lombok.Getter;

@Entity
@Table(name = "Vehicle")
@Getter
public class VehicleDataModel {
    @Id
    private String vehicleID;
    private String vehicleName;
    private double downforce;
    private double drag;
    private double pressureToTorqueRatio;
    private double mass;
    private double powerMax;
    private double torqueMax;
    private Double rpmPowerMax;
    private Double rpmTorqueMax;
    private String powertrainType;
    private int numberOfGears;
    private Double firstGear;
    private Double secondGear;
    private Double thirdGear;
    private Double fourthGear;
    private Double fifthGear;
    private Double sixthGear;
    private Double seventhGear;
    private Double eighthGear;
    private double finalDriveRatio;
    private double longitudinalGrip;
    private double lateralGrip;
    private double tyreRadius;

    @Version
    private long version;

    public VehicleDataModel() {
    }

    /**
     * Class constructor with parameter vehicle
     *
     * @param vehicle is the vehicle domain object to be converted to a data model.
     */
    public VehicleDataModel(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null.");
        }

        this.vehicleID = vehicle.getVehicleID().getId();
        this.vehicleName = vehicle.getVehicleName().getStrName();
        this.downforce = vehicle.getAeroModel().getDownforceCoefficient();
        this.drag = vehicle.getAeroModel().getDragCoefficient();
        this.pressureToTorqueRatio = vehicle.getBrakeModel().getPressureToTorqueRatio();
        this.mass = vehicle.getChassisModel().getMass();
        this.powerMax = vehicle.getPowertrainModel().getPowerMax();
        this.torqueMax = vehicle.getPowertrainModel().getTorqueMax();
        this.rpmPowerMax = vehicle.getPowertrainModel().getRpmPowerMax();
        this.rpmTorqueMax = vehicle.getPowertrainModel().getRpmTorqueMax();
        this.powertrainType = vehicle.getPowertrainModel().getPowertrainType().getValue();
        this.numberOfGears = vehicle.getTransmissionModel().getNumberOfGears();
        this.finalDriveRatio = vehicle.getTransmissionModel().getFinalDriveRatio();
        this.longitudinalGrip = vehicle.getTyreModel().getLongitudinalGrip();
        this.lateralGrip = vehicle.getTyreModel().getLateralGrip();
        this.tyreRadius = vehicle.getTyreModel().getTyreRadius();

        this.firstGear = numberOfGears > 0 ? vehicle.getTransmissionModel().getGears().get(0) : null;
        this.secondGear = numberOfGears > 1 ? vehicle.getTransmissionModel().getGears().get(1) : null;
        this.thirdGear = numberOfGears > 2 ? vehicle.getTransmissionModel().getGears().get(2) : null;
        this.fourthGear = numberOfGears > 3 ? vehicle.getTransmissionModel().getGears().get(3) : null;
        this.fifthGear = numberOfGears > 4 ? vehicle.getTransmissionModel().getGears().get(4) : null;
        this.sixthGear = numberOfGears > 5 ? vehicle.getTransmissionModel().getGears().get(5) : null;
        this.seventhGear = numberOfGears > 6 ? vehicle.getTransmissionModel().getGears().get(6) : null;
        this.eighthGear = numberOfGears > 7 ? vehicle.getTransmissionModel().getGears().get(7) : null;

        this.version = vehicle.getVersion();
    }


}

