package lapTimeSimulator.persistence.assembler;

import jdk.jfr.Category;
import lapTimeSimulator.domain.simulation.ISimulationFactory;
import lapTimeSimulator.domain.simulation.Simulation;
import lapTimeSimulator.domain.valueObject.Name;
import lapTimeSimulator.domain.valueObject.TrackID;
import lapTimeSimulator.domain.valueObject.VehicleID;
import lapTimeSimulator.persistence.dataModel.SimulationDataModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimulationDataModelAssembler implements IDataModelAssembler<Simulation, SimulationDataModel>{
    private final ISimulationFactory simulationFactory;

    /**
     * Constructor of the class.
     *
     * @param simulationFactory is the simulation factory.
     */
    public SimulationDataModelAssembler(ISimulationFactory simulationFactory) {
        if (simulationFactory == null) {
            throw new IllegalArgumentException("The simulation factory must be not null.");
        }
        this.simulationFactory = simulationFactory;
    }

    /**
     * Method to convert a simulation domain entity to a simulation data model.
     *
     * @param dataModel is the data model to be converted.
     * @return the domain entity.
     */
    @Override
    public Simulation toDomain(SimulationDataModel dataModel) {
        if (dataModel == null) {
            throw new IllegalArgumentException("The simulation data model must be not null.");
        }

        Name name = new Name(dataModel.getSimulationName());
        VehicleID vehicleID = new VehicleID(dataModel.getVehicleID());
        TrackID trackID = new TrackID(dataModel.getTrackID());

        return simulationFactory.createSimulation(name, vehicleID, trackID);
    }

    /**
     * Method to convert a simulation data model to a simulation domain entity.
     *
     * @param dataModels is the list of data models to be converted.
     * @return the list of domain entities.
     */
    @Override
    public List<Simulation> toDomain(List<SimulationDataModel> dataModels) {
        if (dataModels == null) {
            throw new IllegalArgumentException("The list of simulation data models must be not null.");
        }

        List<Simulation> simulations = new ArrayList<>();
        for (SimulationDataModel dataModel : dataModels) {
            simulations.add(toDomain(dataModel));
        }

        return simulations;
    }
}
