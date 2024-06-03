import React, {Component} from "react";
import './TracksSelector.css';

class TracksSelector extends Component {
    constructor(props) {
        super(props);
        this.state = {
            tracks: [],
            error: null,
            selectedTrack: ''
        };
        this.handleChange = this.handleChange.bind(this);
    }

    componentDidMount() {
        fetch("http://localhost:8080/tracks", {method: 'GET'})
            .then(response => response.json())
            .then(data => this.setState({tracks: data}))
            .catch(error => {
                console.log(error);
                this.setState({error: error.toString()});
            });
    }

    handleChange(trackID) {
        this.setState({selectedTrack: trackID});
    }

    render() {
        return (
            <div className={"track-list-container"}>
                <h1 className={"list-header"}>Select a track</h1>
                <div className={"track-list"}>
                    {this.state.tracks.map(track => (
                        <button className={`track-button ${this.state.selectedTrack === track.trackID ? 'track-button-selected' : ''}`}
                                key={track.trackID}
                                onClick={() => this.handleChange(track.trackID)}
                        >
                            {track.trackName}
                        </button>
                    ))}
                    {this.state.error && <p>{this.state.error}</p>}
                </div>
            </div>
        );
    }
}

export default TracksSelector;