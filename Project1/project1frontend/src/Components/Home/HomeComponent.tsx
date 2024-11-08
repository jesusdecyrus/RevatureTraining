import { useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom"
import { useAuthentication } from "../../Context/AuthenticationContext";

/**
 * Home Component
 * @returns HTML
 */
export const HomeComponent:React.FC = () => {
  // Authentication
  
  const { AuthenticationData } = useAuthentication();
  // Navigate
  const navigate = useNavigate();

  // Return user to login when not signed in
  useEffect(() => {
    if (!AuthenticationData.isAuthenticated) {
      navigate("/");
    }
  })

  // HTML
  return(
    <div>
      Welcome {AuthenticationData.username}
    </div>
  )
}