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
import laptimesimulator.utils.dto.outputDataDTO.SimulationInfoOutDTO;
import laptimesimulator.utils.simulation.SimulationStarter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
    @Transactional
    public SimulationDataOutDTO startSimulation(Name simulationName, VehicleID vehicleID, TrackID trackID) {

        Optional<Vehicle> optVehicle = vehicleRepository.ofIdentity(vehicleID);
        if (optVehicle.isEmpty()) {
            throw new IllegalArgumentException("Vehicle not found.");
        }

        Optional<Track> optTrack = trackRepository.ofIdentity(trackID);
        if (optTrack.isEmpty()) {
            throw new IllegalArgumentException("Track not found.");
        }

        Vehicle vehicle = optVehicle.get();
        Name vehicleName = vehicle.getVehicleName();
        Track track = optTrack.get();
        Name trackName = track.getTrackName();

        Simulation simulation = simulationFactory.createSimulation(simulationName, vehicleID, trackID, vehicleName, trackName);
        simulationRepository.save(simulation);

        SimulationDataOutDTO simulationDataOutDTO = simulationMapper.toDTO(simulation, vehicle, track);

        SimulationStarter.startSimulation(simulationDataOutDTO);

        return simulationDataOutDTO;
    }

    public List<SimulationInfoOutDTO> getSimulations() {
        List<SimulationInfoOutDTO> simulationInfoOutDTOList = new ArrayList<>();

        simulationRepository.findAll().forEach(simulation -> {
            Optional<Vehicle> vehicle = vehicleRepository.ofIdentity(simulation.getVehicleID());
            Optional<Track> track = trackRepository.ofIdentity(simulation.getTrackID());
            if (vehicle.isPresent() && track.isPresent()) {
                simulationInfoOutDTOList.add(simulationMapper.toInfoDTO(simulation));
            }
        });
        return simulationInfoOutDTOList;
    }

    public SimulationInfoOutDTO getSimulationByID(SimulationID simulationID) {
        Simulation simulation = simulationRepository.ofIdentity(simulationID).orElseThrow(() -> new IllegalArgumentException("Simulation not found."));
        return simulationMapper.toInfoDTO(simulation);
    }
}