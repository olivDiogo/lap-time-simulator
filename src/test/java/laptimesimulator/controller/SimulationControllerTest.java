package laptimesimulator.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import laptimesimulator.domain.simulation.Simulation;
import laptimesimulator.domain.track.ITrackFactory;
import laptimesimulator.domain.track.Track;
import laptimesimulator.domain.track.TrackFactory;
import laptimesimulator.domain.valueObject.*;
import laptimesimulator.domain.vehicle.IVehicleFactory;
import laptimesimulator.domain.vehicle.Vehicle;
import laptimesimulator.domain.vehicle.VehicleFactory;
import laptimesimulator.mapper.SimulationMapper;
import laptimesimulator.persistence.dataModel.SimulationDataModel;
import laptimesimulator.persistence.simulation.ISimulationRepository;
import laptimesimulator.persistence.track.ITrackRepository;
import laptimesimulator.persistence.vehicle.IVehicleRepository;
import laptimesimulator.utils.dto.inputDataDTO.SimulationDataInDTO;
import laptimesimulator.utils.dto.outputDataDTO.SimulationDataOutDTO;
import laptimesimulator.utils.dto.outputDataDTO.SimulationInfoOutDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SimulationControllerTest {
    @MockBean
    private IVehicleRepository vehicleRepository;

    @MockBean
    private ITrackRepository trackRepository;

    @MockBean
    private ISimulationRepository simulationRepository;

    @MockBean
    private SimulationMapper simulationMapper;

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
        String strVehicleName = "vehicleName";
        String strTrackID = "trackID";
        TrackID trackID = new TrackID(strTrackID);
        Name trackName = new Name("trackName");
        TrackLength trackLength = new TrackLength(1000);

        AeroModel aeroModel = mock(AeroModel.class);
        BrakeModel brakeModel = mock(BrakeModel.class);
        ChassisModel chassisModel = mock(ChassisModel.class);
        Name vehicleName = new Name(strVehicleName);
        PowertrainModel powertrainModel = mock(PowertrainModel.class);
        TransmissionModel transmissionModel = mock(TransmissionModel.class);
        TyreModel tyreModel = mock(TyreModel.class);
        VehicleParameters vehicleParameters = new VehicleParameters(aeroModel, brakeModel, chassisModel, vehicleName, powertrainModel, transmissionModel, tyreModel);

        SimulationDataInDTO simulationDataInDTO = new SimulationDataInDTO(simulationName, strVehicleID, strTrackID);

        IVehicleFactory vehicleFactory = new VehicleFactory();
        Vehicle vehicle = vehicleFactory.createVehicle(vehicleID, vehicleParameters);
        when(vehicleRepository.ofIdentity(vehicleID)).thenReturn(java.util.Optional.of(vehicle));

        ITrackFactory trackFactory = new TrackFactory();
        Track track = trackFactory.createTrack(trackID, trackName, trackLength);
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
        System.out.println(result.getResponse().getContentAsString());
        assertEquals(simulationName, simulation.simulationName);
    }

    @Test
    void shouldReturnBadRequest_whenStartSimulationIsCalledWithNoVehicle() throws Exception {
        // Arrange
        String simulationName = "simulationName";

        String strVehicleID = "vehicleID";
        VehicleID vehicleID = new VehicleID(strVehicleID);
        String strTrackID = "trackID";
        TrackID trackID = new TrackID(strTrackID);
        String strTrackName = "AIA";
        Name trackName = new Name(strTrackName);
        TrackLength trackLength = new TrackLength(1000);

        SimulationDataInDTO simulationDataInDTO = new SimulationDataInDTO(simulationName, strVehicleID, strTrackID);


        when(vehicleRepository.ofIdentity(vehicleID)).thenReturn(java.util.Optional.empty());

        ITrackFactory trackFactory = new TrackFactory();
        Track track = trackFactory.createTrack(trackID, trackName, trackLength);
        when(trackRepository.ofIdentity(trackID)).thenReturn(java.util.Optional.of(track));

        // Act + Assert
        mockMvc.perform(post("/simulations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(simulationDataInDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequest_whenStartSimulationIsCalledWithNoTrack() throws Exception {
        // Arrange
        String simulationName = "simulationName";

        String strVehicleID = "vehicleID";
        VehicleID vehicleID = new VehicleID(strVehicleID);
        String strTrackID = "trackID";
        TrackID trackID = new TrackID(strTrackID);

        SimulationDataInDTO simulationDataInDTO = new SimulationDataInDTO(simulationName, strVehicleID, strTrackID);

        AeroModel aeroModel = mock(AeroModel.class);
        BrakeModel brakeModel = mock(BrakeModel.class);
        ChassisModel chassisModel = mock(ChassisModel.class);
        Name vehicleName = mock(Name.class);
        PowertrainModel powertrainModel = mock(PowertrainModel.class);
        TransmissionModel transmissionModel = mock(TransmissionModel.class);
        TyreModel tyreModel = mock(TyreModel.class);
        VehicleParameters vehicleParameters = new VehicleParameters(aeroModel, brakeModel, chassisModel, vehicleName, powertrainModel, transmissionModel, tyreModel);

        IVehicleFactory vehicleFactory = new VehicleFactory();
        Vehicle vehicle = vehicleFactory.createVehicle(vehicleID, vehicleParameters);
        when(vehicleRepository.ofIdentity(vehicleID)).thenReturn(java.util.Optional.of(vehicle));

        when(trackRepository.ofIdentity(trackID)).thenReturn(java.util.Optional.empty());

        // Act + Assert
        mockMvc.perform(post("/simulations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(simulationDataInDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequest_whenStartSimulationIsCalledWithNullSimulationName() throws Exception {
        // Arrange
        String strVehicleID = "vehicleID";
        String strTrackID = "trackID";

        SimulationDataInDTO simulationDataInDTO = new SimulationDataInDTO(null, strVehicleID, strTrackID);

        // Act + Assert
        mockMvc.perform(post("/simulations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(simulationDataInDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequest_whenStartSimulationIsCalledWithEmptySimulationName() throws Exception {
        // Arrange
        String strVehicleID = "vehicleID";
        String strTrackID = "trackID";

        SimulationDataInDTO simulationDataInDTO = new SimulationDataInDTO("", strVehicleID, strTrackID);

        // Act + Assert
        mockMvc.perform(post("/simulations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(simulationDataInDTO)))
                .andExpect(status().isBadRequest());
    }


    @Test
    void shouldReturnBadRequest_whenStartSimulationIsCalledWithNullVehicleID() throws Exception {
        // Arrange
        String simulationName = "simulationName";
        String strTrackID = "trackID";

        SimulationDataInDTO simulationDataInDTO = new SimulationDataInDTO(simulationName, null, strTrackID);

        // Act + Assert
        mockMvc.perform(post("/simulations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(simulationDataInDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequest_whenStartSimulationIsCalledWithEmptyVehicleID() throws Exception {
        // Arrange
        String simulationName = "simulationName";
        String strTrackID = "trackID";

        SimulationDataInDTO simulationDataInDTO = new SimulationDataInDTO(simulationName, "", strTrackID);

        // Act + Assert
        mockMvc.perform(post("/simulations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(simulationDataInDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequest_whenStartSimulationIsCalledWithNullTrackID() throws Exception {
        // Arrange
        String simulationName = "simulationName";
        String strVehicleID = "vehicleID";

        SimulationDataInDTO simulationDataInDTO = new SimulationDataInDTO(simulationName, strVehicleID, null);

        // Act + Assert
        mockMvc.perform(post("/simulations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(simulationDataInDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnBadRequest_whenStartSimulationIsCalledWithEmptyTrackID() throws Exception {
        // Arrange
        String simulationName = "simulationName";
        String strVehicleID = "vehicleID";

        SimulationDataInDTO simulationDataInDTO = new SimulationDataInDTO(simulationName, strVehicleID, "");

        // Act + Assert
        mockMvc.perform(post("/simulations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(simulationDataInDTO)))
                .andExpect(status().isBadRequest());
    }

}
