import axios from 'axios'

const USER_API_URL = "http://localhost:8080/api/v1/users";
const CREATE_USER_API_URL = "http://localhost:8080/api/v1/create_user";

class UsersService{

    getUser(){
       return axios.get(USER_API_URL);
    }

    createUser(user){
        return axios.post(CREATE_USER_API_URL, user)
    }

    getUserById(userId){
        return axios.get(USER_API_URL + '/' + userId);
    }

    updateUser(user, userId){
        return axios.put(USER_API_URL + '/' + userId, user);
    }

    deleteUser(userId){
        return axios.delete(USER_API_URL + '/' + userId);
    }
}

export default new UsersService()