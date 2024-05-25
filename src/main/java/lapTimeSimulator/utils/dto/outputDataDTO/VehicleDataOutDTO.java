package lapTimeSimulator.utils.dto.outputDataDTO;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@AllArgsConstructor
public class VehicleDataOutDTO extends RepresentationModel<VehicleDataOutDTO> {
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
    public final double numberOfGears;
    public final List<Double> gears;
    public final double finalDriveRatio;
    public final double longitudinalGrip;
    public final double lateralGrip;
    public final double tyreRadius;

}
