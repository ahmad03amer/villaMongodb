import React from "react";
import {Navbar,Col} from "react-bootstrap";

class Footer extends React.Component{
render() {
    let fullYear = new Date().getFullYear();
    return(
        <Navbar fixed={"bottom"} bg="light" variant="light" style={{border:"1px solid black"}}>
            <Col lg={12} className="text-center text-muted">
                <div> {fullYear} - {fullYear+1} , All Rights Reserved By Ahmad Amer</div>
            </Col>
        </Navbar>
    )
}
}

export default  Footer;