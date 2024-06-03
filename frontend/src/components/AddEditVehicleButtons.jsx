import React, {Component} from "react";
import {Link} from "react-router-dom";
import './AddEditVehicleButtons.css';
import AddVehiclePage from "../pages/AddVehiclePage.jsx";
import EditVehiclePage from "../pages/EditVehiclePage.jsx";
import PropTypes from "prop-types";

class AddEditVehicleButtons extends Component {
    constructor(props) {
        super(props);
        this.state = {
            showAddVehiclePage: false,
            showEditVehiclePage: false,
        };
    }

    handleAddVehicleClick = () => {
        this.setState({showAddVehiclePage: true});
    }

    handleEditVehicleClick = () => {
        if (this.props.selectedVehicle) {
            this.setState({showEditVehiclePage: true});
        } else {
            alert("Please select a vehicle to edit");
        }
    }

    render() {
        if (this.state.showAddVehiclePage) {
            return (
                <AddVehiclePage/>
            )
        } else if (this.state.showEditVehiclePage) {
            return (
                <EditVehiclePage vehicleID={this.props.selectedVehicle}/>
            )
        } else {
            return (
                <div className={"add-edit-vehicle-buttons-container"}>
                    <Link to={"/addVehicle"}>
                        <button className={"add-vehicle-button"} onClick={this.handleAddVehicleClick}>
                            Add Vehicle
                        </button>
                    </Link>
                    <Link to={`/editVehicle/${this.props.selectedVehicle}`}>
                        <button className={"edit-vehicle-button"} onClick={this.handleEditVehicleClick}>
                            Edit Vehicle
                        </button>
                    </Link>
                </div>
            )
        }
    }
}

AddEditVehicleButtons.propTypes = {
    selectedVehicle: PropTypes.string
}

export default AddEditVehicleButtons;