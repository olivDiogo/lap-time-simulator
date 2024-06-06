import * as React from 'react';
import Title from './Title';
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
import {CircularProgress, useTheme} from "@mui/material";
import {Link} from "react-router-dom";

function preventDefault(event) {
    event.preventDefault();
}

export default function VehicleModelListDashboard() {
    const theme = useTheme();
    const {state, dispatch} = useContext(AppContext);
    const {vehicles} = state;
    const {loading, error, data} = vehicles;

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
            <TableContainer sx={{height: 400}}>  {/* Set maxHeight here */}
                <Table size="small" stickyHeader>
                    <TableHead>
                        <TableRow>
                            <TableCell sx={{fontWeight: 'bold'}}>Name</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {data.map((vehicle) => (
                            <TableRow key={vehicle.vehicleID}>
                                <TableCell

                                    href="/vehicleModels"
                                    style={{
                                        color: 'inherit', // Inherit table cell text color initially
                                        textDecoration: 'none', // Remove underline
                                        cursor: 'pointer', // Change cursor to pointer on hover
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