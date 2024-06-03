import React, {Component} from "react";
import {Link} from "react-router-dom";
import './AddVehiclePage.css';

class AddVehiclePage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            formData: {
                vehicleName: '',
                downforceCoefficient: '',
                dragCoefficient: '',
                brakePressureTorqueRatio: '',
                mass: '',
                powerMax: '',
                torqueMax: '',
                rpmPowerMax: '',
                rpmTorqueMax: '',
                numberOfGears: '',
                gears: [],
                finalDriveRatio: '',
                longitudinalGrip: '',
                lateralGrip: '',
                tyreRadius: ''
            }
        }
    }

    handleChange = (event) => {
        const { name, value } = event.target;

        if (name.startsWith("gear")) {
            // Parse the gear number from the name (e.g., "gear1" -> 1)
            const gearNumber = parseInt(name.slice(4));

            // Subtract one to get the index
            const gearIndex = gearNumber - 1;

            // Update the corresponding element in the gears array
            this.setState(prevState => {
                const newGears = [...prevState.formData.gears];
                newGears[gearIndex] = value;

                return {
                    formData: {
                        ...prevState.formData,
                        gears: newGears
                    }
                };
            });
        } else {
            // For all other fields, update the state as before
            this.setState({
                formData: {
                    ...this.state.formData,
                    [name]: value
                }
            });
        }
    }

    handleAddVehicle = () => {
        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(this.state.formData)
        }
        fetch('http://localhost:8080/vehicles', requestOptions)
            .then(response => response.json())
            .then(data => console.log(data))
            .catch(error => console.log(error));
    }

    render() {
        return (
            <div className={"add-vehicle-container"}>
                <h1>Add vehicle</h1>

                <form>
                    <label id={"vehicle-name"}>
                        Vehicle Name{' '}
                        <input type={"text"} name={"vehicleName"} onChange={this.handleChange}/>
                    </label>

                    <div className={"aero-model"}>
                        <h2>Aerodynamics</h2>
                        <label id={"downforce-coefficient"}>
                            Downforce coefficient{' '}
                            <input type={"text"} name={"downforceCoefficient"} onChange={this.handleChange}/>
                        </label>
                        <label id={"drag-coefficient"}>
                            Drag coefficient{' '}
                            <input type={"text"} name={"dragCoefficient"} onChange={this.handleChange}/>
                        </label>
                    </div>
                    <div className={"brake-model"}>
                        <h2>Brakes</h2>
                        <label id={"brake-pressure-torque-ratio"}>
                            Brake pressure/torque ratio{' '}
                            <input type={"text"} name={"brakePressureTorqueRatio"} onChange={this.handleChange}/>
                        </label>
                    </div>
                    <div className={"chassis-model"}>
                        <h2>Chassis</h2>
                        <label id={"mass"}>
                            Mass {' '}
                            <input type={"text"} name={"mass"} onChange={this.handleChange}/>
                            {' '}kg
                        </label>
                    </div>
                    <div className={"powertrain-model"}>
                        <h2>Powertrain</h2>
                        <label id={"engine-maxPower"}>
                            Engine maximum power{' '}
                            <input type={"text"} name={"powerMax"} onChange={this.handleChange}/>
                            {' '}hp
                        </label>
                        <label id={"engine-maxTorque"}>
                            Engine maximum torque{' '}
                            <input type={"text"} name={"torqueMax"} onChange={this.handleChange}/>
                            {' '}Nm
                        </label>
                        <label id={"rpm-maxPower"}>
                            RPM at maximum power{' '}
                            <input type={"text"} name={"rpmPowerMax"} onChange={this.handleChange}/>
                            {' '}rpm
                        </label>
                        <label id={"rpm-maxTorque"}>
                            RPM at maximum torque{' '}
                            <input type={"text"} name={"rpmTorqueMax"} onChange={this.handleChange}/>
                            {' '}rpm
                        </label>
                    </div>
                    <div className={"transmission-model"}>
                        <h2>Transmission</h2>
                        <label id={"number-of-gears"}>
                            Number of gears{' '}
                            <input type={"text"} name={"numberOfGears"} onChange={this.handleChange}/>
                        </label>
                        <div className={"gear-ratios"}>
                            <h4>Gear Ratios</h4>
                            <label id={"gear-ratios"}>
                                1st{' '}
                                <input type={"text"} name={"gear1"} onChange={this.handleChange}/>
                                2nd{' '}
                                <input type={"text"} name={"gear2"} onChange={this.handleChange}/>
                                3rd{' '}
                                <input type={"text"} name={"gear3"} onChange={this.handleChange}/>
                                4th{' '}
                                <input type={"text"} name={"gear4"} onChange={this.handleChange}/>
                                5th{' '}
                                <input type={"text"} name={"gear5"} onChange={this.handleChange}/>
                                6th{' '}
                                <input type={"text"} name={"gear6"} onChange={this.handleChange}/>
                                7th{' '}
                                <input type={"text"} name={"gear7"} onChange={this.handleChange}/>
                                8th{' '}
                                <input type={"text"} name={"gear8"} onChange={this.handleChange}/>
                            </label>
                        </div>
                        <label id={"final-drive-ratio"}>
                            Final drive ratio{' '}
                            <input type={"text"} name={"finalDriveRatio"} onChange={this.handleChange}/>
                        </label>
                    </div>
                    <div className={"tyre-model"}>
                        <h2>Tyres</h2>
                        <label id={"longitudinal-grip"}>
                            Longitudinal grip{' '}
                            <input type={"text"} name={"longitudinalGrip"} onChange={this.handleChange}/>
                        </label>
                        <label id={"lateral-grip"}>
                            Lateral grip{' '}
                            <input type={"text"} name={"lateralGrip"} onChange={this.handleChange}/>
                        </label>
                        <label id={"tyre-radius"}>
                            Tyre radius{' '}
                            <input type={"text"} name={"tyreRadius"} onChange={this.handleChange}/>
                            {' '}m
                        </label>
                    </div>
                    <Link to={"/"}>
                        <button type={"submit"} onClick={this.handleAddVehicle}>Add Vehicle</button>
                    </Link>
                </form>


                <Link to={"/"}>
                    <button>Back</button>
                </Link>
            </div>
        )
    }

}

export default AddVehiclePage;