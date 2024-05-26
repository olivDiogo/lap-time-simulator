package laptimesimulator.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import laptimesimulator.ddd.IMapper;
import laptimesimulator.domain.valueObject.VehicleParameters;
import laptimesimulator.domain.vehicle.IVehicleFactory;
import laptimesimulator.domain.vehicle.Vehicle;
import laptimesimulator.domain.vehicle.VehicleFactory;
import laptimesimulator.mapper.VehicleMapper;
import laptimesimulator.persistence.vehicle.IVehicleRepository;
import laptimesimulator.service.VehicleService;
import laptimesimulator.utils.dto.inputDataDTO.VehicleDataInDTO;
import laptimesimulator.utils.dto.outputDataDTO.VehicleDataOutDTO;
import laptimesimulator.utils.vehicleParameters.VehicleParametersUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class VehicleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IVehicleRepository vehicleRepository;

    @Test
    void shouldInstantiateVehicleController_whenParametersAreValid() {
        // Arrange
        IVehicleFactory vehicleFactory = new VehicleFactory();
        IMapper<Vehicle, VehicleDataOutDTO> vehicleMapper = new VehicleMapper();
        VehicleService vehicleService = new VehicleService(vehicleFactory, vehicleRepository, vehicleMapper);

        // Act
        VehicleController vehicleController = new VehicleController(vehicleService);

        // Assert
        assertNotNull(vehicleController);
    }

    @Test
    void shouldCreateCombustionVehicle_whenParametersAreValid() throws Exception {
        // Arrange
        String vehicleName = "CombustionVehicle";
        double downforceCoefficient = 1.0;
        double dragCoefficient = -1.0;
        double pressureToTorqueRatio = 1.0;
        double mass = 1.0;
        double powerMax = 1.0;
        double torqueMax = 1.0;
        double rpmPowerMax = 1.0;
        double rpmTorqueMax = 1.0;
        int numberOfGears = 1;
        List<Double> gears = List.of(1.0);
        double finalDriveRatio = 1.0;
        double longitudinalGrip = 1.0;
        double lateralGrip = 1.0;
        double tyreRadius = 1.0;

        VehicleDataInDTO vehicleDataDTO = new VehicleDataInDTO(vehicleName, downforceCoefficient, dragCoefficient, pressureToTorqueRatio, mass, powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, numberOfGears, gears, finalDriveRatio, longitudinalGrip, lateralGrip, tyreRadius);

        // Act + Assert
        MvcResult result = mockMvc.perform(post("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vehicleDataDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        // Assert
        String content = result.getResponse().getContentAsString();
        VehicleDataOutDTO vehicle = objectMapper.readValue(content, VehicleDataOutDTO.class);
        assertEquals(vehicleName, vehicle.vehicleName);
    }

    @Test
    void shouldCreateElectricVehicle_whenParametersAreValidAndRPMsAreNull() throws Exception {
        // Arrange
        String vehicleName = "ElectricVehicle";
        double downforceCoefficient = 1.0;
        double dragCoefficient = -1.0;
        double pressureToTorqueRatio = 1.0;
        double mass = 1.0;
        double powerMax = 1.0;
        double torqueMax = 1.0;
        Double rpmPowerMax = null;
        Double rpmTorqueMax = null;
        int numberOfGears = 1;
        List<Double> gears = List.of(1.0);
        double finalDriveRatio = 1.0;
        double longitudinalGrip = 1.0;
        double lateralGrip = 1.0;
        double tyreRadius = 1.0;

        VehicleDataInDTO vehicleDataDTO = new VehicleDataInDTO(vehicleName, downforceCoefficient, dragCoefficient, pressureToTorqueRatio, mass, powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, numberOfGears, gears, finalDriveRatio, longitudinalGrip, lateralGrip, tyreRadius);

        // Act + Assert
        MvcResult result = mockMvc.perform(post("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vehicleDataDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        // Assert
        String content = result.getResponse().getContentAsString();
        VehicleDataOutDTO vehicle = objectMapper.readValue(content, VehicleDataOutDTO.class);
        assertEquals(vehicleName, vehicle.vehicleName);

    }

    @Test
    void shouldCreateElectricVehicle_whenParametersAreValidAndRPMsAreZero() throws Exception {
        // Arrange
        String vehicleName = "ElectricVehicle";
        double downforceCoefficient = 1.0;
        double dragCoefficient = -1.0;
        double pressureToTorqueRatio = 1.0;
        double mass = 1.0;
        double powerMax = 1.0;
        double torqueMax = 1.0;
        double rpmPowerMax = 0;
        double rpmTorqueMax = 0;
        int numberOfGears = 1;
        List<Double> gears = List.of(1.0);
        double finalDriveRatio = 1.0;
        double longitudinalGrip = 1.0;
        double lateralGrip = 1.0;
        double tyreRadius = 1.0;

        VehicleDataInDTO vehicleDataDTO = new VehicleDataInDTO(vehicleName, downforceCoefficient, dragCoefficient, pressureToTorqueRatio, mass, powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, numberOfGears, gears, finalDriveRatio, longitudinalGrip, lateralGrip, tyreRadius);

        // Act + Assert
        MvcResult result = mockMvc.perform(post("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vehicleDataDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        // Assert
        String content = result.getResponse().getContentAsString();
        VehicleDataOutDTO vehicle = objectMapper.readValue(content, VehicleDataOutDTO.class);
        assertEquals(vehicleName, vehicle.vehicleName);
    }

    @Test
    void shouldGetBadRequest_whenVehicleParametersAreInvalid() throws Exception {
        // Arrange
        String vehicleName = "ElectricVehicle";
        double downforceCoefficient = 1.0;
        double dragCoefficient = 111.0;
        double pressureToTorqueRatio = 1.0;
        double mass = 1.0;
        double powerMax = 1.0;
        double torqueMax = 1.0;
        double rpmPowerMax = 0.0;
        double rpmTorqueMax = 0.0;
        int numberOfGears = 1;
        List<Double> gears = List.of(1.0);
        double finalDriveRatio = 1.0;
        double longitudinalGrip = 1.0;
        double lateralGrip = 1.0;
        double tyreRadius = 1.0;

        VehicleDataInDTO vehicleDataDTO = new VehicleDataInDTO(vehicleName, downforceCoefficient, dragCoefficient, pressureToTorqueRatio, mass, powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, numberOfGears, gears, finalDriveRatio, longitudinalGrip, lateralGrip, tyreRadius);

        // Act + Assert
        mockMvc.perform(post("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vehicleDataDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldGetListOfVehicles_whenVehiclesExist() throws Exception {
        // Arrange
        String vehicleName = "ElectricVehicle";
        double downforceCoefficient = 1.0;
        double dragCoefficient = -1.0;
        double pressureToTorqueRatio = 1.0;
        double mass = 1.0;
        double powerMax = 1.0;
        double torqueMax = 1.0;
        double rpmPowerMax = 0;
        double rpmTorqueMax = 0;
        int numberOfGears = 1;
        List<Double> gears = List.of(1.0);
        double finalDriveRatio = 1.0;
        double longitudinalGrip = 1.0;
        double lateralGrip = 1.0;
        double tyreRadius = 1.0;

        VehicleDataInDTO vehicleDataDTO = new VehicleDataInDTO(vehicleName, downforceCoefficient, dragCoefficient, pressureToTorqueRatio, mass, powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, numberOfGears, gears, finalDriveRatio, longitudinalGrip, lateralGrip, tyreRadius);

        VehicleParameters vehicleParameters = VehicleParametersUtils.getVehicleParameters(vehicleDataDTO);
        IVehicleFactory vehicleFactory = new VehicleFactory();
        Vehicle vehicle = vehicleFactory.createVehicle(vehicleParameters);

        when(vehicleRepository.findAll()).thenReturn(List.of(vehicle));

        // Act + Assert
        MvcResult result = mockMvc.perform(get("/vehicles"))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String content = result.getResponse().getContentAsString();
        List<VehicleDataOutDTO> vehicles = objectMapper.readValue(content, new TypeReference<>() {
        });
        assertFalse(vehicles.isEmpty());
    }

    @Test
    void shouldGetVehicleById_whenVehicleExists() throws Exception {
        // Arrange
        String vehicleName = "ElectricVehicle";
        double downforceCoefficient = 1.0;
        double dragCoefficient = -1.0;
        double pressureToTorqueRatio = 1.0;
        double mass = 1.0;
        double powerMax = 1.0;
        double torqueMax = 1.0;
        double rpmPowerMax = 0;
        double rpmTorqueMax = 0;
        int numberOfGears = 1;
        List<Double> gears = List.of(1.0);
        double finalDriveRatio = 1.0;
        double longitudinalGrip = 1.0;
        double lateralGrip = 1.0;
        double tyreRadius = 1.0;

        VehicleDataInDTO vehicleDataDTO = new VehicleDataInDTO(vehicleName, downforceCoefficient, dragCoefficient, pressureToTorqueRatio, mass, powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, numberOfGears, gears, finalDriveRatio, longitudinalGrip, lateralGrip, tyreRadius);

        VehicleParameters vehicleParameters = VehicleParametersUtils.getVehicleParameters(vehicleDataDTO);
        IVehicleFactory vehicleFactory = new VehicleFactory();
        Vehicle vehicle = vehicleFactory.createVehicle(vehicleParameters);

        when(vehicleRepository.ofIdentity(vehicle.getVehicleID())).thenReturn(java.util.Optional.of(vehicle));

        // Act + Assert
        MvcResult result = mockMvc.perform(get("/vehicles/" + vehicle.getVehicleID().getId()))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        String content = result.getResponse().getContentAsString();
        VehicleDataOutDTO vehicleDTO = objectMapper.readValue(content, VehicleDataOutDTO.class);
        assertEquals(vehicleName, vehicleDTO.vehicleName);
    }

    @Test
    void shouldThrowException_whenVehicleIDIsNull() {
        // Arrange
        IVehicleFactory vehicleFactory = new VehicleFactory();
        IMapper<Vehicle, VehicleDataOutDTO> vehicleMapper = new VehicleMapper();
        VehicleService vehicleService = new VehicleService(vehicleFactory, vehicleRepository, vehicleMapper);

        VehicleController vehicleController = new VehicleController(vehicleService);

        String expectedMessage = "Vehicle ID cannot be null.";

        // Act & Assert
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                vehicleController.getVehicleById(null));

        // Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
