import * as React from 'react';
import {updateLateralGrip, updateLongitudinalGrip, updateTyreRadius} from "../../context/Actions.jsx";
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import {useContext} from "react";
import AppContext from "../../context/AppContext.jsx";

export default function TyreModelUpdate() {

    const {state, dispatch} = useContext(AppContext);
    const {selectedVehicle} = state;
    const {vehicle} = selectedVehicle;

    let { longitudinalGrip, lateralGrip, tyreRadius } = vehicle;

    const handleLongitudinalGripChange = (event) => {
        const newLongitudinalGrip = parseFloat(event.target.value);
        if (!isNaN(newLongitudinalGrip)) {
            updateLongitudinalGrip(dispatch, newLongitudinalGrip);
        }
    }

    const handleLateralGripChange = (event) => {
        const newLateralGrip = parseFloat(event.target.value);
        if (!isNaN(newLateralGrip)) {
            updateLateralGrip(dispatch, newLateralGrip);
        }
    }

    const handleTyreRadiusChange = (event) => {
        const newTyreRadius = parseFloat(event.target.value);
        if (!isNaN(newTyreRadius)) {
            updateTyreRadius(dispatch, newTyreRadius);
        }
    }

    return (
        <Box id={"tyre-model"} sx={{
            display: 'flex', flexDirection: 'column', gap: '20px',
            borderRadius: '10px', border: '2px solid lightgrey', padding: '20px',
            boxShadow: '0 4px 8px rgba(0, 0, 0, 0.5)',
            gridColumn: 'span 3' // This spans the box across 3 columns
        }}>
            <Typography variant="h6" gutterBottom component="div">
                Tyre Model
            </Typography>
            <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                <Typography variant="body1" gutterBottom component="div" sx={{width: '100%'}}>
                    Longitudinal Grip
                </Typography>
                <TextField sx={{width: '500%'}}
                           required
                           id="longitudinalGrip"
                           label="Required"
                           defaultValue={longitudinalGrip}
                           onChange={handleLongitudinalGripChange}
                />
            </Box>
            <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                <Typography variant="body1" gutterBottom component="div" sx={{width: '100%'}}>
                    Lateral Grip
                </Typography>
                <TextField sx={{width: '500%'}}
                           required
                           id="lateralGrip"
                           label="Required"
                           defaultValue={lateralGrip}
                           onChange={handleLateralGripChange}
                />
            </Box>
            <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                <Typography variant="body1" gutterBottom component="div" sx={{width: '100%'}}>
                    Tyre Radius
                </Typography>
                <TextField sx={{width: '487%'}}
                           required
                           id="Tyre Radius"
                           label="Required"
                           defaultValue={tyreRadius}
                           onChange={handleTyreRadiusChange}
                />
                <Typography variant="body1" gutterBottom component="div">
                    m
                </Typography>
            </Box>
        </Box>
    )
}