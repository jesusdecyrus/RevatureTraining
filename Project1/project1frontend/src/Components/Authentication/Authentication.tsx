import { Button, Container } from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.css';
import './authentication.css'
import { LoginComponent } from "./Login/LoginComponent";
import { useState } from "react";
import { SignUpComponent } from "./SignUp/SignUpComponent";
import { CSSTransition } from 'react-transition-group';

/**
 * Authentication Component
 * @returns HTML
 */
export const AuthenticationComponent:React.FC = () => {  
  // Toggles between login and sign up components
  const [isLogin, setIsLogin] = useState(true);
  const toggleComponent = () => {
    setIsLogin(!isLogin);
  };

  // HTML
  return (
    <div>
      {/* Application Title */}
      <Container>
        <div className="display-5 mt-5">
          <span className="custom-hover">E</span>
          <span className="custom-hover">M</span>
          <span className="custom-hover">P</span>
          <span className="custom-hover">L</span>
          <span className="custom-hover">O</span>
          <span className="custom-hover">Y</span>
          <span className="custom-hover">E</span>
          <span className="custom-hover">E</span>
          <span className="mr-1"> </span>
          <span className="custom-hover">R</span>
          <span className="custom-hover">E</span>
          <span className="custom-hover">I</span>
          <span className="custom-hover">M</span>
          <span className="custom-hover">B</span>
          <span className="custom-hover">U</span>
          <span className="custom-hover">R</span>
          <span className="custom-hover">S</span>
          <span className="custom-hover">E</span>
          <span className="custom-hover">M</span>
          <span className="custom-hover">E</span>
          <span className="custom-hover">N</span>
          <span className="custom-hover">T</span>
          <span className="mr-1"> </span>
          <span className="custom-hover">S</span>
          <span className="custom-hover">Y</span>
          <span className="custom-hover">S</span>
          <span className="custom-hover">T</span>
          <span className="custom-hover">E</span>
          <span className="custom-hover">M</span>
        </div>
      </Container>

      {/* Login and Sign Up Components */}
      <Container className="d-flex flex-row justify-content-center mt-5">
        <CSSTransition in={isLogin} timeout={500} classNames="hollow" unmountOnExit>
          <LoginComponent/>
        </CSSTransition>

        <CSSTransition in={!isLogin} timeout={500} classNames="hollow" unmountOnExit>
          <SignUpComponent/>
        </CSSTransition>
      </Container>

      {/* Button to toggle */}
      <footer className="custom-footer">
        <svg xmlns="http://www.w3.org/2000/svg" width="2.5rem" height="2.5rem" fill="currentColor" className="bi bi-arrow-left-right m-5 custom-svg" viewBox="0 0 16 16" onClick={toggleComponent}>
        <path fill-rule="evenodd" d="M1 11.5a.5.5 0 0 0 .5.5h11.793l-3.147 3.146a.5.5 0 0 0 .708.708l4-4a.5.5 0 0 0 0-.708l-4-4a.5.5 0 0 0-.708.708L13.293 11H1.5a.5.5 0 0 0-.5.5m14-7a.5.5 0 0 1-.5.5H2.707l3.147 3.146a.5.5 0 1 1-.708.708l-4-4a.5.5 0 0 1 0-.708l4-4a.5.5 0 1 1 .708.708L2.707 4H14.5a.5.5 0 0 1 .5.5"/>
          {isLogin ? 'Go to Sign Up' : 'Go to Login'}
        </svg>
      </footer>
    </div>
  )
}