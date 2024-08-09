import * as React from 'react';
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import {useContext, useState} from "react";
import AppContext from "../../context/AppContext.jsx";
import {updatePressureToTorqueRatio} from "../../context/Actions.jsx";

export default function BrakeModelCreate() {
    const { dispatch } = useContext(AppContext);
    const [pressureToTorqueRatio, setPressureToTorqueRatio] = useState('');
    const [error, setError] = useState(false);
    const [helperText, setHelperText] = useState('');

    const handlePressureToTorqueRatioChange = (event) => {
        const newPressureToTorqueRatio = event.target.value;
        if (/^-?\d*\.?\d*$/.test(newPressureToTorqueRatio)) {
            setPressureToTorqueRatio(newPressureToTorqueRatio);
            setError(false);
            setHelperText('');
            if (newPressureToTorqueRatio !== '') {
                updatePressureToTorqueRatio(dispatch, parseFloat(newPressureToTorqueRatio));
            }
        } else {
            setError(true);
            setHelperText('Only numbers, one dot, and a minus sign are allowed.');
        }
    };

    return (
        <Box id="brake-model" sx={{
            display: 'flex', flexDirection: 'column', gap: '20px',
            borderRadius: '10px', border: '2px solid lightgrey', padding: '20px',
            boxShadow: '0 4px 8px rgba(0, 0, 0, 0.5)'
        }}>
            <Typography variant="h6" gutterBottom component="div">
                Brake Model
            </Typography>
            <Box display="flex" alignItems="center" gap="10px">
                <Typography variant="body1" gutterBottom component="div" sx={{ width: '100%' }}>
                    Pressure to Torque Ratio
                </Typography>
                <TextField
                    sx={{ width: '70%' }}
                    required
                    id="pressureToTorqueRatio"
                    label="Required"
                    value={pressureToTorqueRatio}
                    onChange={handlePressureToTorqueRatioChange}
                    // error={error}
                    // helperText={helperText}
                    inputProps={{
                        pattern: "^-?\\d*\\.?\\d*$",
                        title: "Only numbers, one dot, and a minus sign are allowed."
                    }}
                />
            </Box>
        </Box>
    );
}