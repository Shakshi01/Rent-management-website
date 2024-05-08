// src/components/auth/LogIn.jsx
import React, { useState } from "react";
import { useHistory } from "react-router-dom";
import { useAuth } from "../../context/AuthContext";
import "./contact.css";
import axios from 'axios'; // Move axios import here for better organization

const LogIn = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const history = useHistory();
  const { login } = useAuth();

  const handleLogin = async (event) => {
    event.preventDefault();
    // Attempt to log in via the server first
    try {
      const response = await axios.get(`http://ec2-54-144-121-176.compute-1.amazonaws.com:8000/server/landlordController/login?uname=${email}&pswd=${password}`);
      console.log(response.data);
      if (response.data) { // Check if login was successful based on response
        await login(); // Assuming login updates some context or state
        history.push('/dashboard'); // Redirect to dashboard after successful login
      }
    } catch (error) {
      console.error(error);
      // Handle failed login, possibly set error messages etc.
    }
  };

  return (
    <section className='contact mb'>
      <div className='container'>
        <form onSubmit={handleLogin}>
          <input
            type="text"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            placeholder="Username" 
            id="uname"
          />
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="Enter your password" 
            id="password"
          />
          <button type="submit">Log In</button>
        </form>
      </div>
    </section>
  );
};

export default LogIn;




// const Contact = () => {
//   return (
//     <>
//       <section className='contact mb'>
//         {/* <Back name='Contact Us' title='Get Helps & Friendly Support' cover={img} /> */}
//         <div className='container'>
//           <form className='shadow'>
          
//           <h4>Login</h4><br />
//             <div>
//               <input type='text' placeholder='Email' />
//             </div>
//             <input type='password' placeholder='Enter your password' />
//             <button>Login</button>
            
//           </form>
//         </div>
//       </section>
//     </>
//   )
// }
