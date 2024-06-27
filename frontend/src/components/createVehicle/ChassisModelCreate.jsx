import * as React from 'react';
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import {updateVehicleMass} from "../../context/Actions.jsx";
import {useContext, useState} from "react";
import AppContext from "../../context/AppContext.jsx";

export default function ChassisModelCreate() {
    const { dispatch } = useContext(AppContext);
    const [vehicleMass, setVehicleMass] = useState('');
    const [error, setError] = useState(false);
    const [helperText, setHelperText] = useState('');

    const handleVehicleMassChange = (event) => {
        const newMass = event.target.value;
        if (/^\d*\.?\d*$/.test(newMass)) {
            setVehicleMass(newMass);
            setError(false);
            setHelperText('');
            if (newMass !== '') {
                updateVehicleMass(dispatch, parseFloat(newMass));
            }
        } else {
            setError(true);
            setHelperText('Only numbers and one dot are allowed.');
        }
    };

    return (
        <Box id="chassis-model" sx={{
            display: 'flex', flexDirection: 'column', gap: '20px',
            borderRadius: '10px', border: '2px solid lightgrey', padding: '20px',
            boxShadow: '0 4px 8px rgba(0, 0, 0, 0.5)'
        }}>
            <Typography variant="h6" gutterBottom component="div">
                Chassis Model
            </Typography>
            <Box display="flex" alignItems="center" gap="10px">
                <Typography variant="body1" gutterBottom component="div" sx={{ width: '100%' }}>
                    Vehicle Mass
                </Typography>
                <TextField
                    sx={{ width: '100%' }}
                    required
                    id="vehicleMass"
                    label="Required"
                    value={vehicleMass}
                    onChange={handleVehicleMassChange}
                    // error={error}
                    // helperText={helperText}
                    inputProps={{
                        pattern: "^\d*\.?\d*$",
                        title: "Only numbers and one dot are allowed."
                    }}
                />
                <Typography variant="body1" gutterBottom component="div">
                    kg
                </Typography>
            </Box>
        </Box>
    );
}