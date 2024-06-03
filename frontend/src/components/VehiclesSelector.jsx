import React, {Component} from "react";
import './VehiclesSelector.css';
import AddEditVehicleButtons from "./AddEditVehicleButtons.jsx";


class VehiclesSelector extends Component {
    constructor(props) {
        super(props);
        this.state = {
            vehicles: [],
            error: null,
            selectedVehicle: ''
        };
        this.handleChange = this.handleChange.bind(this);
    }

    componentDidMount() {
        fetch("http://localhost:8080/vehicles", {method: 'GET'})
            .then(response => response.json())
            .then(data => this.setState({vehicles: data}))
            .catch(error => {
                console.log(error);
                this.setState({error: error.toString()});
            });
    }

    handleChange(vehicleID) {
        this.setState({selectedVehicle: vehicleID});
    }

    render() {
        return (
            <div className={"vehicle-list-container"}>
                <h1 className={"list-header"}>Select a vehicle</h1>
                <div className={"vehicle-list"}>
                    {this.state.vehicles.map(vehicle => (
                        <button
                            className={`vehicle-button ${this.state.selectedVehicle === vehicle.vehicleID ? 'vehicle-button-selected' : ''}`}
                            key={vehicle.vehicleID}
                            onClick={() => this.handleChange(vehicle.vehicleID)}>
                            {vehicle.vehicleName}
                        </button>
                    ))}
                    {this.state.error && <p>{this.state.error}</p>}
                </div>
                <div className={"add-edit-vehicle-buttons"}>
                    <AddEditVehicleButtons selectedVehicle={this.state.selectedVehicle}/>
                </div>
            </div>
        );
    }
}

export default VehiclesSelector;