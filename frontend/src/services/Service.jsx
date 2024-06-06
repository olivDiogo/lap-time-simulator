export const URL_API = 'http://localhost:8080';

/**
 * Fetch all tracks from the server
 * @param success - callback function to handle the response
 * @param failure - callback function to handle the error
 */
export function fetchTracksFromServer(success, failure) {
    fetch(`${URL_API}/tracks`)
        .then(res => res.json())
        .then(res => success(res))
        .catch(err => failure(err.message));
}

/**
 * Fetch all vehicles from the server
 * @param success - callback function to handle the response
 * @param failure - callback function to handle the error
 */
export function fetchVehiclesFromServer(success, failure) {
    fetch(`${URL_API}/vehicles`)
        .then(res => res.json())
        .then(res => success(res))
        .catch(err => failure(err.message));
}

/**
 * Fetch all simulations from the server
 * @param success - callback function to handle the response
 * @param failure - callback function to handle the error
 */
export function fetchSimulationsFromServer(success, failure) {
    fetch(`${URL_API}/simulations`)
        .then(res => res.json())
        .then(res => success(res))
        .catch(err => failure(err.message));
}