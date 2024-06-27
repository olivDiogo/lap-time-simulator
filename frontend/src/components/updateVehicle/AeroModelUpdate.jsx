import * as React from 'react';
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import {useContext} from "react";
import AppContext from "../../context/AppContext.jsx";
import {updateDownforceCoefficient, updateDragCoefficient} from "../../context/Actions.jsx";

export default function AeroModelUpdate() {

    const {state, dispatch} = useContext(AppContext);
    const {selectedVehicle} = state;
    const {vehicle} = selectedVehicle;

    let { downforceCoefficient, dragCoefficient } = vehicle;

    const handleDownforceCoefficientChange = (event) => {
        const newDownforceCoefficient = parseFloat(event.target.value);
        if (!isNaN(newDownforceCoefficient)) {
            updateDownforceCoefficient(dispatch, newDownforceCoefficient);
        }
    }

    const handleDragCoefficientChange = (event) => {
        const newDragCoefficient = parseFloat(event.target.value);
        if (!isNaN(newDragCoefficient)) {
            updateDragCoefficient(dispatch, newDragCoefficient);
        }
    }

    return (
        <Box id={"aero-model"} sx={{
            display: 'flex', flexDirection: 'column', gap: '20px',
            borderRadius: '10px', border: '2px solid lightgrey', padding: '20px',
            boxShadow: '0 4px 8px rgba(0, 0, 0, 0.5)'
        }}>
            <Typography variant="h6" gutterBottom component="div">
                Aero Model
            </Typography>
            <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                <Typography variant="body1" gutterBottom component="div" sx={{width: '100%'}}>
                    Downforce Coefficient
                </Typography>
                <TextField sx={{width: '70%'}}
                           required
                           id="downforceCoefficient"
                           label="Required"
                           value={downforceCoefficient}
                           onChange={handleDownforceCoefficientChange}
                />
            </Box>
            <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                <Typography variant="body1" gutterBottom component="div" sx={{width: '100%'}}>
                    Drag Coefficient
                </Typography>
                <TextField
                    sx={{width: '70%'}}
                    required
                    id="dragCoefficient"
                    label="Required"
                    value={dragCoefficient}
                    onChange={handleDragCoefficientChange}
                />
            </Box>
        </Box>
    )
}