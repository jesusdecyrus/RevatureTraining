import React, { createContext, useState, ReactNode, useContext } from "react";

// Define the type for authentication data
interface AuthenticationData {
  username: string;
  password: string;
  isAuthenticated: boolean;
}

// Define the type for the context value
interface AuthenticationContextType {
  AuthenticationData: AuthenticationData;
  authenticationLogin: (username: string, password: string) => void;
  authenticationLogout: () => void;
}

// Create the context with a default value of undefined for easier type checking
const AuthContext = createContext<AuthenticationContextType | undefined>(undefined);

// Component
export const AuthenticationProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  // Setter  
  const [AuthenticationData, setAuthenticationData] = useState<AuthenticationData>({ username: "", password: "", isAuthenticated: false });

    // Updates the authentication data when logging in
    const authenticationLogin = (username: string, password: string) => {
      setAuthenticationData({ username, password, isAuthenticated: true });
    };

    // Updates the authentication data when logging out
    const authenticationLogout = () => {
      setAuthenticationData({ username: "", password: "", isAuthenticated: false });
    };

    return (
      <AuthContext.Provider value={{ AuthenticationData, authenticationLogin, authenticationLogout }}>
        {children}
      </AuthContext.Provider>
    );
};

// Custom Hook for access and update in any component
export const useAuthentication = () => {
    const context = useContext(AuthContext);
    if (context === undefined) {
      throw new Error("useAuthentication must be used within an AuthenticationProvider");
    }
    return context;
};