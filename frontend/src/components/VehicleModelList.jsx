import * as React from 'react';
import Title from '../components/Title';
import Table from "@mui/material/Table";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import TableBody from "@mui/material/TableBody";
import {useContext, useEffect} from "react";
import AppContext from "../context/AppContext.jsx";
import {fetchVehicles, updateSelectedVehicle} from "../context/Actions.jsx";
import Box from "@mui/material/Box";
import {CircularProgress, Collapse, useTheme} from "@mui/material";

import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import {Link} from "react-router-dom";
import {useNavigate} from "react-router";


function preventDefault(event) {
    event.preventDefault();
}

export default function VehicleModelsList() {
    const theme = useTheme();
    const {state, dispatch} = useContext(AppContext);
    const {vehicles} = state;
    const {loading, error, data} = vehicles;

    const [open, setOpen] = React.useState(data.map(() => false));

    const navigate = useNavigate();

    useEffect(() => {
        fetchVehicles(dispatch);
    }, []);

    const handleCreateSimulationOrEditVehicleModelClick = (vehicle) => {
        updateSelectedVehicle(dispatch, vehicle)
    }


    if (loading) {
        return (
            <Box
                display="flex"
                justifyContent="center"
                alignItems="center"
                height="100vh" // Adjust this value as needed
            >
                <CircularProgress size={60} style={{color: 'black'}}/>
            </Box>
        );
    }

    if (error) {
        return (
            <Box
                display="flex"
                justifyContent="center"
                alignItems="center"
                height="100vh" // Adjust this value as needed
            >
                <h1>Error: {error}</h1>
            </Box>
        )
    }

    // Sort the data array alphabetically by vehicleName
    const sortedData = data.sort((a, b) => a.vehicleName.localeCompare(b.vehicleName));


    return (
        <React.Fragment>
            <Title>Vehicle Models</Title>
            <TableContainer sx={{maxHeight: 600}}>  {/* Set maxHeight here */}
                <Table size="small" stickyHeader>
                    <TableHead>
                        <TableRow>
                            <TableCell sx={{fontWeight: 'bold'}}>Name</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {sortedData.map((vehicle, index) => (
                            <React.Fragment key={vehicle.vehicleID}>
                                <TableRow onClick={() => setOpen(prevOpen => {
                                    const newOpen = [...prevOpen];
                                    newOpen[index] = !newOpen[index];
                                    return newOpen;
                                })}>
                                    <TableCell
                                        style={{
                                            color: 'inherit', // Inherit table cell text color initially
                                            textDecoration: 'none', // Remove underline
                                            cursor: 'pointer', // Change cursor to pointer on hover
                                            borderBottom: open[index] ? '2px solid grey' : 'white'
                                        }}
                                        onMouseEnter={(event) => {
                                            event.preventDefault(); // Prevent default behavior on hover
                                            event.currentTarget.style.color = '#007bff'; // Change color to primary on hover
                                        }}
                                        onMouseLeave={(event) => {
                                            event.currentTarget.style.color = 'inherit'; // Revert color on mouse leave
                                        }}
                                    >
                                        {vehicle.vehicleName}
                                    </TableCell>
                                </TableRow>
                                <TableRow>
                                    <TableCell style={{
                                        paddingBottom: 0,
                                        paddingTop: 0,
                                        backgroundColor: 'rgba(0, 0, 0, 0.03)',
                                        borderRadius: '0 0 20px 20px'
                                    }} colSpan={6}>
                                        <Collapse in={open[index]} timeout="auto" unmountOnExit>
                                            <Box
                                                margin={1}
                                                marginTop={3}
                                                sx={{
                                                    display: 'grid',
                                                    gridTemplateColumns: 'repeat(3, 1fr)', // This creates two columns of equal width
                                                    gap: '50px', // Adjust the gap between the boxes as needed
                                                    justifyContent: 'center',
                                                }}
                                            >

                                                <Box>
                                                    <Typography variant="h6" gutterBottom component="div">
                                                        Aero Model
                                                    </Typography>
                                                    <Typography variant="body1" gutterBottom component="div">
                                                        Downforce Coefficient: <span
                                                        style={{fontWeight: 'bold'}}>{vehicle.downforceCoefficient}</span>
                                                    </Typography>
                                                    <Typography variant="body1" gutterBottom component="div">
                                                        Drag Coefficient: <span
                                                        style={{fontWeight: 'bold'}}>{vehicle.dragCoefficient}</span>
                                                    </Typography>
                                                </Box>
                                                <Box>
                                                    <Typography variant="h6" gutterBottom component="div">
                                                        Brake Model
                                                    </Typography>
                                                    <Typography variant="body1" gutterBottom component="div">
                                                        Pressure to Torque Ratio: <span
                                                        style={{fontWeight: 'bold'}}>{vehicle.pressureToTorqueRatio}</span>
                                                    </Typography>
                                                </Box>
                                                <Box>
                                                    <Typography variant="h6" gutterBottom component="div">
                                                        Chassis Model
                                                    </Typography>
                                                    <Typography variant="body1" gutterBottom component="div">
                                                        Vehicle Mass: <span
                                                        style={{fontWeight: 'bold'}}>{vehicle.mass}</span>
                                                    </Typography>
                                                </Box>

                                                {/* Divider between rows */}
                                                {/*<Divider sx={{ gridColumn: '1 / -1', borderBottom: '1px solid lightgrey'}} />*/}

                                                <Box>
                                                    <Typography variant="h6" gutterBottom component="div">
                                                        Powertrain Model
                                                    </Typography>
                                                    <Typography variant="body1" gutterBottom component="div">
                                                        Max. Power: <span
                                                        style={{fontWeight: 'bold'}}>{vehicle.powerMax}</span>
                                                    </Typography>
                                                    <Typography variant="body1" gutterBottom component="div">
                                                        Max. Torque: <span
                                                        style={{fontWeight: 'bold'}}>{vehicle.torqueMax}</span>
                                                    </Typography>
                                                    <Typography variant="body1" gutterBottom component="div">
                                                        RPM at Max. Power: <span
                                                        style={{fontWeight: 'bold'}}>{vehicle.rpmPowerMax}</span>
                                                    </Typography>
                                                    <Typography variant="body1" gutterBottom component="div">
                                                        RPM at Max. Torque: <span
                                                        style={{fontWeight: 'bold'}}>{vehicle.rpmTorqueMax}</span>
                                                    </Typography>
                                                </Box>
                                                <Box>
                                                    <Typography variant="h6" gutterBottom component="div">
                                                        Transmission Model
                                                    </Typography>
                                                    {vehicle.gears.map((gear, index) => (
                                                        <Typography variant="body1" gutterBottom component="div"
                                                                    key={index}>
                                                            Gear {index + 1}: <span
                                                            style={{fontWeight: 'bold'}}>{gear}</span>
                                                        </Typography>
                                                    ))}
                                                    <Typography variant="body1" gutterBottom component="div">
                                                        Final Drive Ratio: <span
                                                        style={{fontWeight: 'bold'}}>{vehicle.finalDriveRatio}</span>
                                                    </Typography>
                                                </Box>
                                                <Box>
                                                    <Typography variant="h6" gutterBottom component="div">
                                                        Tyre Model
                                                    </Typography>
                                                    <Typography variant="body1" gutterBottom component="div">
                                                        Longitudinal Grip: <span
                                                        style={{fontWeight: 'bold'}}>{vehicle.longitudinalGrip}</span>
                                                    </Typography>
                                                    <Typography variant="body1" gutterBottom component="div">
                                                        Lateral Grip: <span
                                                        style={{fontWeight: 'bold'}}>{vehicle.lateralGrip}</span>
                                                    </Typography>
                                                    <Typography variant="body1" gutterBottom component="div">
                                                        Tyre Radius: <span
                                                        style={{fontWeight: 'bold'}}>{vehicle.tyreRadius}</span>
                                                    </Typography>
                                                </Box>
                                            </Box>
                                            <Box
                                                sx={{
                                                    mb: 2,
                                                    display: 'flex',
                                                    alignItems: 'center',
                                                    justifyContent: 'center',
                                                }}
                                            >
                                                <Button
                                                    onClick={() => {
                                                        handleCreateSimulationOrEditVehicleModelClick(vehicle)
                                                    }}
                                                    variant="contained"
                                                    sx={{
                                                        margin: '10px',
                                                        backgroundColor: 'lightgrey',
                                                        '&:hover': {
                                                            backgroundColor: 'darkgrey', // Change this to the color you want on hover
                                                        },
                                                    }}
                                                >
                                                    <Link
                                                        to={"/createSimulation"}
                                                        style={{
                                                            color: 'black',
                                                            textDecoration: 'none',
                                                        }}
                                                        onMouseEnter={(e) => {
                                                            e.target.style.textDecoration = 'none';
                                                            e.target.style.color = 'black';
                                                        }}
                                                        onMouseLeave={(e) => {
                                                            e.target.style.textDecoration = 'none';
                                                            e.target.style.color = 'black';
                                                        }}
                                                    >
                                                        Create simulation
                                                    </Link>
                                                </Button>
                                                <Button
                                                    onClick={() => {
                                                        handleCreateSimulationOrEditVehicleModelClick(vehicle)
                                                    }}
                                                    variant="contained"
                                                    sx={{
                                                        margin: '10px',
                                                        backgroundColor: 'lightgrey',
                                                        '&:hover': {
                                                            backgroundColor: 'darkgrey', // Change this to the color you want on hover
                                                        },
                                                    }}
                                                >
                                                    <Link
                                                        to={"/vehicleModels/" + vehicle.vehicleID}
                                                        style={{
                                                            color: 'black',
                                                            textDecoration: 'none',
                                                        }}
                                                        onMouseEnter={(e) => {
                                                            e.target.style.textDecoration = 'none';
                                                            e.target.style.color = 'black';
                                                        }}
                                                        onMouseLeave={(e) => {
                                                            e.target.style.textDecoration = 'none';
                                                            e.target.style.color = 'black';
                                                        }}
                                                    >
                                                        Edit Vehicle Model
                                                    </Link>
                                                </Button>
                                            </Box>

                                        </Collapse>
                                    </TableCell>
                                </TableRow>
                            </React.Fragment>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
            <Box sx={{marginTop: '50px', display: 'flex', alignItems:'center', justifyContent: 'center'}}>
                <Button
                    variant="contained"
                    sx={{
                        margin: '10px',
                        backgroundColor: 'lightgrey',
                        '&:hover': {
                            backgroundColor: 'darkgrey', // Change this to the color you want on hover
                        },
                    }}
                >
                    <Link
                        to={"/vehicleModels/create"}
                        style={{
                            color: 'black',
                            textDecoration: 'none',
                        }}
                        onMouseEnter={(e) => {
                            e.target.style.textDecoration = 'none';
                            e.target.style.color = 'black';
                        }}
                        onMouseLeave={(e) => {
                            e.target.style.textDecoration = 'none';
                            e.target.style.color = 'black';
                        }}
                    >
                        Create Vehicle Model
                    </Link>
                </Button>
                <Button
                    sx={{
                        margin: '10px',
                        backgroundColor: 'lightgrey',
                        color: 'black',
                        '&:hover': {
                            backgroundColor: 'darkgrey', // Change this to the color you want on hover
                            color: 'black'
                        },
                    }}
                    onClick={() => navigate(-1)}
                    variant="contained"
                >
                    Back
                </Button>
            </Box>
        </React.Fragment>
    )
}
