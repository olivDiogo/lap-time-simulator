package lapTimeSimulator.mapper;

import lapTimeSimulator.domain.simulation.Simulation;
import lapTimeSimulator.domain.track.Track;
import lapTimeSimulator.domain.vehicle.Vehicle;
import lapTimeSimulator.utils.dto.outputDataDTO.SimulationDataOutDTO;
import org.springframework.stereotype.Component;

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

        return new SimulationDataOutDTO(simulationID, vehicleID, vehicleName, sCz, sCx,
                rBrkF2P, mCar, pEngMax, tEngMax, nEngPMax, nEngTMax, numberOfGears, gears,
                finalDriveRatio, mux, muy, rrTyre, trackID, trackName);
    }
}
