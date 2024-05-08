import React from 'react';
import { BrowserRouter as Router } from 'react-router-dom'; // Correctly import BrowserRouter
import { AuthProvider } from './context/AuthContext'; // Ensure this path is correct
import Pages from './components/pages/Pages';
import "./App.css";
// const express = require('express');
// const cors = require('cors');
// const bodyParser = require('body-parser');
// const app = express();

// app.use(cors());


function App() {
  return (
    <Router>
      <AuthProvider>
        <Pages />
      </AuthProvider>
    </Router>
  );
}

export default App;
