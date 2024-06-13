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
import TextField from "@mui/material/TextField";
import {
    fetchVehicleModelById,
    postUpdatedVehicleModel,
    updateDownforceCoefficient,
    updateDragCoefficient, updateFinalDriveRatio, updateSingleGearRatio, updateLateralGrip, updateLongitudinalGrip,
    updateNumberOfGears,
    updatePowerMax,
    updatePowertrainType,
    updatePressureToTorqueRatio,
    updateRpmPowerMax,
    updateRpmTorqueMax,
    updateTorqueMax, updateTyreRadius,
    updateVehicleMass, updateGearRatios
} from "../context/Actions.jsx";
import {FormControl, InputLabel, MenuItem, Select} from "@mui/material";
import Button from "@mui/material/Button";

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

export default function EditVehicleModelPage() {
    const [open, setOpen] = useState(false);
    const toggleDrawer = () => {
        setOpen(!open);
    };

    const {state, dispatch} = useContext(AppContext);
    const {selectedVehicle} = state;
    const {vehicle} = selectedVehicle;
    let {
        vehicleName, downforceCoefficient, dragCoefficient, pressureToTorqueRatio,
        mass, powerMax, torqueMax, rpmPowerMax, rpmTorqueMax, powertrainType, numberOfGears: initialNumberOfGears,
        gears: initialGears, finalDriveRatio, longitudinalGrip, lateralGrip, tyreRadius
    } = vehicle;
    const [numberOfGears, setNumberOfGears] = useState(initialNumberOfGears);
    const [gears, setGears] = useState(initialGears);


    const handleDownforceCoefficientChange = (event) => {
        const newDownforceCoefficient = parseFloat(event.target.value);
        if (!isNaN(newDownforceCoefficient)) {
            updateDownforceCoefficient(dispatch, newDownforceCoefficient);
        }
    }

    const handleDragCoefficientChange = (event) => {
        const newDragCoefficient = parseFloat(event.target.value);
        if (!isNaN(newDragCoefficient)) {
            updateDragCoefficient(dispatch, newDragCoefficient);
        }
    }

    const handlePressureToTorqueRatioChange = (event) => {
        const newPressureToTorqueRatio = parseFloat(event.target.value);
        if (!isNaN(newPressureToTorqueRatio)) {
            updatePressureToTorqueRatio(dispatch, newPressureToTorqueRatio);
        }
    }

    const handleVehicleMassChange = (event) => {
        const newMass = parseFloat(event.target.value);
        if (!isNaN(newMass)) {
            updateVehicleMass(dispatch, newMass);
        }
    }

    const handlePowerMaxChange = (event) => {
        const newPowerMax = parseFloat(event.target.value);
        if (!isNaN(newPowerMax)) {
            updatePowerMax(dispatch, newPowerMax);
        }
    }

    const handleTorqueMaxChange = (event) => {
        const newTorqueMax = parseFloat(event.target.value);
        if (!isNaN(newTorqueMax)) {
            updateTorqueMax(dispatch, newTorqueMax);
        }
    }

    const handleRpmPowerMaxChange = (event) => {
        const newRpmPowerMax = parseFloat(event.target.value);
        if (!isNaN(newRpmPowerMax)) {
            updateRpmPowerMax(dispatch, newRpmPowerMax);
        }
    }

    const handleRpmTorqueMaxChange = (event) => {
        const newRpmTorqueMax = parseFloat(event.target.value);
        if (!isNaN(newRpmTorqueMax)) {
            updateRpmTorqueMax(dispatch, newRpmTorqueMax);
        }
    }

    const handlePowertrainTypeChange = (event) => {
        const newType = event.target.value;
        updatePowertrainType(dispatch, newType);
    };

    const handleNumberOfGearsChange = (event) => {
        const newNumberOfGears =parseInt(event.target.value, 10);
        if (!isNaN(newNumberOfGears)) {
            setNumberOfGears(newNumberOfGears);
            updateNumberOfGears(dispatch, newNumberOfGears);

            let updatedGears = [...gears];

            setGears((prevGears) => {
                if(newNumberOfGears < prevGears.length) {
                    updatedGears = prevGears.slice(0, newNumberOfGears);
                    while (updatedGears.length < newNumberOfGears) {
                        updatedGears.push('');
                    }
                } else {
                    updatedGears = [...prevGears];
                    for (let i = prevGears.length; i < newNumberOfGears; i++) {
                        updatedGears.push('');
                    }
                }

                updateGearRatios(dispatch, updatedGears);
                return updatedGears;
            });
        }
    }

    const handleSingleGearRatioChange = (event, index) => {
        const newGearRatio = parseFloat(event.target.value);
        if (!isNaN(newGearRatio)) {
            const updatedGears = [...gears];
            updatedGears[index] = newGearRatio;
            setGears(updatedGears);
            updateSingleGearRatio(dispatch, newGearRatio, index);
        }
    }

    const handleFinalDriveRatioChange = (event) => {
        const newFinalDriveRatio = parseFloat(event.target.value);
        if (!isNaN(newFinalDriveRatio)) {
            updateFinalDriveRatio(dispatch, newFinalDriveRatio);
        }
    }

    const handleLongitudinalGripChange = (event) => {
        const newLongitudinalGrip = parseFloat(event.target.value);
        if (!isNaN(newLongitudinalGrip)) {
            updateLongitudinalGrip(dispatch, newLongitudinalGrip);
        }
    }

    const handleLateralGripChange = (event) => {
        const newLateralGrip = parseFloat(event.target.value);
        if (!isNaN(newLateralGrip)) {
            updateLateralGrip(dispatch, newLateralGrip);
        }
    }

    const handleTyreRadiusChange = (event) => {
        const newTyreRadius = parseFloat(event.target.value);
        if (!isNaN(newTyreRadius)) {
            updateTyreRadius(dispatch, newTyreRadius);
        }
    }

    const handleUpdateVehicle = () => {
        postUpdatedVehicleModel(dispatch, vehicle);
        fetchVehicleModelById(dispatch, vehicle.vehicleID)
    };

    useEffect(() => {
        if (powertrainType === 'ELECTRIC') {
            rpmPowerMax = ('');
            rpmTorqueMax = ('');
        }
    }, [powertrainType]);

    useEffect(() => {
        setGears((prevGears) => {
            const newGears = [...prevGears];
            for (let i = 0; i < 8; i++) {
                if (i < numberOfGears) {
                    if (newGears[i] === null || newGears[i] === undefined) {
                        newGears[i] = '';
                    }
                } else {
                    newGears[i] = null;
                }
            }
            return newGears;
        });
    }, [numberOfGears]);


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
                            {vehicleName}
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
                        <Box component={"form"}>
                            <Paper sx={{
                                p: 2,
                                padding: 5,
                                display: 'grid',
                                gridTemplateColumns: 'repeat(3, 1fr)', // This creates three columns of equal width
                                gap: '20px', // Adjust the gap between the boxes as needed
                                justifyContent: 'center',
                            }}>
                                <Box id={"aero-model"} sx={{
                                    display: 'flex', flexDirection: 'column', gap: '20px',
                                    borderRadius: '10px', border: '2px solid lightgrey', padding: '20px',
                                    boxShadow: '0 4px 8px rgba(0, 0, 0, 0.5)'
                                }}>
                                    <Typography variant="h6" gutterBottom component="div">
                                        Aero Model
                                    </Typography>
                                    <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                                        <Typography variant="body1" gutterBottom component="div" sx={{width: '100%'}}>
                                            Downforce Coefficient
                                        </Typography>
                                        <TextField sx={{width: '70%'}}
                                                   required
                                                   id="downforceCoefficient"
                                                   label="Required"
                                                   value={downforceCoefficient}
                                                   onChange={handleDownforceCoefficientChange}
                                        />
                                    </Box>
                                    <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                                        <Typography variant="body1" gutterBottom component="div" sx={{width: '100%'}}>
                                            Drag Coefficient
                                        </Typography>
                                        <TextField
                                            sx={{width: '70%'}}
                                            required
                                            id="dragCoefficient"
                                            label="Required"
                                            value={dragCoefficient}
                                            onChange={handleDragCoefficientChange}
                                        />
                                    </Box>
                                </Box>

                                <Box id={"brake-model"} sx={{
                                    display: 'flex', flexDirection: 'column', gap: '20px',
                                    borderRadius: '10px', border: '2px solid lightgrey', padding: '20px',
                                    boxShadow: '0 4px 8px rgba(0, 0, 0, 0.5)'
                                }}>
                                    <Typography variant="h6" gutterBottom component="div">
                                        Brake Model
                                    </Typography>
                                    <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                                        <Typography variant="body1" gutterBottom component="div" sx={{width: '100%'}}>
                                            Pressure to Torque Ratio
                                        </Typography>
                                        <TextField sx={{width: '70%'}}
                                                   required
                                                   id="pressureToTorqueRatio"
                                                   label="Required"
                                                   defaultValue={pressureToTorqueRatio}
                                                   onChange={handlePressureToTorqueRatioChange}
                                        />
                                    </Box>
                                </Box>

                                <Box id={"chassis-model"} sx={{
                                    display: 'flex', flexDirection: 'column', gap: '20px',
                                    borderRadius: '10px', border: '2px solid lightgrey', padding: '20px',
                                    boxShadow: '0 4px 8px rgba(0, 0, 0, 0.5)'
                                }}>
                                    <Typography variant="h6" gutterBottom component="div">
                                        Chassis Model
                                    </Typography>
                                    <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                                        <Typography variant="body1" gutterBottom component="div" sx={{width: '100%'}}>
                                            Vehicle Mass
                                        </Typography>
                                        <TextField sx={{width: '100%'}}
                                                   required
                                                   id="vehicleMass"
                                                   label="Required"
                                                   defaultValue={mass}
                                                   onChange={handleVehicleMassChange}
                                        />
                                        <Typography variant="body1" gutterBottom component="div">
                                            kg
                                        </Typography>
                                    </Box>
                                </Box>

                                <Box id={"powertrain-model"} sx={{
                                    display: 'flex', flexDirection: 'column', gap: '20px',
                                    borderRadius: '10px', border: '2px solid lightgrey', padding: '20px',
                                    boxShadow: '0 4px 8px rgba(0, 0, 0, 0.5)'
                                }}>
                                    <Typography variant="h6" gutterBottom component="div">
                                        Powertrain Model
                                    </Typography>
                                    <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                                        <Typography variant="body1" gutterBottom component="div" sx={{width: '80%'}}>
                                            Max. Power
                                        </Typography>
                                        <TextField sx={{width: '100%'}}
                                                   required
                                                   id="powerMax"
                                                   label="Required"
                                                   defaultValue={powerMax}
                                                   onChange={handlePowerMaxChange}
                                        />
                                        <Typography variant="body1" gutterBottom component="div" padding={"4px"}>
                                            hp
                                        </Typography>
                                    </Box>
                                    <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                                        <Typography variant="body1" gutterBottom component="div" sx={{width: '80%'}}>
                                            Max. Torque
                                        </Typography>
                                        <TextField sx={{width: '100%'}}
                                                   required
                                                   id="torqueMax"
                                                   label="Required"
                                                   defaultValue={torqueMax}
                                                   onChange={handleTorqueMaxChange}
                                        />
                                        <Typography variant="body1" gutterBottom component="div" padding={"1px"}>
                                            Nm
                                        </Typography>
                                    </Box>
                                    <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                                        <Typography variant="body1" gutterBottom component="div" sx={{width: '80%'}}>
                                            RPM at Max. Power
                                        </Typography>
                                        <TextField
                                            sx={{width: '100%', ...(powertrainType === 'ELECTRIC' ? {backgroundColor: '#f0f0f0'} : {})}}
                                            id="rpmPowerMax"
                                            label={powertrainType === 'ELECTRIC' ? "" : "Required"}
                                            required={powertrainType !== 'ELECTRIC'}
                                            value={powertrainType !== 'ELECTRIC' ? rpmPowerMax : ""}
                                            disabled={powertrainType === 'ELECTRIC'}
                                            onChange={handleRpmPowerMaxChange}
                                        />
                                        <Typography variant="body1" gutterBottom component="div">
                                            rpm
                                        </Typography>
                                    </Box>
                                    <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                                        <Typography variant="body1" gutterBottom component="div" sx={{width: '80%'}}>
                                            RPM at Max. Torque
                                        </Typography>
                                        <TextField
                                            sx={{width: '100%', ...(powertrainType === 'ELECTRIC' ? {backgroundColor: '#f0f0f0'} : {})}}
                                            id="rmpTorqueMax"
                                            label={powertrainType === 'ELECTRIC' ? "" : "Required"}
                                            required={powertrainType !== 'ELECTRIC'}
                                            value={powertrainType !== 'ELECTRIC' ? rpmTorqueMax : ""}
                                            disabled={powertrainType === 'ELECTRIC'}
                                            onChange={handleRpmTorqueMaxChange}
                                        />
                                        <Typography variant="body1" gutterBottom component="div">
                                            rpm
                                        </Typography>
                                    </Box>
                                    <Box sx={{width: '100%'}}>
                                        <FormControl fullWidth>
                                            <InputLabel id="powertrain-type">Type</InputLabel>
                                            <Select
                                                labelId="powertrain-type"
                                                id="powertrain-type"
                                                value={powertrainType}
                                                label="Type"
                                                onChange={handlePowertrainTypeChange}
                                            >
                                                <MenuItem value={"COMBUSTION"}>Combustion</MenuItem>
                                                <MenuItem value={"ELECTRIC"}>Electric</MenuItem>
                                            </Select>
                                        </FormControl>
                                    </Box>
                                </Box>


                                <Box id={"Transmission-model"} sx={{
                                    display: 'flex', flexDirection: 'column', gap: '20px',
                                    borderRadius: '10px', border: '2px solid lightgrey', padding: '20px',
                                    boxShadow: '0 4px 8px rgba(0, 0, 0, 0.5)',
                                    width: '100%',
                                    gridColumn: 'span 2' // This spans the box across 2 columns
                                }}>
                                    <Typography variant="h6" gutterBottom component="div">
                                        Transmission Model
                                    </Typography>
                                    <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                                        <Typography variant="body1" gutterBottom component="div" sx={{width: '60%'}}>
                                            No. of gears
                                        </Typography>
                                        <TextField sx={{width: '300%'}}
                                                   required
                                                   id="numberOfGears"
                                                   label="Required"
                                                   value={numberOfGears}
                                                   onChange={handleNumberOfGearsChange}
                                                   inputProps={{step: "1"}}
                                        />
                                    </Box>
                                    <Box display={"grid"} gridTemplateColumns={'repeat(3, 1fr)'} gap={"10px"}
                                         width={"100%"}>
                                        {[...Array(8)].map((_, index) => {
                                            const isRequired = index < numberOfGears;
                                            const justifySelf = index % 3 === 0 ? 'start' : index % 3 === 1 ? 'center' : 'end';

                                            return (
                                                <Box key={index} display={"flex"} alignItems={"center"}
                                                     sx={{justifySelf}}>
                                                    <Typography variant="body1" gutterBottom component="div"
                                                                sx={{width: '40%'}}>
                                                        Gear {index + 1}
                                                    </Typography>
                                                    <TextField
                                                        sx={{width: '60%', ...(isRequired ? {} : {backgroundColor: '#f0f0f0'})}}
                                                        required={isRequired}
                                                        disabled={!isRequired}
                                                        id={`gearRatio${index + 1}`}
                                                        label={isRequired ? "Required" : ""}
                                                        value={gears[index] || ''}
                                                        InputProps={{
                                                            readOnly: !isRequired,
                                                        }}
                                                        onChange={(event) => handleSingleGearRatioChange(event, index)}
                                                        inputProps={{step: "0.01"}}
                                                    />
                                                </Box>
                                            );
                                        })}
                                    </Box>

                                    <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                                        <Typography variant="body1" gutterBottom component="div" sx={{width: '80%'}}>
                                            Final Drive Ratio
                                        </Typography>
                                        <TextField sx={{width: '300%'}}
                                                   required
                                                   id="finalDriveRatio"
                                                   label="Required"
                                                   defaultValue={finalDriveRatio}
                                                   onChange={handleFinalDriveRatioChange}
                                        />
                                    </Box>
                                </Box>

                                <Box id={"tyre-model"} sx={{
                                    display: 'flex', flexDirection: 'column', gap: '20px',
                                    borderRadius: '10px', border: '2px solid lightgrey', padding: '20px',
                                    boxShadow: '0 4px 8px rgba(0, 0, 0, 0.5)',
                                    gridColumn: 'span 3' // This spans the box across 3 columns
                                }}>
                                    <Typography variant="h6" gutterBottom component="div">
                                        Tyre Model
                                    </Typography>
                                    <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                                        <Typography variant="body1" gutterBottom component="div" sx={{width: '100%'}}>
                                            Longitudinal Grip
                                        </Typography>
                                        <TextField sx={{width: '500%'}}
                                                   required
                                                   id="longitudinalGrip"
                                                   label="Required"
                                                   defaultValue={longitudinalGrip}
                                                   onChange={handleLongitudinalGripChange}
                                        />
                                    </Box>
                                    <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                                        <Typography variant="body1" gutterBottom component="div" sx={{width: '100%'}}>
                                            Lateral Grip
                                        </Typography>
                                        <TextField sx={{width: '500%'}}
                                                   required
                                                   id="lateralGrip"
                                                   label="Required"
                                                   defaultValue={lateralGrip}
                                                    onChange={handleLateralGripChange}
                                        />
                                    </Box>
                                    <Box display={"flex"} alignItems={"center"} gap={"10px"}>
                                        <Typography variant="body1" gutterBottom component="div" sx={{width: '100%'}}>
                                            Tyre Radius
                                        </Typography>
                                        <TextField sx={{width: '487%'}}
                                                   required
                                                   id="Tyre Radius"
                                                   label="Required"
                                                   defaultValue={tyreRadius}
                                                    onChange={handleTyreRadiusChange}
                                        />
                                        <Typography variant="body1" gutterBottom component="div">
                                            m
                                        </Typography>
                                    </Box>
                                </Box>

                                <Button
                                    variant="contained"
                                    color="primary"
                                    onClick={handleUpdateVehicle}
                                    sx={{gridColumn: '1 / -1', justifySelf: 'center'}}
                                >
                                    Update
                                </Button>

                            </Paper>
                        </Box>
                        <Copyright sx={{pt: 4}}/>
                    </Container>
                </Box>
            </Box>
        </ThemeProvider>
    );
}