package laptimesimulator.domain.valueObject;

import lombok.*;

@Getter
@EqualsAndHashCode
public class VehicleParameters {
    private final AeroModel aeroModel;
    private final BrakeModel brakeModel;
    private final ChassisModel chassisModel;
    private final Name vehicleName;
    private final PowertrainModel powertrainModel;
    private final TransmissionModel transmissionModel;
    private final TyreModel tyreModel;

    public VehicleParameters(@NonNull AeroModel aeroModel, @NonNull BrakeModel brakeModel, @NonNull ChassisModel chassisModel, @NonNull Name vehicleName,
                             @NonNull PowertrainModel powertrainModel, @NonNull TransmissionModel transmissionModel, @NonNull TyreModel tyreModel) {
        this.aeroModel = aeroModel;
        this.brakeModel = brakeModel;
        this.chassisModel = chassisModel;
        this.powertrainModel = powertrainModel;
        this.vehicleName = vehicleName;
        this.transmissionModel = transmissionModel;
        this.tyreModel = tyreModel;
    }
}
