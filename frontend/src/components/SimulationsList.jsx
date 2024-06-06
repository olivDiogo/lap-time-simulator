import * as React from 'react';
import Link from '@mui/material/Link';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Title from './Title';
import {useContext, useEffect} from "react";
import AppContext from "../context/AppContext.jsx";
import {fetchSimulations} from "../context/Actions.jsx";
import Box from "@mui/material/Box";
import {CircularProgress, useTheme} from "@mui/material";

function preventDefault(event) {
    event.preventDefault();
}

export default function SimulationsList() {
    const theme = useTheme();
    const {state, dispatch} = useContext(AppContext);
    const {simulations} = state;
    const {loading, error, data} = simulations;

    useEffect(() => {
        fetchSimulations(dispatch);
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
            <Title>Simulations</Title>
            <Table size="small" stickyHeader>
                <TableHead>
                    <TableRow>
                        <TableCell sx={{fontWeight: 'bold'}}>Description</TableCell>
                        <TableCell sx={{fontWeight: 'bold'}}>Vehicle</TableCell>
                        <TableCell sx={{fontWeight: 'bold'}}>Track</TableCell>
                        <TableCell align="right" sx={{fontWeight: 'bold'}}>Results</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {data.map((sim) => (
                        <TableRow key={sim.simulationID}>
                            <TableCell>{sim.simulationName}</TableCell>
                            <TableCell>{sim.vehicleName}</TableCell>
                            <TableCell>{sim.trackName}</TableCell>
                            <TableCell align="right">
                                <Link color={"primary"} href={"#"} onClick={preventDefault}>
                                    View Results
                                </Link>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
            <Box sx={{mt: 2}}>
                <Link style={{
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
    );
}