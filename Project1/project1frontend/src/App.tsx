import React from 'react';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { LoginComponent } from './Components/Login/LoginComponent';
import { SignUpComponent } from './Components/SignUp/SignUpComponent';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<LoginComponent/>}/>
          <Route path="/signup" element={<SignUpComponent/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
