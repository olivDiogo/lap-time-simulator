package lapTimeSimulator.service;

import lapTimeSimulator.ddd.IMapper;
import lapTimeSimulator.ddd.IRepository;
import lapTimeSimulator.domain.simulation.ISimulationFactory;
import lapTimeSimulator.domain.simulation.Simulation;
import lapTimeSimulator.domain.track.Track;
import lapTimeSimulator.domain.valueObject.Name;
import lapTimeSimulator.domain.valueObject.SimulationID;
import lapTimeSimulator.domain.valueObject.TrackID;
import lapTimeSimulator.domain.valueObject.VehicleID;
import lapTimeSimulator.domain.vehicle.Vehicle;
import lapTimeSimulator.utils.dto.outputDataDTO.TrackDataOutDTO;
import lapTimeSimulator.utils.dto.outputDataDTO.VehicleDataOutDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class SimulationService {
    private ISimulationFactory simulationFactory;
    private IRepository<SimulationID, Simulation> simulationRepository;
    private IRepository<TrackID, Track> trackRepository;
    private IRepository<VehicleID, Vehicle> vehicleRepository;
    private IMapper<Vehicle, VehicleDataOutDTO> vehicleMapper;
    private IMapper<Track, TrackDataOutDTO> trackMapper;

//    public SimulationService(ISimulationFactory simulationFactory, IRepository<SimulationID, Simulation> simulationRepository, IRepository<TrackID, Track> trackRepository, IRepository<VehicleID, Vehicle> vehicleRepository) {
//        if (simulationFactory == null || simulationRepository == null
//                || trackRepository == null || vehicleRepository == null) {
//            throw new IllegalArgumentException("Simulation service parameters cannot be null.");
//        }
//        this.simulationFactory = simulationFactory;
//        this.simulationRepository = simulationRepository;
//        this.trackRepository = trackRepository;
//        this.vehicleRepository = vehicleRepository;
//    }

    public Simulation startSimulation(Name name, VehicleID vehicleID, TrackID trackID) {
        Simulation simulation = simulationFactory.createSimulation(name, vehicleID, trackID);
        simulationRepository.save(simulation);

        Vehicle vehicle = vehicleRepository.ofIdentity(vehicleID).get();
        Track track = trackRepository.ofIdentity(trackID).get();



        return simulation;
    }
}