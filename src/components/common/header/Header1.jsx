import React, { useState } from "react";
import "./header.css"; // Ensures CSS is shared between Header and Header1
import { Link } from "react-router-dom";
import { useAuth } from '../../../context/AuthContext'; // Correct relative path if needed

const Header1 = () => {
  const [navList, setNavList] = useState(false);
  const { logout } = useAuth(); // Use the logout function from the auth context

  return (
    <header>
      <div className='container flex'>
        <div className='logo'>
          <img src='./images/logo.png' alt='Logo' />
        </div>
        <div className='nav'>
          <ul className={navList ? "small" : "flex"}>
            <li><Link to='/dashboard'>Dashboard</Link></li>
            <li><Link to='/dashboard/team'>Team</Link></li>
            <li><Link to='/dashboard/blog'>Properties</Link></li>
            <li><Link to='/dashboard/contact'>Contact</Link></li>
            {/* <li><Link to='/dashboard/pricing'>Pricing</Link></li> */}

          </ul>
        </div>
        <div className='button flex'>
        <Link to='/'>
          <button onClick={logout} className='btn1'>
            <i className='fa fa-sign-out'></i> Logout
          </button>
          </Link>
        </div>
        <div className='toggle'>
          <button onClick={() => setNavList(!navList)}>
            {navList ? <i className='fa fa-times'></i> : <i className='fa fa-bars'></i>}
          </button>
        </div>
      </div>
    </header>
  );
};

export default Header1;
