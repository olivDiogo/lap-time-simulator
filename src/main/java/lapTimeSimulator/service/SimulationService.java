package lapTimeSimulator.service;

import lapTimeSimulator.ddd.IRepository;
import lapTimeSimulator.domain.simulation.ISimulationFactory;
import lapTimeSimulator.domain.simulation.Simulation;
import lapTimeSimulator.domain.track.Track;
import lapTimeSimulator.domain.valueObject.Name;
import lapTimeSimulator.domain.valueObject.SimulationID;
import lapTimeSimulator.domain.valueObject.TrackID;
import lapTimeSimulator.domain.valueObject.VehicleID;
import lapTimeSimulator.domain.vehicle.Vehicle;
import lapTimeSimulator.mapper.SimulationMapper;
import lapTimeSimulator.utils.dto.outputDataDTO.SimulationDataOutDTO;
import lapTimeSimulator.utils.simulation.SimulationStarter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class SimulationService {
    private ISimulationFactory simulationFactory;
    private IRepository<SimulationID, Simulation> simulationRepository;
    private IRepository<TrackID, Track> trackRepository;
    private IRepository<VehicleID, Vehicle> vehicleRepository;
    private SimulationMapper simulationMapper;

    /**
     * Starts a simulation, by generating the simulation data and saving it to a file.
     *
     * @param simulationName is the simulationName of the simulation.
     * @param vehicleID is the vehicle identifier.
     * @param trackID is the track identifier.
     * @return the simulation entity.
     */
    public Simulation startSimulation(Name simulationName, VehicleID vehicleID, TrackID trackID) {
        Simulation simulation = simulationFactory.createSimulation(simulationName, vehicleID, trackID);
        simulationRepository.save(simulation);

        Vehicle vehicle = vehicleRepository.ofIdentity(vehicleID).get();
        Track track = trackRepository.ofIdentity(trackID).get();

        SimulationDataOutDTO simulationDataOutDTO = simulationMapper.toDTO(simulation, vehicle, track);

        SimulationStarter.startSimulation(simulationDataOutDTO);

        return simulation;
    }
}