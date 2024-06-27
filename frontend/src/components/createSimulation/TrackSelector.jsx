import * as React from 'react';
import Box from '@mui/material/Box';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import AppContext from "../../context/AppContext.jsx";
import { useContext, useEffect } from "react";
import { fetchTracks, updateSelectedTrack } from "../../context/Actions.jsx";

export default function TrackSelector() {
    const { state, dispatch } = useContext(AppContext);
    const { tracks, selectedTrack } = state;
    const { loading, error, data } = tracks;
    const { trackId, trackName } = selectedTrack;

    const handleChange = (event) => {
        const selectedTrackId = event.target.value;
        const selectedTrack = data.find(track => track.trackID === selectedTrackId);
        updateSelectedTrack(dispatch, selectedTrack.trackID, selectedTrack.trackName);
    };

    useEffect(() => {
        fetchTracks(dispatch);
    }, [dispatch]);

    return (
        <Box sx={{ width: "100%" }}>
            <FormControl fullWidth>
                <InputLabel id="track-selector-label">Track</InputLabel>
                <Select
                    labelId="track-selector-label"
                    id="track-selector"
                    value={trackId}
                    label="Track"
                    onChange={handleChange}
                >
                    {data.map((track) => (
                        <MenuItem key={track.trackID} value={track.trackID}>
                            {track.trackName}
                        </MenuItem>
                    ))}
                </Select>
            </FormControl>
        </Box>
    );
}
