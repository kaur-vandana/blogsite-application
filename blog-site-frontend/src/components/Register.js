import React, { useState } from "react";
import { useNavigate } from 'react-router-dom';

import classes from './Form.module.css';

import Button from "./UI/Button";


function Register() {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [emailId, setEmailId] = useState('');
    const [error, setError] = useState(null);
    const navigate = useNavigate();
  
    const handleSubmit = (event) => {
      event.preventDefault();
  
      if (username === "" || password === "" || emailId === "") {

        setError("Please enter your username, password and emailid");
        return;
      } 


      handleRegister();

      navigate('/login', 
      { state: { username: username } }
      );
      
    };

    const handleLogout = async () => {
        navigate('/login', 
        { state: { username: username } }
        );
      }


    const handleRegister = async () => {
        try {
            //   Send the POST request
              const response = await fetch('http://localhost:8081/api/v1.0/blogsite/user/register', {

              method: 'POST',
              headers: {
                'Content-Type': 'application/json'
              },
              body: JSON.stringify({
                username: username,
                emailId: emailId,
                password: password,
              }),
              }); 
          
              if (response.ok) {
                // Handle the response data
                const jsonData = await response.json();
                // setCategoryData(jsonData);
                console.log(jsonData);
                // ...do something with the response...
              } else {
                // Handle the error
                throw new Error('Error: ' + response.status);
              }
            } catch (error) {
              // Handle any errors
              console.error(error);
            }
      }



  
    return (
  
      <div className={classes.container}>
        <h1 className={classes.h1}>Registration</h1>
    
        <form onSubmit={handleSubmit}>
  
          <div className={classes.formgroup}>
            <label className={classes.label} htmlFor="username">Username</label>
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
            <label className={classes.label} htmlFor="password">Password</label>
            <input
              id="password"
              type="text"
              placeholder="Password"
              className={classes.input}
              value={password}
              onChange={(event) => setPassword(event.target.value)}
            />
          </div>

          <div className={classes.formgroup}>
            <label className={classes.label} htmlFor="emailId">emailId</label>
            <input
              id="emailId"
              type="emailId"
              placeholder="emailId"
              className={classes.input}
              value={emailId}
              onChange={(event) => setEmailId(event.target.value)}
            />
          </div>
    
          <div className={classes.buttoncontainer}>
          <Button type="submit">Register</Button>
          </div>
         
          <div className={classes.container}>
        <Button type="submit" onClick={handleLogout}>Login</Button>
        </div>
         
        </form>
    
        {error && <p className={classes.error}>{error}</p>}
      </div>
    );
    
  };

 
  
export default Register;