import React,{Component} from "react";
import {Button, Card, CardBody, CardGroup,  CardText, CardTitle} from "reactstrap";
import axios from "axios";
import CardHeader from "reactstrap/es/CardHeader";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faEdit} from '@fortawesome/free-solid-svg-icons';
export default class VillasList extends Component{

    constructor(props) {
        super(props);
        this.state = {
            villas : []
        };
    }

    componentDidMount() {
        axios.get("http://localhost:8080/api/villa/findAll")
            .then(response => response.data)
            .then((data) => {
                this.setState({villas:data});
            });
    }

    render() {
        return(
            <Card className="border border-dark bg-dark-gray ">
                <CardHeader>Villas List</CardHeader>
                <table>
                    <tbody>
                    {
                        this.state.villas.length === 0 ?
                            <tr align="center">
                                <div >There is no villas </div>
                            </tr> :
                            this.state.villas.map((villa) => (
                                <tr key={villa.id}>
                                    <td>{villa.name}</td>
                                    <td>{villa.cost}</td>
                                    <td>{villa.address.country}</td>
                                    <td>{villa.address.city}</td>
                                    <td>{villa.address.postalCode}</td>
                                    <td> <Button size="sm" variant="outline-primary"><FontAwesomeIcon icon={faEdit} /> </Button></td>
                                </tr>
                            ))
                    }
                    </tbody>
                </table>
            </Card>
        )
    }
}