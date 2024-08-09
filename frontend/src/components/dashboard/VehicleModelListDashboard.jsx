import * as React from 'react';
import Title from '../Title.jsx';
import Table from "@mui/material/Table";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import TableBody from "@mui/material/TableBody";
import {useContext, useEffect} from "react";
import AppContext from "../../context/AppContext.jsx";
import {fetchVehicles} from "../../context/Actions.jsx";
import Box from "@mui/material/Box";
import {CircularProgress, Collapse, useTheme} from "@mui/material";
import {Link} from "react-router-dom";
import Typography from "@mui/material/Typography";

function preventDefault(event) {
    event.preventDefault();
}

export default function VehicleModelListDashboard() {
    const theme = useTheme();
    const {state, dispatch} = useContext(AppContext);
    const {vehicles} = state;
    const {loading, error, data} = vehicles;

    const [open, setOpen] = React.useState(data.map(() => false));


    useEffect(() => {
        fetchVehicles(dispatch);
    }, []);


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
            <TableContainer sx={{maxHeight: 400}}>  {/* Set maxHeight here */}
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
                                            borderBottom: open[index] ? '2px solid grey' : 'white', // Change background color when row is clicked

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
                                    <TableCell style={{paddingBottom: 0, paddingTop: 0, backgroundColor: 'rgba(0, 0, 0, 0.03)', borderRadius: '0 0 20px 20px'}} colSpan={6}>
                                        <Collapse in={open[index]} timeout="auto" unmountOnExit>
                                            <Box
                                                margin={1}
                                                marginBottom={3}
                                                sx={{
                                                    display: 'grid',
                                                    gridTemplateColumns: 'repeat(3, 1fr)', // This creates two columns of equal width
                                                    gap: '30px', // Adjust the gap between the boxes as needed
                                                    justifyContent: 'center',
                                                }}
                                            >

                                                <Box>
                                                    <Typography variant="h7" gutterBottom component="div" fontSize={"1.2em"}>
                                                        Aero Model
                                                    </Typography>
                                                    <Typography variant="p" gutterBottom component="div" fontSize={"0.9em"}>
                                                        Downforce Coefficient: <span
                                                        style={{fontWeight: 'bold'}}>{vehicle.downforceCoefficient}</span>
                                                    </Typography>
                                                    <Typography variant="p" gutterBottom component="div" fontSize={"0.9em"}>
                                                        Drag Coefficient: <span
                                                        style={{fontWeight: 'bold'}}>{vehicle.dragCoefficient}</span>
                                                    </Typography>
                                                </Box>
                                                <Box>
                                                    <Typography variant="h7" gutterBottom component="div" fontSize={"1.2em"}>
                                                        Brake Model
                                                    </Typography>
                                                    <Typography variant="p" gutterBottom component="div" fontSize={"0.9em"}>
                                                        Pressure to Torque Ratio: <span
                                                        style={{fontWeight: 'bold'}}>{vehicle.pressureToTorqueRatio}</span>
                                                    </Typography>
                                                </Box>
                                                <Box>
                                                    <Typography variant="h7" gutterBottom component="div" fontSize={"1.2em"}>
                                                        Chassis Model
                                                    </Typography>
                                                    <Typography variant="p" gutterBottom component="div" fontSize={"0.9em"}>
                                                        Vehicle Mass: <span
                                                        style={{fontWeight: 'bold'}}>{vehicle.mass}</span>
                                                    </Typography>
                                                </Box>

                                                {/* Divider between rows */}
                                                {/*<Divider sx={{ gridColumn: '1 / -1', borderBottom: '1px solid lightgrey'}} />*/}

                                                <Box>
                                                    <Typography variant="h7" gutterBottom component="div" fontSize={"1.2em"}>
                                                        Powertrain Model
                                                    </Typography>
                                                    <Typography variant="p" gutterBottom component="div" fontSize={"0.9em"}>
                                                        Max. Power: <span
                                                        style={{fontWeight: 'bold'}}>{vehicle.powerMax}</span>
                                                    </Typography>
                                                    <Typography variant="p" gutterBottom component="div" fontSize={"0.9em"}>
                                                        Max. Torque: <span
                                                        style={{fontWeight: 'bold'}}>{vehicle.torqueMax}</span>
                                                    </Typography>
                                                    <Typography variant="p" gutterBottom component="div" fontSize={"0.9em"}>
                                                        RPM at Max. Power: <span
                                                        style={{fontWeight: 'bold'}}>{vehicle.rpmPowerMax}</span>
                                                    </Typography>
                                                    <Typography variant="p" gutterBottom component="div" fontSize={"0.9em"}>
                                                        RPM at Max. Torque: <span
                                                        style={{fontWeight: 'bold'}}>{vehicle.rpmTorqueMax}</span>
                                                    </Typography>
                                                </Box>
                                                <Box>
                                                    <Typography variant="h7" gutterBottom component="div" fontSize={"1.2em"}>
                                                        Transmission Model
                                                    </Typography>
                                                    {vehicle.gears.map((gear, index) => (
                                                        <Typography variant="p" gutterBottom component="div" fontSize={"0.9em"}
                                                                    key={index}>
                                                            Gear {index + 1}: <span
                                                            style={{fontWeight: 'bold'}}>{gear}</span>
                                                        </Typography>
                                                    ))}
                                                    <Typography variant="p" gutterBottom component="div" fontSize={"0.9em"}>
                                                        Final Drive Ratio: <span
                                                        style={{fontWeight: 'bold'}}>{vehicle.finalDriveRatio}</span>
                                                    </Typography>
                                                </Box>
                                                <Box>
                                                    <Typography variant="h7" gutterBottom component="div" fontSize={"1.2em"}>
                                                        Tyre Model
                                                    </Typography>
                                                    <Typography variant="p" gutterBottom component="div" fontSize={"0.9em"}>
                                                        Longitudinal Grip: <span
                                                        style={{fontWeight: 'bold'}}>{vehicle.longitudinalGrip}</span>
                                                    </Typography>
                                                    <Typography variant="p" gutterBottom component="div" fontSize={"0.9em"}>
                                                        Lateral Grip: <span
                                                        style={{fontWeight: 'bold'}}>{vehicle.lateralGrip}</span>
                                                    </Typography>
                                                    <Typography variant="p" gutterBottom component="div" fontSize={"0.9em"}>
                                                        Tyre Radius: <span
                                                        style={{fontWeight: 'bold'}}>{vehicle.tyreRadius}</span>
                                                    </Typography>
                                                </Box>
                                            </Box>
                                        </Collapse>
                                    </TableCell>
                                </TableRow>
                            </React.Fragment>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
            <Box sx={{mt: 2}}>
                <Link to={"/vehicleModels"} style={{
                    color: theme.palette.primary.main,
                    textDecoration: 'underline',
                    textDecorationColor: `rgba(0, 0, 0, 0.4)`
                }}
                      onMouseEnter={(e) => e.target.style.textDecoration = 'underline'}
                      onMouseLeave={(e) => {
                          e.target.style.textDecoration = 'underline';
                          e.target.style.textDecorationColor = `rgba(0, 0, 0, 0.4)`
                      }}>
                    See more
                </Link>
            </Box>
        </React.Fragment>
    )
        ;
}