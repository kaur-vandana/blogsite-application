import React from 'react';
import { render, screen } from '@testing-library/react';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import App from './App';
// import ReceiverComponent from './components/ReceiverComponent';
// import Register from './components/Register';
// import Login from './components/Login';

describe('App', () => {
  test('renders Register component when the path is "/"', () => {
    render(
      <App />
    );
    expect(screen.getByText('Register')).toBeInTheDocument();
  });


  test('renders Login component when the path is "/login"', () => {
    render(
        <App />
    );
    expect(screen.getByText('Login')).toBeInTheDocument();
  });


  // test('renders ReceiverComponent component when the path is "/blogs"', () => {
  //   render(
  //       <App />
  //   );
  //   expect(screen.getByText('ReceiverComponent')).toBeInTheDocument();
  // });
}


);
