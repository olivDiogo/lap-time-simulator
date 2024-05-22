package lapTimeSimulator.mapper;

import lapTimeSimulator.domain.valueObject.*;
import lapTimeSimulator.domain.vehicle.Vehicle;
import lapTimeSimulator.utils.dto.outputDataDTO.VehicleDataOutDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VehicleMapperTest {

    @Test
    void shouldReturnVehicleDataOutDTO_whenToDTOIsCalled() {
        // Assert
        VehicleID vehicleID = mock(VehicleID.class);
        when(vehicleID.getId()).thenReturn("1");

        Name vehicleName = mock(Name.class);
        when(vehicleName.getStrName()).thenReturn("Test Vehicle");

        AeroModel aeroModel = mock(AeroModel.class);
        when(aeroModel.getDownforceCoefficient()).thenReturn(100.0);
        when(aeroModel.getDragCoefficient()).thenReturn(50.0);

        BrakeModel brakeModel = mock(BrakeModel.class);
        when(brakeModel.getPressureToTorqueRatio()).thenReturn(0.5);

        ChassisModel chassisModel = mock(ChassisModel.class);
        when(chassisModel.getMass()).thenReturn(500.0);

        PowertrainModel powertrainModel = mock(PowertrainModel.class);
        when(powertrainModel.getPowerMax()).thenReturn(1000.0);
        when(powertrainModel.getTorqueMax()).thenReturn(500.0);

        TransmissionModel transmissionModel = mock(TransmissionModel.class);
        when(transmissionModel.getNumberOfGears()).thenReturn(6);
        when(transmissionModel.getGears()).thenReturn(List.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0));
        when(transmissionModel.getFinalDriveRatio()).thenReturn(3.0);

        TyreModel tyreModel = mock(TyreModel.class);
        when(tyreModel.getLongitudinalGrip()).thenReturn(1.0);
        when(tyreModel.getLateralGrip()).thenReturn(1.0);

        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getVehicleID()).thenReturn(vehicleID);
        when(vehicle.getVehicleName()).thenReturn(vehicleName);
        when(vehicle.getAeroModel()).thenReturn(aeroModel);
        when(vehicle.getBrakeModel()).thenReturn(brakeModel);
        when(vehicle.getChassisModel()).thenReturn(chassisModel);
        when(vehicle.getPowertrainModel()).thenReturn(powertrainModel);
        when(vehicle.getTransmissionModel()).thenReturn(transmissionModel);
        when(vehicle.getTyreModel()).thenReturn(tyreModel);

        VehicleMapper vehicleMapper = new VehicleMapper();

        // Act
        VehicleDataOutDTO vehicleDataOutDTO = vehicleMapper.toDTO(vehicle);

        // Assert
        assertEquals(vehicleID.getId(), vehicleDataOutDTO.vehicleID);
    }

    @Test
    void shouldThrowException_whenVehicleIsNull() {
        // Arrange
        VehicleMapper vehicleMapper = new VehicleMapper();
        Vehicle vehicle = null;

        String expectedMessage = "The vehicle cannot be null.";

        // Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> vehicleMapper.toDTO(vehicle));

        // Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldMapVehicleListToVehicleDataOutDTOList_whenVehicleListIsValid() {
        // Assert
        VehicleID vehicleID = mock(VehicleID.class);
        when(vehicleID.getId()).thenReturn("1");

        Name vehicleName = mock(Name.class);
        when(vehicleName.getStrName()).thenReturn("Test Vehicle");

        AeroModel aeroModel = mock(AeroModel.class);
        when(aeroModel.getDownforceCoefficient()).thenReturn(100.0);
        when(aeroModel.getDragCoefficient()).thenReturn(50.0);

        BrakeModel brakeModel = mock(BrakeModel.class);
        when(brakeModel.getPressureToTorqueRatio()).thenReturn(0.5);

        ChassisModel chassisModel = mock(ChassisModel.class);
        when(chassisModel.getMass()).thenReturn(500.0);

        PowertrainModel powertrainModel = mock(PowertrainModel.class);
        when(powertrainModel.getPowerMax()).thenReturn(1000.0);
        when(powertrainModel.getTorqueMax()).thenReturn(500.0);

        TransmissionModel transmissionModel = mock(TransmissionModel.class);
        when(transmissionModel.getNumberOfGears()).thenReturn(6);
        when(transmissionModel.getGears()).thenReturn(List.of(1.0, 2.0, 3.0, 4.0, 5.0, 6.0));
        when(transmissionModel.getFinalDriveRatio()).thenReturn(3.0);

        TyreModel tyreModel = mock(TyreModel.class);
        when(tyreModel.getLongitudinalGrip()).thenReturn(1.0);
        when(tyreModel.getLateralGrip()).thenReturn(1.0);

        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.getVehicleID()).thenReturn(vehicleID);
        when(vehicle.getVehicleName()).thenReturn(vehicleName);
        when(vehicle.getAeroModel()).thenReturn(aeroModel);
        when(vehicle.getBrakeModel()).thenReturn(brakeModel);
        when(vehicle.getChassisModel()).thenReturn(chassisModel);
        when(vehicle.getPowertrainModel()).thenReturn(powertrainModel);
        when(vehicle.getTransmissionModel()).thenReturn(transmissionModel);
        when(vehicle.getTyreModel()).thenReturn(tyreModel);

        VehicleMapper vehicleMapper = new VehicleMapper();

        List<Vehicle> vehicles = List.of(vehicle);

        // Act
        List<VehicleDataOutDTO> vehiclesDataOutDTO = vehicleMapper.toDTO(vehicles);

        // Assert
        assertEquals(vehicleID.getId(), vehiclesDataOutDTO.get(0).vehicleID);
    }
}
