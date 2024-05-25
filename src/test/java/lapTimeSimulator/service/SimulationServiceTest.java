package lapTimeSimulator.service;

import lapTimeSimulator.domain.simulation.ISimulationFactory;
import lapTimeSimulator.domain.simulation.Simulation;
import lapTimeSimulator.domain.track.Track;
import lapTimeSimulator.domain.valueObject.Name;
import lapTimeSimulator.domain.valueObject.TrackID;
import lapTimeSimulator.domain.valueObject.VehicleID;
import lapTimeSimulator.domain.vehicle.Vehicle;
import lapTimeSimulator.mapper.SimulationMapper;
import lapTimeSimulator.persistence.simulation.ISimulationRepository;
import lapTimeSimulator.persistence.track.ITrackRepository;
import lapTimeSimulator.persistence.vehicle.IVehicleRepository;
import lapTimeSimulator.utils.dto.outputDataDTO.SimulationDataOutDTO;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SimulationServiceTest {
    @Test
    void shouldStartSimulation_whenParametersAreValid() {
        // Arrange
        Name simulationName = mock(Name.class);
        VehicleID vehicleID = mock(VehicleID.class);
        TrackID trackID = mock(TrackID.class);

        Simulation simulation = mock(Simulation.class);
        SimulationDataOutDTO simulationDataOutDTO = mock(SimulationDataOutDTO.class);
        Optional<Vehicle> vehicle = Optional.of(mock(Vehicle.class));
        Optional<Track> track = Optional.of(mock(Track.class));

        ISimulationFactory simulationFactory = mock(ISimulationFactory.class);
        when(simulationFactory.createSimulation(simulationName, vehicleID, trackID)).thenReturn(simulation);

        ISimulationRepository simulationRepository = mock(ISimulationRepository.class);
        when(simulationRepository.save(simulation)).thenReturn(simulation);

        IVehicleRepository vehicleRepository = mock(IVehicleRepository.class);
        when(vehicleRepository.ofIdentity(vehicleID)).thenReturn(vehicle);

        ITrackRepository trackRepository = mock(ITrackRepository.class);
        when(trackRepository.ofIdentity(trackID)).thenReturn(track);

        SimulationMapper simulationMapper = mock(SimulationMapper.class);
        when(simulationMapper.toDTO(simulation, vehicle.get(), track.get())).thenReturn(simulationDataOutDTO);

        SimulationService simulationService = new SimulationService(simulationFactory, simulationRepository, trackRepository, vehicleRepository, simulationMapper);

        // Act
        SimulationDataOutDTO result = simulationService.startSimulation(simulationName, vehicleID, trackID);

        // Assert
        assertEquals(simulationDataOutDTO, result);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenVehicleNotFound() {
        // Arrange
        Name simulationName = mock(Name.class);
        VehicleID vehicleID = mock(VehicleID.class);
        TrackID trackID = mock(TrackID.class);

        Simulation simulation = mock(Simulation.class);
        SimulationDataOutDTO simulationDataOutDTO = mock(SimulationDataOutDTO.class);
        Optional<Vehicle> vehicle = Optional.empty();
        Optional<Track> track = Optional.of(mock(Track.class));

        ISimulationFactory simulationFactory = mock(ISimulationFactory.class);
        when(simulationFactory.createSimulation(simulationName, vehicleID, trackID)).thenReturn(simulation);

        ISimulationRepository simulationRepository = mock(ISimulationRepository.class);
        when(simulationRepository.save(simulation)).thenReturn(simulation);

        IVehicleRepository vehicleRepository = mock(IVehicleRepository.class);
        when(vehicleRepository.ofIdentity(vehicleID)).thenReturn(vehicle);

        ITrackRepository trackRepository = mock(ITrackRepository.class);
        when(trackRepository.ofIdentity(trackID)).thenReturn(track);

        SimulationMapper simulationMapper = mock(SimulationMapper.class);
        when(simulationMapper.toDTO(simulation, null, track.get())).thenReturn(simulationDataOutDTO);

        SimulationService simulationService = new SimulationService(simulationFactory, simulationRepository, trackRepository, vehicleRepository, simulationMapper);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> simulationService.startSimulation(simulationName, vehicleID, trackID));

        // Assert
        assertEquals("Vehicle not found.", exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentException_whenTrackNotFound() {
        // Arrange
        Name simulationName = mock(Name.class);
        VehicleID vehicleID = mock(VehicleID.class);
        TrackID trackID = mock(TrackID.class);

        Simulation simulation = mock(Simulation.class);
        SimulationDataOutDTO simulationDataOutDTO = mock(SimulationDataOutDTO.class);
        Optional<Vehicle> vehicle = Optional.of(mock(Vehicle.class));
        Optional<Track> track = Optional.empty();

        ISimulationFactory simulationFactory = mock(ISimulationFactory.class);
        when(simulationFactory.createSimulation(simulationName, vehicleID, trackID)).thenReturn(simulation);

        ISimulationRepository simulationRepository = mock(ISimulationRepository.class);
        when(simulationRepository.save(simulation)).thenReturn(simulation);

        IVehicleRepository vehicleRepository = mock(IVehicleRepository.class);
        when(vehicleRepository.ofIdentity(vehicleID)).thenReturn(vehicle);

        ITrackRepository trackRepository = mock(ITrackRepository.class);
        when(trackRepository.ofIdentity(trackID)).thenReturn(track);

        SimulationMapper simulationMapper = mock(SimulationMapper.class);
        when(simulationMapper.toDTO(simulation, vehicle.get(), null)).thenReturn(simulationDataOutDTO);

        SimulationService simulationService = new SimulationService(simulationFactory, simulationRepository, trackRepository, vehicleRepository, simulationMapper);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> simulationService.startSimulation(simulationName, vehicleID, trackID));

        // Assert
        assertEquals("Track not found.", exception.getMessage());
    }
}
