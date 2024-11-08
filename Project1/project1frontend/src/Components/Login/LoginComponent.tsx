import { useState } from "react";
import { Button, Container, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom"
import 'bootstrap/dist/css/bootstrap.css';
import axios from "axios";
import { useAuthentication } from "../../Context/AuthenticationContext";

/**
 * Login Component
 * @returns HTML
 */
export const LoginComponent:React.FC = () => {
  // Authentication
  const { authenticationLogin } = useAuthentication();
  // Navigate
  const navigate = useNavigate();

  // User setter
  const [user, setUser] = useState({
    username: "",
    password: ""
  })

  // Handle input changes
  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const {name, value} = event.target;

    // Update state
    setUser(previousUser => ({
      ...previousUser,
      [name]: value
    }))
  }

  // Backend communication 
  const login = async () => {
    try {
      // POST request to the login endpoint
      await axios.post("http://localhost:150/users/login", user);
      
      // Update authentication
      authenticationLogin(user.username, user.password);

      // Login Success
      navigate("/home");
    } catch(error) {
      console.log("Failed to log in: ", error);
    }
  }

  // HTML
  return (
    <div className="d-flex justify-content-center align-items-center custom-height">
      <Container className="bg-secondary rounded-pill p-5">
        <h3>Login</h3>
        <div>
          <Form.Control
            type="text"
            placeholder="username"
            name="username"
            value={user.username}
            onChange={handleInputChange}
          />
        </div>
        <div>
          <Form.Control
            type="password"
            placeholder="password"
            name="password"
            value={user.password}
            onChange={handleInputChange}
          />
        </div>

        <div>
          <Button className="btn-dark mt-4" onClick={login}>Login</Button>
        </div>
        <div>
        <Button className="btn-light mt-3" onClick={() => navigate("/signup")}>Sign Up</Button>
        </div>
      </Container>
    </div>
  )
}