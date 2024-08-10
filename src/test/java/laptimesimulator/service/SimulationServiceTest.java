package laptimesimulator.service;

import laptimesimulator.domain.simulation.ISimulationFactory;
import laptimesimulator.domain.simulation.Simulation;
import laptimesimulator.domain.track.Track;
import laptimesimulator.domain.valueObject.Name;
import laptimesimulator.domain.valueObject.SimulationID;
import laptimesimulator.domain.valueObject.TrackID;
import laptimesimulator.domain.valueObject.VehicleID;
import laptimesimulator.domain.vehicle.Vehicle;
import laptimesimulator.mapper.SimulationMapper;
import laptimesimulator.persistence.simulation.ISimulationRepository;
import laptimesimulator.persistence.track.ITrackRepository;
import laptimesimulator.persistence.vehicle.IVehicleRepository;
import laptimesimulator.utils.dto.outputDataDTO.SimulationInfoOutDTO;
import laptimesimulator.utils.dto.outputDataDTO.simulationData.SimulationOptionsDataOutDTO;
import laptimesimulator.utils.dto.outputDataDTO.simulationData.SimulationTrackDataOutDTO;
import laptimesimulator.utils.dto.outputDataDTO.simulationData.SimulationVehicleDataOutDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SimulationServiceTest {
    @Test
    void shouldCreateSimulation_whenParametersAreValid() {
        // Arrange
        Name simulationName = mock(Name.class);
        when(simulationName.toString()).thenReturn("Simulation");
        VehicleID vehicleID = mock(VehicleID.class);
        Name vehicleName = mock(Name.class);
        TrackID trackID = mock(TrackID.class);
        Name trackName = mock(Name.class);

        Vehicle mockVehicle = mock(Vehicle.class);
        Track mockTrack = mock(Track.class);

        Simulation simulation = mock(Simulation.class);
        when(simulation.getSimulationID()).thenReturn(mock(SimulationID.class));
        when(simulation.getSimulationID().getId()).thenReturn("1");
        when(simulation.getSimulationName()).thenReturn(simulationName);
        when(simulation.getVehicleID()).thenReturn(vehicleID);
        when(simulation.getVehicleName()).thenReturn(vehicleName);
        when(simulation.getTrackID()).thenReturn(trackID);
        when(simulation.getTrackName()).thenReturn(trackName);

        SimulationInfoOutDTO simulationInfoOutDTO = new SimulationInfoOutDTO(simulation.getSimulationID().getId(), simulation.getSimulationName().toString(), simulation.getVehicleID().getId(), simulation.getVehicleName().toString(), simulation.getTrackID().getId(), simulation.getTrackName().toString());

        SimulationTrackDataOutDTO simulationTrackDataOutDTO = mock(SimulationTrackDataOutDTO.class);
        SimulationVehicleDataOutDTO simulationVehicleDataOutDTO = mock(SimulationVehicleDataOutDTO.class);
        Optional<Vehicle> vehicle = Optional.of(mockVehicle);
        Optional<Track> track = Optional.of(mockTrack);

        ISimulationFactory simulationFactory = mock(ISimulationFactory.class);

        when(mockVehicle.getVehicleName()).thenReturn(vehicleName);
        when(mockTrack.getTrackName()).thenReturn(trackName);
        when(simulationFactory.createSimulation(simulationName, vehicleID, trackID, vehicleName, trackName)).thenReturn(simulation);

        ISimulationRepository simulationRepository = mock(ISimulationRepository.class);
        when(simulationRepository.save(simulation)).thenReturn(simulation);

        IVehicleRepository vehicleRepository = mock(IVehicleRepository.class);
        when(vehicleRepository.ofIdentity(vehicleID)).thenReturn(vehicle);

        ITrackRepository trackRepository = mock(ITrackRepository.class);
        when(trackRepository.ofIdentity(trackID)).thenReturn(track);

        SimulationMapper simulationMapper = mock(SimulationMapper.class);
        when(simulationMapper.toDTO(vehicle.get())).thenReturn(simulationVehicleDataOutDTO);
        when(simulationMapper.toDTO(track.get())).thenReturn(simulationTrackDataOutDTO);
        when(simulationMapper.toInfoDTO(simulation)).thenReturn(simulationInfoOutDTO);

        SimulationService simulationService = new SimulationService(simulationFactory, simulationRepository, trackRepository, vehicleRepository, simulationMapper);

        String expectedSimulationName = "Simulation";

        // Act
        SimulationInfoOutDTO result = simulationService.createSimulation(simulationName, vehicleID, trackID);

        // Assert
        assertEquals(expectedSimulationName, result.simulationName);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenVehicleNotFound() {
        // Arrange
        Name simulationName = mock(Name.class);
        VehicleID vehicleID = mock(VehicleID.class);
        Name vehicleName = mock(Name.class);
        TrackID trackID = mock(TrackID.class);
        Name trackName = mock(Name.class);

        Simulation simulation = mock(Simulation.class);
        SimulationVehicleDataOutDTO simulationVehicleDataOutDTO = mock(SimulationVehicleDataOutDTO.class);
        Optional<Vehicle> vehicle = Optional.empty();
        Optional<Track> track = Optional.of(mock(Track.class));

        ISimulationFactory simulationFactory = mock(ISimulationFactory.class);
        when(simulationFactory.createSimulation(simulationName, vehicleID, trackID, vehicleName, trackName)).thenReturn(simulation);

        ISimulationRepository simulationRepository = mock(ISimulationRepository.class);
        when(simulationRepository.save(simulation)).thenReturn(simulation);

        IVehicleRepository vehicleRepository = mock(IVehicleRepository.class);
        when(vehicleRepository.ofIdentity(vehicleID)).thenReturn(vehicle);

        ITrackRepository trackRepository = mock(ITrackRepository.class);
        when(trackRepository.ofIdentity(trackID)).thenReturn(track);

        SimulationMapper simulationMapper = mock(SimulationMapper.class);
        when(simulationMapper.toDTO((Vehicle) null)).thenReturn(simulationVehicleDataOutDTO);

        SimulationService simulationService = new SimulationService(simulationFactory, simulationRepository, trackRepository, vehicleRepository, simulationMapper);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> simulationService.createSimulation(simulationName, vehicleID, trackID));

        // Assert
        assertEquals("Vehicle not found.", exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentException_whenTrackNotFound() {
        // Arrange
        Name simulationName = mock(Name.class);
        VehicleID vehicleID = mock(VehicleID.class);
        Name vehicleName = mock(Name.class);
        TrackID trackID = mock(TrackID.class);
        Name trackName = mock(Name.class);

        Simulation simulation = mock(Simulation.class);
        SimulationTrackDataOutDTO simulationTrackDataOutDTO = mock(SimulationTrackDataOutDTO.class);
        Optional<Vehicle> vehicle = Optional.of(mock(Vehicle.class));
        Optional<Track> track = Optional.empty();

        ISimulationFactory simulationFactory = mock(ISimulationFactory.class);
        when(simulationFactory.createSimulation(simulationName, vehicleID, trackID, vehicleName, trackName)).thenReturn(simulation);

        ISimulationRepository simulationRepository = mock(ISimulationRepository.class);
        when(simulationRepository.save(simulation)).thenReturn(simulation);

        IVehicleRepository vehicleRepository = mock(IVehicleRepository.class);
        when(vehicleRepository.ofIdentity(vehicleID)).thenReturn(vehicle);

        ITrackRepository trackRepository = mock(ITrackRepository.class);
        when(trackRepository.ofIdentity(trackID)).thenReturn(track);

        SimulationMapper simulationMapper = mock(SimulationMapper.class);
        when(simulationMapper.toDTO((Track) null)).thenReturn(simulationTrackDataOutDTO);

        SimulationService simulationService = new SimulationService(simulationFactory, simulationRepository, trackRepository, vehicleRepository, simulationMapper);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> simulationService.createSimulation(simulationName, vehicleID, trackID));

        // Assert
        assertEquals("Track not found.", exception.getMessage());
    }

    @Test
    void shouldStartSimulation_whenSimulationExists(){
        // Arrange
        SimulationID simulationID = mock(SimulationID.class);
        Name simulationName = mock(Name.class);
        Simulation simulation = mock(Simulation.class);
        VehicleID vehicleID = mock(VehicleID.class);
        Vehicle vehicle = mock(Vehicle.class);
        TrackID trackID = mock(TrackID.class);
        Track track = mock(Track.class);

        /* Defining the behaviour of the simulation repository and simulation object mocks */
        Optional<Simulation> simulationOpt = Optional.of(simulation);
        ISimulationRepository simulationRepository = mock(ISimulationRepository.class);
        when(simulationRepository.ofIdentity(simulationID)).thenReturn(simulationOpt);
        when(simulation.getVehicleID()).thenReturn(vehicleID);
        when(vehicleID.getId()).thenReturn("234");
        when(simulation.getTrackID()).thenReturn(trackID);
        when(trackID.getId()).thenReturn("345");
        when(simulation.getSimulationID()).thenReturn(simulationID);
        when(simulationID.getId()).thenReturn("1");
        when(simulation.getSimulationName()).thenReturn(simulationName);
        when(simulationName.getStrName()).thenReturn("Simulation");

        /* Defining the behaviour of the vehicle repository mock */
        Optional<Vehicle> vehicleOpt = Optional.of(vehicle);
        IVehicleRepository vehicleRepository = mock(IVehicleRepository.class);
        when(vehicleRepository.ofIdentity(vehicleID)).thenReturn(vehicleOpt);

        /* Defining the behaviour of the track repository mock */
        Optional<Track> trackOpt = Optional.of(track);
        ITrackRepository trackRepository = mock(ITrackRepository.class);
        when(trackRepository.ofIdentity(trackID)).thenReturn(trackOpt);

        /* Creating the simulation data DTOs mocks */
        SimulationVehicleDataOutDTO simulationVehicleDataOutDTO = mock(SimulationVehicleDataOutDTO.class);
        SimulationTrackDataOutDTO simulationTrackDataOutDTO = mock(SimulationTrackDataOutDTO.class);

        /* Defining the behaviour of the simulation mapper mock */
        SimulationMapper simulationMapper = mock(SimulationMapper.class);
        when(simulationMapper.toDTO(vehicle)).thenReturn(simulationVehicleDataOutDTO);
        when(simulationMapper.toDTO(track)).thenReturn(simulationTrackDataOutDTO);

        SimulationInfoOutDTO simulationInfoOutDTO = new SimulationInfoOutDTO("1", "Simulation", "234", "Vehicle", "345", "Track");
        when(simulationMapper.toInfoDTO(simulation)).thenReturn(simulationInfoOutDTO);

        /* Creating the simulation factory mock */
        ISimulationFactory simulationFactory = mock(ISimulationFactory.class);

        /* Instantiating the simulation service */
        SimulationService simulationService = new SimulationService(simulationFactory, simulationRepository, trackRepository, vehicleRepository, simulationMapper);

        // Act
        SimulationInfoOutDTO result = simulationService.startSimulation(simulationID);

        // Assert
        assertEquals(simulationInfoOutDTO.simulationID, result.simulationID);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenStartingSimulationWasNotFound(){
        // Arrange
        SimulationID simulationID = mock(SimulationID.class);
        ISimulationRepository simulationRepository = mock(ISimulationRepository.class);
        when(simulationRepository.ofIdentity(simulationID)).thenReturn(Optional.empty());

        ITrackRepository trackRepository = mock(ITrackRepository.class);
        IVehicleRepository vehicleRepository = mock(IVehicleRepository.class);
        SimulationMapper simulationMapper = mock(SimulationMapper.class);

        SimulationService simulationService = new SimulationService(mock(ISimulationFactory.class), simulationRepository, trackRepository, vehicleRepository, simulationMapper);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> simulationService.startSimulation(simulationID));

        // Assert
        assertEquals("Simulation not found.", exception.getMessage());
    }

    @Test
    void shouldGetSimulations_whenSimulationsExist() {
        // Arrange
        ISimulationFactory simulationFactory = mock(ISimulationFactory.class);
        SimulationMapper simulationMapper = mock(SimulationMapper.class);

        SimulationInfoOutDTO simulationInfoOutDTO = mock(SimulationInfoOutDTO.class);
        SimulationInfoOutDTO simulationInfoOutDTO2 = mock(SimulationInfoOutDTO.class);
        SimulationInfoOutDTO simulationInfoOutDTO3 = mock(SimulationInfoOutDTO.class);

        Simulation simulation1 = mock(Simulation.class);
        Simulation simulation2 = mock(Simulation.class);
        Simulation simulation3 = mock(Simulation.class);

        VehicleID vehicleID1 = mock(VehicleID.class);
        VehicleID vehicleID2 = mock(VehicleID.class);
        VehicleID vehicleID3 = mock(VehicleID.class);
        when(simulation1.getVehicleID()).thenReturn(vehicleID1);
        when(simulation2.getVehicleID()).thenReturn(vehicleID2);
        when(simulation3.getVehicleID()).thenReturn(vehicleID3);

        TrackID trackID1 = mock(TrackID.class);
        TrackID trackID2 = mock(TrackID.class);
        TrackID trackID3 = mock(TrackID.class);
        when(simulation1.getTrackID()).thenReturn(trackID1);
        when(simulation2.getTrackID()).thenReturn(trackID2);
        when(simulation3.getTrackID()).thenReturn(trackID3);

        ISimulationRepository simulationRepository = mock(ISimulationRepository.class);
        when(simulationRepository.findAll()).thenReturn(List.of(simulation1, simulation2, simulation3));

        Vehicle vehicle1 = mock(Vehicle.class);
        Vehicle vehicle2 = mock(Vehicle.class);
        Vehicle vehicle3 = mock(Vehicle.class);

        Track track1 = mock(Track.class);
        Track track2 = mock(Track.class);
        Track track3 = mock(Track.class);

        ITrackRepository trackRepository = mock(ITrackRepository.class);
        when(trackRepository.ofIdentity(trackID1)).thenReturn(Optional.of(track1));
        when(trackRepository.ofIdentity(trackID2)).thenReturn(Optional.of(track2));
        when(trackRepository.ofIdentity(trackID3)).thenReturn(Optional.of(track3));

        IVehicleRepository vehicleRepository = mock(IVehicleRepository.class);
        when(vehicleRepository.ofIdentity(vehicleID1)).thenReturn(Optional.of(vehicle1));
        when(vehicleRepository.ofIdentity(vehicleID2)).thenReturn(Optional.of(vehicle2));
        when(vehicleRepository.ofIdentity(vehicleID3)).thenReturn(Optional.of(vehicle3));

        SimulationService simulationService = new SimulationService(simulationFactory, simulationRepository, trackRepository, vehicleRepository, simulationMapper);

        when(simulationMapper.toInfoDTO(simulation1)).thenReturn(simulationInfoOutDTO);
        when(simulationMapper.toInfoDTO(simulation2)).thenReturn(simulationInfoOutDTO2);
        when(simulationMapper.toInfoDTO(simulation3)).thenReturn(simulationInfoOutDTO3);

        // Act
        List<SimulationInfoOutDTO> result = simulationService.getSimulations();

        // Assert
        assertEquals(3, result.size());
        assertEquals(simulationInfoOutDTO, result.get(0));
        assertEquals(simulationInfoOutDTO2, result.get(1));
        assertEquals(simulationInfoOutDTO3, result.get(2));
    }

    @Test
    void shouldGetEmptyListOfSimulations_whenNoSimulationsExist() {
        // Arrange
        ISimulationFactory simulationFactory = mock(ISimulationFactory.class);
        SimulationMapper simulationMapper = mock(SimulationMapper.class);

        ISimulationRepository simulationRepository = mock(ISimulationRepository.class);
        when(simulationRepository.findAll()).thenReturn(new ArrayList<>());

        ITrackRepository trackRepository = mock(ITrackRepository.class);
        IVehicleRepository vehicleRepository = mock(IVehicleRepository.class);

        SimulationService simulationService = new SimulationService(simulationFactory, simulationRepository, trackRepository, vehicleRepository, simulationMapper);

        // Act
        List<SimulationInfoOutDTO> result = simulationService.getSimulations();

        // Assert
        assertEquals(0, result.size());
    }

    @Test
    void shouldGetEmptyListOfSimulations_whenNoVehicleExists() {
        // Arrange
        ISimulationFactory simulationFactory = mock(ISimulationFactory.class);
        SimulationMapper simulationMapper = mock(SimulationMapper.class);

        SimulationInfoOutDTO simulationInfoOutDTO = mock(SimulationInfoOutDTO.class);
        SimulationInfoOutDTO simulationInfoOutDTO2 = mock(SimulationInfoOutDTO.class);
        SimulationInfoOutDTO simulationInfoOutDTO3 = mock(SimulationInfoOutDTO.class);

        Simulation simulation1 = mock(Simulation.class);
        Simulation simulation2 = mock(Simulation.class);
        Simulation simulation3 = mock(Simulation.class);

        VehicleID vehicleID1 = mock(VehicleID.class);
        VehicleID vehicleID2 = mock(VehicleID.class);
        VehicleID vehicleID3 = mock(VehicleID.class);
        when(simulation1.getVehicleID()).thenReturn(vehicleID1);
        when(simulation2.getVehicleID()).thenReturn(vehicleID2);
        when(simulation3.getVehicleID()).thenReturn(vehicleID3);

        TrackID trackID1 = mock(TrackID.class);
        TrackID trackID2 = mock(TrackID.class);
        TrackID trackID3 = mock(TrackID.class);
        when(simulation1.getTrackID()).thenReturn(trackID1);
        when(simulation2.getTrackID()).thenReturn(trackID2);
        when(simulation3.getTrackID()).thenReturn(trackID3);

        ISimulationRepository simulationRepository = mock(ISimulationRepository.class);
        when(simulationRepository.findAll()).thenReturn(List.of(simulation1, simulation2, simulation3));

        Track track1 = mock(Track.class);
        Track track2 = mock(Track.class);
        Track track3 = mock(Track.class);

        ITrackRepository trackRepository = mock(ITrackRepository.class);
        when(trackRepository.ofIdentity(trackID1)).thenReturn(Optional.of(track1));
        when(trackRepository.ofIdentity(trackID2)).thenReturn(Optional.of(track2));
        when(trackRepository.ofIdentity(trackID3)).thenReturn(Optional.of(track3));

        IVehicleRepository vehicleRepository = mock(IVehicleRepository.class);
        when(vehicleRepository.ofIdentity(vehicleID1)).thenReturn(Optional.empty());
        when(vehicleRepository.ofIdentity(vehicleID2)).thenReturn(Optional.empty());
        when(vehicleRepository.ofIdentity(vehicleID3)).thenReturn(Optional.empty());

        SimulationService simulationService = new SimulationService(simulationFactory, simulationRepository, trackRepository, vehicleRepository, simulationMapper);

        when(simulationMapper.toInfoDTO(simulation1)).thenReturn(simulationInfoOutDTO);
        when(simulationMapper.toInfoDTO(simulation2)).thenReturn(simulationInfoOutDTO2);
        when(simulationMapper.toInfoDTO(simulation3)).thenReturn(simulationInfoOutDTO3);

        // Act
        List<SimulationInfoOutDTO> result = simulationService.getSimulations();

        // Assert
        assertEquals(0, result.size());
    }

    @Test
    void shouldGetEmptyListOfSimulations_whenNoTrackExists() {
        // Arrange
        ISimulationFactory simulationFactory = mock(ISimulationFactory.class);
        SimulationMapper simulationMapper = mock(SimulationMapper.class);

        SimulationInfoOutDTO simulationInfoOutDTO = mock(SimulationInfoOutDTO.class);
        SimulationInfoOutDTO simulationInfoOutDTO2 = mock(SimulationInfoOutDTO.class);
        SimulationInfoOutDTO simulationInfoOutDTO3 = mock(SimulationInfoOutDTO.class);

        Simulation simulation1 = mock(Simulation.class);
        Simulation simulation2 = mock(Simulation.class);
        Simulation simulation3 = mock(Simulation.class);

        VehicleID vehicleID1 = mock(VehicleID.class);
        VehicleID vehicleID2 = mock(VehicleID.class);
        VehicleID vehicleID3 = mock(VehicleID.class);
        when(simulation1.getVehicleID()).thenReturn(vehicleID1);
        when(simulation2.getVehicleID()).thenReturn(vehicleID2);
        when(simulation3.getVehicleID()).thenReturn(vehicleID3);

        TrackID trackID1 = mock(TrackID.class);
        TrackID trackID2 = mock(TrackID.class);
        TrackID trackID3 = mock(TrackID.class);
        when(simulation1.getTrackID()).thenReturn(trackID1);
        when(simulation2.getTrackID()).thenReturn(trackID2);
        when(simulation3.getTrackID()).thenReturn(trackID3);

        ISimulationRepository simulationRepository = mock(ISimulationRepository.class);
        when(simulationRepository.findAll()).thenReturn(List.of(simulation1, simulation2, simulation3));

        Vehicle vehicle1 = mock(Vehicle.class);
        Vehicle vehicle2 = mock(Vehicle.class);
        Vehicle vehicle3 = mock(Vehicle.class);

        ITrackRepository trackRepository = mock(ITrackRepository.class);
        when(trackRepository.ofIdentity(trackID1)).thenReturn(Optional.empty());
        when(trackRepository.ofIdentity(trackID2)).thenReturn(Optional.empty());
        when(trackRepository.ofIdentity(trackID3)).thenReturn(Optional.empty());

        IVehicleRepository vehicleRepository = mock(IVehicleRepository.class);
        when(vehicleRepository.ofIdentity(vehicleID1)).thenReturn(Optional.of(vehicle1));
        when(vehicleRepository.ofIdentity(vehicleID2)).thenReturn(Optional.of(vehicle2));
        when(vehicleRepository.ofIdentity(vehicleID3)).thenReturn(Optional.of(vehicle3));

        SimulationService simulationService = new SimulationService(simulationFactory, simulationRepository, trackRepository, vehicleRepository, simulationMapper);

        when(simulationMapper.toInfoDTO(simulation1)).thenReturn(simulationInfoOutDTO);
        when(simulationMapper.toInfoDTO(simulation2)).thenReturn(simulationInfoOutDTO2);
        when(simulationMapper.toInfoDTO(simulation3)).thenReturn(simulationInfoOutDTO3);

        // Act
        List<SimulationInfoOutDTO> result = simulationService.getSimulations();

        // Assert
        assertEquals(0, result.size());
    }

    @Test
    void shouldGetSimulationById_whenSimulationExists(){
        // Arrange
        ISimulationFactory simulationFactory = mock(ISimulationFactory.class);
        ITrackRepository trackRepository = mock(ITrackRepository.class);
        IVehicleRepository vehicleRepository = mock(IVehicleRepository.class);
        SimulationMapper simulationMapper = mock(SimulationMapper.class);

        SimulationInfoOutDTO simulationInfoOutDTO = mock(SimulationInfoOutDTO.class);

        Simulation simulation1 = mock(Simulation.class);
        SimulationID simulationID = mock(SimulationID.class);
        when(simulation1.getSimulationID()).thenReturn(simulationID);
        when(simulationID.getId()).thenReturn("1");

        ISimulationRepository simulationRepository = mock(ISimulationRepository.class);
        when(simulationRepository.ofIdentity(any(SimulationID.class))).thenReturn(Optional.of(simulation1));

        SimulationService simulationService = new SimulationService(simulationFactory, simulationRepository, trackRepository, vehicleRepository, simulationMapper);

        when(simulationMapper.toInfoDTO(simulation1)).thenReturn(simulationInfoOutDTO);


        // Act
        SimulationInfoOutDTO result = simulationService.getSimulationByID(simulationID);

        // Assert
        assertEquals(simulationInfoOutDTO.simulationID, result.simulationID);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenSimulationNotFound(){
        // Arrange
        ISimulationFactory simulationFactory = mock(ISimulationFactory.class);
        ITrackRepository trackRepository = mock(ITrackRepository.class);
        IVehicleRepository vehicleRepository = mock(IVehicleRepository.class);
        SimulationMapper simulationMapper = mock(SimulationMapper.class);

        SimulationID simulationID = mock(SimulationID.class);

        ISimulationRepository simulationRepository = mock(ISimulationRepository.class);
        when(simulationRepository.ofIdentity(any(SimulationID.class))).thenReturn(Optional.empty());

        SimulationService simulationService = new SimulationService(simulationFactory, simulationRepository, trackRepository, vehicleRepository, simulationMapper);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> simulationService.getSimulationByID(simulationID));

        // Assert
        assertEquals("Simulation not found.", exception.getMessage());
    }

}
