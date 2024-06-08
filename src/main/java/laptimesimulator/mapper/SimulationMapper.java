package laptimesimulator.mapper;

import laptimesimulator.domain.simulation.Simulation;
import laptimesimulator.domain.track.Track;
import laptimesimulator.domain.vehicle.Vehicle;
import laptimesimulator.utils.dto.outputDataDTO.simulationData.SimulationOptionsDataOutDTO;
import laptimesimulator.utils.dto.outputDataDTO.simulationData.SimulationTrackDataOutDTO;
import laptimesimulator.utils.dto.outputDataDTO.simulationData.SimulationVehicleDataOutDTO;
import laptimesimulator.utils.dto.outputDataDTO.SimulationInfoOutDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SimulationMapper {
    private static final String NULL_VEHICLE_PARAMETERS = "The simulation parameters cannot be null.";

    /**
     * Maps the simulation vehicle data to a DTO.
     *
     * @param vehicle are the vehicle parameters.
     * @return the simulation data transfer object.
     */
    public SimulationVehicleDataOutDTO toDTO(Vehicle vehicle) {
        if (vehicle == null) { // Can't implement IMapper because method has only 1 parameter
            throw new IllegalArgumentException("The vehicle parameters cannot be null.");
        }

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
        List<Double> gears = vehicle.getTransmissionModel().getGears();
        double finalDriveRatio = vehicle.getTransmissionModel().getFinalDriveRatio();
        double mux = vehicle.getTyreModel().getLongitudinalGrip();
        double muy = vehicle.getTyreModel().getLateralGrip();
        double rrTyre = vehicle.getTyreModel().getTyreRadius();

        return new SimulationVehicleDataOutDTO(vehicleID, vehicleName, sCz, sCx,
                rBrkF2P, mCar, pEngMax, tEngMax, nEngPMax, nEngTMax, gears,
                finalDriveRatio, mux, muy, rrTyre);
    }

    /**
     * Maps the track to a DTO.
     *
     * @param track are the track parameters.
     * @return the simulation data transfer object.
     */
    public SimulationTrackDataOutDTO toDTO(Track track) {
        if (track == null) {
            throw new IllegalArgumentException("The track parameters cannot be null.");
        }

        String trackID = track.getTrackID().getId();
        String trackName = track.getTrackName().getStrName();

        return new SimulationTrackDataOutDTO(trackID, trackName);
    }

    /**
     * Maps the simulation to a DTO.
     *
     * @param simulation are the simulation parameters.
     * @return the simulation data transfer object.
     */
    public SimulationOptionsDataOutDTO toDTO(Simulation simulation) {
        if (simulation == null) {
            throw new IllegalArgumentException("The simulation options cannot be null.");
        }

        String simulationID = simulation.getSimulationID().getId();
        String simulationName = simulation.getSimulationName().getStrName();

        return new SimulationOptionsDataOutDTO(simulationID, simulationName);
    }


    /**
     * Maps the simulation to an info DTO.
     *
     * @param simulation are the simulation parameters.
     * @return the simulation info data transfer object.
     */
    public SimulationInfoOutDTO toInfoDTO(Simulation simulation){
        if (simulation == null) {
            throw new IllegalArgumentException(NULL_VEHICLE_PARAMETERS);
        }

        String simulationID = simulation.getSimulationID().getId();
        String simulationName = simulation.getSimulationName().getStrName();
        String vehicleID = simulation.getVehicleID().getId();
        String vehicleName = simulation.getVehicleName().getStrName();
        String trackID = simulation.getTrackID().getId();
        String trackName = simulation.getTrackName().getStrName();

        return new SimulationInfoOutDTO(simulationID, simulationName, vehicleID, vehicleName, trackID, trackName);
    }
}
