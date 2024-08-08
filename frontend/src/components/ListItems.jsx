import * as React from 'react';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import track_icon from "../assets/track_icon.png";
import car_icon from "../assets/car_icon.svg";
import dashboard_icon from "../assets/dashboard_icon.png";
import createSimulation_icon from "../assets/createSimulation_icon.png";
import simResults_icon from "../assets/simResults_icon.png";
import settings_icon from "../assets/settings_icon.png";
import logOut_icon from "../assets/logOut_icon.png";
import {Link} from "react-router-dom";

export const mainListItems = (
        <React.Fragment>
            <Link to={"/dashboard"} style={{textDecoration: 'none'}}>
                <ListItemButton sx={{mb: 1}}>

                    <ListItemIcon>
                        <img src={dashboard_icon} alt="dashboard" width={"30"} height={"30"}/>
                    </ListItemIcon>

                    <ListItemText primary="Dashboard" sx={{'& .MuiTypography-root': {color: 'black', fontWeight: 'bold'}}}/>
                </ListItemButton>
            </Link>

            <Link to={"/tracks"} style={{textDecoration: 'none'}}>
                <ListItemButton sx={{mb: 1}}>

                    <ListItemIcon>
                        <img src={track_icon} alt="tracks" width={"30"} height={"30"}/>
                    </ListItemIcon>

                    <ListItemText primary="Tracks" sx={{'& .MuiTypography-root': {color: 'black', fontWeight: 'bold'}}}/>
                </ListItemButton>
            </Link>

            <Link to={"/vehicleModels"} style={{color: 'black', textDecoration: 'none'}}>
                <ListItemButton sx={{mb: 1}}>
                    <ListItemIcon>
                        <img src={car_icon} alt="cars" width={"30"} height={"30"}/>
                    </ListItemIcon>
                    <ListItemText primary="Vehicle Models"
                                  sx={{'& .MuiTypography-root': {color: 'black', fontWeight: 'bold'}}}/>
                </ListItemButton>
            </Link>

            <Link to={"/createSimulation"} style={{color: 'black', textDecoration: 'none'}}>
                <ListItemButton sx={{mb: 1}}>
                    <ListItemIcon>
                        <img src={createSimulation_icon} alt="cars" width={"30"} height={"30"}/>
                    </ListItemIcon>
                    <ListItemText primary="Create Simulation"
                                  sx={{'& .MuiTypography-root': {color: 'black', fontWeight: 'bold'}}}/>
                </ListItemButton>
            </Link>

            <Link to={"/simulations"} style={{color: 'black', textDecoration: 'none'}}>
                <ListItemButton sx={{mb: 1}}>
                    <ListItemIcon>
                        <img src={simResults_icon} alt="results" width={"30"} height={"30"}/>
                    </ListItemIcon>
                    <ListItemText primary="Results" sx={{'& .MuiTypography-root': {color: 'black', fontWeight: 'bold'}}}/>
                </ListItemButton>
            </Link>
        </React.Fragment>
    )
;

export const secondaryListItems = (
    <React.Fragment>
        <ListItemButton sx={{mb: 1}}>
            <ListItemIcon>
                <img src={settings_icon} alt="settings" width={"30"} height={"30"}/>
            </ListItemIcon>
            <ListItemText primary="Settings" sx={{'& .MuiTypography-root': {color: 'black', fontWeight: 'bold'}}}/>
        </ListItemButton>

        <ListItemButton sx={{mb: 1}}>
            <ListItemIcon>
                <img src={logOut_icon} alt="logOut" width={"30"} height={"30"}/>
            </ListItemIcon>
            <ListItemText primary="Log out" sx={{'& .MuiTypography-root': {color: 'black', fontWeight: 'bold'}}}/>
        </ListItemButton>
    </React.Fragment>
);