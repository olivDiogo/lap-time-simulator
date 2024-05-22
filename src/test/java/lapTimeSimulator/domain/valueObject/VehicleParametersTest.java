package lapTimeSimulator.domain.valueObject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VehicleParametersTest {
    @Test
    void shouldCreateVehicleParameters_whenAllParametersAreValid() {
        // Arrange
        AeroModel aeroModel = new AeroModel(1.0, -1.0);
        BrakeModel brakeModel = new BrakeModel(1.0);
        ChassisModel chassisModel = new ChassisModel(1.0);
        Name vehicleName = new Name("vehicleName");
        PowertrainModel powertrainModel = new PowertrainModel(1.0, 1.0, 2, 4);
        TransmissionModel transmissionModel = new TransmissionModel(1, List.of(1.0, 2.0, 3.0), 4);
        TyreModel tyreModel = new TyreModel(1.0, 1.0, 4);

        // Act
        VehicleParameters vehicleParameters = new VehicleParameters(aeroModel, brakeModel, chassisModel, vehicleName, powertrainModel, transmissionModel, tyreModel);

        // Then
        assertNotNull(vehicleParameters);
    }

    @Test
    void shouldThrowNullPointerException_whenAeroModelIsNull(){
        // Arrange
        BrakeModel brakeModel = new BrakeModel(1.0);
        ChassisModel chassisModel = new ChassisModel(1.0);
        Name vehicleName = new Name("vehicleName");
        PowertrainModel powertrainModel = new PowertrainModel(1.0, 1.0, 2, 4);
        TransmissionModel transmissionModel = new TransmissionModel(1, List.of(1.0, 2.0, 3.0), 4);
        TyreModel tyreModel = new TyreModel(1.0, 1.0, 4);

        // Act + Assert
        assertThrows(NullPointerException.class, () ->
                new VehicleParameters(null, brakeModel, chassisModel, vehicleName, powertrainModel, transmissionModel, tyreModel));
    }

    @Test
    void shouldThrowNullPointerException_whenBrakeModelIsNull(){
        // Arrange
        AeroModel aeroModel = new AeroModel(1.0, -1.0);
        ChassisModel chassisModel = new ChassisModel(1.0);
        Name vehicleName = new Name("vehicleName");
        PowertrainModel powertrainModel = new PowertrainModel(1.0, 1.0, 2, 4);
        TransmissionModel transmissionModel = new TransmissionModel(1, List.of(1.0, 2.0, 3.0), 4);
        TyreModel tyreModel = new TyreModel(1.0, 1.0, 4);

        // Act + Assert
        assertThrows(NullPointerException.class, () ->
                new VehicleParameters(aeroModel, null, chassisModel, vehicleName, powertrainModel, transmissionModel, tyreModel));
    }

    @Test
    void shouldThrowNullPointerException_whenChassisModelIsNull(){
        // Arrange
        AeroModel aeroModel = new AeroModel(1.0, -1.0);
        BrakeModel brakeModel = new BrakeModel(1.0);
        Name vehicleName = new Name("vehicleName");
        PowertrainModel powertrainModel = new PowertrainModel(1.0, 1.0, 2, 4);
        TransmissionModel transmissionModel = new TransmissionModel(1, List.of(1.0, 2.0, 3.0), 4);
        TyreModel tyreModel = new TyreModel(1.0, 1.0, 4);

        // Act + Assert
        assertThrows(NullPointerException.class, () ->
                new VehicleParameters(aeroModel, brakeModel, null, vehicleName, powertrainModel, transmissionModel, tyreModel));
    }

    @Test
    void shouldThrowNullPointerException_whenVehicleNameIsNull(){
        // Arrange
        AeroModel aeroModel = new AeroModel(1.0, -1.0);
        BrakeModel brakeModel = new BrakeModel(1.0);
        ChassisModel chassisModel = new ChassisModel(1.0);
        PowertrainModel powertrainModel = new PowertrainModel(1.0, 1.0, 2, 4);
        TransmissionModel transmissionModel = new TransmissionModel(1, List.of(1.0, 2.0, 3.0), 4);
        TyreModel tyreModel = new TyreModel(1.0, 1.0, 4);

        // Act + Assert
        assertThrows(NullPointerException.class, () ->
                new VehicleParameters(aeroModel, brakeModel, chassisModel, null, powertrainModel, transmissionModel, tyreModel));
    }

    @Test
    void shouldThrowNullPointerException_whenPowertrainModelIsNull(){
        // Arrange
        AeroModel aeroModel = new AeroModel(1.0, -1.0);
        BrakeModel brakeModel = new BrakeModel(1.0);
        ChassisModel chassisModel = new ChassisModel(1.0);
        Name vehicleName = new Name("vehicleName");
        TransmissionModel transmissionModel = new TransmissionModel(1, List.of(1.0, 2.0, 3.0), 4);
        TyreModel tyreModel = new TyreModel(1.0, 1.0, 4);

        // Act + Assert
        assertThrows(NullPointerException.class, () ->
                new VehicleParameters(aeroModel, brakeModel, chassisModel, vehicleName, null, transmissionModel, tyreModel));
    }

    @Test
    void shouldThrowNullPointerException_whenTransmissionModelIsNull(){
        // Arrange
        AeroModel aeroModel = new AeroModel(1.0, -1.0);
        BrakeModel brakeModel = new BrakeModel(1.0);
        ChassisModel chassisModel = new ChassisModel(1.0);
        Name vehicleName = new Name("vehicleName");
        PowertrainModel powertrainModel = new PowertrainModel(1.0, 1.0, 2, 4);
        TyreModel tyreModel = new TyreModel(1.0, 1.0, 4);

        // Act + Assert
        assertThrows(NullPointerException.class, () ->
                new VehicleParameters(aeroModel, brakeModel, chassisModel, vehicleName, powertrainModel, null, tyreModel));
    }

    @Test
    void shouldThrowNullPointerException_whenTyreModelIsNull(){
        // Arrange
        AeroModel aeroModel = new AeroModel(1.0, -5);
        BrakeModel brakeModel = new BrakeModel(1.0);
        ChassisModel chassisModel = new ChassisModel(1.0);
        Name vehicleName = new Name("vehicleName");
        PowertrainModel powertrainModel = new PowertrainModel(1.0, 1.0, 2, 4);
        TransmissionModel transmissionModel = new TransmissionModel(1, List.of(1.0, 2.0, 3.0), 4);

        // Act + Assert
        assertThrows(NullPointerException.class, () ->
                new VehicleParameters(aeroModel, brakeModel, chassisModel, vehicleName, powertrainModel, transmissionModel, null));
    }
}
