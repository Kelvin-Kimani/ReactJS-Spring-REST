import logo from './logo.svg';
import './App.css';
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom'
import ListUsers from './components/ListUsers';
import ListUsersFn from './components/ListUsersFn';
import Footer from './components/Footer';
import Header from './components/Header';
import CreateUser from './components/CreateUser';
import UpdateUser from './components/UpdateUser';
import ViewUser from './components/ViewUser';

function App() {
  return (
    <main>
      <Router>
        
        <Header/>

          <Switch>

              
              <Route path="/users" component={ListUsers}></Route>
              <Route path="/create_user/:userId" component={CreateUser}></Route>
              <Route path="/view_user/:userId" component={ViewUser}></Route>
              

          </Switch>

        <Footer/>
      
      </Router>
    </main>
  );
}

export default App;
