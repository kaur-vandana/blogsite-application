import React, { useState } from "react";
import { useNavigate } from 'react-router-dom';

import classes from './Form.module.css';
import Button from "./UI/Button";

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const [boolValue, setBoolValue] = useState('');

  const isUserPresent = async () => {
    try {

      //   Send the GET request
      const response = await fetch('http://localhost:8081/users', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          username: username,
          password: password,
        }),
      }); 
      
  
      if (response.ok) {
        // Handle the response data
        const boolValue = await response.json();
        setBoolValue(boolValue);

        // ...do something with the response...
      } else {
        // Handle the error
        throw new Error('Error: ' + response.status);
      }
    } catch {
      console.error(error);
    }
        
       
  }



  const handleSubmit = (event) => {
    event.preventDefault();

    
    isUserPresent();

    if (username === "" || password === "" || boolValue === '') {
      setError("Please enter correct username and password");
      return;
    } 
    
    navigate('/blogs', 
    { state: { username: username, password: password, isLoggedIn: true } }
    );
    
  };

  return (

    <div className={classes.container}>
      <h1 className={classes.h1}>Login</h1>
  
      <form onSubmit={handleSubmit}>

        <div className={classes.formgroup}>
          <label className={classes.label} htmlFor="username">
            Username
          </label>
          <input
            id="id"
            type="text"
            placeholder="username"
            className={classes.input}
            value={username}
            onChange={(event) => setUsername(event.target.value)}
          />
        </div>
  
        <div className={classes.formgroup}>
          <label className={classes.label} htmlFor="password">
            Password
          </label>
          <input
            id="password"
            type="password"
            placeholder="Password"
            className={classes.input}
            value={password}
            onChange={(event) => setPassword(event.target.value)}
          />
        </div>
  
        <div className={classes.buttoncontainer}>
        <Button type="submit">Login</Button>
        </div>
       
      </form>
  
      {error && <p className={classes.error}>{error}</p>}
    </div>
  );
  
};

export default Login;