import React, { Component } from 'react'
import { Link } from 'react-router-dom';
import UsersService from '../services/UsersService';


export default class ViewUser extends Component {
    constructor(props){
        super(props)

        this.state = {
            userId : this.props.match.params.userId,
            user: {}
        }
    }


    componentDidMount(){
        
        UsersService.getUserById(this.state.userId).then( (res) => {
            this.setState({user: res.data});
        });
    
    }


    render() {
        return (
            <div className ="container">
                <div className="card mt-4">
                    <h3 className = "text-center fw-bold py-4">User Details</h3>
                    <div className="card-body">
                        <div className="row">
                            <div className="col-4">
                                FirstName:
                                <p className="text-muted">{this.state.user.firstName}</p>
                            </div>
                            <div className="col-4">
                                LastName: 
                                <p className="text-muted">{this.state.user.lastName}</p>
                            </div>
                            <div className="col-4">
                                Email Address: 
                                <p className="text-muted">{this.state.user.emailAddress}</p>
                            </div>
                        </div>
                        <Link to='/users'>
                            <button className="btn btn-primary">Back</button>
                        </Link>
                    </div>
                </div>
            </div>
        )
    }
}
