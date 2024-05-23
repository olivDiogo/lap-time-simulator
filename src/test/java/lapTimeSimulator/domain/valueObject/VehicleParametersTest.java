package lapTimeSimulator.domain.valueObject;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VehicleParametersTest {
    @Test
    void shouldCreateVehicleParameters_whenAllParametersAreValidAndVehicleIsCombustionType() {
        // Arrange
        AeroModel aeroModel = new AeroModel(1.0, -1.0);
        BrakeModel brakeModel = new BrakeModel(1.0);
        ChassisModel chassisModel = new ChassisModel(1.0);
        Name vehicleName = new Name("vehicleName");
        PowertrainModelCombustion powertrainModelCombustion = new PowertrainModelCombustion(1.0, 1.0, 2, 4);
        TransmissionModel transmissionModel = new TransmissionModel(1, List.of(1.0, 2.0, 3.0), 4);
        TyreModel tyreModel = new TyreModel(1.0, 1.0, 4);

        // Act
        VehicleParameters vehicleParameters = new VehicleParameters(aeroModel, brakeModel, chassisModel, vehicleName, powertrainModelCombustion, null, transmissionModel, tyreModel);

        // Then
        assertNotNull(vehicleParameters);
    }

    @Test
    void shouldCreateVehicleParameters_whenAllParametersAreValidAndVehicleIsElectricType() {
        // Arrange
        AeroModel aeroModel = new AeroModel(1.0, -1.0);
        BrakeModel brakeModel = new BrakeModel(1.0);
        ChassisModel chassisModel = new ChassisModel(1.0);
        Name vehicleName = new Name("vehicleName");
        PowertrainModelElectric powertrainModelElectric = new PowertrainModelElectric(1.0, 2.0);
        TransmissionModel transmissionModel = new TransmissionModel(1, List.of(1.0, 2.0, 3.0), 4);
        TyreModel tyreModel = new TyreModel(1.0, 1.0, 4);

        // Act
        VehicleParameters vehicleParameters = new VehicleParameters(aeroModel, brakeModel, chassisModel, vehicleName, null, powertrainModelElectric, transmissionModel, tyreModel);

        // Then
        assertNotNull(vehicleParameters);
    }

    @Test
    void shouldThrowNullPointerException_whenAeroModelIsNull(){
        // Arrange
        BrakeModel brakeModel = new BrakeModel(1.0);
        ChassisModel chassisModel = new ChassisModel(1.0);
        Name vehicleName = new Name("vehicleName");
        PowertrainModelCombustion powertrainModelCombustion = new PowertrainModelCombustion(1.0, 1.0, 2, 4);
        TransmissionModel transmissionModel = new TransmissionModel(1, List.of(1.0, 2.0, 3.0), 4);
        TyreModel tyreModel = new TyreModel(1.0, 1.0, 4);

        // Act + Assert
        assertThrows(NullPointerException.class, () ->
                new VehicleParameters(null, brakeModel, chassisModel, vehicleName, powertrainModelCombustion, null, transmissionModel, tyreModel));
    }

    @Test
    void shouldThrowNullPointerException_whenBrakeModelIsNull(){
        // Arrange
        AeroModel aeroModel = new AeroModel(1.0, -1.0);
        ChassisModel chassisModel = new ChassisModel(1.0);
        Name vehicleName = new Name("vehicleName");
        PowertrainModelCombustion powertrainModelCombustion = new PowertrainModelCombustion(1.0, 1.0, 2, 4);
        TransmissionModel transmissionModel = new TransmissionModel(1, List.of(1.0, 2.0, 3.0), 4);
        TyreModel tyreModel = new TyreModel(1.0, 1.0, 4);

        // Act + Assert
        assertThrows(NullPointerException.class, () ->
                new VehicleParameters(aeroModel, null, chassisModel, vehicleName, powertrainModelCombustion, null, transmissionModel, tyreModel));
    }

    @Test
    void shouldThrowNullPointerException_whenChassisModelIsNull(){
        // Arrange
        AeroModel aeroModel = new AeroModel(1.0, -1.0);
        BrakeModel brakeModel = new BrakeModel(1.0);
        Name vehicleName = new Name("vehicleName");
        PowertrainModelCombustion powertrainModelCombustion = new PowertrainModelCombustion(1.0, 1.0, 2, 4);
        TransmissionModel transmissionModel = new TransmissionModel(1, List.of(1.0, 2.0, 3.0), 4);
        TyreModel tyreModel = new TyreModel(1.0, 1.0, 4);

        // Act + Assert
        assertThrows(NullPointerException.class, () ->
                new VehicleParameters(aeroModel, brakeModel, null, vehicleName, powertrainModelCombustion, null, transmissionModel, tyreModel));
    }

    @Test
    void shouldThrowNullPointerException_whenVehicleNameIsNull(){
        // Arrange
        AeroModel aeroModel = new AeroModel(1.0, -1.0);
        BrakeModel brakeModel = new BrakeModel(1.0);
        ChassisModel chassisModel = new ChassisModel(1.0);
        PowertrainModelCombustion powertrainModelCombustion = new PowertrainModelCombustion(1.0, 1.0, 2, 4);
        TransmissionModel transmissionModel = new TransmissionModel(1, List.of(1.0, 2.0, 3.0), 4);
        TyreModel tyreModel = new TyreModel(1.0, 1.0, 4);

        // Act + Assert
        assertThrows(NullPointerException.class, () ->
                new VehicleParameters(aeroModel, brakeModel, chassisModel, null, powertrainModelCombustion, null, transmissionModel, tyreModel));
    }

    @Test
    void shouldThrowNullPointerException_whenTransmissionModelIsNull(){
        // Arrange
        AeroModel aeroModel = new AeroModel(1.0, -1.0);
        BrakeModel brakeModel = new BrakeModel(1.0);
        ChassisModel chassisModel = new ChassisModel(1.0);
        Name vehicleName = new Name("vehicleName");
        PowertrainModelCombustion powertrainModelCombustion = new PowertrainModelCombustion(1.0, 1.0, 2, 4);
        TyreModel tyreModel = new TyreModel(1.0, 1.0, 4);

        // Act + Assert
        assertThrows(NullPointerException.class, () ->
                new VehicleParameters(aeroModel, brakeModel, chassisModel, vehicleName, powertrainModelCombustion, null,null, tyreModel));
    }

    @Test
    void shouldThrowNullPointerException_whenTyreModelIsNull(){
        // Arrange
        AeroModel aeroModel = new AeroModel(1.0, -5);
        BrakeModel brakeModel = new BrakeModel(1.0);
        ChassisModel chassisModel = new ChassisModel(1.0);
        Name vehicleName = new Name("vehicleName");
        PowertrainModelCombustion powertrainModelCombustion = new PowertrainModelCombustion(1.0, 1.0, 2, 4);
        TransmissionModel transmissionModel = new TransmissionModel(1, List.of(1.0, 2.0, 3.0), 4);

        // Act + Assert
        assertThrows(NullPointerException.class, () ->
                new VehicleParameters(aeroModel, brakeModel, chassisModel, vehicleName, powertrainModelCombustion, null, transmissionModel, null));
    }

    @Test
    void shouldThrowIllegalArgumentException_whenPowertrainModelCombustionAndPowertrainModelElectricAreNull(){
        // Arrange
        AeroModel aeroModel = new AeroModel(1.0, -5);
        BrakeModel brakeModel = new BrakeModel(1.0);
        ChassisModel chassisModel = new ChassisModel(1.0);
        Name vehicleName = new Name("vehicleName");
        TransmissionModel transmissionModel = new TransmissionModel(1, List.of(1.0, 2.0, 3.0), 4);
        TyreModel tyreModel = new TyreModel(1.0, 1.0, 4);

        String expectedMessage = "Powertrain model cannot be null.";

        // Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new VehicleParameters(aeroModel, brakeModel, chassisModel, vehicleName, null, null, transmissionModel, tyreModel));

        // Assert
        assertEquals(expectedMessage, e.getMessage());
    }

    @Test
    void shouldThrowIllegalArgumentException_whenPowertrainModelCombustionAndPowertrainModelElectricAreNotNull(){
        // Arrange
        AeroModel aeroModel = new AeroModel(1.0, -5);
        BrakeModel brakeModel = new BrakeModel(1.0);
        ChassisModel chassisModel = new ChassisModel(1.0);
        Name vehicleName = new Name("vehicleName");
        PowertrainModelCombustion powertrainModelCombustion = new PowertrainModelCombustion(1.0, 1.0, 2, 4);
        PowertrainModelElectric powertrainModelElectric = new PowertrainModelElectric(1.0, 2.0);
        TransmissionModel transmissionModel = new TransmissionModel(1, List.of(1.0, 2.0, 3.0), 4);
        TyreModel tyreModel = new TyreModel(1.0, 1.0, 4);

        String expectedMessage = "Only one powertrain model can be used.";

        // Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new VehicleParameters(aeroModel, brakeModel, chassisModel, vehicleName, powertrainModelCombustion, powertrainModelElectric, transmissionModel, tyreModel));

        // Assert
        assertEquals(expectedMessage, e.getMessage());
    }
}
