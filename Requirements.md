# Lap Time Simulation Tool
## Development - First Stage

### Core Features
- Lap Time Simulation (single-point quasi-static)

### Requirements
#### Tracks
- There is a list of tracks available for selection by the user, in a drop-down menu.
- The tracks are defined by curvature and distance, only.

#### Cars
- The cars are defined by:
    * Chassis (defined by **mass**)
    * Tyre model (defined by **longitudinal grip** and **lateral grip**)
    * Aerodynamics (defined by **downforce** and **drag**)
    * Powertrain (defined by **power** and **torque**)
    * Transmission (defined by **gear ratios**)
    * Brakes (defined by **brake pressure to torque ratio**)

#### UI/UX
- The user can select a track from a list in a drop-down menu.
- The user fills the form with the car's parameters.
- The user selects a button to start the simulation.
- Once the simulation is done, the user can see the results.
- The results are displayed in the following way:
    * Track Map (function of speed)
    * Distanced-based graph with:
      * Speed
      * Throttle
      * Braking
      * Steering

### Technologies
- Simulation algorithms: C++/Python
- Backend: Java (Spring Boot)
- Frontend: React
- Database: ?
- Hosting: Cloud-based service ?
- Version Control: Git