package lapTimeSimulator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lapTimeSimulator.ddd.IMapper;
import lapTimeSimulator.domain.vehicle.IVehicleFactory;
import lapTimeSimulator.domain.vehicle.Vehicle;
import lapTimeSimulator.domain.vehicle.VehicleFactory;
import lapTimeSimulator.mapper.VehicleMapper;
import lapTimeSimulator.persistence.vehicle.IVehicleRepository;
import lapTimeSimulator.service.VehicleService;
import lapTimeSimulator.utils.dto.VehicleDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        VehicleService vehicleService = new VehicleService(vehicleFactory, vehicleRepository);
        IMapper<Vehicle, VehicleDTO> vehicleMapper = new VehicleMapper();

        // Act
        VehicleController vehicleController = new VehicleController(vehicleService, vehicleMapper);

        // Assert
        assertNotNull(vehicleController);
    }

    @Test
    void shouldThrowException_whenVehicleServiceIsNull() {
        // Arrange
        IMapper<Vehicle, VehicleDTO> vehicleMapper = new VehicleMapper();

        String expectedMessage = "Vehicle service and mapper cannot be null.";

        // Act & Assert
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new VehicleController(null, vehicleMapper));

        // Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowException_whenVehicleMapperIsNull() {
        // Arrange
        IVehicleFactory vehicleFactory = new VehicleFactory();
        VehicleService vehicleService = new VehicleService(vehicleFactory, vehicleRepository);

        String expectedMessage = "Vehicle service and mapper cannot be null.";

        // Act & Assert
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                new VehicleController(vehicleService, null));

        // Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldCreateVehicle_whenParametersAreValid() throws Exception {
        // Arrange
        String vehicleID = "vehicleID";
        String vehicleName = "vehicleName";

        VehicleDTO vehicleDataDTO = new VehicleDTO(vehicleID, vehicleName, 1.0,
                1.0, 1.0, 1.0, 1.0, 1.0,
                1, List.of(1.0, 2.4, 5.4), 1.0, 1.0, 1.0);

        // Act + Assert
        MvcResult result = mockMvc.perform(post("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vehicleDataDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        // Assert
        String content = result.getResponse().getContentAsString();
        VehicleDTO vehicle = objectMapper.readValue(content, VehicleDTO.class);
        assertEquals(vehicleName, vehicle.vehicleName);

    }

    @Test
    void shouldThrowException_whenVehicleDTOIsNull() {
        // Arrange
        IVehicleFactory vehicleFactory = new VehicleFactory();
        VehicleService vehicleService = new VehicleService(vehicleFactory, vehicleRepository);
        IMapper<Vehicle, VehicleDTO> vehicleMapper = new VehicleMapper();
        VehicleController vehicleController = new VehicleController(vehicleService, vehicleMapper);

        String expectedMessage = "Vehicle DTO cannot be null.";

        // Act & Assert
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                vehicleController.createVehicle(null));

        // Assert
        String actualMessage = e.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void shouldThrowBadRequest_whenGearsListIsNull() throws Exception {
        // Arrange
        String vehicleID = "vehicleID";
        String vehicleName = "vehicleName";

        VehicleDTO vehicleDTO = new VehicleDTO(vehicleID, vehicleName, 1.0,
                1.0, 1.0, 1.0, 1.0, 1.0,
                1, null, 1.0, 1.0, 1.0);

        // Act + Assert
        mockMvc.perform(post("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vehicleDTO)))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowBadRequest_whenVehicleIDIsNullOrBlank(String vehicleID) throws Exception {
        // Arrange
        String vehicleName = "vehicleName";

        VehicleDTO vehicleDTO = new VehicleDTO(vehicleID, vehicleName, 1.0,
                1.0, 1.0, 1.0, 1.0, 1.0,
                1, List.of(1.0, 2.4, 5.4), 1.0, 1.0, 1.0);

        // Act + Assert
        mockMvc.perform(post("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vehicleDTO)))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowBadRequest_whenVehicleNameIsNullOrBlank(String vehicleName) throws Exception {
        // Arrange
        String vehicleID = "vehicleID";

        VehicleDTO vehicleDTO = new VehicleDTO(vehicleID, vehicleName, 1.0,
                1.0, 1.0, 1.0, 1.0, 1.0,
                1, List.of(1.0, 2.4, 5.4), 1.0, 1.0, 1.0);

        // Act + Assert
        mockMvc.perform(post("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(vehicleDTO)))
                .andExpect(status().isBadRequest());
    }
}
