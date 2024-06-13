import * as React from 'react';
import Title from './Title';
import TableContainer from "@mui/material/TableContainer";
import Table from "@mui/material/Table";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import TableCell from "@mui/material/TableCell";
import TableBody from "@mui/material/TableBody";
import {Link} from "react-router-dom";
import {useContext, useEffect} from "react";
import AppContext from "../context/AppContext.jsx";
import {fetchTracks} from "../context/Actions.jsx";
import {CircularProgress, Collapse, useTheme} from "@mui/material";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import {chooseCorrectTrackImage} from "../services/ChooseCorrectTrackImage.jsx";


function preventDefault(event) {
    event.preventDefault();
}

export default function TrackListDashboard() {
    const theme = useTheme();
    const {state, dispatch} = useContext(AppContext);
    const {tracks} = state;
    const { loading, error, data } = tracks;

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
                <CircularProgress size={60} style={{ color: 'black' }} />
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
            <TableContainer sx={{height: 400}}>  {/* Set maxHeight here */}
                <Table size="small" stickyHeader>
                    <TableHead>
                        <TableRow >
                            <TableCell sx={{fontWeight:'bold'}}>Name</TableCell>
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
                                            cursor: 'pointer',
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
                                        {track.trackName}
                                    </TableCell>
                                </TableRow>
                                <TableRow>
                                    <TableCell style={{paddingBottom: 0, paddingTop: 0, backgroundColor: 'rgba(0, 0, 0, 0.03)', borderRadius: '0 0 20px 20px'}} colSpan={6}>
                                        <Collapse in={open[index]} timeout="auto" unmountOnExit>
                                            <Box margin={1} sx={{display: 'flex', alignItems: 'center', gap: '30px', fontSize: '14px'}}>
                                                <img src={chooseCorrectTrackImage(track.trackIconPath)} alt={"track"} width={150} height={150}/>
                                                <Box>
                                                    <Typography variant="p" gutterBottom component="div">
                                                        Location:  <span style={{fontWeight: "bold"}}>{track.trackLocation}</span>
                                                    </Typography>
                                                    <Typography variant="p" gutterBottom component="div">
                                                        Layout:  <span style={{fontWeight: "bold"}}>{track.trackLayout}</span>
                                                    </Typography>
                                                    <Typography variant="p" gutterBottom component="div">
                                                        Length:  <span style={{fontWeight: "bold"}}>{track.trackLength} m</span>
                                                    </Typography>
                                                    <Typography variant="p" gutterBottom component="div">
                                                        Corners:  <span style={{fontWeight: "bold"}}>{track.numberOfCorners}</span>
                                                    </Typography>
                                                    <Typography variant="p" gutterBottom component="div">
                                                        Race Lap Record:  <span style={{fontWeight: "bold"}}>{track.trackRaceLapRecord}</span>
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
            <Box sx={{ mt: 2}}>
                <Link
                    to={"/tracks"}
                    style={{
                        color: theme.palette.primary.main,
                        textDecoration: 'underline',
                        textDecorationColor: `rgba(0, 0, 0, 0.4)`
                    }}
                    onMouseEnter={(e) => e.target.style.textDecoration = 'underline'}
                    onMouseLeave={(e) => { e.target.style.textDecoration = 'underline';
                        e.target.style.textDecorationColor = `rgba(0, 0, 0, 0.4)`}}
                >
                    See more
                </Link>
            </Box>
        </React.Fragment>
    );
}