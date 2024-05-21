package lapTimeSimulator.persistence.assembler;

import lapTimeSimulator.domain.valueObject.*;
import lapTimeSimulator.domain.valueObject.AeroModel;
import lapTimeSimulator.domain.vehicle.IVehicleFactory;
import lapTimeSimulator.domain.vehicle.Vehicle;
import lapTimeSimulator.persistence.dataModel.VehicleDataModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VehicleDataModelAssembler implements IDataModelAssembler<Vehicle, VehicleDataModel>{
    private final IVehicleFactory vehicleFactory;

    /**
     * Constructor of the class.
     *
     * @param vehicleFactory is the vehicle factory.
     */
    public VehicleDataModelAssembler(IVehicleFactory vehicleFactory) {
        if (vehicleFactory == null)
            throw new IllegalArgumentException("The vehicle factory must be not null.");
        this.vehicleFactory = vehicleFactory;
    }


    @Override
    public Vehicle toDomain(VehicleDataModel dataModel) {
        if(dataModel == null)
            throw new IllegalArgumentException("The vehicle data model must be not null.");

        VehicleID vehicleID = new VehicleID(dataModel.getVehicleID());
        AeroModel aeroModel = new AeroModel(dataModel.getDownforce(), dataModel.getDrag());
        BrakeModel brakeModel = new BrakeModel(dataModel.getPressureToTorqueRatio());
        ChassisModel chassisModel = new ChassisModel(dataModel.getMass());
        PowertrainModel powertrainModel = new PowertrainModel(dataModel.getPower(), dataModel.getTorque());
        TyreModel tyreModel = new TyreModel(dataModel.getLongitudinalGrip(), dataModel.getLateralGrip());
        Name vehicleName = new Name(dataModel.getVehicleName());

        List<Double> gears = List.of(dataModel.getFirstGear(), dataModel.getSecondGear(), dataModel.getThirdGear(), dataModel.getFourthGear(), dataModel.getFifthGear(), dataModel.getSixthGear(), dataModel.getSeventhGear(), dataModel.getEighthGear());
        TransmissionModel transmissionModel = new TransmissionModel(dataModel.getNumberOfGears(), gears, dataModel.getFinalDriveRatio());

        VehicleParameters vehicleParameters = new VehicleParameters(aeroModel, brakeModel, chassisModel, vehicleName,
                powertrainModel, transmissionModel, tyreModel);

        return vehicleFactory.createVehicle(vehicleID, vehicleParameters);
    }

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
