import './signup.css';
import { Button, Container, Form } from "react-bootstrap"
import 'bootstrap/dist/css/bootstrap.css';
import { useState } from "react";
import axios from "axios";
import { useNavigate } from 'react-router-dom';

/**
 * SignUp Component
 * @returns HTML 
 */
export const SignUpComponent:React.FC = () => {
  // Navigate
  const navigate = useNavigate();

  // Setter
  const [user, setUser] = useState({
    firstName: "",
    lastName: "",
    username: "",
    password: "",
  });

  // Handle changes to input fields
  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = event.target;

    // Update state by setting to new value
    setUser(previousUser => ({
      ...previousUser, // Spread operator that keeps previous property into a new object
      [name]: value // Sets the property based on the [name] of the field for example password: value
    }))
  }

  // Backend communication
  const signup = async () => {
    try {
      // POST request to the signup endpoint
      await axios.post("http://localhost:150/users/signup", user);
      navigate("/");
    } catch (error) {
      console.log("Failed to sign up: ", error);
    }
  }

  return (
    <div className="d-flex justify-content-center align-items-center custom-height">
      <Container className="bg-secondary rounded-pill p-5">
        <div>
          <h1>Sign Up</h1>
          <div>
            <Form.Control
              type="text"
              placeholder="first name"
              name="firstName"
              value={user.firstName}
              onChange={handleInputChange}
            />
          </div>
          <div>
            <Form.Control
              type="text"
              placeholder="last name"
              name="lastName"
              value={user.lastName}
              onChange={handleInputChange}
            />
          </div>
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
            <Button className="btn-dark mt-4" onClick={signup}>Sign Up</Button>
          </div>
          <div>
            <Button className="btn-light mt-3" onClick={() => navigate("/")}>Login</Button>
          </div>
        </div>
      </Container>
    </div>
  )
}