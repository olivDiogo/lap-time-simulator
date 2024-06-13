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

/**
 * Fetch vehicle model by its ID
 * @param vehicleId - vehicle ID
 * @param success - callback function to handle the response
 * @param failure - callback function to handle the error
 */
export function fetchSelectedVehicleModelFromServer(vehicleId, success, failure){
    fetch(`${URL_API}/vehicles/${vehicleId}`)
        .then(res => res.json())
        .then(res => success(res))
        .catch(err => failure(err.message));
}

/**
 * Updates the vehicle model on the server
 * @param vehicle - vehicle model
 * @param success - callback function to handle the response
 * @param failure - callback function to handle the error
 */
export function postUpdatedVehicleModelToServer(vehicle, success, failure){
    fetch(`${URL_API}/vehicles/${vehicle.vehicleID}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(vehicle)
    })
        .then(res => res.json())
        .then(res => success(res))
        .catch(err => failure(err.message));
}