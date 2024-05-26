package laptimesimulator.persistence.simulation;

import laptimesimulator.domain.simulation.Simulation;
import laptimesimulator.domain.valueObject.SimulationID;
import laptimesimulator.persistence.assembler.IDataModelAssembler;
import laptimesimulator.persistence.dataModel.SimulationDataModel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class SimulationRepository implements ISimulationRepository{
    ISimulationSpringDataRepository repository;
    IDataModelAssembler<Simulation, SimulationDataModel> assembler;

    /**
     * Constructor of the class.
     *
     * @param repository is the simulation repository.
     * @param assembler is the simulation data model assembler.
     */
    public SimulationRepository(ISimulationSpringDataRepository repository, IDataModelAssembler<Simulation, SimulationDataModel> assembler) {
        if(repository == null || assembler == null)
            throw new IllegalArgumentException("The repository and the assembler must be not null.");
        this.repository = repository;
        this.assembler = assembler;
    }

    /**
     * Method to save a simulation entity.
     *
     * @param entity is the domain entity to be saved.
     * @return the saved domain entity.
     */
    @Override
    public Simulation save(Simulation entity) {
        if(entity == null)
            throw new IllegalArgumentException("The simulation must be not null.");

        SimulationDataModel dataModel = new SimulationDataModel(entity);
        repository.save(dataModel);

        return entity;
    }

    /**
     * Method to find all simulation entities.
     *
     * @return a list with all simulation entities.
     */
    @Override
    public List<Simulation> findAll() {
        List<SimulationDataModel> listSimulationDataModelSaved = repository.findAll();

        return assembler.toDomain(listSimulationDataModelSaved);
    }

    /**
     * Method to find a simulation entity by its unique identifier.
     *
     * @param objectID is the unique identifier of the domain entity.
     * @return an optional with the domain entity if it exists.
     */
    @Override
    public Optional<Simulation> ofIdentity(SimulationID objectID) {
        if(objectID == null)
            throw new IllegalArgumentException("The simulation ID must be not null.");

        Optional<SimulationDataModel> optionalSimulationDataModel = repository.findById(objectID.getId());

        return optionalSimulationDataModel.map(simulationDataModel -> assembler.toDomain(simulationDataModel));

    }

    /**
     * Method to check if a simulation entity exists by its unique identifier.
     *
     * @param objectID is the unique identifier of the domain entity.
     * @return true if the entity exists, false otherwise.
     */
    @Override
    public boolean containsOfIdentity(SimulationID objectID) {
        return repository.existsById(objectID.getId());
    }
}
