import React from 'react'
import {Navbar,Nav} from 'react-bootstrap';
import logo from './imgs/logo.png';
import {Link} from "react-router-dom";

class NavigationBar extends  React.Component{

    render() {
        return (
            <Navbar bg="light" variant="light" style={{border:"1px solid black",height:"100px"}} >
                <Link to={""} className="navbar-brand">
                    <Navbar.Brand href="/"><img src={logo} alt="logo" style={{width:"100px", height:"100px"}}/></Navbar.Brand>
                </Link>

                <Nav className="mr-auto">
                    <Link  to={""} className="nav-link">Home</Link>
                    <Link to={""} className="nav-link" >Register</Link>
                    <Link to={""} className="nav-link" >Login</Link>
                    <Link to={"add"} className="nav-link" >Add Villa</Link>
                    <Link to={"list"} className="nav-link">Villas List</Link>
                </Nav>
            </Navbar>
        );
    }
}

export default NavigationBar;