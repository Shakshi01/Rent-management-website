import React from "react";
import { BrowserRouter as Router, Switch, Route, useLocation } from "react-router-dom";
import Header from "../common/header/Header";
import Header1 from "../common/header/Header1";
import Home from "../home/Home";
import Home1 from "../home/Dashboard";
import Team from "../home/team/Team"; 
import Footer from "../common/footer/Footer";
import About from "../about/About";
import Pricing from "../pricing/Pricing";
import Blog from "../blog/Blog";
import Services from "../services/Services";
import Contact from "../contact/Contact";
import SignUp from "../auth/SignUp";
import LogIn from "../auth/LogIn";

const AppHeader = () => {
  const location = useLocation();
  console.log(location.pathname); // This will log the current path
  if (location.pathname.startsWith('/dashboard')) {
    return <Header1 />;
  }
  return <Header />;
};

const Pages = () => {
  return (
    <Router>
      <AppHeader />
      <Switch>
        <Route exact path='/' component={Home} />
        <Route exact path='/about' component={About} />
        <Route exact path='/services' component={Services} />
        <Route exact path='/signup' component={SignUp} />
        <Route exact path='/login' component={LogIn} />

        {/* Nested routes under /dashboard */}
        <Route exact path='/dashboard' component={Home1} />
        <Route exact path='/dashboard/blog' component={Blog} />
        <Route exact path='/dashboard/contact' component={Contact} />
        <Route exact path='/dashboard/pricing' component={Pricing} />
        <Route exact path='/dashboard/team' component={Team} />
      </Switch>
      <Footer />
    </Router>
  );
};

export default Pages;
