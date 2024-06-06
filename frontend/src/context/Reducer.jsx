import {
    FETCH_TRACKS_SUCCESS,
    FETCH_TRACKS_STARTED,
    FETCH_TRACKS_FAILURE,
    FETCH_VEHICLES_STARTED,
    FETCH_VEHICLES_FAILURE,
    FETCH_VEHICLES_SUCCESS,
    FETCH_SIMULATIONS_STARTED,
    FETCH_SIMULATIONS_FAILURE,
    FETCH_SIMULATIONS_SUCCESS,
} from "./Actions.jsx";

function reducer(state, action) {
    switch (action.type) {
        case FETCH_TRACKS_STARTED:
            return {
                ...state,
                tracks: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case FETCH_TRACKS_SUCCESS:
            return {
                ...state,
                tracks: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }

        case FETCH_TRACKS_FAILURE:
            return {
                ...state,
                tracks: {
                    loading: false,
                    error: action.payload.error,
                    data: [],
                }
            }

        case FETCH_VEHICLES_STARTED:
            return {
                ...state,
                vehicles: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case FETCH_VEHICLES_SUCCESS:
            return {
                ...state,
                vehicles: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }

        case FETCH_VEHICLES_FAILURE:
            return {
                ...state,
                vehicles: {
                    loading: false,
                    error: action.payload.error,
                    data: [],
                }
            }

        case FETCH_SIMULATIONS_STARTED:
            return {
                ...state,
                simulations: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case FETCH_SIMULATIONS_SUCCESS:
            return {
                ...state,
                simulations: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }

        case FETCH_SIMULATIONS_FAILURE:
            return {
                ...state,
                simulations: {
                    loading: false,
                    error: action.payload.error,
                    data: [],
                }
            }

        default:
            return state;
    }
}

export default reducer;