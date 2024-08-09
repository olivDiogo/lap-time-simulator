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
import {useContext, useEffect} from "react";
import AppContext from "../../context/AppContext.jsx";

export default function PowertrainModelUpdate() {

    const {state, dispatch} = useContext(AppContext);
    const {selectedVehicle} = state;
    const {vehicle} = selectedVehicle;

    let { powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, powertrainType } = vehicle;

    const handlePowerMaxChange = (event) => {
        const newPowerMax = parseFloat(event.target.value);
        if (!isNaN(newPowerMax)) {
            updatePowerMax(dispatch, newPowerMax);
        }
    }

    const handleTorqueMaxChange = (event) => {
        const newTorqueMax = parseFloat(event.target.value);
        if (!isNaN(newTorqueMax)) {
            updateTorqueMax(dispatch, newTorqueMax);
        }
    }

    const handleRpmPowerMaxChange = (event) => {
        const newRpmPowerMax = parseFloat(event.target.value);
        if (!isNaN(newRpmPowerMax)) {
            updateRpmPowerMax(dispatch, newRpmPowerMax);
        }
    }

    const handleRpmTorqueMaxChange = (event) => {
        const newRpmTorqueMax = parseFloat(event.target.value);
        if (!isNaN(newRpmTorqueMax)) {
            updateRpmTorqueMax(dispatch, newRpmTorqueMax);
        }
    }

    const handlePowertrainTypeChange = (event) => {
        const newType = event.target.value;
        updatePowertrainType(dispatch, newType);
    };

    useEffect(() => {
        if (powertrainType === 'ELECTRIC') {
            rpmPowerMax = ('');
            rpmTorqueMax = ('');
        }
    }, [powertrainType]);

    return (
        <Box id={"powertrain-model"} sx={{
            display: 'flex', flexDirection: 'column', gap: '20px',
            borderRadius: '10px', border: '2px solid lightgrey', padding: '20px',
            boxShadow: '0 4px 8px rgba(0, 0, 0, 0.5)'
        }}>
            <Typography variant="h6" gutterBottom component="div">
                Powertrain Model
            </Typography>
            <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                <Typography variant="body1" gutterBottom component="div" sx={{width: '80%'}}>
                    Max. Power
                </Typography>
                <TextField sx={{width: '100%'}}
                           required
                           id="powerMax"
                           label="Required"
                           defaultValue={powerMax}
                           onChange={handlePowerMaxChange}
                />
                <Typography variant="body1" gutterBottom component="div" padding={"4px"}>
                    hp
                </Typography>
            </Box>
            <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                <Typography variant="body1" gutterBottom component="div" sx={{width: '80%'}}>
                    Max. Torque
                </Typography>
                <TextField sx={{width: '100%'}}
                           required
                           id="torqueMax"
                           label="Required"
                           defaultValue={torqueMax}
                           onChange={handleTorqueMaxChange}
                />
                <Typography variant="body1" gutterBottom component="div" padding={"1px"}>
                    Nm
                </Typography>
            </Box>
            <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                <Typography variant="body1" gutterBottom component="div" sx={{width: '80%'}}>
                    RPM at Max. Power
                </Typography>
                <TextField
                    sx={{width: '100%', ...(powertrainType === 'ELECTRIC' ? {backgroundColor: '#f0f0f0'} : {})}}
                    id="rpmPowerMax"
                    label={powertrainType === 'ELECTRIC' ? "" : "Required"}
                    required={powertrainType !== 'ELECTRIC'}
                    value={powertrainType !== 'ELECTRIC' ? rpmPowerMax : ""}
                    disabled={powertrainType === 'ELECTRIC'}
                    onChange={handleRpmPowerMaxChange}
                />
                <Typography variant="body1" gutterBottom component="div">
                    rpm
                </Typography>
            </Box>
            <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                <Typography variant="body1" gutterBottom component="div" sx={{width: '80%'}}>
                    RPM at Max. Torque
                </Typography>
                <TextField
                    sx={{width: '100%', ...(powertrainType === 'ELECTRIC' ? {backgroundColor: '#f0f0f0'} : {})}}
                    id="rmpTorqueMax"
                    label={powertrainType === 'ELECTRIC' ? "" : "Required"}
                    required={powertrainType !== 'ELECTRIC'}
                    value={powertrainType !== 'ELECTRIC' ? rpmTorqueMax : ""}
                    disabled={powertrainType === 'ELECTRIC'}
                    onChange={handleRpmTorqueMaxChange}
                />
                <Typography variant="body1" gutterBottom component="div">
                    rpm
                </Typography>
            </Box>
            <Box sx={{width: '100%'}}>
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
    )
}