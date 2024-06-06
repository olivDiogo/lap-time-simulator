import * as React from 'react';
import Title from './Title';
import TableContainer from "@mui/material/TableContainer";
import Table from "@mui/material/Table";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import TableBody from "@mui/material/TableBody";
import {useContext, useEffect} from "react";
import AppContext from "../context/AppContext.jsx";
import {fetchTracks} from "../context/Actions.jsx";
import {CircularProgress, Collapse} from "@mui/material";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import {chooseCorrectTrackImage} from "../services/ChooseCorrectTrackImage.jsx";
import Button from "@mui/material/Button";
import {Link} from "react-router-dom";

function preventDefault(event) {
    event.preventDefault();
}

export default function TrackList() {
    const {state, dispatch} = useContext(AppContext);
    const {tracks} = state;
    const {loading, error, data} = tracks;

    const [open, setOpen] = React.useState(data.map(() => false));

    useEffect(() => {
        fetchTracks(dispatch);
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
            <Title>Tracks</Title>
            <TableContainer sx={{maxHeight: 600}}>  {/* Set maxHeight here */}
                <Table size="small" stickyHeader>
                    <TableHead>
                        <TableRow>
                            <TableCell sx={{fontWeight: 'bold'}}>Name</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {data.map((track, index) => (
                            <React.Fragment key={track.trackID}>
                                <TableRow onClick={() => setOpen(prevOpen => {
                                    const newOpen = [...prevOpen];
                                    newOpen[index] = !newOpen[index];
                                    return newOpen;
                                })}>
                                    <TableCell
                                        style={{
                                            color: 'inherit', // Inherit table cell text color initially
                                            textDecoration: 'none', // Remove underline
                                            cursor: 'pointer'
                                        }}
                                        onMouseEnter={(event) => {
                                            event.preventDefault(); // Prevent default behavior on hover
                                            event.currentTarget.style.color = '#007bff'; // Change color to primary on hover
                                        }}
                                        onMouseLeave={(event) => {
                                            event.currentTarget.style.color = 'inherit'; // Revert color on mouse leave
                                        }}
                                    >
                                        {track.trackName}
                                    </TableCell>
                                </TableRow>
                                <TableRow>
                                    <TableCell style={{paddingBottom: 0, paddingTop: 0}} colSpan={6}>
                                        <Collapse in={open[index]} timeout="auto" unmountOnExit>
                                            <Box margin={1} sx={{display: 'flex', alignItems: 'center', gap: '100px'}}>
                                                <img src={chooseCorrectTrackImage(track.trackIconPath)} alt={"track"} width={300} height={300}/>
                                                <Box>
                                                    <Typography variant="h6" gutterBottom component="div">
                                                        Location:  <span style={{fontWeight: "bold"}}>{track.trackLocation}</span>
                                                    </Typography>
                                                    <Typography variant="h6" gutterBottom component="div">
                                                        Layout:  <span style={{fontWeight: "bold"}}>{track.trackLayout}</span>
                                                    </Typography>
                                                    <Typography variant="h6" gutterBottom component="div">
                                                        Length:  <span style={{fontWeight: "bold"}}>{track.trackLength} m</span>
                                                    </Typography>
                                                    <Typography variant="h6" gutterBottom component="div">
                                                        Corners:  <span style={{fontWeight: "bold"}}>{track.numberOfCorners}</span>
                                                    </Typography>
                                                    <Typography variant="h6" gutterBottom component="div">
                                                        Race Lap Record:  <span style={{fontWeight: "bold"}}>{track.trackRaceLapRecord}</span>
                                                    </Typography>
                                                </Box>
                                            </Box>
                                            <Box sx={{ mb: 2, display: 'flex', alignItems: 'center', justifyContent: 'center'}}>
                                                <Button variant="contained" sx={{backgroundColor:'lightgrey', '&:hover': {
                                                        backgroundColor: 'darkgrey', // Change this to the color you want on hover
                                                    }}}>
                                                    <Link to={"/createSimulation"}
                                                          style={{
                                                              color: 'black',
                                                              textDecoration: 'none'
                                                          }}
                                                          onMouseEnter={(e) => { e.target.style.textDecoration = 'none',
                                                              e.target.style.color = `black`}}
                                                          onMouseLeave={(e) => { e.target.style.textDecoration = 'none';
                                                              e.target.style.color = `black`}}
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
    );
}