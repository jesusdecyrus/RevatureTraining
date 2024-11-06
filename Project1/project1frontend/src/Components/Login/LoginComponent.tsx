import { Container } from "react-bootstrap";
import { useNavigate } from "react-router-dom"

/**
 * Login Component
 * @returns HTML
 */
export const LoginComponent:React.FC = () => {
  

  const navigate = useNavigate();

  return (
    <Container>
      <h3>Login</h3>
      <button>Login</button>
      <button onClick={() => navigate("/signup")}>Sign Up</button>
    </Container>
  )
}