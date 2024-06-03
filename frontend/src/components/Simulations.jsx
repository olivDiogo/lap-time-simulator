import * as React from 'react';
import Link from '@mui/material/Link';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Title from './Title';

// Generate Order Data
function createData(id, date, description, vehicle, track, results) {
    return {id, date, description, vehicle, track, results};
}

const rows = [
    createData(
        0,
        '16 Mar, 2019',
        'Simulation 1',
        'Porsche 911 GT3 Cup - Setup 1',
        'Monza',
        'Link to results',
    ),
    createData(
        1,
        '17 Mar, 2019',
        'Simulation 2',
        'Porsche 991 GT3 Cup - Setup 2',
        'Monza',
        'Link to results',
    ),
    createData(
        2,
        '17 Mar, 2019',
        'Simulation 3 ',
        'Porsche 911 GT3 Cup - Setup 2',
        'Monza',
        'Link to results'
    ),
    createData(
        3,
        '18 Mar, 2019',
        'Simulation 3',
        'Porsche 911 GT3 Cup - Setup 3',
        'Monza',
        'Link to results'
    ),
    createData(
        4,
        '19 Mar, 2019',
        'Simulation 3',
        'Ferrari 488 GT3 - Setup 1',
        'Suzuka',
        'Link to results'
    ),
];

function preventDefault(event) {
    event.preventDefault();
}

export default function Simulations() {
    return (
        <React.Fragment>
            <Title>Recent Simulations</Title>
            <Table size="small">
                <TableHead>
                    <TableRow>
                        <TableCell>Date</TableCell>
                        <TableCell>Description</TableCell>
                        <TableCell>Vehicle</TableCell>
                        <TableCell>Track</TableCell>
                        <TableCell align="right">Results</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {rows.map((row) => (
                        <TableRow key={row.id}>
                            <TableCell>{row.date}</TableCell>
                            <TableCell>{row.description}</TableCell>
                            <TableCell>{row.vehicle}</TableCell>
                            <TableCell>{row.track}</TableCell>
                            <TableCell align="right">
                                <Link color={"primary"} href={"#"} onClick={preventDefault} >
                                    {row.results}
                                </Link>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
            <Link color="primary" href="#" onClick={preventDefault} sx={{mt: 3}}>
                See more simulations
            </Link>
        </React.Fragment>
    );
}