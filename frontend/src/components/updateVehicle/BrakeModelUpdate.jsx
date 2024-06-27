import * as React from 'react';
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import {useContext} from "react";
import AppContext from "../../context/AppContext.jsx";
import {updatePressureToTorqueRatio} from "../../context/Actions.jsx";

export default function BrakeModelUpdate() {

    const {state, dispatch} = useContext(AppContext);
    const {selectedVehicle} = state;
    const {vehicle} = selectedVehicle;

    let { pressureToTorqueRatio } = vehicle;

    const handlePressureToTorqueRatioChange = (event) => {
        const newPressureToTorqueRatio = parseFloat(event.target.value);
        if (!isNaN(newPressureToTorqueRatio)) {
            updatePressureToTorqueRatio(dispatch, newPressureToTorqueRatio);
        }
    }

    return (
        <Box id={"brake-model"} sx={{
            display: 'flex', flexDirection: 'column', gap: '20px',
            borderRadius: '10px', border: '2px solid lightgrey', padding: '20px',
            boxShadow: '0 4px 8px rgba(0, 0, 0, 0.5)'
        }}>
            <Typography variant="h6" gutterBottom component="div">
                Brake Model
            </Typography>
            <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                <Typography variant="body1" gutterBottom component="div" sx={{width: '100%'}}>
                    Pressure to Torque Ratio
                </Typography>
                <TextField sx={{width: '70%'}}
                           required
                           id="pressureToTorqueRatio"
                           label="Required"
                           defaultValue={pressureToTorqueRatio}
                           onChange={handlePressureToTorqueRatioChange}
                />
            </Box>
        </Box>
    )
}