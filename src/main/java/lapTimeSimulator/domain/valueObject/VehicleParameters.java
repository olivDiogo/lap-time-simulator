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
    private PowertrainModelCombustion powertrainModelCombustion;
    private PowertrainModelElectric powertrainModelElectric;
    @NonNull
    private TransmissionModel transmissionModel;
    @NonNull
    private TyreModel tyreModel;
}
