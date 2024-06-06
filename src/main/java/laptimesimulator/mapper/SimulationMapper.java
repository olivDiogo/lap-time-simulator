package laptimesimulator.mapper;

import laptimesimulator.domain.simulation.Simulation;
import laptimesimulator.domain.track.Track;
import laptimesimulator.domain.vehicle.Vehicle;
import laptimesimulator.utils.dto.outputDataDTO.SimulationDataOutDTO;
import laptimesimulator.utils.dto.outputDataDTO.SimulationInfoOutDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimulationMapper {

    /**
     * Maps the simulation, vehicle and track to a DTO.
     *
     * @param simulation are the simulation parameters.
     * @param vehicle are the vehicle parameters.
     * @param track are the track parameters.
     * @return the simulation data transfer object.
     */
    public SimulationDataOutDTO toDTO(Simulation simulation, Vehicle vehicle, Track track) {
        if (simulation == null || vehicle == null || track == null) { // Can't implement IMapper because method has only 1 parameter
            throw new IllegalArgumentException("The simulation parameters cannot be null.");
        }

        String simulationID = simulation.getSimulationID().getId();
        String simulationName = simulation.getSimulationName().getStrName();
        String vehicleID = vehicle.getVehicleID().getId();
        String vehicleName = vehicle.getVehicleName().getStrName();
        double sCz = vehicle.getAeroModel().getDownforceCoefficient();
        double sCx = vehicle.getAeroModel().getDragCoefficient();
        double rBrkF2P = vehicle.getBrakeModel().getPressureToTorqueRatio();
        double mCar = vehicle.getChassisModel().getMass();
        double pEngMax = vehicle.getPowertrainModel().getPowerMax();
        double tEngMax = vehicle.getPowertrainModel().getTorqueMax();
        Double nEngPMax = vehicle.getPowertrainModel().getRpmPowerMax();
        Double nEngTMax = vehicle.getPowertrainModel().getRpmTorqueMax();
        int numberOfGears = vehicle.getTransmissionModel().getNumberOfGears();
        List<Double> gears = vehicle.getTransmissionModel().getGears();
        double finalDriveRatio = vehicle.getTransmissionModel().getFinalDriveRatio();
        double mux = vehicle.getTyreModel().getLongitudinalGrip();
        double muy = vehicle.getTyreModel().getLateralGrip();
        double rrTyre = vehicle.getTyreModel().getTyreRadius();
        String trackID = track.getTrackID().getId();
        String trackName = track.getTrackName().getStrName();

        return new SimulationDataOutDTO(simulationID, simulationName, vehicleID, vehicleName, sCz, sCx,
                rBrkF2P, mCar, pEngMax, tEngMax, nEngPMax, nEngTMax, numberOfGears, gears,
                finalDriveRatio, mux, muy, rrTyre, trackID, trackName);
    }


    /**
     * Maps the simulation to an info DTO.
     *
     * @param simulation are the simulation parameters.
     * @return the simulation info data transfer object.
     */
    public SimulationInfoOutDTO toInfoDTO(Simulation simulation){
        if (simulation == null) {
            throw new IllegalArgumentException("The simulation parameters cannot be null.");
        }

        String simulationID = simulation.getSimulationID().getId();
        String simulationName = simulation.getSimulationName().getStrName();
        String vehicleID = simulation.getVehicleID().getId();
        String vehicleName = simulation.getVehicleName().getStrName();
        String trackID = simulation.getTrackID().getId();
        String trackName = simulation.getTrackName().getStrName();

        return new SimulationInfoOutDTO(simulationID, simulationName, vehicleID, vehicleName, trackID, trackName);
    }

    /**
     * Maps a list of simulations to a list of simulation info DTOs.
     *
     * @param simulations are the simulations.
     * @return the simulation data transfer object.
     */
    public List<SimulationInfoOutDTO> toInfoDTO(List<Simulation> simulations) {
        if (simulations == null) {
            throw new IllegalArgumentException("The simulation parameters cannot be null.");
        }

        List<SimulationInfoOutDTO> simulationInfoOutDTOs = new ArrayList<>();

        for (Simulation simulation : simulations) {
            simulationInfoOutDTOs.add(toInfoDTO(simulation));
        }

        return simulationInfoOutDTOs;
    }
}
