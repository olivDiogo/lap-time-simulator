import * as React from 'react';
import {Link} from "react-router-dom";
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Title from '../Title.jsx';
import {useContext, useEffect} from "react";
import AppContext from "../../context/AppContext.jsx";
import {fetchSimulations, postStartSimulation} from "../../context/Actions.jsx";
import Box from "@mui/material/Box";
import {CircularProgress, useTheme} from "@mui/material";
import startSimulationButton from "../../assets/play_button.png"
import TableContainer from "@mui/material/TableContainer";

function preventDefault(event) {
    event.preventDefault();
}

export default function SimulationsListDashboard() {
    const theme = useTheme();
    const {state, dispatch} = useContext(AppContext);
    const {simulations} = state;
    const {loading, error, data} = simulations;

    useEffect(() => {
        fetchSimulations(dispatch);
    }, []);

    const startSimulation = (simulationId) => {
        postStartSimulation(dispatch, simulationId);
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

    return (
        <React.Fragment>
            <Title>Simulations</Title>
            <TableContainer sx={{maxHeight: 400}}>
                <Table size="small" stickyHeader>
                    <TableHead>
                        <TableRow>
                            <TableCell sx={{fontWeight: 'bold'}}>Description</TableCell>
                            <TableCell sx={{fontWeight: 'bold'}}>Vehicle</TableCell>
                            <TableCell sx={{fontWeight: 'bold'}}>Track</TableCell>
                            <TableCell sx={{fontWeight: 'bold', display: "flex", alignItems: "center", justifyContent: "center"}}>Start Simulation</TableCell>
                            <TableCell align="right" sx={{fontWeight: 'bold'}}>Results</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {data.map((sim) => (
                            <TableRow key={sim.simulationID}>
                                <TableCell>{sim.simulationName}</TableCell>
                                <TableCell>{sim.vehicleName}</TableCell>
                                <TableCell>{sim.trackName}</TableCell>
                                <TableCell sx={{display: "flex", alignItems: "center", justifyContent: "center"}}>
                                    <Box color={"primary"} href={"#"} onClick={() => startSimulation(sim.simulationID)} sx={{cursor: "pointer"}}>
                                        <img src={startSimulationButton} alt={"play-button"} width={"20"} height={"20"}/>
                                    </Box>
                                </TableCell>
                                <TableCell align="right">
                                    <Box color={"primary"} href={"#"} onClick={preventDefault}>
                                        View Results
                                    </Box>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>

            <Box sx={{mt: 2}}>
                <Link
                    to={"/simulations"}
                    style={{
                        color: theme.palette.primary.main,
                        textDecoration: 'underline',
                        textDecorationColor: `rgba(0, 0, 0, 0.4)`
                    }}
                    onMouseEnter={(e) => e.target.style.textDecoration = 'underline'}
                    onMouseLeave={(e) => { e.target.style.textDecoration = 'underline';
                        e.target.style.textDecorationColor = `rgba(0, 0, 0, 0.4)`}}>
                    See more
                </Link>
            </Box>
        </React.Fragment>
    );
}