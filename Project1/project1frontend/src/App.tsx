import React, { useEffect } from 'react';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { SignUpComponent } from './Components/Authentication/SignUp/SignUpComponent';
import { HomeComponent } from './Components/Home/HomeComponent';
import { AuthenticationComponent } from './Components/Authentication/Authentication';

function App() {
  useEffect(() => {
    document.title = "Employee Reimbursement System";
  }, []); 
  
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<AuthenticationComponent/>}/>
          <Route path="/signup" element={<SignUpComponent/>}/>
          <Route path="/home" element={<HomeComponent/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
