import * as React from 'react';
import Button from '@mui/material/Button';
import AppContext from "../../context/AppContext.jsx";
import {useContext} from "react";
import {postCreateSimulation} from "../../context/Actions.jsx";

export default function CreateSimulationButton() {
    const {state, dispatch} = useContext(AppContext);
    const {selectedTrack, selectedVehicle} = state;
    const {trackId} = selectedTrack;
    const {vehicle} = selectedVehicle;
    const {vehicleID} = vehicle;
    const {simulation} = state;
    const {simulationName} = simulation;

    const handleCreateSimulation = () => {
        postCreateSimulation(dispatch, simulationName, trackId, vehicleID);
    }
    return (
        <Button onClick={() => {
            handleCreateSimulation()
        }}
                variant="contained" sx={{
            color: 'black',
            backgroundColor: 'lightgrey', '&:hover': {
                backgroundColor: 'darkgrey', // Change this to the color you want on hover
            }
        }}>
            Create Simulation
        </Button>
    )
}

