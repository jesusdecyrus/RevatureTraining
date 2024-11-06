import { useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom"

/**
 * Home Component
 * @returns HTML
 */
export const HomeComponent:React.FC = () => {
  // Navigate
  const navigate = useNavigate();
  
  // Access the user
  const location = useLocation();
  const user = location.state?.user;

  // Return user to login when not signed in
  useEffect(() => {
    if (!user || user === undefined) {
      navigate("/");
    }
  })

  // HTML
  return(
    <div>
      Welcome {user}
    </div>
  )
}