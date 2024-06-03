import React from 'react'
import ReactDOM from 'react-dom/client'
import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import './index.css'
import SignIn from "./pages/SignIn.jsx";
import AddVehiclePage from "./pages/AddVehiclePage.jsx";
import AppProvider from "./context/AppProvider.jsx";
import Dashboard from "./pages/Dashboard.jsx";

ReactDOM.createRoot(document.getElementById('root')).render(
    <AppProvider>
        <React.StrictMode>
            <Router>
                <Routes>
                    <Route path="/dashboard" element={<Dashboard/>}/>
                    <Route path="/authentication" element={<SignIn/>}/>
                    <Route path="/addVehicle" element={<AddVehiclePage/>}/>
                    {/*<Route path="/editVehicle/:vehicleID" element={<EditVehiclePage />}/>*/}
                </Routes>
            </Router>
        </React.StrictMode>,
    </AppProvider>
)
