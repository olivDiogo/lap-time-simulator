import * as React from 'react';
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import {FormControl, InputLabel, MenuItem, Select} from "@mui/material";
import {
    updatePowerMax,
    updatePowertrainType,
    updateRpmPowerMax,
    updateRpmTorqueMax,
    updateTorqueMax
} from "../../context/Actions.jsx";
import {useContext, useEffect, useState} from "react";
import AppContext from "../../context/AppContext.jsx";

export default function PowertrainModelCreate() {
    const { dispatch, state } = useContext(AppContext);
    const [powertrainType, setPowertrainType] = useState(state.powertrainType || '');
    const [powerMax, setPowerMax] = useState('');
    const [torqueMax, setTorqueMax] = useState('');
    const [rpmPowerMax, setRpmPowerMax] = useState('');
    const [rpmTorqueMax, setRpmTorqueMax] = useState('');

    useEffect(() => {
        if (powertrainType === 'ELECTRIC') {
            setRpmPowerMax('');
            setRpmTorqueMax('');
        }
    }, [powertrainType]);

    const handlePowerMaxChange = (event) => {
        let newPowerMax = event.target.value.replace(/[^0-9.]+/, ''); // Allow only numbers and one dot
        newPowerMax = newPowerMax.replace(/(\..*)\./, '$1'); // Remove subsequent dots
        setPowerMax(newPowerMax);
        updatePowerMax(dispatch, parseFloat(newPowerMax));
    };

    const handleTorqueMaxChange = (event) => {
        let newTorqueMax = event.target.value.replace(/[^0-9.]+/, ''); // Allow only numbers and one dot
        newTorqueMax = newTorqueMax.replace(/(\..*)\./, '$1'); // Remove subsequent dots
        setTorqueMax(newTorqueMax);
        updateTorqueMax(dispatch, parseFloat(newTorqueMax));
    };

    const handleRpmPowerMaxChange = (event) => {
        let newRpmPowerMax = event.target.value.replace(/[^0-9.]+/, ''); // Allow only numbers and one dot
        newRpmPowerMax = newRpmPowerMax.replace(/(\..*)\./, '$1'); // Remove subsequent dots
        setRpmPowerMax(newRpmPowerMax);
        updateRpmPowerMax(dispatch, parseFloat(newRpmPowerMax));
    };

    const handleRpmTorqueMaxChange = (event) => {
        let newRpmTorqueMax = event.target.value.replace(/[^0-9.]+/, ''); // Allow only numbers and one dot
        newRpmTorqueMax = newRpmTorqueMax.replace(/(\..*)\./, '$1'); // Remove subsequent dots
        setRpmTorqueMax(newRpmTorqueMax);
        updateRpmTorqueMax(dispatch, parseFloat(newRpmTorqueMax));
    };

    const handlePowertrainTypeChange = (event) => {
        const newType = event.target.value;
        setPowertrainType(newType);
        updatePowertrainType(dispatch, newType);
    };

    return (
        <Box id="powertrain-model" sx={{
            display: 'flex', flexDirection: 'column', gap: '20px',
            borderRadius: '10px', border: '2px solid lightgrey', padding: '20px',
            boxShadow: '0 4px 8px rgba(0, 0, 0, 0.5)'
        }}>
            <Typography variant="h6" gutterBottom component="div">
                Powertrain Model
            </Typography>
            <Box display="flex" alignItems="center" gap="10px">
                <Typography variant="body1" gutterBottom component="div" sx={{ width: '80%' }}>
                    Max. Power
                </Typography>
                <TextField
                    sx={{ width: '100%' }}
                    required
                    id="powerMax"
                    label="Required"
                    value={powerMax}
                    onChange={handlePowerMaxChange}
                    error={!/^\d*\.?\d*$/.test(powerMax)}
                    helperText={!/^\d*\.?\d*$/.test(powerMax) ? "Only numbers and one dot are allowed." : ""}
                    InputProps={{
                        inputProps: {
                            pattern: /^\d*\.?\d*$/,
                            title: "Only numbers and one dot are allowed."
                        }
                    }}
                />
                <Typography variant="body1" gutterBottom component="div" padding="4px">
                    hp
                </Typography>
            </Box>
            <Box display="flex" alignItems="center" gap="10px">
                <Typography variant="body1" gutterBottom component="div" sx={{ width: '80%' }}>
                    Max. Torque
                </Typography>
                <TextField
                    sx={{ width: '100%' }}
                    required
                    id="torqueMax"
                    label="Required"
                    value={torqueMax}
                    onChange={handleTorqueMaxChange}
                    error={!/^\d*\.?\d*$/.test(torqueMax)}
                    helperText={!/^\d*\.?\d*$/.test(torqueMax) ? "Only numbers and one dot are allowed." : ""}
                    InputProps={{
                        inputProps: {
                            pattern: /^\d*\.?\d*$/,
                            title: "Only numbers and one dot are allowed."
                        }
                    }}
                />
                <Typography variant="body1" gutterBottom component="div" padding="1px">
                    Nm
                </Typography>
            </Box>
            <Box display="flex" alignItems="center" gap="10px">
                <Typography variant="body1" gutterBottom component="div" sx={{ width: '80%' }}>
                    RPM at Max. Power
                </Typography>
                <TextField
                    sx={{ width: '100%', ...(powertrainType === 'ELECTRIC' ? { backgroundColor: '#f0f0f0' } : {}) }}
                    id="rpmPowerMax"
                    label={powertrainType === 'ELECTRIC' ? "" : "Required"}
                    required={powertrainType !== 'ELECTRIC'}
                    value={rpmPowerMax}
                    disabled={powertrainType === 'ELECTRIC'}
                    onChange={handleRpmPowerMaxChange}
                    error={!/^\d*\.?\d*$/.test(rpmPowerMax)}
                    helperText={!/^\d*\.?\d*$/.test(rpmPowerMax) ? "Only numbers and one dot are allowed." : ""}
                    InputProps={{
                        inputProps: {
                            pattern: /^\d*\.?\d*$/,
                            title: "Only numbers and one dot are allowed."
                        }
                    }}
                />
                <Typography variant="body1" gutterBottom component="div">
                    rpm
                </Typography>
            </Box>
            <Box display="flex" alignItems="center" gap="10px">
                <Typography variant="body1" gutterBottom component="div" sx={{ width: '80%' }}>
                    RPM at Max. Torque
                </Typography>
                <TextField
                    sx={{ width: '100%', ...(powertrainType === 'ELECTRIC' ? { backgroundColor: '#f0f0f0' } : {}) }}
                    id="rmpTorqueMax"
                    label={powertrainType === 'ELECTRIC' ? "" : "Required"}
                    required={powertrainType !== 'ELECTRIC'}
                    value={rpmTorqueMax}
                    disabled={powertrainType === 'ELECTRIC'}
                    onChange={handleRpmTorqueMaxChange}
                    error={!/^\d*\.?\d*$/.test(rpmTorqueMax)}
                    helperText={!/^\d*\.?\d*$/.test(rpmTorqueMax) ? "Only numbers and one dot are allowed." : ""}
                    InputProps={{
                        inputProps: {
                            pattern: /^\d*\.?\d*$/,
                            title: "Only numbers and one dot are allowed."
                        }
                    }}
                />
                <Typography variant="body1" gutterBottom component="div">
                    rpm
                </Typography>
            </Box>
            <Box sx={{ width: '100%' }}>
                <FormControl fullWidth>
                    <InputLabel id="powertrain-type">Type</InputLabel>
                    <Select
                        labelId="powertrain-type"
                        id="powertrain-type"
                        value={powertrainType}
                        label="Type"
                        onChange={handlePowertrainTypeChange}
                    >
                        <MenuItem value={"COMBUSTION"}>Combustion</MenuItem>
                        <MenuItem value={"ELECTRIC"}>Electric</MenuItem>
                    </Select>
                </FormControl>
            </Box>
        </Box>
    );
}