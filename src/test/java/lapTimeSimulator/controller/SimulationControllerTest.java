package lapTimeSimulator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lapTimeSimulator.domain.simulation.Simulation;
import lapTimeSimulator.domain.track.ITrackFactory;
import lapTimeSimulator.domain.track.Track;
import lapTimeSimulator.domain.track.TrackFactory;
import lapTimeSimulator.domain.valueObject.*;
import lapTimeSimulator.domain.vehicle.IVehicleFactory;
import lapTimeSimulator.domain.vehicle.Vehicle;
import lapTimeSimulator.domain.vehicle.VehicleFactory;
import lapTimeSimulator.persistence.simulation.ISimulationRepository;
import lapTimeSimulator.persistence.track.ITrackRepository;
import lapTimeSimulator.persistence.vehicle.IVehicleRepository;
import lapTimeSimulator.utils.dto.inputDataDTO.SimulationDataInDTO;
import lapTimeSimulator.utils.dto.outputDataDTO.SimulationDataOutDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SimulationControllerTest {
    @MockBean
    private ISimulationRepository simulationRepository;

    @MockBean
    private IVehicleRepository vehicleRepository;

    @MockBean
    private ITrackRepository trackRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnSimulationDataOutDTOEntityModel_whenStartSimulationIsCalledWithValidArguments() throws Exception {
        // Arrange
        String simulationName = "simulationName";

        String strVehicleID = "vehicleID";
        VehicleID vehicleID = new VehicleID(strVehicleID);
        String strTrackID = "trackID";
        TrackID trackID = new TrackID(strTrackID);
        Name trackName = new Name("trackName");

        AeroModel aeroModel = mock(AeroModel.class);
        BrakeModel brakeModel = mock(BrakeModel.class);
        ChassisModel chassisModel = mock(ChassisModel.class);
        Name vehicleName = mock(Name.class);
        PowertrainModel powertrainModel = mock(PowertrainModel.class);
        TransmissionModel transmissionModel = mock(TransmissionModel.class);
        TyreModel tyreModel = mock(TyreModel.class);
        VehicleParameters vehicleParameters = new VehicleParameters(aeroModel, brakeModel, chassisModel, vehicleName, powertrainModel, transmissionModel, tyreModel);

        SimulationDataInDTO simulationDataInDTO = new SimulationDataInDTO(simulationName, strVehicleID, strTrackID);

        IVehicleFactory vehicleFactory = new VehicleFactory();
        Vehicle vehicle = vehicleFactory.createVehicle(vehicleID, vehicleParameters);
        when(vehicleRepository.ofIdentity(vehicleID)).thenReturn(java.util.Optional.of(vehicle));

        ITrackFactory trackFactory = new TrackFactory();
        Track track = trackFactory.createTrack(trackID, trackName);
        when(trackRepository.ofIdentity(trackID)).thenReturn(java.util.Optional.of(track));

        // Act + Assert
        MvcResult result = mockMvc.perform(post("/simulations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(simulationDataInDTO)))
                .andExpect(status().isCreated())
                .andReturn();

        // Assert
        String content = result.getResponse().getContentAsString();
        SimulationDataOutDTO simulation = objectMapper.readValue(content, SimulationDataOutDTO.class);
        assertEquals(simulationName, simulation.simulationName);
    }

}
