import * as React from 'react';
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import {useContext} from "react";
import AppContext from "../../context/AppContext.jsx";
import {updateVehicleName} from "../../context/Actions.jsx";

export default function VehicleNameCreate() {

    const {dispatch} = useContext(AppContext)
    const handleVehicleNameChange = (event) => {
        updateVehicleName(dispatch, event.target.value)
    }


    return (
        <Box id={"vehicle-name"} sx={{
            display: 'flex', flexDirection: 'column', gap: '20px',
            borderRadius: '10px', border: '2px solid lightgrey', padding: '20px',
            boxShadow: '0 4px 8px rgba(0, 0, 0, 0.5)',
            gridColumn: 'span 3'
        }}>
            <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                <Typography variant="h6" gutterBottom component="div" sx={{width: '100%'}}>
                    Vehicle Model Name
                </Typography>
                <TextField sx={{width: '400%'}}
                           required
                           id="downforceCoefficient"
                           label="Required"
                           defaultValue={""}
                           onChange={handleVehicleNameChange}
                />
            </Box>
        </Box>
    )
}