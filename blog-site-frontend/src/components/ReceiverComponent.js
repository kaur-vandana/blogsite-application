import React, { useState } from 'react';
import { useLocation } from 'react-router-dom';
import classes from './Form.module.css';
import Button from "./UI/Button";
import { useNavigate } from 'react-router-dom';


function ReceiverComponent() {
  const location = useLocation();
  const { state } = location;
  const navigate = useNavigate();

  const [category, setCategory] = useState('');
  const [daterange, setDaterange] = useState('');
  const [error, setError] = useState(null);

  const { username, password, isLoggedIn } = state;

  const [from, setFrom] = useState('');
  const [to, setTo] = useState('');

  // Access and use the login details
  console.log(username);
  console.log(password);
  console.log(isLoggedIn);

  const [blogsdata, setData] = useState('');
  const [categorydata, setCategoryData] = useState('');

  const handleCategorySubmit = (event) => {
    event.preventDefault();

    if (category === "") {
      setError("Please enter your category");
    } 
    console.log(category);

    handleCategory();
  };

  const handleSubmit = (event) => {
    event.preventDefault();

    if (from === "" || to === "") {
      setError("Please enter your from and to");
    } 
    console.log(category);

    handleDateRange();
  };




  const handleButtonClick = async () => {
    try {
    //   Send the GET request
      const response = await fetch('http://127.0.0.1:8081/api/v1.0/blogsite/user/getall', {
        method: 'GET',
        headers: {
            'username': username,
            'password': password
        },
      }
      ); 
  
      if (response.ok) {
        // Handle the response data
        const jsonData = await response.json();
        setData(jsonData);
        console.log(blogsdata);
        // ...do something with the response...
      } else {
        // Handle the error
        throw new Error('Error: ' + response.status);
      }
    } catch (error) {
      // Handle any errors
      console.error(error);
    }

  };

  const handleCategory = async () => {
    try {
        //   Send the GET request
          const response = await fetch('http://localhost:8765/api/v1.0/blogsite/blogs/info/'+ category); 
      
          if (response.ok) {
            // Handle the response data
            const jsonData = await response.json();
            setCategoryData(jsonData);
            console.log(categorydata);
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



  

  const handleDateRange = async () => {
    try {
        //   Send the GET request
          const response = await fetch('http://localhost:8765/api/v1.0/blogsite/blogs/get/category/'+ from + '/' + to); 
      
          if (response.ok) {
            // Handle the response data
            const jsonData = await response.json();
           
            setDaterange(jsonData);
            console.log(daterange);
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
  

  const handleLogout = async () => {
    navigate('/login');
  }





  return ( 
    <div >
        <h1 className={classes.h1}>Welcome, User :    {username}</h1>
        {/* <p>Your password: {password}</p> */}

        <div className={classes.container}>
        <Button type="submit" onClick={handleButtonClick}>Get all {username} blogs</Button>
        </div>
        
        {/* <p>{JSON.stringify(data)}</p> */}

        <ul className={classes.list}>
            {Array.from(blogsdata).map((item, index) => (
                <p key={index}>
                  blog: {index}
                    <li>blogname: {item.blogName}</li>
                    <li>category: {item.category}</li>
                    <li>article: {item.article}</li>
                    <li>authorName: {item.authorName}</li>
                    <li>date: {item.date}</li>
                </p>
            ))}
        </ul>





        <div className={classes.container}>
          <p>Search for other user blogs depending on the Category</p>
          <p>Enter the Category</p>
          <div className={classes.formgroup}>
              <form onSubmit={handleCategorySubmit}>
                <input
                id="id"
                type="text"
                placeholder="Category"
                value={category}
                onChange={(event) => setCategory(event.target.value)}
                />

                <div className={classes.buttoncontainer}>
                   <Button type="submit">Search</Button>
                </div>

              </form>
            
          </div>
        </div>

        <ul className={classes.list}>
            {Array.from(categorydata).map((item, index) => (
                <p key={index}>
                  blog: {index}
                    <li>blogname: {item.blogName}</li>
                    <li>category: {item.category}</li>
                    <li>article: {item.article}</li>
                    <li>authorName: {item.authorName}</li>
                    <li>date: {item.date}</li>
                </p>
            ))}
        </ul>


        <div className={classes.container}>
        <p>Search for other user blogs depending on Date Range</p>
        <p>Enter the Date Range</p>
            <form onSubmit={handleSubmit}>

              <div className={classes.formgroup}>
                <label className={classes.label} htmlFor="from">from</label>
                <input
                  id="id"
                  type="text"
                  placeholder="From"
                  value={from}
                  onChange={(event) => setFrom(event.target.value)}
                />
                </div>

               <div className={classes.formgroup}>
                <label className={classes.label} htmlFor="to">to</label>
                <input
                id="to"
                  type="text"
                  placeholder="To"
                  value={to}
                  onChange={(event) => setTo(event.target.value)}
                />
                </div>

                <div className={classes.buttoncontainer}>
                <Button type="submit">Enter Date Range</Button>
                </div>
              </form>
        </div>

        <ul className={classes.list}>
            {Array.from(daterange).map((item, index) => (
              
                <p key={index}>
                  blog: {index}
                    <li>blogname: {item.blogName}</li>
                    <li>category: {item.category}</li>
                    <li>article: {item.article}</li>
                    <li>authorName: {item.authorName}</li>
                    <li>date: {item.date}</li>
                </p>
            ))}
        </ul>


        <div className={classes.container}>
        <Button type="submit" onClick={handleLogout}>LogOut</Button>
        </div>

    </div>
  );
}

export default ReceiverComponent;
