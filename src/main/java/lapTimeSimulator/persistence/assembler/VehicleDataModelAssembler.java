package lapTimeSimulator.persistence.assembler;

import lapTimeSimulator.domain.valueObject.*;
import lapTimeSimulator.domain.valueObject.AeroModel;
import lapTimeSimulator.domain.vehicle.IVehicleFactory;
import lapTimeSimulator.domain.vehicle.Vehicle;
import lapTimeSimulator.persistence.dataModel.VehicleDataModel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

        PowertrainModel powertrainModel;
        if(dataModel.getRpmPowerMax() <= 0 || dataModel.getRpmTorqueMax() <= 0) {
            powertrainModel = new PowertrainModel(dataModel.getPowerMax(), dataModel.getTorqueMax());
        } else {
            powertrainModel = new PowertrainModel(dataModel.getPowerMax(), dataModel.getTorqueMax(), dataModel.getRpmPowerMax(), dataModel.getRpmTorqueMax());
        }

        TyreModel tyreModel = new TyreModel(dataModel.getLongitudinalGrip(), dataModel.getLateralGrip(), dataModel.getTyreRadius());
        Name vehicleName = new Name(dataModel.getVehicleName());

        List<Double> gears = List.of(dataModel.getFirstGear(), dataModel.getSecondGear(), dataModel.getThirdGear(), dataModel.getFourthGear(), dataModel.getFifthGear(), dataModel.getSixthGear(), dataModel.getSeventhGear(), dataModel.getEighthGear());
        TransmissionModel transmissionModel = new TransmissionModel(dataModel.getNumberOfGears(), gears, dataModel.getFinalDriveRatio());

        VehicleParameters vehicleParameters = new VehicleParameters(aeroModel, brakeModel, chassisModel, vehicleName,
                powertrainModel, transmissionModel, tyreModel);

        return vehicleFactory.createVehicle(vehicleID, vehicleParameters);
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
