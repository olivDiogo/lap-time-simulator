import {
    FETCH_TRACKS_SUCCESS,
    FETCH_TRACKS_STARTED,
    FETCH_TRACKS_FAILURE,
    FETCH_VEHICLES_STARTED,
    FETCH_VEHICLES_FAILURE,
    FETCH_VEHICLES_SUCCESS,
    FETCH_SIMULATIONS_STARTED,
    FETCH_SIMULATIONS_FAILURE,
    FETCH_SIMULATIONS_SUCCESS,
    UPDATE_SELECTED_VEHICLE,
    UPDATE_SELECTED_TRACK,
    UPDATE_NUMBER_OF_GEARS,
    UPDATE_POWERTRAIN_TYPE,
    POST_UPDATED_VEHICLE_MODEL_FAILURE,
    POST_UPDATED_VEHICLE_MODEL_STARTED,
    POST_UPDATED_VEHICLE_MODEL_SUCCESS,
    UPDATE_DOWNFORCE_COEFFICIENT,
    UPDATE_DRAG_COEFFICIENT,
    UPDATE_PRESSURE_TO_TORQUE_RATIO,
    UPDATE_VEHICLE_MASS,
    UPDATE_POWER_MAX,
    UPDATE_TORQUE_MAX,
    UPDATE_RPM_POWER_MAX,
    UPDATE_RPM_TORQUE_MAX,
    UPDATE_GEAR_RATIO,
    UPDATE_FINAL_DRIVE_RATIO,
    UPDATE_LONGITUDINAL_GRIP,
    UPDATE_LATERAL_GRIP,
    UPDATE_TYRE_RADIUS,
    FETCH_VEHICLE_MODEL_BY_ID_STARTED,
    FETCH_VEHICLE_MODEL_BY_ID_SUCCESS,
    FETCH_VEHICLE_MODEL_BY_ID_FAILURE, UPDATE_GEARS,
} from "./Actions.jsx";

function reducer(state, action) {
    switch (action.type) {
        case FETCH_TRACKS_STARTED:
            return {
                ...state,
                tracks: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case FETCH_TRACKS_SUCCESS:
            return {
                ...state,
                tracks: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }

        case FETCH_TRACKS_FAILURE:
            return {
                ...state,
                tracks: {
                    loading: false,
                    error: action.payload.error,
                    data: [],
                }
            }

        case FETCH_VEHICLES_STARTED:
            return {
                ...state,
                vehicles: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case FETCH_VEHICLES_SUCCESS:
            return {
                ...state,
                vehicles: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }

        case FETCH_VEHICLES_FAILURE:
            return {
                ...state,
                vehicles: {
                    loading: false,
                    error: action.payload.error,
                    data: [],
                }
            }

        case FETCH_SIMULATIONS_STARTED:
            return {
                ...state,
                simulations: {
                    loading: true,
                    error: null,
                    data: []
                }
            }

        case FETCH_SIMULATIONS_SUCCESS:
            return {
                ...state,
                simulations: {
                    loading: false,
                    error: null,
                    data: [...action.payload.data]
                }
            }

        case FETCH_SIMULATIONS_FAILURE:
            return {
                ...state,
                simulations: {
                    loading: false,
                    error: action.payload.error,
                    data: [],
                }
            }

        case UPDATE_SELECTED_VEHICLE:
            return {
                ...state,
                selectedVehicle: {
                    vehicle: action.payload.vehicle
                }
            }

        case UPDATE_SELECTED_TRACK:
            return {
                ...state,
                selectedTrack: {
                    trackId: action.payload.trackId,
                    trackName: action.payload.trackName,
                }
            }

        case UPDATE_DOWNFORCE_COEFFICIENT:
            return {
                ...state,
                selectedVehicle: {
                    vehicle: {
                        ...state.selectedVehicle.vehicle,
                        downforceCoefficient: action.payload.newDownforceCoefficient
                    }
                }
            }

        case UPDATE_DRAG_COEFFICIENT:
            return {
                ...state,
                selectedVehicle: {
                    vehicle: {
                        ...state.selectedVehicle.vehicle,
                        dragCoefficient: action.payload.newDragCoefficient
                    }
                }
            }

        case UPDATE_PRESSURE_TO_TORQUE_RATIO:
            return {
                ...state,
                selectedVehicle: {
                    vehicle: {
                        ...state.selectedVehicle.vehicle,
                        pressureToTorqueRatio: action.payload.newPressureToTorqueRatio
                    }
                }
            }

        case UPDATE_VEHICLE_MASS:
            return {
                ...state,
                selectedVehicle: {
                    vehicle: {
                        ...state.selectedVehicle.vehicle,
                        mass: action.payload.newVehicleMass
                    }
                }
            }

        case UPDATE_POWER_MAX:
            return {
                ...state,
                selectedVehicle: {
                    vehicle: {
                        ...state.selectedVehicle.vehicle,
                        powerMax: action.payload.newPowerMax
                    }
                }
            }

        case UPDATE_TORQUE_MAX:
            return {
                ...state,
                selectedVehicle: {
                    vehicle: {
                        ...state.selectedVehicle.vehicle,
                        torqueMax: action.payload.newTorqueMax
                    }
                }
            }

        case UPDATE_RPM_POWER_MAX:
            return {
                ...state,
                selectedVehicle: {
                    vehicle: {
                        ...state.selectedVehicle.vehicle,
                        rpmPowerMax: action.payload.newRpmPowerMax
                    }
                }
            }

        case UPDATE_RPM_TORQUE_MAX:
            return {
                ...state,
                selectedVehicle: {
                    vehicle: {
                        ...state.selectedVehicle.vehicle,
                        rpmTorqueMax: action.payload.newRpmTorqueMax
                    }
                }
            }

        case UPDATE_POWERTRAIN_TYPE:
            return {
                ...state,
                selectedVehicle: {
                    vehicle: {
                        ...state.selectedVehicle.vehicle,
                        powertrainType: action.payload.newPowertrainType
                    }
                }
            }

        case UPDATE_NUMBER_OF_GEARS:
            return {
                ...state,
                selectedVehicle: {
                    vehicle: {
                        ...state.selectedVehicle.vehicle,
                        numberOfGears: action.payload.newNumberOfGears
                    }
                }
            }

        case UPDATE_GEARS:
            return {
                ...state,
                selectedVehicle: {
                    ...state.selectedVehicle,
                    vehicle: {
                        ...state.selectedVehicle.vehicle,
                        gears: action.payload.newGearRatios
                    }
                }
            };

        case UPDATE_GEAR_RATIO:
            return {
                ...state,
                selectedVehicle: {
                    ...state.selectedVehicle,
                    vehicle: {
                        ...state.selectedVehicle.vehicle,
                        gears: state.selectedVehicle.vehicle.gears.map((gear, index) =>
                            index === action.payload.index ? action.payload.newGearRatio : gear
                        )
                    }
                }
            };

        case UPDATE_FINAL_DRIVE_RATIO:
            return {
                ...state,
                selectedVehicle: {
                    vehicle: {
                        ...state.selectedVehicle.vehicle,
                        finalDriveRatio: action.payload.newFinalDriveRatio
                    }
                }
            }

        case UPDATE_LONGITUDINAL_GRIP:
            return {
                ...state,
                selectedVehicle: {
                    vehicle: {
                        ...state.selectedVehicle.vehicle,
                        longitudinalGrip: action.payload.newLongitudinalGrip
                    }
                }
            }

        case UPDATE_LATERAL_GRIP:
            return {
                ...state,
                selectedVehicle: {
                    vehicle: {
                        ...state.selectedVehicle.vehicle,
                        lateralGrip: action.payload.newLateralGrip
                    }
                }
            }

        case UPDATE_TYRE_RADIUS:
            return {
                ...state,
                selectedVehicle: {
                    vehicle: {
                        ...state.selectedVehicle.vehicle,
                        tyreRadius: action.payload.newTyreRadius
                    }
                }
            }

        case POST_UPDATED_VEHICLE_MODEL_STARTED:
            return {
                ...state,
                selectedVehicle: {
                    ...state.selectedVehicle,
                    loading: true,
                    error: null,
                }
            }

        case POST_UPDATED_VEHICLE_MODEL_SUCCESS:
            return {
                ...state,
                selectedVehicle: {
                    ...state.selectedVehicle,
                    loading: false,
                    error: null,
                }
            }

        case POST_UPDATED_VEHICLE_MODEL_FAILURE:
            return {
                ...state,
                selectedVehicle: {
                    ...state.selectedVehicle,
                    loading: false,
                    error: action.payload.error,
                }
            }

        case FETCH_VEHICLE_MODEL_BY_ID_STARTED:
            return {
                ...state,
                selectedVehicle: {
                    ...state.selectedVehicle,
                    loading: true,
                    error: null,
                }
            }

        case FETCH_VEHICLE_MODEL_BY_ID_SUCCESS:
            return {
                ...state,
                selectedVehicle: {
                    ...state.selectedVehicle,
                    loading: false,
                    error: null,
                    vehicle: action.payload.data
                }
            }

        case FETCH_VEHICLE_MODEL_BY_ID_FAILURE:
            return {
                ...state,
                selectedVehicle: {
                    ...state.selectedVehicle,
                    loading: false,
                    error: action.payload.error,
                }
            }

        default:
            return state;
    }
}

export default reducer;