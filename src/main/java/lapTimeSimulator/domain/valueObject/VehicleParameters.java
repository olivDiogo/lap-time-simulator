package lapTimeSimulator.domain.valueObject;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
public class VehicleParameters {
    private AeroModel aeroModel;
    private BrakeModel brakeModel;
    private ChassisModel chassisModel;
    private Name vehicleName;
    private PowertrainModel powertrainModel;
    private TransmissionModel transmissionModel;
    private TyreModel tyreModel;

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
