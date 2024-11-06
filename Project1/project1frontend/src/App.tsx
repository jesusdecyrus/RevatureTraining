import React from 'react';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { LoginComponent } from './Components/Login/LoginComponent';
import { SignUpComponent } from './Components/SignUp/SignUpComponent';
import { HomeComponent } from './Components/Home/HomeComponent';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<LoginComponent/>}/>
          <Route path="/signup" element={<SignUpComponent/>}/>
          <Route path="/home" element={<HomeComponent/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
