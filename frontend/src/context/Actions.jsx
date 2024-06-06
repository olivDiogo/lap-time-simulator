import {fetchSimulationsFromServer, fetchTracksFromServer, fetchVehiclesFromServer} from "../services/Service.jsx";

export const FETCH_TRACKS_STARTED = 'FETCH_TRACKS_STARTED';
export const FETCH_TRACKS_SUCCESS = 'FETCH_TRACKS_SUCCESS';
export const FETCH_TRACKS_FAILURE = 'FETCH_TRACKS_FAILURE';

export const FETCH_VEHICLES_STARTED = 'FETCH_VEHICLES_STARTED';
export const FETCH_VEHICLES_SUCCESS = 'FETCH_VEHICLES_SUCCESS';
export const FETCH_VEHICLES_FAILURE = 'FETCH_VEHICLES_FAILURE';

export const FETCH_SIMULATIONS_STARTED = 'FETCH_SIMULATIONS_STARTED';
export const FETCH_SIMULATIONS_SUCCESS = 'FETCH_SIMULATIONS_SUCCESS';
export const FETCH_SIMULATIONS_FAILURE = 'FETCH_SIMULATIONS_FAILURE';


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






