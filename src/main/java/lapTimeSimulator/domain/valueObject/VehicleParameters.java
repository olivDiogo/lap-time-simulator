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
    private PowertrainModelCombustion powertrainModelCombustion;
    private PowertrainModelElectric powertrainModelElectric;
    private TransmissionModel transmissionModel;
    private TyreModel tyreModel;

    public VehicleParameters(@NonNull AeroModel aeroModel, @NonNull BrakeModel brakeModel, @NonNull ChassisModel chassisModel, @NonNull Name vehicleName,
                             PowertrainModelCombustion powertrainModelCombustion, PowertrainModelElectric powertrainModelElectric,
                             @NonNull TransmissionModel transmissionModel, @NonNull TyreModel tyreModel) {

        if (powertrainModelCombustion == null && powertrainModelElectric == null) {
            throw new IllegalArgumentException("Powertrain model cannot be null.");
        }

        if (powertrainModelCombustion != null && powertrainModelElectric != null) {
            throw new IllegalArgumentException("Only one powertrain model can be used.");
        }

        this.aeroModel = aeroModel;
        this.brakeModel = brakeModel;
        this.chassisModel = chassisModel;
        this.powertrainModelCombustion = powertrainModelCombustion;
        this.powertrainModelElectric = powertrainModelElectric;
        this.vehicleName = vehicleName;
        this.transmissionModel = transmissionModel;
        this.tyreModel = tyreModel;
    }
}
