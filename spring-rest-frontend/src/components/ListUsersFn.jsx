import React, {useEffect} from 'react'
import {Row, Table, Button, Container} from 'react-bootstrap'
import UsersService from '../services/UsersService'


const ListUsersFn = () => {

    const [users, setUsers] = React.useState([]);

    useEffect(()=> {
        
        UsersService.getUser().then(res =>{
            setUsers(res.data)
        });

    }, []);


    console.log(users);


    return (
        <Container>
                <div className="my-4 d-flex justify-content-between">
                    <h2 className="text-center">Users List</h2>
                    <Button variant="primary">Add User</Button>
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
                            {users.length > 0 && (
                                users.map((user) => (
                                    <tr key = {user.userId}>
                                        <td>{user.firstName}</td>
                                        <td>{user.lastName}</td>
                                        <td>{user.emailAddress}</td>
                                    </tr>
                                )))}

                        </tbody>
                        
                    </Table>
                </Row>

            </Container>
    )
}

export default ListUsersFn
