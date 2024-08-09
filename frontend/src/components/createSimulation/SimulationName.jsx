import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import AppContext from "../../context/AppContext.jsx";
import {useContext} from "react";
import {updateSimulationName} from "../../context/Actions.jsx";

export default function SimulationName() {
    const {dispatch} = useContext(AppContext);
    // const {simulation} = state;
    // const {simulationName} = simulation;

    const handleChange = (event) => {
        updateSimulationName(dispatch, event.target.value);
    }

    return (
        <Box
            component="form"
            sx={{ m: 1, width: '100%' }}
            noValidate
            autoComplete="off"
            onChange={handleChange}
        >
            <TextField id="simulation-name" label="Simulation Name" variant="outlined" />
        </Box>
    );
}
