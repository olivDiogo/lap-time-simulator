package laptimesimulator.mapper;

import laptimesimulator.domain.simulation.Simulation;
import laptimesimulator.domain.track.Track;
import laptimesimulator.domain.valueObject.*;
import laptimesimulator.domain.vehicle.Vehicle;
import laptimesimulator.utils.dto.outputDataDTO.SimulationDataOutDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SimulationMapperTest {

    @Test
    void shouldCreateSimulationDataOutDTO_whenParametersAreValid() {
        // Arrange
        String simulationID = "1";
        Simulation simulation = mock(Simulation.class);
        when(simulation.getSimulationID()).thenReturn(mock(SimulationID.class));
        when(simulation.getSimulationID().getId()).thenReturn(simulationID);
        when(simulation.getSimulationName()).thenReturn(mock(Name.class));
        when(simulation.getSimulationName().getStrName()).thenReturn("Simulation");

        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getVehicleID()).thenReturn(mock(VehicleID.class));
        when(vehicle.getVehicleID().getId()).thenReturn("2");
        when(vehicle.getVehicleName()).thenReturn(mock(Name.class));
        when(vehicle.getVehicleName().getStrName()).thenReturn("F1");
        when(vehicle.getAeroModel()).thenReturn(mock(AeroModel.class));
        when(vehicle.getAeroModel().getDownforceCoefficient()).thenReturn(1.0);
        when(vehicle.getAeroModel()).thenReturn(mock(AeroModel.class));
        when(vehicle.getAeroModel().getDragCoefficient()).thenReturn(-1.0);
        when(vehicle.getBrakeModel()).thenReturn(mock(BrakeModel.class));
        when(vehicle.getBrakeModel().getPressureToTorqueRatio()).thenReturn(1.0);
        when(vehicle.getChassisModel()).thenReturn(mock(ChassisModel.class));
        when(vehicle.getChassisModel().getMass()).thenReturn(1.0);
        when(vehicle.getPowertrainModel()).thenReturn(mock(PowertrainModel.class));
        when(vehicle.getPowertrainModel().getPowerMax()).thenReturn(1.0);
        when(vehicle.getPowertrainModel()).thenReturn(mock(PowertrainModel.class));
        when(vehicle.getPowertrainModel().getTorqueMax()).thenReturn(1.0);
        when(vehicle.getPowertrainModel()).thenReturn(mock(PowertrainModel.class));
        when(vehicle.getPowertrainModel().getRpmPowerMax()).thenReturn(1.0);
        when(vehicle.getPowertrainModel()).thenReturn(mock(PowertrainModel.class));
        when(vehicle.getPowertrainModel().getRpmTorqueMax()).thenReturn(1.0);
        when(vehicle.getTransmissionModel()).thenReturn(mock(TransmissionModel.class));
        when(vehicle.getTransmissionModel().getNumberOfGears()).thenReturn(1);
        when(vehicle.getTransmissionModel()).thenReturn(mock(TransmissionModel.class));
        when(vehicle.getTransmissionModel().getGears()).thenReturn(List.of(1.0));
        when(vehicle.getTransmissionModel()).thenReturn(mock(TransmissionModel.class));
        when(vehicle.getTransmissionModel().getFinalDriveRatio()).thenReturn(1.0);
        when(vehicle.getTyreModel()).thenReturn(mock(TyreModel.class));
        when(vehicle.getTyreModel().getLongitudinalGrip()).thenReturn(1.0);
        when(vehicle.getTyreModel()).thenReturn(mock(TyreModel.class));
        when(vehicle.getTyreModel().getLateralGrip()).thenReturn(1.0);
        when(vehicle.getTyreModel()).thenReturn(mock(TyreModel.class));
        when(vehicle.getTyreModel().getTyreRadius()).thenReturn(1.0);

        Track track = mock(Track.class);
        when(track.getTrackID()).thenReturn(mock(laptimesimulator.domain.valueObject.TrackID.class));
        when(track.getTrackID().getId()).thenReturn("1");
        when(track.getTrackName()).thenReturn(mock(Name.class));
        when(track.getTrackName().getStrName()).thenReturn("Monza");

        SimulationMapper simulationMapper = new SimulationMapper();

        // Act
        SimulationDataOutDTO simulationDataOutDTO = simulationMapper.toDTO(simulation, vehicle, track);

        // Assert
        assertEquals(simulationID, simulationDataOutDTO.simulationID);
    }

    @Test
    void shouldThrowIllegalArgumentException_whenSimulationIsNull() {
        // Arrange
        Simulation simulation = null;
        Vehicle vehicle = mock(Vehicle.class);
        Track track = mock(Track.class);

        SimulationMapper simulationMapper = new SimulationMapper();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> simulationMapper.toDTO(simulation, vehicle, track));
        assertNotNull(exception);
        assertEquals("The simulation parameters cannot be null.", exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentException_whenVehicleIsNull() {
        // Arrange
        Simulation simulation = mock(Simulation.class);
        Vehicle vehicle = null;
        Track track = mock(Track.class);

        SimulationMapper simulationMapper = new SimulationMapper();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> simulationMapper.toDTO(simulation, vehicle, track));
        assertNotNull(exception);
        assertEquals("The simulation parameters cannot be null.", exception.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentException_whenTrackIsNull() {
        // Arrange
        Simulation simulation = mock(Simulation.class);
        Vehicle vehicle = mock(Vehicle.class);
        Track track = null;

        SimulationMapper simulationMapper = new SimulationMapper();

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> simulationMapper.toDTO(simulation, vehicle, track));
        assertNotNull(exception);
        assertEquals("The simulation parameters cannot be null.", exception.getMessage());
    }
}
