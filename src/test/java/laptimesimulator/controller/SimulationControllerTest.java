package laptimesimulator.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import laptimesimulator.domain.simulation.ISimulationFactory;
import laptimesimulator.domain.simulation.Simulation;
import laptimesimulator.domain.simulation.SimulationFactory;
import laptimesimulator.domain.track.ITrackFactory;
import laptimesimulator.domain.track.Track;
import laptimesimulator.domain.track.TrackFactory;
import laptimesimulator.domain.valueObject.*;
import laptimesimulator.domain.vehicle.IVehicleFactory;
import laptimesimulator.domain.vehicle.Vehicle;
import laptimesimulator.domain.vehicle.VehicleFactory;
import laptimesimulator.persistence.simulation.ISimulationRepository;
import laptimesimulator.persistence.track.ITrackRepository;
import laptimesimulator.persistence.vehicle.IVehicleRepository;
import laptimesimulator.utils.dto.inputDataDTO.SimulationDataInDTO;
import laptimesimulator.utils.dto.outputDataDTO.SimulationInfoOutDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
//@AutoConfigureMockMvc
//class SimulationControllerTest {
//    @MockBean
//    private IVehicleRepository vehicleRepository;
//
//    @MockBean
//    private ITrackRepository trackRepository;
//
//    @MockBean
//    private ISimulationRepository simulationRepository;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void shouldReturnSimulationDataOutDTOEntityModel_whenStartSimulationIsCalledWithValidArguments() throws Exception {
//        // Arrange
//        String simulationName = "simulationName";
//        String strVehicleID = "vehicleID";
//        String strTrackID = "trackID";
//        SimulationDataInDTO simulationDataInDTO = new SimulationDataInDTO(simulationName, strVehicleID, strTrackID);
//
//        TrackID trackID = new TrackID(strTrackID);
//
//        // Create a vehicle
//        VehicleID vehicleID = new VehicleID(strVehicleID);
//        AeroModel aeroModel = new AeroModel(10.1, -20.2);
//        BrakeModel brakeModel = new BrakeModel(30.3);
//        ChassisModel chassisModel = new ChassisModel(40.4);
//        Name vehicleName = new Name("vehicleName");
//        PowertrainModel powertrainModel = new PowertrainModel(50.5, 60.6, 1000.0, 2000.0, PowertrainType.COMBUSTION);
//        TransmissionModel transmissionModel = new TransmissionModel(2, List.of(3.0, 5.0), 4);
//        TyreModel tyreModel = new TyreModel(70.7, 80.8, 0.3);
//        VehicleParameters vehicleParameters = new VehicleParameters(aeroModel, brakeModel, chassisModel, vehicleName, powertrainModel, transmissionModel, tyreModel);
//
//        IVehicleFactory vehicleFactory = new VehicleFactory();
//        Vehicle vehicle = vehicleFactory.createVehicle(vehicleID, vehicleParameters);
//
//        when(vehicleRepository.ofIdentity(vehicleID)).thenReturn(java.util.Optional.of(vehicle));
//
//        // Create a track
//        String strTrackName = "AIA";
//        Name trackName = new Name(strTrackName);
//        TrackLength trackLength = new TrackLength(1000);
//        String trackIconPath = "trackIconPath";
//        String trackLocation = "trackLocation";
//        String trackLapRecord = "trackLapRecord";
//        String numberOfCorners = "23";
//        String trackLayout = "trackLayout";
//
//        ITrackFactory trackFactory = new TrackFactory();
//        Track track = trackFactory.createTrack(trackID, trackName, trackLength, trackIconPath, trackLocation, trackLapRecord, numberOfCorners, trackLayout);
//        when(trackRepository.ofIdentity(trackID)).thenReturn(java.util.Optional.of(track));
//
//        // Act + Assert
//        MvcResult result = mockMvc.perform(post("/simulations")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(simulationDataInDTO)))
//                .andExpect(status().isCreated())
//                .andReturn();
//
//        // Assert
//        String content = result.getResponse().getContentAsString();
//
//        SimulationInfoOutDTO simulation = objectMapper.readValue(content, SimulationInfoOutDTO.class);
//        assertEquals(simulationName, simulation.simulationName);
//    }
//
//
//    @Test
//    void shouldReturnBadRequest_whenStartSimulationIsCalledWithNoVehicle() throws Exception {
//        // Arrange
//        String simulationName = "simulationName";
//
//        String strVehicleID = "vehicleID";
//        VehicleID vehicleID = new VehicleID(strVehicleID);
//        String strTrackID = "trackID";
//        TrackID trackID = new TrackID(strTrackID);
//        String strTrackName = "AIA";
//        Name trackName = new Name(strTrackName);
//        TrackLength trackLength = new TrackLength(1000);
//        String trackIconPath = "trackIconPath";
//        String trackLocation = "trackLocation";
//        String trackLapRecord = "trackLapRecord";
//        String numberOfCorners = "23";
//        String trackLayout = "trackLayout";
//
//        SimulationDataInDTO simulationDataInDTO = new SimulationDataInDTO(simulationName, strVehicleID, strTrackID);
//
//
//        when(vehicleRepository.ofIdentity(vehicleID)).thenReturn(java.util.Optional.empty());
//
//        ITrackFactory trackFactory = new TrackFactory();
//        Track track = trackFactory.createTrack(trackID, trackName, trackLength, trackIconPath, trackLocation, trackLapRecord, numberOfCorners, trackLayout);
//        when(trackRepository.ofIdentity(trackID)).thenReturn(java.util.Optional.of(track));
//
//        // Act + Assert
//        mockMvc.perform(post("/simulations")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(simulationDataInDTO)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void shouldReturnBadRequest_whenStartSimulationIsCalledWithNoTrack() throws Exception {
//        // Arrange
//        String simulationName = "simulationName";
//
//        String strVehicleID = "vehicleID";
//        VehicleID vehicleID = new VehicleID(strVehicleID);
//        String strTrackID = "trackID";
//        TrackID trackID = new TrackID(strTrackID);
//
//        SimulationDataInDTO simulationDataInDTO = new SimulationDataInDTO(simulationName, strVehicleID, strTrackID);
//
//        AeroModel aeroModel = mock(AeroModel.class);
//        BrakeModel brakeModel = mock(BrakeModel.class);
//        ChassisModel chassisModel = mock(ChassisModel.class);
//        Name vehicleName = mock(Name.class);
//        PowertrainModel powertrainModel = mock(PowertrainModel.class);
//        TransmissionModel transmissionModel = mock(TransmissionModel.class);
//        TyreModel tyreModel = mock(TyreModel.class);
//        VehicleParameters vehicleParameters = new VehicleParameters(aeroModel, brakeModel, chassisModel, vehicleName, powertrainModel, transmissionModel, tyreModel);
//
//        IVehicleFactory vehicleFactory = new VehicleFactory();
//        Vehicle vehicle = vehicleFactory.createVehicle(vehicleID, vehicleParameters);
//        when(vehicleRepository.ofIdentity(vehicleID)).thenReturn(java.util.Optional.of(vehicle));
//
//        when(trackRepository.ofIdentity(trackID)).thenReturn(java.util.Optional.empty());
//
//        // Act + Assert
//        mockMvc.perform(post("/simulations")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(simulationDataInDTO)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void shouldReturnBadRequest_whenStartSimulationIsCalledWithNullSimulationName() throws Exception {
//        // Arrange
//        String strVehicleID = "vehicleID";
//        String strTrackID = "trackID";
//
//        SimulationDataInDTO simulationDataInDTO = new SimulationDataInDTO(null, strVehicleID, strTrackID);
//
//        // Act + Assert
//        mockMvc.perform(post("/simulations")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(simulationDataInDTO)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void shouldReturnBadRequest_whenStartSimulationIsCalledWithEmptySimulationName() throws Exception {
//        // Arrange
//        String strVehicleID = "vehicleID";
//        String strTrackID = "trackID";
//
//        SimulationDataInDTO simulationDataInDTO = new SimulationDataInDTO("", strVehicleID, strTrackID);
//
//        // Act + Assert
//        mockMvc.perform(post("/simulations")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(simulationDataInDTO)))
//                .andExpect(status().isBadRequest());
//    }
//
//
//    @Test
//    void shouldReturnBadRequest_whenStartSimulationIsCalledWithNullVehicleID() throws Exception {
//        // Arrange
//        String simulationName = "simulationName";
//        String strTrackID = "trackID";
//
//        SimulationDataInDTO simulationDataInDTO = new SimulationDataInDTO(simulationName, null, strTrackID);
//
//        // Act + Assert
//        mockMvc.perform(post("/simulations")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(simulationDataInDTO)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void shouldReturnBadRequest_whenStartSimulationIsCalledWithEmptyVehicleID() throws Exception {
//        // Arrange
//        String simulationName = "simulationName";
//        String strTrackID = "trackID";
//
//        SimulationDataInDTO simulationDataInDTO = new SimulationDataInDTO(simulationName, "", strTrackID);
//
//        // Act + Assert
//        mockMvc.perform(post("/simulations")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(simulationDataInDTO)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void shouldReturnBadRequest_whenStartSimulationIsCalledWithNullTrackID() throws Exception {
//        // Arrange
//        String simulationName = "simulationName";
//        String strVehicleID = "vehicleID";
//
//        SimulationDataInDTO simulationDataInDTO = new SimulationDataInDTO(simulationName, strVehicleID, null);
//
//        // Act + Assert
//        mockMvc.perform(post("/simulations")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(simulationDataInDTO)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void shouldReturnBadRequest_whenStartSimulationIsCalledWithEmptyTrackID() throws Exception {
//        // Arrange
//        String simulationName = "simulationName";
//        String strVehicleID = "vehicleID";
//
//        SimulationDataInDTO simulationDataInDTO = new SimulationDataInDTO(simulationName, strVehicleID, "");
//
//        // Act + Assert
//        mockMvc.perform(post("/simulations")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(simulationDataInDTO)))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void shouldGetListOfSimulations_whenGetSimulationsIsCalled() throws Exception {
//        // Arrange
//        /* Create Simulations */
//        ISimulationFactory simulationFactory = new SimulationFactory();
//
//        Name simulationName1 = new Name("simulationName1");
//        VehicleID vehicleID1 = new VehicleID("vehicleID1");
//        TrackID trackID1 = new TrackID("trackID1");
//        Name vehicleName1 = new Name("vehicleName1");
//        Name trackName1 = new Name("trackName1");
//        Simulation simulation1 = simulationFactory.createSimulation(simulationName1, vehicleID1, trackID1, vehicleName1, trackName1);
//
//        Name simulationName2 = new Name("simulationName2");
//        Simulation simulation2 = simulationFactory.createSimulation(simulationName2, vehicleID1, trackID1, vehicleName1, trackName1);
//
//        when(simulationRepository.findAll()).thenReturn(List.of(simulation1, simulation2));
//
//        /* Create track */
//        ITrackFactory trackFactory = new TrackFactory();
//        Track track = trackFactory.createTrack(trackID1, trackName1, new TrackLength(1000), "trackIconPath", "trackLocation", "trackLapRecord", "23", "trackLayout");
//        when(trackRepository.ofIdentity(trackID1)).thenReturn(java.util.Optional.of(track));
//
//        /* Create vehicle */
//        IVehicleFactory vehicleFactory = new VehicleFactory();
//        Vehicle vehicle = vehicleFactory.createVehicle(vehicleID1, new VehicleParameters(
//                new AeroModel(10.1, -20.2), new BrakeModel(30.3),
//                new ChassisModel(40.4), vehicleName1, new PowertrainModel(50.5, 60.6, 1000.0, 4000.0, PowertrainType.COMBUSTION),
//                new TransmissionModel(2, List.of(3.0, 5.0), 4),
//                new TyreModel(70.7, 80.8, 0.3)));
//        when(vehicleRepository.ofIdentity(vehicleID1)).thenReturn(java.util.Optional.of(vehicle));
//
//
//        // Act + Assert
//        MvcResult result = mockMvc.perform(get("/simulations"))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        // Assert
//        String content = result.getResponse().getContentAsString();
//        List<SimulationInfoOutDTO> simulations = objectMapper.readValue(content, List.class);
//        assertEquals(2, simulations.size());
//    }
//
//    @Test
//    void shouldReturnEmptyList_whenGetSimulationsIsCalledWithNoSimulations() throws Exception {
//        // Arrange
//        when(simulationRepository.findAll()).thenReturn(List.of());
//
//        // Act + Assert
//        MvcResult result = mockMvc.perform(get("/simulations"))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        // Assert
//        String content = result.getResponse().getContentAsString();
//        List<SimulationInfoOutDTO> simulations = objectMapper.readValue(content, List.class);
//        assertEquals(0, simulations.size());
//    }
//
//    @Test
//    void shouldReturnSimulationInfoOutDTO_whenGetSimulationById() throws Exception {
//        // Arrange
//        ISimulationFactory simulationFactory = new SimulationFactory();
//        Simulation simulation = simulationFactory.createSimulation(new Name("simulationName"), new VehicleID("vehicleID"), new TrackID("trackID"), new Name("vehicleName"), new Name("trackName"));
//
//        SimulationID simulationID = simulation.getSimulationID();
//        when(simulationRepository.ofIdentity(simulationID)).thenReturn(java.util.Optional.of(simulation));
//
//        // Act
//        MvcResult result = mockMvc.perform(get("/simulations/" + simulationID.getId()))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        // Assert
//        String content = result.getResponse().getContentAsString();
//        SimulationInfoOutDTO simulationInfoOutDTO = objectMapper.readValue(content, SimulationInfoOutDTO.class);
//
//        assertEquals(simulationID.getId(), simulationInfoOutDTO.simulationID);
//    }
//
//    @Test
//    void shouldReturnBadRequest_whenGetSimulationByIdWithInvalidID() throws Exception {
//        // Arrange
//        String strSimulationID = "invalidID";
//
//        // Act + Assert
//        mockMvc.perform(get("/simulations/" + strSimulationID))
//                .andExpect(status().isBadRequest());
//    }
//
//}
