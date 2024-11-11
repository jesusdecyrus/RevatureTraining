import { useEffect, useState } from "react"
import { useAuthentication } from "../../../Context/AuthenticationContext";
import axios from "axios";
import { Button, Container, Form } from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.css';
import './profile.css'

/**
 * Profile Component
 * @returns HTML
 */
export const ProfileComponent:React.FC = () => {
  // Authentication
  const { AuthenticationData } = useAuthentication();
  
  // User REMOVE 
  const [user, setUser] = useState({
    firstName: "Cyrus",
    lastName: "De Jesus",
    username: "cdejesus",
    role: "Employee",
  });
  // Edit Mode
  const [isEditing, setIsEditing] = useState(false);

  // Handle input changes
  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setUser((previousUser) => ({ ...previousUser, [name]: value }));
  };

  // Retrieve user
  useEffect(() => {
    const getUser = async () => {
      try {
        const response = await axios.get("http://localhost:150/users/one/" + AuthenticationData.username);
        setUser(response.data);
      } catch (error) {
        console.log("Failed to retrieve user: ", error);
      }
    };
    getUser();
  });

  // HTML
  return (
    <div>
      <Container className="mt-2">
        <div>
          First Name: {user.firstName} 
          <Form.Control
            type="text"
            placeholder="new first name"
            name="firstName"
            onChange={handleInputChange}
            className="custom-form"
          />
        </div>
        <div>
          Last Name: {user.lastName} 
          <Form.Control
            type="text"
            placeholder="new last name"
            name="lastName"
            onChange={handleInputChange}
            className="custom-form"
          />
        </div>
        <div>
          Username: {user.username} 
          <Form.Control
            type="text"
            placeholder="new username"
            name="username"
            onChange={handleInputChange}
            className="custom-form"
          />
        </div>
        <div>
          Role: {user.role} 
          <Form.Control
            type="text"
            placeholder="new role"
            name="role"
            onChange={handleInputChange}
            className="custom-form"
          />
        </div>

        <Button className="btn-secondary">Edit</Button>
        <Button className="btn-secondary">Save</Button>
      </Container>
    </div>
  )
}