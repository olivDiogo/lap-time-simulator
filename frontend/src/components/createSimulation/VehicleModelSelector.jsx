import * as React from 'react';
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import AppContext from "../../context/AppContext.jsx";
import { useContext, useEffect } from "react";
import {fetchVehicles, updateSelectedVehicle} from "../../context/Actions.jsx";

export default function VehicleModelSelector() {
    const { state, dispatch } = useContext(AppContext);
    const { vehicles, selectedVehicle } = state;
    const { loading, error, data } = vehicles;
    const { vehicle } = selectedVehicle;
    const { vehicleName, vehicleID } = vehicle;

    const handleChange = (event) => {
        const selectedVehicleId = event.target.value;
        const selectedVehicle = data.find(vehicle => vehicle.vehicleID === selectedVehicleId);
        updateSelectedVehicle(dispatch, selectedVehicle);
    };

    useEffect(() => {
        fetchVehicles(dispatch);
    }, [dispatch]);

    return (
        <Box sx={{ width: "100%" }}>
            <FormControl fullWidth>
                <InputLabel id="vehicle-selector-label">Vehicle Model</InputLabel>
                <Select
                    labelId="vehicle-model-selector-label"
                    id="vehicle-model-selector"
                    value={vehicleID}
                    label="Vehicle Model"
                    onChange={handleChange}
                >
                    {data.map((vehicle) => (
                        <MenuItem key={vehicle.vehicleID} value={vehicle.vehicleID}>
                            {vehicle.vehicleName}
                        </MenuItem>
                    ))}
                </Select>
            </FormControl>
        </Box>
    );
}
