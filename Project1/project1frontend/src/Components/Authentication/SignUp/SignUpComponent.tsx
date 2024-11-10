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
    <div className="bg-secondary mt-4 p-5 rounded">
      <h2>Sign Up</h2>
      <div className='mt-4'>
        <Form.Control
          type="text"
          placeholder="first name"
          name="firstName"
          value={user.firstName}
          onChange={handleInputChange}
        />
      </div>
      <div className='mt-2'>
        <Form.Control
          type="text"
          placeholder="last name"
          name="lastName"
          value={user.lastName}
          onChange={handleInputChange}
        />
      </div>
      <div className='mt-2'>
        <Form.Control
          type="text"
          placeholder="username"
          name="username"
          value={user.username}
          onChange={handleInputChange}
        />
      </div>
      <div className='mt-2'>
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
    </div>
  )
}