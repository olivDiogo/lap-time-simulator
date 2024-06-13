package laptimesimulator.persistence.assembler;

import laptimesimulator.domain.valueObject.*;
import laptimesimulator.domain.valueObject.AeroModel;
import laptimesimulator.domain.vehicle.IVehicleFactory;
import laptimesimulator.domain.vehicle.Vehicle;
import laptimesimulator.persistence.dataModel.VehicleDataModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class VehicleDataModelAssembler implements IDataModelAssembler<Vehicle, VehicleDataModel>{
    private final IVehicleFactory vehicleFactory;

    /**
     * Method to convert a vehicle domain entity to a vehicle data model.
     *
     * @param dataModel is the data model to be converted.
     * @return the domain entity.
     */
    @Override
    public Vehicle toDomain(VehicleDataModel dataModel) {
        if(dataModel == null)
            throw new IllegalArgumentException("The vehicle data model must be not null.");

        VehicleID vehicleID = new VehicleID(dataModel.getVehicleID());
        AeroModel aeroModel = new AeroModel(dataModel.getDownforce(), dataModel.getDrag());
        BrakeModel brakeModel = new BrakeModel(dataModel.getPressureToTorqueRatio());
        ChassisModel chassisModel = new ChassisModel(dataModel.getMass());

        PowertrainModel powertrainModel = new PowertrainModel(dataModel.getPowerMax(), dataModel.getTorqueMax(), dataModel.getRpmPowerMax(), dataModel.getRpmTorqueMax(), PowertrainType.valueOf(dataModel.getPowertrainType().toUpperCase()));

        TyreModel tyreModel = new TyreModel(dataModel.getLongitudinalGrip(), dataModel.getLateralGrip(), dataModel.getTyreRadius());
        Name vehicleName = new Name(dataModel.getVehicleName());

        List<Double> gears = Stream.of(dataModel.getFirstGear(), dataModel.getSecondGear(), dataModel.getThirdGear(), dataModel.getFourthGear(), dataModel.getFifthGear(), dataModel.getSixthGear(), dataModel.getSeventhGear(), dataModel.getEighthGear())
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        TransmissionModel transmissionModel = new TransmissionModel(dataModel.getNumberOfGears(), gears, dataModel.getFinalDriveRatio());

        VehicleParameters vehicleParameters = new VehicleParameters(aeroModel, brakeModel, chassisModel, vehicleName,
                powertrainModel, transmissionModel, tyreModel);

        long version = dataModel.getVersion();

        return vehicleFactory.createVehicle(vehicleID, vehicleParameters, version);
    }

    /**
     * Method to convert a list of vehicle data models to a list of vehicle domain entities.
     *
     * @param dataModels is the list of data models to be converted.
     * @return the list of domain entities.
     */
    @Override
    public List<Vehicle> toDomain(List<VehicleDataModel> dataModels) {
        List<Vehicle> vehicles = new ArrayList<>();

        for(VehicleDataModel dataModel : dataModels) {
            Vehicle vehicle = toDomain(dataModel);
            vehicles.add(vehicle);
        }

        return vehicles;
    }
}
