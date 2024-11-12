import { useEffect, useState } from "react"
import { useAuthentication } from "../../../Context/AuthenticationContext";
import axios from "axios";
import { Button, Container, Form, Modal } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.css";
import "./profile.css"
import { useNavigate } from "react-router-dom";

/**
 * Profile Component Props
 */
interface ProfileComponentProps {
  AuthenticatedUser: {
    firstName: string;
    lastName: string;
    username: string;
    role: string;
  };
}

/**
 * Profile Component
 * @returns HTML
 */
export function ProfileComponent({ AuthenticatedUser }: ProfileComponentProps) {  
  // Authentication
  const { authenticationLogin, AuthenticationData } = useAuthentication();

  // Edit Mode
  const [isEditing, setIsEditing] = useState(false);

  // Handle input changes
  const [editedUser, setEditedUser] = useState({
    firstName: "",
    lastName: "",
    username: "",
    role: "",
  });
  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setEditedUser((previousUser) => ({ ...previousUser, [name]: value }));
  };
  const [passwords, setPasswords] = useState({
    oldPassword: "",
    newPassword: "",
  });
  const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setPasswords((previousPassword) => ({ ...previousPassword, [name]: value }));
  };

  // Save changes to update
  const [isError, setIsError] = useState(false);
  const updateProfile = async () => {
    try {
      editedUser.username = AuthenticatedUser.username;
      editedUser.role = AuthenticatedUser.role;
      await axios.put("http://localhost:150/users/update", editedUser);
      setIsError(false);
      setIsEditing(false);
    } catch (error) {
      console.log("Failed to update user: ", error);
      // Show error message
      setIsError(true);
    }
  }

  // Modal
  const [modalName, setModalName] = useState("");
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isUpdated, setIsUpdated] = useState(false);
  const [isError2, setIsError2] = useState(false);
  const toggleModal = () => {
    setIsModalOpen(!isModalOpen);
    setIsUpdated(false);
    setIsError2(false);
  };
  const updateUsername = async () => {
    try {
      await axios.put(`http://localhost:150/users/update/username/${AuthenticationData.username}/${editedUser.username}`);
      authenticationLogin(editedUser.username, AuthenticationData.password);
      setIsUpdated(true);
      setIsError2(false);
      editedUser.username = "";
    } catch (error) {
      console.log("Failed to update username: ", error);
      setIsUpdated(false);
      setIsError2(true);
    }
  };
  const updatePassword = async () => {
    try {
      if (passwords.oldPassword === AuthenticationData.password) {
        await axios.put(`http://localhost:150/users/update/password/${AuthenticationData.username}/${passwords.newPassword}`);
        authenticationLogin(AuthenticationData.username, passwords.newPassword);
        setIsUpdated(true);
        setIsError2(false);
        setPasswords({oldPassword: "", newPassword: ""});
      } else {
        setIsUpdated(false);
        setIsError2(true);
      }
    } catch (error) {
      console.log("Failed to update password: ", error);
      setIsUpdated(false);
      setIsError2(true);
    }
  }

  // HTML
  return (
    <div>
      {/* Profile Container */}
      <Container className="mt-5">
        <div className="d-flex mb-2">
          <h5 className="mt-1 custom-profile1">First Name |</h5>
          <h5 className="mt-1 custom-profile2">{AuthenticatedUser.firstName}</h5>
          {isEditing && 
            <a>
              <Form.Control
                type="text"
                placeholder="enter new first name"
                name="firstName"
                onChange={handleInputChange}
                className="custom-form"
              />
            </a>
          }
        </div>
        <div className="d-flex mb-2">
          <h5 className="mt-1 custom-profile1">Last Name |</h5>
          <h5 className="mt-1 custom-profile2">{AuthenticatedUser.lastName}</h5>
          {isEditing && 
            <a>
              <Form.Control
                type="text"
                placeholder="enter new last name"
                name="lastName"
                onChange={handleInputChange}
                className="custom-form"
              />
            </a>
          }
        </div>
        <div className="d-flex mb-2">
          <h5 className="mt-1 custom-profile1">Username |</h5>
          <h5 className="mt-1 custom-profile2">{AuthenticatedUser.username}</h5>
        </div>

        {/* Error */}
        {isError && 
          <div className="d-flex flex-row text-danger custom-ml">Please Fill Out All Fields</div>
        }

        {/* Buttons */}
        <section className="d-flex flex-row mt-2">
          <div>
            <Button className="btn-secondary m-3" onClick={() => setIsEditing(!isEditing)}>Edit</Button>
          </div>
          {!isEditing && 
            <div>
              <Button className="btn-secondary m-3" onClick={() => {toggleModal(); setModalName("Username");}}>Change Username</Button>
            </div>
          }
          {!isEditing && 
            <div>
              <Button className="btn-secondary m-3" onClick={() => {toggleModal(); setModalName("Password");}}>Change Password</Button>
            </div>  
          }
          <div>
            {isEditing &&
              <Button className="btn-success m-3" onClick={updateProfile}>Save</Button>
            }
          </div>
        </section>
      </Container>
      
      {/* Modal */}
      <Modal show={isModalOpen} onHide={toggleModal} backdrop="static" keyboard={false} animation={true}>
      <Modal.Header closeButton>
        <Modal.Title>Change {modalName}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
      {/* Username Modal */}
      {modalName === "Username" &&
        <div className="d-flex">
          <h5 className="mt-1 custom-profile1">Username |</h5>
          <h5 className="mt-1 custom-profile2">{AuthenticatedUser.username}</h5>
          <a>
            <Form.Control
              type="text"
              placeholder="enter new user name"
              name="username"
              value={editedUser.username}
              onChange={handleInputChange}
              className="custom-form"
            />
          </a>
        </div>
      }
      {/* Password Modal */}
      {modalName === "Password" &&
        <section>
          <div className="d-flex">
            <h5 className="mt-1 custom-password">Old Password |</h5>
            <a>
              <Form.Control
                type="password"
                placeholder="enter old password"
                name="oldPassword"
                value={passwords.oldPassword}
                onChange={handlePasswordChange}
                className="custom-form"
              />
            </a>
          </div>
          <div className="d-flex">
            <h5 className="mt-1 custom-password">New Password |</h5>
            <a>
              <Form.Control
                type="password"
                placeholder="enter new password"
                name="newPassword"
                value={passwords.newPassword}
                onChange={handlePasswordChange}
                className="custom-form"
              />
            </a>
          </div>
        </section>
      }
      </Modal.Body>
      <Modal.Footer>
        {isUpdated && <div className="text-success">Successfully Changed {modalName}</div>}
        {isError2 && <div className="text-danger">Enter A Valid {modalName}</div>}
        {modalName === "Username" &&
          <Button variant="success" onClick={updateUsername}>Save</Button>
        }
        {modalName === "Password" &&
          <Button variant="success" onClick={updatePassword}>Save</Button>
        }
      </Modal.Footer>
    </Modal>
    </div>
  )
}