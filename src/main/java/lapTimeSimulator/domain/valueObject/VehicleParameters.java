package lapTimeSimulator.domain.valueObject;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@EqualsAndHashCode
@AllArgsConstructor
public class VehicleParameters {
    @NotNull(message = "The aero model cannot be null!")
    private AeroModel aeroModel;
    @NotNull(message = "The brake model cannot be null!")
    private BrakeModel brakeModel;
    @NotNull(message = "The chassis model cannot be null!")
    private ChassisModel chassisModel;
    @NotNull(message = "The vehicle name cannot be null!")
    private Name vehicleName;
    @NotNull(message = "The powertrain model cannot be null!")
    private PowertrainModel powertrainModel;
    @NotNull(message = "The transmission model cannot be null!")
    private TransmissionModel transmissionModel;
    @NotNull(message = "The tyre model cannot be null!")
    private TyreModel tyreModel;

}
