import {
    fetchSelectedVehicleModelFromServer,
    fetchSimulationsFromServer,
    fetchTracksFromServer,
    fetchVehiclesFromServer,
    postCreatedVehicleModelToServer,
    postCreateSimulationToServer,
    postUpdatedVehicleModelToServer, startSimulationInServer
} from "../services/Service.jsx";

export const FETCH_TRACKS_STARTED = 'FETCH_TRACKS_STARTED';
export const FETCH_TRACKS_SUCCESS = 'FETCH_TRACKS_SUCCESS';
export const FETCH_TRACKS_FAILURE = 'FETCH_TRACKS_FAILURE';

export const FETCH_VEHICLES_STARTED = 'FETCH_VEHICLES_STARTED';
export const FETCH_VEHICLES_SUCCESS = 'FETCH_VEHICLES_SUCCESS';
export const FETCH_VEHICLES_FAILURE = 'FETCH_VEHICLES_FAILURE';

export const FETCH_SIMULATIONS_STARTED = 'FETCH_SIMULATIONS_STARTED';
export const FETCH_SIMULATIONS_SUCCESS = 'FETCH_SIMULATIONS_SUCCESS';
export const FETCH_SIMULATIONS_FAILURE = 'FETCH_SIMULATIONS_FAILURE';

export const UPDATE_SELECTED_VEHICLE = 'UPDATE_SELECTED_VEHICLE';
export const UPDATE_SELECTED_TRACK = 'UPDATE_SELECTED_TRACK';

export const UPDATE_SIMULATION_NAME = 'UPDATE_SIMULATION_NAME';

export const UPDATE_DOWNFORCE_COEFFICIENT = 'UPDATE_DOWNFORCE_COEFFICIENT';
export const UPDATE_DRAG_COEFFICIENT = 'UPDATE_DRAG_COEFFICIENT';
export const UPDATE_PRESSURE_TO_TORQUE_RATIO = 'UPDATE_PRESSURE_TO_TORQUE_RATIO';
export const UPDATE_VEHICLE_MASS = 'UPDATE_VEHICLE_MASS';
export const UPDATE_POWER_MAX = 'UPDATE_POWER_MAX';
export const UPDATE_TORQUE_MAX = 'UPDATE_TORQUE_MAX';
export const UPDATE_RPM_POWER_MAX = 'UPDATE_RPM_POWER_MAX';
export const UPDATE_RPM_TORQUE_MAX = 'UPDATE_RPM_TORQUE_MAX';
export const UPDATE_POWERTRAIN_TYPE = 'UPDATE_POWERTRAIN_TYPE';
export const UPDATE_NUMBER_OF_GEARS = 'UPDATE_NUMBER_OF_GEARS';
export const UPDATE_GEARS = 'UPDATE_GEARS';
export const UPDATE_GEAR_RATIO = 'UPDATE_GEAR_RATIO';
export const UPDATE_FINAL_DRIVE_RATIO = 'UPDATE_FINAL_DRIVE_RATIO';
export const UPDATE_LONGITUDINAL_GRIP = 'UPDATE_LONGITUDINAL_GRIP';
export const UPDATE_LATERAL_GRIP = 'UPDATE_LATERAL_GRIP';
export const UPDATE_TYRE_RADIUS = 'UPDATE_TYRE_RADIUS';
export const UPDATE_VEHICLE_NAME = 'UPDATE_VEHICLE_NAME';

export const POST_UPDATED_VEHICLE_MODEL_STARTED = 'POST_UPDATED_VEHICLE_MODEL_STARTED';
export const POST_UPDATED_VEHICLE_MODEL_SUCCESS = 'POST_UPDATED_VEHICLE_MODEL_SUCCESS';
export const POST_UPDATED_VEHICLE_MODEL_FAILURE = 'POST_UPDATED_VEHICLE_MODEL_FAILURE';

export const FETCH_VEHICLE_MODEL_BY_ID_STARTED = 'FETCH_VEHICLE_MODEL_BY_ID_STARTED';
export const FETCH_VEHICLE_MODEL_BY_ID_SUCCESS = 'FETCH_VEHICLE_MODEL_BY_ID_SUCCESS';
export const FETCH_VEHICLE_MODEL_BY_ID_FAILURE = 'FETCH_VEHICLE_MODEL_BY_ID_FAILURE';

export const POST_CREATED_VEHICLE_MODEL_STARTED = 'POST_CREATED_VEHICLE_MODEL_STARTED';
export const POST_CREATED_VEHICLE_MODEL_SUCCESS = 'POST_CREATED_VEHICLE_MODEL_SUCCESS';
export const POST_CREATED_VEHICLE_MODEL_FAILURE = 'POST_CREATED_VEHICLE_MODEL_FAILURE';

export const POST_CREATE_SIMULATION_STARTED = 'POST_CREATE_SIMULATION_STARTED';
export const POST_CREATE_SIMULATION_SUCCESS = 'POST_CREATE_SIMULATION_SUCCESS';
export const POST_CREATE_SIMULATION_FAILURE = 'POST_CREATE_SIMULATION_FAILURE';

export const POST_START_SIMULATION_STARTED = 'POST_START_SIMULATION_STARTED';
export const POST_START_SIMULATION_SUCCESS = 'POST_START_SIMULATION_SUCCESS';
export const POST_START_SIMULATION_FAILURE = 'POST_START_SIMULATION_FAILURE';

export const RESET_SELECTED_VEHICLE = 'RESET_SELECTED_VEHICLE';

export const HIDE_ALERT = 'HIDE_ALERT';

/**
 * Fetch all tracks from the server
 * @param dispatch - dispatch function to dispatch actions
 */
export function fetchTracks(dispatch) {
    const action = {
        type: FETCH_TRACKS_STARTED,
    };
    dispatch(action);

    const success = (res) => {
        const action = fetchTracksSuccess(res);
        dispatch(action);
    };

    const failure = (err) => {
        const action = fetchTracksFailure(err);
        dispatch(action);
    }

    fetchTracksFromServer(success, failure);
}

/**
 * Action to dispatch when fetching tracks started
 * @param data - data to be dispatched
 * @returns {{payload: {data}, type: string}}
 */
function fetchTracksSuccess(data) {
    return {
        type: FETCH_TRACKS_SUCCESS,
        payload: {
            data: data,
        }
    }
}

/**
 * Action to dispatch when fetching tracks failed
 * @param error - error to be dispatched
 * @returns {{payload: {error}, type: string}}
 */
function fetchTracksFailure(error) {
    return {
        type: FETCH_TRACKS_FAILURE,
        payload: {
            error: error,
        }
    }
}

/**
 * Fetch all vehicles from the server
 * @param dispatch - dispatch function to dispatch actions
 */
export function fetchVehicles(dispatch) {
    const action = {
        type: FETCH_VEHICLES_STARTED,
    };
    dispatch(action);

    const success = (res) => {
        const action = fetchVehiclesSuccess(res);
        dispatch(action);
    };

    const failure = (err) => {
        const action = fetchVehiclesFailure(err);
        dispatch(action);
    }

    fetchVehiclesFromServer(success, failure);
}

/**
 * Action to dispatch when fetching vehicles started
 * @param data - data to be dispatched
 * @returns {{payload: {data}, type: string}}
 */
function fetchVehiclesSuccess(data) {
    return {
        type: FETCH_VEHICLES_SUCCESS,
        payload: {
            data: data,
        }
    }
}

/**
 * Action to dispatch when fetching vehicles failed
 * @param error - error to be dispatched
 * @returns {{payload: {error}, type: string}}
 */
function fetchVehiclesFailure(error) {
    return {
        type: FETCH_VEHICLES_FAILURE,
        payload: {
            error: error,
        }
    }
}

/**
 * Fetch all simulations from the server
 * @param dispatch - dispatch function to dispatch actions
 */
export function fetchSimulations(dispatch) {
    const action = {
        type: FETCH_SIMULATIONS_STARTED,
    };
    dispatch(action);

    const success = (res) => {
        const action = fetchSimulationsSuccess(res);
        dispatch(action);
    };

    const failure = (err) => {
        const action = fetchSimulationsFailure(err);
        dispatch(action);
    }

    fetchSimulationsFromServer(success, failure);
}

/**
 * Action to dispatch when fetching simulations started
 * @param data - data to be dispatched
 * @returns {{payload: {data}, type: string}}
 */
function fetchSimulationsSuccess(data) {
    return {
        type: FETCH_SIMULATIONS_SUCCESS,
        payload: {
            data: data,
        }
    }
}

/**
 * Action to dispatch when fetching simulations failed
 * @param error - error to be dispatched
 * @returns {{payload: {error}, type: string}}
 */
function fetchSimulationsFailure(error) {
    return {
        type: FETCH_SIMULATIONS_FAILURE,
        payload: {
            error: error,
        }
    }
}

/**
 * Updates the selected vehicle
 * @param dispatch - dispatch function to dispatch actions
 * @param vehicle - vehicle
 */
export function updateSelectedVehicle(dispatch, vehicle) {
    const action = {
        type: UPDATE_SELECTED_VEHICLE,
        payload: {
            vehicle: vehicle
        }
    };
    dispatch(action);
}

/**
 * Updates the selected track
 * @param dispatch - dispatch function to dispatch actions
 * @param trackId - track id
 * @param trackName - track name
 */
export function updateSelectedTrack(dispatch, trackId, trackName) {
    const action = {
        type: UPDATE_SELECTED_TRACK,
        payload: {
            trackId: trackId,
            trackName: trackName,
        }
    };
    dispatch(action);
}

/**
 * Updates the downforce coefficient
 * @param dispatch - dispatch function to dispatch actions
 * @param newDownforceCoefficient - new downforce coefficient
 */
export function updateDownforceCoefficient(dispatch, newDownforceCoefficient){
    const action = {
        type: UPDATE_DOWNFORCE_COEFFICIENT,
        payload: {
            newDownforceCoefficient: newDownforceCoefficient
        }
    };
    dispatch(action);
}

/**
 * Updates the drag coefficient
 * @param dispatch - dispatch function to dispatch actions
 * @param newDragCoefficient - new drag coefficient
 */
export function updateDragCoefficient(dispatch, newDragCoefficient){
    const action = {
        type: UPDATE_DRAG_COEFFICIENT,
        payload: {
            newDragCoefficient: newDragCoefficient
        }
    };
    dispatch(action);
}

/**
 * Updates the pressure to torque ratio
 * @param dispatch - dispatch function to dispatch actions
 * @param newPressureToTorqueRatio - new pressure to torque ratio
 */
export function updatePressureToTorqueRatio(dispatch, newPressureToTorqueRatio){
    const action = {
        type: UPDATE_PRESSURE_TO_TORQUE_RATIO,
        payload: {
            newPressureToTorqueRatio: newPressureToTorqueRatio
        }
    };
    dispatch(action);
}

/**
 * Updates the vehicle mass
 * @param dispatch - dispatch function to dispatch actions
 * @param newVehicleMass - new vehicle mass
 */
export function updateVehicleMass(dispatch, newVehicleMass){
    const action = {
        type: UPDATE_VEHICLE_MASS,
        payload: {
            newVehicleMass: newVehicleMass
        }
    };
    dispatch(action);
}

/**
 * Updates the power max
 * @param dispatch - dispatch function to dispatch actions
 * @param newPowerMax - new power max
 */
export function updatePowerMax(dispatch, newPowerMax){
    const action = {
        type: UPDATE_POWER_MAX,
        payload: {
            newPowerMax: newPowerMax
        }
    };
    dispatch(action);
}

/**
 * Updates the torque max
 * @param dispatch - dispatch function to dispatch actions
 * @param newTorqueMax - new torque max
 */
export function updateTorqueMax(dispatch, newTorqueMax){
    const action = {
        type: UPDATE_TORQUE_MAX,
        payload: {
            newTorqueMax: newTorqueMax
        }
    };
    dispatch(action);
}

/**
 * Updates the rpm power max
 * @param dispatch - dispatch function to dispatch actions
 * @param newRpmPowerMax - new rpm power max
 */
export function updateRpmPowerMax(dispatch, newRpmPowerMax){
    const action = {
        type: UPDATE_RPM_POWER_MAX,
        payload: {
            newRpmPowerMax: newRpmPowerMax
        }
    };
    dispatch(action);
}

/**
 * Updates the rpm torque max
 * @param dispatch - dispatch function to dispatch actions
 * @param newRpmTorqueMax - new rpm torque max
 */
export function updateRpmTorqueMax(dispatch, newRpmTorqueMax){
    const action = {
        type: UPDATE_RPM_TORQUE_MAX,
        payload: {
            newRpmTorqueMax: newRpmTorqueMax
        }
    };
    dispatch(action);
}

/**
 * Updates the powertrain type
 * @param dispatch - dispatch function to dispatch actions
 * @param newPowertrainType - new powertrain type
 */
export function updatePowertrainType(dispatch, newPowertrainType){
    const action = {
        type: UPDATE_POWERTRAIN_TYPE,
        payload: {
            newPowertrainType: newPowertrainType
        }
    };
    dispatch(action);
}

/**
 * Updates the number of gears
 * @param dispatch - dispatch function to dispatch actions
 * @param newNumberOfGears - new number of gears
 */
export function updateNumberOfGears(dispatch, newNumberOfGears){
    const action = {
        type: UPDATE_NUMBER_OF_GEARS,
        payload: {
            newNumberOfGears: newNumberOfGears
        }
    };
    dispatch(action);
}

/**
 * Updates the gear ratios
 * @param dispatch - dispatch function to dispatch actions
 * @param newGearRatios - new gear ratios array
 */
export function updateGearRatios(dispatch, newGearRatios){
    const action = {
        type: UPDATE_GEARS,
        payload: {
            newGearRatios: newGearRatios
        }
    };
    dispatch(action);
}

/**
 * Updates a single gear ratio
 * @param dispatch - dispatch function to dispatch actions
 * @param newGearRatio - new gear ratio
 * @param index - index of the gear ratio
 */
export function updateSingleGearRatio(dispatch, newGearRatio, index){
    const action = {
        type: UPDATE_GEAR_RATIO,
        payload: {
            newGearRatio: newGearRatio,
            index: index
        }
    };
    dispatch(action);
}

/**
 * Updates the final drive ratio
 * @param dispatch - dispatch function to dispatch actions
 * @param newFinalDriveRatio - new final drive ratio
 */
export function updateFinalDriveRatio(dispatch, newFinalDriveRatio){
    const action = {
        type: UPDATE_FINAL_DRIVE_RATIO,
        payload: {
            newFinalDriveRatio: newFinalDriveRatio
        }
    };
    dispatch(action);
}

/**
 * Updates the longitudinal grip
 * @param dispatch - dispatch function to dispatch actions
 * @param newLongitudinalGrip - new longitudinal grip
 */
export function updateLongitudinalGrip(dispatch, newLongitudinalGrip){
    const action = {
        type: UPDATE_LONGITUDINAL_GRIP,
        payload: {
            newLongitudinalGrip: newLongitudinalGrip
        }
    };
    dispatch(action);
}

/**
 * Updates the lateral grip
 * @param dispatch - dispatch function to dispatch actions
 * @param newLateralGrip - new lateral grip
 */
export function updateLateralGrip(dispatch, newLateralGrip){
    const action = {
        type: UPDATE_LATERAL_GRIP,
        payload: {
            newLateralGrip: newLateralGrip
        }
    };
    dispatch(action);
}

/**
 * Updates the tyre radius
 * @param dispatch - dispatch function to dispatch actions
 * @param newTyreRadius - new tyre radius
 */
export function updateTyreRadius(dispatch, newTyreRadius){
    const action = {
        type: UPDATE_TYRE_RADIUS,
        payload: {
            newTyreRadius: newTyreRadius
        }
    };
    dispatch(action);
}

/**
 * Updates the vehicle name
 * @param dispatch - dispatch function to dispatch actions
 * @param newVehicleName - new vehicle name
 */
export function updateVehicleName(dispatch, newVehicleName){
    const action = {
        type: UPDATE_VEHICLE_NAME,
        payload: {
            newVehicleName: newVehicleName
        }
    };
    dispatch(action);
}

/**
 * Updated the vehicle model and saves it in the database
 * @param dispatch - dispatch function to dispatch actions
 * @param newVehicleModel - new vehicle model
 */
export function postUpdatedVehicleModel(dispatch, newVehicleModel){
    const action = {
        type: POST_UPDATED_VEHICLE_MODEL_STARTED,
    };
    dispatch(action);

    const success = (res) => {
        const action = postUpdatedVehicleModelSuccess(res);
        dispatch(action);
    };

    const failure = (err) => {
        const action = postUpdatedVehicleModelFailure(err);
        dispatch(action);
    }

    postUpdatedVehicleModelToServer(newVehicleModel, success, failure);
}

function postUpdatedVehicleModelSuccess(data) {
    return {
        type: POST_UPDATED_VEHICLE_MODEL_SUCCESS,
        payload: {
            data: data,
        },
    }
}

function postUpdatedVehicleModelFailure(error) {
    return {
        type: POST_UPDATED_VEHICLE_MODEL_FAILURE,
        payload: {
            error: error,
        }
    }
}

/**
 * Fetches the vehicle model by its ID
 * @param dispatch - dispatch function to dispatch actions
 * @param vehicleId - vehicle ID
 */
export function fetchVehicleModelById(dispatch, vehicleId){
    const action = {
        type: FETCH_VEHICLE_MODEL_BY_ID_STARTED,
    };
    dispatch(action);

    const success = (res) => {
        const action = fetchVehicleModelByIdSuccess(res);
        dispatch(action);
    };

    const failure = (err) => {
        const action = fetchVehicleModelByIdFailure(err);
        dispatch(action);
    }

    fetchSelectedVehicleModelFromServer(vehicleId, success, failure);
}

function fetchVehicleModelByIdSuccess(data) {
    return {
        type: FETCH_VEHICLE_MODEL_BY_ID_SUCCESS,
        payload: {
            data: data,
        }
    }
}

function fetchVehicleModelByIdFailure(error) {
    return {
        type: FETCH_VEHICLE_MODEL_BY_ID_FAILURE,
        payload: {
            error: error,
        }
    }
}

/**
 * Sends a request to the server to create a new vehicle model
 * @param dispatch - dispatch function to dispatch actions
 * @param newVehicleModel - new vehicle model
 */
export function postCreatedVehicleModel(dispatch, newVehicleModel){
    const action = {
        type: POST_CREATED_VEHICLE_MODEL_STARTED,
    };
    dispatch(action);

    const success = (res) => {
        const action = postCreatedVehicleModelSuccess(res);
        dispatch(action);
    };

    const failure = (err) => {
        const action = postCreatedVehicleModelFailure(err);
        dispatch(action);
    }

    postCreatedVehicleModelToServer(newVehicleModel, success, failure);
}

function postCreatedVehicleModelSuccess(data) {
    return {
        type: POST_CREATED_VEHICLE_MODEL_SUCCESS,
        payload: {
            data: data,
        },
    }
}

function postCreatedVehicleModelFailure(error) {
    return {
        type: POST_CREATED_VEHICLE_MODEL_FAILURE,
        payload: {
            error: error,
        }
    }
}

/**
 * Sends a request to the server to create a new simulation
 * @param dispatch - dispatch function to dispatch actions
 * @param simulationName - simulationName
 * @param trackId - trackId
 * @param vehicleId - vehicleId
 */
export function postCreateSimulation(dispatch, simulationName, trackId, vehicleId){
    const action = {
        type: POST_CREATE_SIMULATION_STARTED,
    };
    dispatch(action);

    const success = (res) => {
        const action = postCreateSimulationSuccess(res);
        dispatch(action);
    };

    const failure = (err) => {
        const action = postCreateSimulationFailure(err);
        dispatch(action);
    }

    postCreateSimulationToServer(simulationName, trackId, vehicleId, success, failure);
}

function postCreateSimulationSuccess(data) {
    return {
        type: POST_CREATE_SIMULATION_SUCCESS,
        payload: {
            data: data,
        },
    }
}

function postCreateSimulationFailure(error) {
    return {
        type: POST_CREATE_SIMULATION_FAILURE,
        payload: {
            error: error,
        }
    }
}

/**
 * Resets the selected vehicle
 * @param dispatch - dispatch function to dispatch actions
 */
export function resetSelectedVehicle(dispatch) {
    const action = {
        type: RESET_SELECTED_VEHICLE,
    };
    dispatch(action);
}

/**
 * Updates the simulation name
 * @param dispatch - dispatch function to dispatch actions
 * @param simulationName - new simulation name
 */
export function updateSimulationName(dispatch, simulationName) {
    const action = {
        type: UPDATE_SIMULATION_NAME,
        payload: {
            simulationName: simulationName
        }
    };
    dispatch(action);
}

/**
 * Sends a request to the server to start a simulation
 * @param dispatch - dispatch function to dispatch actions
 * @param simulationId - simulationId
 */
export function postStartSimulation(dispatch, simulationId) {
    const action = {
        type: POST_START_SIMULATION_STARTED,
        payload: {
            simulationId: simulationId
        }
    };
    dispatch(action);

    const success = (res) => {
        const action = postStartSimulationSuccess(res);
        dispatch(action);
    };

    const failure = (err) => {
        const action = postStartSimulationFailure(err);
        dispatch(action);
    }

    startSimulationInServer(simulationId, success, failure);
}

function postStartSimulationSuccess(data) {
    return {
        type: POST_START_SIMULATION_SUCCESS,
        payload: {
            data: data,
        },
    }
}

function postStartSimulationFailure(error) {
    return {
        type: POST_START_SIMULATION_FAILURE,
        payload: {
            error: error,
        }
    }
}

export function hideAlert(dispatch) {
    const action = {
        type: HIDE_ALERT,
    };
    dispatch(action);
}
