import React from 'react';
import { BrowserRouter as Router, Route,  Routes, Navigate  } from 'react-router-dom';
import ReceiverComponent from './components/ReceiverComponent';

import Register from './components/Register';

import './App.css';
import Login from './components/Login';

const App = () => {
  return (
   
      <Router>
        <Routes>
        <Route exact path="/" element={<Register/>} />
        <Route exact path="/login" element={<Login/>} />
        <Route path="/blogs" element={<ReceiverComponent/>}/>
        </Routes>
      </Router>
  

  );
};

export default App;
