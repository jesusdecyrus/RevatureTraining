import { useState } from "react";
import { Button, Form } from "react-bootstrap";
import { useNavigate } from "react-router-dom"
import 'bootstrap/dist/css/bootstrap.css';
import axios from "axios";
import { useAuthentication } from "../../../Context/AuthenticationContext";

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

  // Message
  const [message, setMessage] = useState("");

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
      setMessage("Invalid Username or Password");
    }
  }

  // HTML
  return (
    <div className="bg-secondary mt-4 p-5 rounded">
      <h2>Login</h2>
      <div className="mt-4">
        <Form.Control
          type="text"
          placeholder="username"
          name="username"
          value={user.username}
          onChange={handleInputChange}
        />
      </div>
      <div className="mt-2">
        <Form.Control
          type="password"
          placeholder="password"
          name="password"
          value={user.password}
          onChange={handleInputChange}
        />
      </div>

      {/* Messages and Button */}
      <div className="mt-2 text-danger">
        {message}
      </div>
      <div>
        <Button className="btn-dark mt-3" onClick={login}>Login</Button>
      </div>
    </div>
  )
}