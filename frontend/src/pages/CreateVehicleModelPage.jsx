import * as React from 'react';
import {styled, createTheme, ThemeProvider} from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import MuiDrawer from '@mui/material/Drawer';
import Box from '@mui/material/Box';
import MuiAppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import Badge from '@mui/material/Badge';
import Container from '@mui/material/Container';
import Paper from '@mui/material/Paper';
import Link from '@mui/material/Link';
import MenuIcon from '@mui/icons-material/Menu';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import NotificationsIcon from '@mui/icons-material/Notifications';
import {mainListItems, secondaryListItems} from '../components/ListItems.jsx';
import backgroundImage from '../assets/chequered_flag.jpg';
import {useContext, useEffect, useState} from "react";
import AppContext from "../context/AppContext.jsx";
import {hideAlert, postCreatedVehicleModel, resetSelectedVehicle} from "../context/Actions.jsx";
import {Alert, Stack} from "@mui/material";
import Button from "@mui/material/Button";
import AeroModelCreate from "../components/createVehicle/AeroModelCreate.jsx";
import BrakeModelCreate from "../components/createVehicle/BrakeModelCreate.jsx";
import ChassisModelCreate from "../components/createVehicle/ChassisModelCreate.jsx";
import PowertrainModelCreate from "../components/createVehicle/PowertrainModelCreate.jsx";
import TransmissionModelCreate from "../components/createVehicle/TransmissionModelCreate.jsx";
import TyreModelCreate from "../components/createVehicle/TyreModelCreate.jsx";
import {useNavigate} from "react-router";
import VehicleNameCreate from "../components/createVehicle/VehicleNameCreate.jsx";


function Copyright(props) {
    return (
        <Typography variant="body2" color="text.secondary" align="center" {...props}>
            {'Copyright © '}
            <Link color="inherit" href="https://mui.com/">
                Your Website
            </Link>{' '}
            {new Date().getFullYear()}
            {'.'}
        </Typography>
    );
}

const drawerWidth = 240;

const AppBar = styled(MuiAppBar, {
    shouldForwardProp: (prop) => prop !== 'open',
})(({theme, open}) => ({
    zIndex: theme.zIndex.drawer + 1,
    transition: theme.transitions.create(['width', 'margin'], {
        easing: theme.transitions.easing.sharp,
        duration: theme.transitions.duration.leavingScreen,
    }),
    ...(open && {
        marginLeft: drawerWidth,
        width: `calc(100% - ${drawerWidth}px)`,
        transition: theme.transitions.create(['width', 'margin'], {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.enteringScreen,
        }),
    }),
}));

const Drawer = styled(MuiDrawer, {shouldForwardProp: (prop) => prop !== 'open'})(
    ({theme, open}) => ({
        '& .MuiDrawer-paper': {
            position: 'relative',
            whiteSpace: 'nowrap',
            width: drawerWidth,
            transition: theme.transitions.create('width', {
                easing: theme.transitions.easing.sharp,
                duration: theme.transitions.duration.enteringScreen,
            }),
            boxSizing: 'border-box',
            ...(!open && {
                overflowX: 'hidden',
                transition: theme.transitions.create('width', {
                    easing: theme.transitions.easing.sharp,
                    duration: theme.transitions.duration.leavingScreen,
                }),
                width: theme.spacing(7),
                [theme.breakpoints.up('sm')]: {
                    width: theme.spacing(9),
                },
            }),
            '&::after': {
                content: '""',
                position: 'absolute',
                bottom: 0,
                left: 0,
                width: '100%',
                height: '100%', // adjust this value to change the height of the image
                backgroundImage: `url(${backgroundImage})`, // source: https://stock.adobe.com/search?k=checkered+flag+background&asset_id=122185112
                backgroundSize: 'cover',
                backgroundRepeat: 'no-repeat',
                zIndex: -1,
                opacity: 0.3,
            },
        },
    }),
);

// TODO remove, this demo shouldn't need to reset the theme.
const defaultTheme = createTheme();

export default function CreateVehicleModelPage() {
    const [open, setOpen] = useState(false);
    const toggleDrawer = () => {
        setOpen(!open);
    };

    const {state, dispatch} = useContext(AppContext);
    const {selectedVehicle} = state;
    const {vehicle} = selectedVehicle;

    const navigate = useNavigate(); // Initialize useNavigate

    const {alert} = state;
    useEffect(() => {
        if (alert.showAlert) {
            const timer = setTimeout(() => {
                hideAlert(dispatch);
            }, 5000);

            return () => clearTimeout(timer);
        }
    }, [alert, dispatch]);

    useEffect(() => {
        resetSelectedVehicle(dispatch)
    }, []);


    const handleCreateVehicle = () => {
        postCreatedVehicleModel(dispatch, vehicle)
    }

    return (
        <ThemeProvider theme={defaultTheme}>
            <Box sx={{display: 'flex'}}>
                <CssBaseline/>
                <AppBar position="absolute" open={open}
                        sx={{backgroundColor: 'black'}}>
                    <Toolbar
                        sx={{
                            pr: '24px', // keep right padding when drawer closed
                        }}
                    >
                        <IconButton
                            edge="start"
                            color="inherit"
                            aria-label="open drawer"
                            onClick={toggleDrawer}
                            sx={{
                                marginRight: '36px',
                                ...(open && {display: 'none'}),
                            }}
                        >
                            <MenuIcon/>
                        </IconButton>
                        <Typography
                            component="h1"
                            variant="h6"
                            color="inherit"
                            noWrap
                            sx={{flexGrow: 1}}
                        >
                            Create Vehicle Model
                        </Typography>
                        <IconButton color="inherit">
                            <Badge badgeContent={4} color="secondary">
                                <NotificationsIcon/>
                            </Badge>
                        </IconButton>
                    </Toolbar>
                </AppBar>
                <Drawer variant="permanent" open={open}>
                    <Toolbar
                        sx={{
                            display: 'flex',
                            alignItems: 'center',
                            justifyContent: 'flex-end',
                            px: [1],
                        }}
                    >
                        <IconButton onClick={toggleDrawer}>
                            <ChevronLeftIcon/>
                        </IconButton>
                    </Toolbar>
                    <Divider/>
                    <List component="nav">
                        {mainListItems}
                        <Divider sx={{my: 1}}/>
                        {secondaryListItems}
                    </List>
                </Drawer>
                <Box
                    component="main"
                    sx={{
                        backgroundColor: (theme) =>
                            theme.palette.mode === 'light'
                                ? theme.palette.grey[100]
                                : theme.palette.grey[900],

                        // backgroundImage: `url(${backgroundImage})`,
                        // backgroundRepeat: 'no-repeat',
                        // backgroundSize: 'cover',
                        flexGrow: 1,
                        height: '100vh',
                        overflow: 'auto',

                        // position: 'relative'
                    }}
                >
                    <Toolbar/>
                    <Container maxWidth="lg" sx={{mt: 4, mb: 4}}>
                        <Paper sx={{
                            p: 2,
                            padding: 5,

                        }}>
                            <Box component={"form"} sx={{
                                display: 'grid',
                                gridTemplateColumns: 'repeat(3, 1fr)', // This creates three columns of equal width
                                gap: '20px', // Adjust the gap between the boxes as needed
                                justifyContent: 'center',
                            }}>

                                <VehicleNameCreate/>
                                <AeroModelCreate/>
                                <BrakeModelCreate/>
                                <ChassisModelCreate/>
                                <PowertrainModelCreate/>
                                <TransmissionMendodelCreate/>
                                <TyreModelCreate/>

                                {alert.showAlert && (
                                    <Stack sx={{width: '100%'}} spacing={2}>
                                        <Alert severity={alert.alertType}>
                                            {alert.alertMessage}
                                        </Alert>
                                    </Stack>
                                )}

                            </Box>

                            <Box display={"flex"} alignItems={"center"} justifyContent={"center"} marginTop={"20px"}>
                                <Button
                                    variant="contained"
                                    color="primary"
                                    onClick={handleCreateVehicle}
                                    sx={{
                                        gridColumn: '1 / -1',
                                        justifySelf: 'center',
                                        color: 'black',
                                        backgroundColor: 'lightgrey',
                                        '&:hover': {
                                            backgroundColor: 'darkgrey', color: 'black'// Change this to the color you want on hover
                                        },
                                    }}
                                >
                                    Create
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
                        </Paper>
                        <Copyright sx={{pt: 4}}/>
                    </Container>
                </Box>
            </Box>
        </ThemeProvider>
    );
}