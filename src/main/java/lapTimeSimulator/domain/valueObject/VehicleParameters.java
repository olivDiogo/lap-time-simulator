package lapTimeSimulator.domain.valueObject;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class VehicleParameters {
    @NonNull
    private AeroModel aeroModel;
    @NonNull
    private BrakeModel brakeModel;
    @NonNull
    private ChassisModel chassisModel;
    @NonNull
    private Name vehicleName;
    @NonNull
    private PowertrainModel powertrainModel;
    @NonNull
    private TransmissionModel transmissionModel;
    @NonNull
    private TyreModel tyreModel;
}
