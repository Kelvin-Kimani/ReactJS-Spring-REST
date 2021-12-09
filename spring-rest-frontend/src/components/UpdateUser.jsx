import React, { Component } from 'react'
import { Link } from 'react-router-dom'
import UsersService from '../services/UsersService';


export default class UpdateUser extends Component {
    constructor(props){
        super(props)

        this.state = {
            userId : this.props.match.params.userId,
            firstName : '',
            lastName : '',
            emailAddress : ''
        }

        this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.changeEmailAddressHandler = this.changeEmailAddressHandler.bind(this);
        this.updateUser = this.updateUser.bind(this);

    }


    componentDidMount(){
        UsersService.getUserById(this.state.userId).then( (res) => {
            let user = res.data;
            this.setState({firstName : user.firstName,
                            lastName : user.lastName,
                            emailAddress : user.emailAddress});
        });
    }


    updateUser = (e) =>{
        
        e.preventDefault();
        let user = {firstName : this.state.firstName,
                    lastName : this.state.lastName,
                    emailAddress : this.state.emailAddress};
        console.log('User => ' + JSON.stringify(user));


        UsersService.updateUser(user, this.state.userId).then( res =>{
            this.props.history.push('/users');
        })

    }
    changeFirstNameHandler = (event) =>{
        this.setState({firstName : event.target.value});
    }


    changeLastNameHandler = (event) =>{
        this.setState({lastName : event.target.value});
    }


    changeEmailAddressHandler = (event) =>{
        this.setState({emailAddress : event.target.value});
    }

    
    render() {

        return (
            <div>
                <div className="container">
                    <div className="card col-md-6 offset-md-3 offset-md-3 my-4">
                        <h2 className="text-center my-4">Update User Form</h2>
                        <div className="card-body">
                            <form action="">

                                <div className="form-group my-3">
                                    <label>First Name</label>
                                    <input placeholder="First Name" name ="firstName" className="form-control"
                                        value={this.state.firstName} onChange={this.changeFirstNameHandler}/>
                                </div>

                                <div className="form-group my-3">
                                    <label>Last Name</label>
                                    <input placeholder="Last Name" name ="lastName" className="form-control"
                                        value={this.state.lastName} onChange={this.changeLastNameHandler}/>
                                </div>

                                <div className="form-group my-3">
                                    <label>Email Address</label>
                                    <input placeholder="Email Name" name ="emailAddress" className="form-control"
                                        value={this.state.emailAddress} onChange={this.changeEmailAddressHandler}/>
                                </div>

                                <div className="d-flex justify-content-between">

                                    <button className="btn btn-success" onClick={this.updateUser}>Save</button>
                                
                                    <Link to="/users">
                                        <button className="btn btn-primary">Cancel</button>
                                    </Link>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}
