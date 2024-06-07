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
import laptimesimulator.utils.dto.outputDataDTO.simulationData.SimulationOptionsDataOutDTO;
import laptimesimulator.utils.dto.outputDataDTO.simulationData.SimulationTrackDataOutDTO;
import laptimesimulator.utils.dto.outputDataDTO.simulationData.SimulationVehicleDataOutDTO;
import laptimesimulator.utils.dto.outputDataDTO.SimulationInfoOutDTO;
import laptimesimulator.utils.simulationStarter.SimulationStarter;
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
    public SimulationInfoOutDTO startSimulation(Name simulationName, VehicleID vehicleID, TrackID trackID) {

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

        SimulationVehicleDataOutDTO simulationVehicleDataOutDTO = simulationMapper.toDTO(vehicle);
        SimulationTrackDataOutDTO simulationTrackDataOutDTO = simulationMapper.toDTO(track);
        SimulationOptionsDataOutDTO simulationOptionsDataOutDTO = new SimulationOptionsDataOutDTO(simulation.getSimulationID().getId(), simulation.getSimulationName().getStrName());

        SimulationStarter.startSimulation(simulationVehicleDataOutDTO, simulationTrackDataOutDTO, simulationOptionsDataOutDTO);

        return simulationMapper.toInfoDTO(simulation);
    }

    /**
     * Gets all the simulations.
     *
     * @return the list of simulation info DTOs.
     */
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

    /**
     * Gets a simulation by its ID.
     *
     * @param simulationID is the simulation identifier.
     * @return the simulation info DTO.
     */
    public SimulationInfoOutDTO getSimulationByID(SimulationID simulationID) {
        Simulation simulation = simulationRepository.ofIdentity(simulationID).orElseThrow(() -> new IllegalArgumentException("Simulation not found."));
        return simulationMapper.toInfoDTO(simulation);
    }
}