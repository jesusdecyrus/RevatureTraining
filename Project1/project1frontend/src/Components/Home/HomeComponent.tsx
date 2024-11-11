import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom"
import { useAuthentication } from "../../Context/AuthenticationContext";
import { Container } from "react-bootstrap";
import axios from "axios";
import { ReimbursementComponent } from "./Reimbursement/ReimbursementComponent";
import { EmployeeComponent } from "./Employees/EmployeeComponent";
import { ProfileComponent } from "./Profile/ProfileComponent";
import './home.css';

/**
 * Home Component
 * @returns HTML
 */
export const HomeComponent:React.FC = () => {
  // Authentication
  // const { AuthenticationData } = useAuthentication();
  // Navigate
  const navigate = useNavigate();

  // Determines which component to show
  const [component, setComponent] = useState("Reimbursements");

  // User
  const [user, setUser] = useState({
    firstName: "Cyrus",
    lastName: "De Jesus",
    username: "cdejesus",
    role: "Manager",
  });

  // useEffect(() => {
  //   if (!AuthenticationData.isAuthenticated) {
  //     // Return user to login when not signed in
  //     navigate("/");
  //   }
  //   else {
  //     // Retrieve the current logged in user's info
  //     const getUserData = async () => {
  //       try {
  //         const response = await axios.get("http://localhost:150/users/one/" + AuthenticationData.username);
  //         setUser(response.data);
  //       } catch (error) {
  //         console.log("Failed to retrieve user: ", error);
  //       }
  //     };
  //     getUserData();
  //   }
  // });

  // HTML
  return(
    <div>
      <nav className="navbar navbar-expand-sm navbar-dark bg-secondary">
        <a className="navbar-brand">Employee Reimbursement System</a>
        <div className="collapse navbar-collapse">
          <ul className="navbar-nav">
            <li className="nav-item">
              <a className="nav-link" onClick={() => setComponent("Reimbursements")}>Reimbursements</a>
            </li>
            {user.role === "Manager" && (
              <li className="nav-item">
                <a className="nav-link" onClick={() => setComponent("Employees")}>Employees</a>
              </li>
            )}
            <li className="nav-item">
              <a className="nav-link" onClick={() => setComponent("Profile")}>Profile</a>
            </li>
          </ul>
        </div>
        <button className="btn btn-outline-dark my-2 my-sm-0">Logout</button>
      </nav>

      <Container>
        {component === "Reimbursements" && (
          <div>
            <h1>Welcome, {user.firstName} {user.lastName}!</h1>
            <ReimbursementComponent></ReimbursementComponent>
          </div>
        )}
        {component === "Employees" && (
          <EmployeeComponent user={user}></EmployeeComponent>
        )}
        {component === "Profile" && (
          <ProfileComponent></ProfileComponent>
        )}
      </Container>
      
      <Container>
            
      </Container>
    </div>
  )
}