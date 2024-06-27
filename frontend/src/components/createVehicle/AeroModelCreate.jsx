import * as React from 'react';
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import {useContext, useState} from "react";
import AppContext from "../../context/AppContext.jsx";
import {updateDownforceCoefficient, updateDragCoefficient} from "../../context/Actions.jsx";

export default function AeroModelCreate() {
    const { dispatch } = useContext(AppContext);

    const [downforceCoefficient, setDownforceCoefficient] = useState('');
    const [dragCoefficient, setDragCoefficient] = useState('');
    const [downforceError, setDownforceError] = useState(false);
    const [dragError, setDragError] = useState(false);
    const [errorMessage, setErrorMessage] = useState('');

    const validateInput = (value) => {
        const regex = /^-?\d*\.?\d*$/;
        return regex.test(value);
    };

    const handleDownforceCoefficientChange = (event) => {
        const newDownforceCoefficient = event.target.value;
        if (validateInput(newDownforceCoefficient)) {
            setDownforceCoefficient(newDownforceCoefficient);
            setDownforceError(false);
            setErrorMessage('');
            if (newDownforceCoefficient !== '') {
                updateDownforceCoefficient(dispatch, parseFloat(newDownforceCoefficient));
            }
        } else {
            setDownforceError(true);
            setErrorMessage('Invalid input: only numbers, one dot, and a minus sign are allowed.');
        }
    };

    const handleDragCoefficientChange = (event) => {
        const newDragCoefficient = event.target.value;
        if (validateInput(newDragCoefficient)) {
            setDragCoefficient(newDragCoefficient);
            setDragError(false);
            setErrorMessage('');
            if (newDragCoefficient !== '') {
                updateDragCoefficient(dispatch, parseFloat(newDragCoefficient));
            }
        } else {
            setDragError(true);
            setErrorMessage('Invalid input: only numbers, one dot, and a minus sign are allowed.');
        }
    };

    return (
        <Box id="aero-model" sx={{
            display: 'flex', flexDirection: 'column', gap: '20px',
            borderRadius: '10px', border: '2px solid lightgrey', padding: '20px',
            boxShadow: '0 4px 8px rgba(0, 0, 0, 0.5)'
        }}>
            <Typography variant="h6" gutterBottom component="div">
                Aero Model
            </Typography>
            <Box display="flex" alignItems="center" gap="10px">
                <Typography variant="body1" gutterBottom component="div" sx={{ width: '100%' }}>
                    Downforce Coefficient
                </Typography>
                <TextField
                    sx={{ width: '70%' }}
                    required
                    id="downforceCoefficient"
                    label="Required"
                    value={downforceCoefficient}
                    onChange={handleDownforceCoefficientChange}
                    // error={downforceError}
                    // helperText={downforceError ? errorMessage : ''}
                    inputProps={{ pattern: "^-?\\d*\\.?\\d*$" }}
                />
            </Box>
            <Box display="flex" alignItems="center" gap="10px">
                <Typography variant="body1" gutterBottom component="div" sx={{ width: '100%' }}>
                    Drag Coefficient
                </Typography>
                <TextField
                    sx={{ width: '70%' }}
                    required
                    id="dragCoefficient"
                    label="Required"
                    value={dragCoefficient}
                    onChange={handleDragCoefficientChange}
                    // error={dragError}
                    // helperText={dragError ? errorMessage : ''}
                    inputProps={{ pattern: "^-?\\d*\\.?\\d*$" }}
                />
            </Box>
        </Box>
    );
}