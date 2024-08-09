import React from 'react'
import ReactDOM from 'react-dom/client'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import './index.css'
import SignIn from "./pages/SignIn.jsx";
import AppProvider from "./context/AppProvider.jsx";
import DashboardPage from "./pages/DashboardPage.jsx";
import TracksListPage from "./pages/TracksListPage.jsx";
import VehicleModelListPage from "./pages/VehicleModelsListPage.jsx";
import UpdateVehicleModelPage from "./pages/UpdateVehicleModelPage.jsx";
import CreateVehicleModelPage from "./pages/CreateVehicleModelPage.jsx";
import CreateSimulationPage from "./pages/CreateSimulationPage.jsx";
import SimulationResultsPage from "./pages/SimulationResultsPage.jsx";

ReactDOM.createRoot(document.getElementById('root')).render(
    <AppProvider>
        <React.StrictMode>
            <Router>
                <Routes>
                    <Route path="/" element={<SignIn/>}/>
                    <Route path="/dashboard" element={<DashboardPage/>}/>
                    <Route path="/tracks" element={<TracksListPage/>}/>
                    <Route path="/vehicleModels" element={<VehicleModelListPage/>}/>
                    <Route path="/vehicleModels/create" element={<CreateVehicleModelPage/>}/>
                    <Route path="/vehicleModels/:id" element={<UpdateVehicleModelPage/>}/>
                    <Route path="/createSimulation" element={<CreateSimulationPage/>}/>
                    <Route path="/simulations" element={<SimulationResultsPage/>}/>
                </Routes>
            </Router>
        </React.StrictMode>,
    </AppProvider>
)
