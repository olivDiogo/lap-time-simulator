package lapTimeSimulator.utils.dto.outputDataDTO;

import java.util.List;

public class VehicleDataOutDTO {
    public final String vehicleID;
    public final String vehicleName;
    public final double downforceCoefficient;
    public final double dragCoefficient;
    public final double pressureToTorqueRatio;
    public final double mass;
    public final double powerMax;
    public final double torqueMax;
    public final double rpmPowerMax;
    public final double rpmTorqueMax;
    public final int numberOfGears;
    public final List<Double> gears;
    public final double finalDriveRatio;
    public final double longitudinalGrip;
    public final double lateralGrip;
    public final double tyreRadius;

    public VehicleDataOutDTO(String vehicleID, String vehicleName, double downforceCoefficient, double dragCoefficient, double pressureToTorqueRatio, double mass,
                             double powerMax, double torqueMax, double rpmPowerMax, double rpmTorqueMax, int numberOfGears, List<Double> gears, double finalDriveRatio,
                             double longitudinalGrip, double lateralGrip, double tyreRadius) {
        if (vehicleID == null || vehicleName == null || gears == null) {
            throw new IllegalArgumentException("The vehicle parameters cannot be null.");
        }

        this.vehicleID = vehicleID;
        this.vehicleName = vehicleName;
        this.downforceCoefficient = downforceCoefficient;
        this.dragCoefficient = dragCoefficient;
        this.pressureToTorqueRatio = pressureToTorqueRatio;
        this.mass = mass;
        this.powerMax = powerMax;
        this.torqueMax = torqueMax;
        this.rpmPowerMax = rpmPowerMax;
        this.rpmTorqueMax = rpmTorqueMax;
        this.numberOfGears = numberOfGears;
        this.gears = gears;
        this.finalDriveRatio = finalDriveRatio;
        this.longitudinalGrip = longitudinalGrip;
        this.lateralGrip = lateralGrip;
        this.tyreRadius = tyreRadius;
    }
}
