package laptimesimulator.service;

import laptimesimulator.ddd.IRepository;
import laptimesimulator.domain.simulation.ISimulationFactory;
import laptimesimulator.domain.simulation.Simulation;
import laptimesimulator.domain.track.Track;
import laptimesimulator.domain.valueObject.Name;
import laptimesimulator.domain.valueObject.SimulationID;
import laptimesimulator.domain.valueObject.TrackID;
import laptimesimulator.domain.valueObject.VehicleID;
import laptimesimulator.domain.vehicle.Vehicle;
import laptimesimulator.mapper.SimulationMapper;
import laptimesimulator.utils.dto.outputDataDTO.SimulationDataOutDTO;
import laptimesimulator.utils.simulation.SimulationStarter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


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
    public SimulationDataOutDTO startSimulation(Name simulationName, VehicleID vehicleID, TrackID trackID) {
        Simulation simulation = simulationFactory.createSimulation(simulationName, vehicleID, trackID);
        simulationRepository.save(simulation);

        Optional<Vehicle> vehicle = vehicleRepository.ofIdentity(vehicleID);
        if (vehicle.isEmpty()) {
            throw new IllegalArgumentException("Vehicle not found.");
        }

        Optional<Track> track = trackRepository.ofIdentity(trackID);
        if (track.isEmpty()) {
            throw new IllegalArgumentException("Track not found.");
        }

        SimulationDataOutDTO simulationDataOutDTO = simulationMapper.toDTO(simulation, vehicle.get(), track.get());

        SimulationStarter.startSimulation(simulationDataOutDTO);

        return simulationDataOutDTO;
    }
}