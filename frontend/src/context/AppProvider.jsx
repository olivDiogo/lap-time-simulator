import React, {useReducer} from 'react';
import PropTypes from "prop-types";
import {Provider} from "./AppContext.jsx";
import reducer from "./Reducer.jsx";

const initialState = {
    tracks: {
        loading: true,
        error: null,
        data: [],
    },

    vehicles: {
        loading: false,
        error: null,
        data: [],
    },

    selectedTrack: {
        trackId: null,
        trackName: null,
    },

    selectedVehicle: {
        loading: false,
        error: null,
        vehicle: '',
    },

    simulations: {
        loading: true,
        error: null,
        data: [],
    },

    simulation: {
        simulationName: null,
    },

    startSimulation: {
        loading: false,
        simulationId: null,
        error: null,
    },

    createSimulation: {
        loading: false,
        vehicleId: null,
        trackId: null,
        simulationName: null,
        error: null,
    },

    alert: {
        alertMessage: '',
        alertType: 'success', // success, error, warning, info
        showAlert: false,
    }
};

const AppProvider = (props) => {
    const [state, dispatch] = useReducer(reducer, initialState);
    return (
        <Provider value={{
            state,
            dispatch
        }}>
            {props.children}
        </Provider>
    );
};

AppProvider.propTypes = {
    children: PropTypes.node,
};

export default AppProvider;