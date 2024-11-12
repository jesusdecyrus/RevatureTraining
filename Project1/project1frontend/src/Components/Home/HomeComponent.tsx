import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom"
import { useAuthentication } from "../../Context/AuthenticationContext";
import { Container } from "react-bootstrap";
import axios from "axios";
import { ReimbursementComponent } from "./Reimbursement/ReimbursementComponent";
import { EmployeeComponent } from "./Employees/EmployeeComponent";
import { ProfileComponent } from "./Profile/ProfileComponent";
import "./home.css";

/**
 * Home Component
 * @returns HTML
 */
export const HomeComponent:React.FC = () => {
  // Authentication
  const navigate = useNavigate();
  const { AuthenticationData } = useAuthentication();
  const [authenticatedUser, setAuthenticatedUser] = useState({
    firstName: "",
    lastName: "",
    username: "",
    role: "",
  });
  useEffect(() => {
    if (!AuthenticationData.isAuthenticated) {
      // Return user to login when not signed in
      navigate("/");
    }
    else {
      // Retrieve the current logged in user's info
      const getUserData = async () => {
        try {
          const response = await axios.get("http://localhost:150/users/one/" + AuthenticationData.username);
          setAuthenticatedUser(response.data);
        } catch (error) {
          console.log("Failed to retrieve user: ", error);
        }
      };
      getUserData();
    }
  });
  // End of Authentication

  // Logout Authentication
  const { authenticationLogout } = useAuthentication();

  // Determines which component to show
  const [component, setComponent] = useState("Reimbursements");

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
            {authenticatedUser.role === "Manager" && (
              <li className="nav-item">
                <a className="nav-link" onClick={() => setComponent("Employees")}>Employees</a>
              </li>
            )}
            <li className="nav-item">
              <a className="nav-link" onClick={() => setComponent("Profile")}>Profile</a>
            </li>
          </ul>
        </div>
        <button className="btn btn-dark my-2 my-sm-0" onClick={() => {authenticationLogout(); navigate("/");}}>Logout</button>
      </nav>

      {/* Reimbursement, Employee, Profile Components */}
      <Container>
        {component === "Reimbursements" && (authenticatedUser.role == "Employee" || authenticatedUser.role == "Manager") && (
          <div>
            <ReimbursementComponent AuthenticatedUser={authenticatedUser}></ReimbursementComponent>
          </div>
        )}
        {component === "Reimbursements" && authenticatedUser.role == "User" && (
          <div className="text-danger mt-5">
            <h4>Please Contact a Manager to Access Reimbursements</h4>
          </div>
        )}
        {component === "Employees" && (
          <EmployeeComponent AuthenticatedUser={authenticatedUser}></EmployeeComponent>
        )}
        {component === "Profile" && (
          <ProfileComponent AuthenticatedUser={authenticatedUser}></ProfileComponent>
        )}
      </Container>
    </div>
  )
}