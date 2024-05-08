import React from "react"
import img from "../images/pricing.jpg"
import Back from "../common/Back"
import "./contact.css"
import { Link } from "react-router-dom"

const Contact = () => {

             var data = JSON.stringify({
                  "uname": document.getElementById("uname"),
                  "password": document.getElementById("password"),
                  "name": {
                      "fname": document.getElementById("fname"),
                      "lname": document.getElementById("lname"),
                    },
                  "email": document.getElementById("email"),
                  "pnumber": document.getElementById("pnum"),
              });

              var xhr = new XMLHttpRequest();
              xhr.withCredentials = true;

              xhr.addEventListener("readystatechange", function() {
              if(this.readyState === 4) {
              console.log(this.responseText);
              }
              });

              xhr.open("POST", "http://ec2-54-144-121-176.compute-1.amazonaws.com:8000/server/landlordController/createLandlord");
              xhr.setRequestHeader("Content-Type", "application/json");

              xhr.send(data);

  return (
    <>
      <section className='contact mb'>
        {/* <Back name='Contact Us' title='Get Helps & Friendly Support' cover={img} /> */}
        <div className='container'>
          <form className='shadow'>
          <Link to='/login'>
          <button>LogIn</button>
          </Link>
          <h4>SignUp</h4><br />
            <div>
              <input type='text' id="fname" placeholder='First Name'/>
            </div>
            <div>
              <input type='text' id="lname" placeholder='Last Name' />
            </div>
            <div>
              <input type='text' id="pnum" placeholder='Phone Number' />
            </div>
            <div>
              <input type='text' id="uname" placeholder='Username' />
            </div>
            <div>
              <input type='text' id="email" placeholder='Email' />
            </div>
            <input type='password' id="password" placeholder='Enter your password' />
            <button>Submit Request</button>
          </form>
        </div>
      </section>
    </>
  )
}

export default Contact
