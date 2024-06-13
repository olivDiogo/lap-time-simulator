package laptimesimulator.utils.dto.inputDataDTO;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(force = true)
@AllArgsConstructor
public class VehicleDataInDTO {
    public final String vehicleID;
    public final String vehicleName;
    public final double downforceCoefficient;
    public final double dragCoefficient;
    public final double pressureToTorqueRatio;
    public final double mass;
    public final double powerMax;
    public final double torqueMax;
    public final Double rpmPowerMax;
    public final Double rpmTorqueMax;
    public final String powertrainType;
    public final int numberOfGears;
    public final List<Double> gears;
    public final double finalDriveRatio;
    public final double longitudinalGrip;
    public final double lateralGrip;
    public final double tyreRadius;
}
