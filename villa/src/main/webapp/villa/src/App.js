import React from 'react';
import './App.css';
import NavigationBar from "./components/NavigationBar";
import {Container,Row ,Col} from 'react-bootstrap';
import {BrowserRouter as Router,Switch ,Route} from 'react-router-dom';
import Welcome from "./components/Welcome";
import Footer from "./components/Footer";
import Villa from "./components/Villa";
import VillasList from "./components/VillasList";

function App() {
 const marginTop ={
     marginTop :"20px"
 };


  return (
    <Router>
        <NavigationBar />
      <Container>
        <Row>
            <Col lg={12} style={marginTop} >
                <Switch>
                    <Route path="/" exact component={Welcome} />
                    <Route path="/add" exact component={Villa} />
                    <Route path="/list" exact component={VillasList} />
                </Switch>
            </Col>
        </Row>
      </Container>
        <Footer />
    </Router>
  );
}

export default App;
