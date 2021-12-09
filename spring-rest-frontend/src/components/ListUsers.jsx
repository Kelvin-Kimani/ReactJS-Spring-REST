import React, { Component } from 'react'
import {Row, Table, Container, Button} from 'react-bootstrap'
import { Link } from 'react-router-dom'
import UsersService from '../services/UsersService'

export default class ListUsers extends Component {

    constructor(props){
        super(props)

        this.state = {
            users : []
        }

        this.editUser = this.editUser.bind(this);
        this.deleteUser = this.deleteUser.bind(this);
        this.viewUser = this.viewUser.bind(this);
    }

    editUser(userId){
        this.props.history.push(`/create_user/${userId}`);
    }

    deleteUser(userId){
        UsersService.deleteUser(userId).then( (res) =>{
            this.setState({users: this.state.users.filter(user => user.userId !== userId)});
        });
    }

    viewUser(userId){
        this.props.history.push(`/view_user/${userId}`);
    }
    
    componentDidMount(){
        UsersService.getUser().then((res) =>{
            this.setState({users : res.data})
        });
    }

    render() {
        return (
            <Container>
                <div className="my-4 d-flex justify-content-between">
                    <h2 className="text-center">Users List</h2>
                    <Link to="/create_user/-1">
                        <Button variant="primary">Add User</Button>
                    </Link>
                </div>
                
                <Row>

                    <Table striped bordered hover>
                        
                        <thead>
                            <tr>
                                <th>First Name</th>
                                <th>Last Name</th>
                                <th>Email Address</th>
                                <th>Actions</th>
                            </tr>
                            
                        </thead>

                        <tbody>
                            
                            {this.state.users.map(
                                user => 
                                <tr key = {user.userId}>
                                    <td>{user.firstName}</td>
                                    <td>{user.lastName}</td>
                                    <td>{user.emailAddress}</td>
                                    <td>
                                        <div className= "d-flex justify-content-between">

                                            <button className="btn btn-info" onClick={ () => this.editUser(user.userId)}>Edit</button>
                                            <button className="btn btn-danger" onClick={ () => this.deleteUser(user.userId)}>Delete</button>
                                            <button className="btn btn-secondary" onClick={ () => this.viewUser(user.userId)}>View</button>

                                        </div>
                                    </td>
                                </tr>
                            )}

                        </tbody>
                        
                    </Table>
                </Row>

            </Container>
        )
    }
}
