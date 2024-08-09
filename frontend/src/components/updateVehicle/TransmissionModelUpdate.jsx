import * as React from 'react';
import Typography from "@mui/material/Typography";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import {useContext, useEffect, useState} from "react";
import AppContext from "../../context/AppContext.jsx";
import {
    updateFinalDriveRatio,
    updateGearRatios,
    updateNumberOfGears,
    updateSingleGearRatio
} from "../../context/Actions.jsx";

export default function TransmissionModelUpdate() {

    const {state, dispatch} = useContext(AppContext);
    const {selectedVehicle} = state;
    const {vehicle} = selectedVehicle;

    let { numberOfGears: initialNumberOfGears, gears: initialGears, finalDriveRatio } = vehicle;

    const [numberOfGears, setNumberOfGears] = useState(initialNumberOfGears);
    const [gears, setGears] = useState(initialGears);

    const handleNumberOfGearsChange = (event) => {
        let newNumberOfGears = parseInt(event.target.value, 10);

        // Validate the input to ensure it's between 1 and 8
        if (!isNaN(newNumberOfGears) && newNumberOfGears >= 1 && newNumberOfGears <= 8) {
            setNumberOfGears(newNumberOfGears);
            updateNumberOfGears(dispatch, newNumberOfGears);

            setGears((prevGears) => {
                let updatedGears = [...prevGears];
                if (newNumberOfGears < prevGears.length) {
                    updatedGears = prevGears.slice(0, newNumberOfGears);
                } else {
                    for (let i = prevGears.length; i < newNumberOfGears; i++) {
                        updatedGears.push('');
                    }
                }

                updateGearRatios(dispatch, updatedGears);
                return updatedGears;
            });
        } else {
            // Handle the case where the input is not valid (optional)
            console.error('Number of gears must be an integer between 1 and 8.');
        }
    };

    const handleSingleGearRatioChange = (event, index) => {
        const newGearRatio = parseFloat(event.target.value);
        if (!isNaN(newGearRatio)) {
            const updatedGears = [...gears];
            updatedGears[index] = newGearRatio;
            setGears(updatedGears);
            updateSingleGearRatio(dispatch, newGearRatio, index);
        }
    }

    const handleFinalDriveRatioChange = (event) => {
        const newFinalDriveRatio = parseFloat(event.target.value);
        if (!isNaN(newFinalDriveRatio)) {
            updateFinalDriveRatio(dispatch, newFinalDriveRatio);
        }
    }

    useEffect(() => {
        setGears((prevGears) => {
            const newGears = [...prevGears];
            for (let i = 0; i < 8; i++) {
                if (i < numberOfGears) {
                    if (newGears[i] === null || newGears[i] === undefined) {
                        newGears[i] = '';
                    }
                } else {
                    newGears[i] = null;
                }
            }
            return newGears;
        });
    }, [numberOfGears]);

    return (
        <Box id={"Transmission-model"} sx={{
            display: 'flex', flexDirection: 'column', gap: '20px',
            borderRadius: '10px', border: '2px solid lightgrey', padding: '20px',
            boxShadow: '0 4px 8px rgba(0, 0, 0, 0.5)',
            width: '100%',
            gridColumn: 'span 2' // This spans the box across 2 columns
        }}>
            <Typography variant="h6" gutterBottom component="div">
                Transmission Model
            </Typography>
            <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                <Typography variant="body1" gutterBottom component="div" sx={{width: '60%'}}>
                    No. of gears
                </Typography>
                <TextField sx={{width: '300%'}}
                           required
                           id="numberOfGears"
                           label="Required"
                           value={numberOfGears}
                           onChange={handleNumberOfGearsChange}
                           inputProps={{step: "1"}}
                />
            </Box>
            <Box display={"grid"} gridTemplateColumns={'repeat(3, 1fr)'} gap={"10px"}
                 width={"100%"}>
                {[...Array(8)].map((_, index) => {
                    const isRequired = index < numberOfGears;
                    const justifySelf = index % 3 === 0 ? 'start' : index % 3 === 1 ? 'center' : 'end';

                    return (
                        <Box key={index} display={"flex"} alignItems={"center"}
                             sx={{justifySelf}}>
                            <Typography variant="body1" gutterBottom component="div"
                                        sx={{width: '40%'}}>
                                Gear {index + 1}
                            </Typography>
                            <TextField
                                sx={{width: '60%', ...(isRequired ? {} : {backgroundColor: '#f0f0f0'})}}
                                required={isRequired}
                                disabled={!isRequired}
                                id={`gearRatio${index + 1}`}
                                label={isRequired ? "Required" : ""}
                                value={gears[index] || ''}
                                InputProps={{
                                    readOnly: !isRequired,
                                }}
                                onChange={(event) => handleSingleGearRatioChange(event, index)}
                                inputProps={{step: "0.01"}}
                            />
                        </Box>
                    );
                })}
            </Box>

            <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                <Typography variant="body1" gutterBottom component="div" sx={{width: '80%'}}>
                    Final Drive Ratio
                </Typography>
                <TextField sx={{width: '300%'}}
                           required
                           id="finalDriveRatio"
                           label="Required"
                           defaultValue={finalDriveRatio}
                           onChange={handleFinalDriveRatioChange}
                />
            </Box>
        </Box>
    )
}