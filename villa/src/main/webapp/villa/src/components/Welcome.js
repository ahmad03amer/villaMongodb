import React from "react";
import {Button, Jumbotron} from "react-bootstrap";

class Welcome extends React.Component{

    render() {
        return(
            <Jumbotron>
                <h1>Hello Users !</h1>
                <p>
                    CRETE
                    Discover
                    Luxury Villa
                    Rentals
                    Find the ideal villa to rent in
                    Crete top travel destinations!
                </p>
                <p>
                    <Button variant="primary">Learn more</Button>
                </p>
            </Jumbotron>
        )
    }
}

export default Welcome;