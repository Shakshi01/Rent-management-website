import React from "react"
import Back from "../common/Back"
import Heading from "../common/Heading"
import img from "../images/about.jpg"
import "./about.css"

const About = () => {
  return (
    <>
      <section className='about'>
        <Back name='About Us' title='About Us - Who We Are?' cover={img} />
        <div className='container flex mtop'>
          <div className='left row'>
            <Heading title='Our Story' subtitle='Check out our company story and work process' />

            <p>At SimpliLease, we make managing rental properties easy and efficient. Our platform is designed with both landlords and tenants in mind, providing a streamlined solution that cuts down the complexity of managing leases.</p>
            <p>Whether you're a landlord looking to simplify the day-to-day tasks of property management or a tenant seeking a hassle-free renting experience, SimpliLease brings everything you need into one easy-to-use interface. Our system enhances communication and handles all aspects of rental management smoothly, ensuring that everyone can stay on top of their responsibilities effortlessly.</p>
            <button className='btn2'>More About Us</button>
          </div>
          <div className='right row'>
            <img src='./immio.jpg' alt='' />
          </div>
        </div>
      </section>
    </>
  )
}

export default About
