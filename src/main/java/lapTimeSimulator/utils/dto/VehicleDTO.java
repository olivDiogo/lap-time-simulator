package lapTimeSimulator.utils.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class VehicleDTO {
    @NotNull(message = "The vehicle ID cannot be null!")
    @NotBlank(message = "The vehicle ID cannot be blank!")
    public final String vehicleID;
    @NotNull(message = "The vehicle name cannot be null!")
    @NotBlank(message = "The vehicle name cannot be blank!")
    public final String vehicleName;
    public final double downforce;
    public final double drag;
    public final double pressureToTorqueRatio;
    public final double mass;
    public final double power;
    public final double torque;
    public final int numberOfGears;
    @NotNull (message = "The gears list cannot be null!")
    public final List<Double> gears;
    public final double finalDriveRatio;
    public final double longitudinalGrip;
    public final double lateralGrip;

}
