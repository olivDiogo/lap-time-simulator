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
import {fetchVehicles} from "../context/Actions.jsx";
import Box from "@mui/material/Box";
import {CircularProgress, Collapse, useTheme} from "@mui/material";

import Typography from "@mui/material/Typography";
import Button from "@mui/material/Button";
import {Link} from "react-router-dom";

function preventDefault(event) {
    event.preventDefault();
}

export default function VehicleModelsList() {
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
                        {data.map((vehicle, index) => (
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
                                            cursor: 'pointer' // Change cursor to pointer on hover
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
                                    <TableCell style={{paddingBottom: 0, paddingTop: 0}} colSpan={6}>
                                        <Collapse in={open[index]} timeout="auto" unmountOnExit>
                                            <Box margin={1} sx={{display: 'flex', alignItems: 'center', gap: '100px'}}>
                                                <Typography variant="h6" gutterBottom component="div">
                                                    CENAS
                                                </Typography>
                                            </Box>
                                            <Box sx={{
                                                mb: 2,
                                                display: 'flex',
                                                alignItems: 'center',
                                                justifyContent: 'center'
                                            }}>
                                                <Button variant="contained" sx={{
                                                    backgroundColor: 'lightgrey', '&:hover': {
                                                        backgroundColor: 'darkgrey', // Change this to the color you want on hover
                                                    }
                                                }}>
                                                    <Link to={"/createSimulation"}
                                                          style={{
                                                              color: 'black',
                                                              textDecoration: 'none'
                                                          }}
                                                          onMouseEnter={(e) => {
                                                              e.target.style.textDecoration = 'none',
                                                                  e.target.style.color = `black`
                                                          }}
                                                          onMouseLeave={(e) => {
                                                              e.target.style.textDecoration = 'none';
                                                              e.target.style.color = `black`
                                                          }}
                                                    >
                                                        Create simulation
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
        </React.Fragment>
    )
}
