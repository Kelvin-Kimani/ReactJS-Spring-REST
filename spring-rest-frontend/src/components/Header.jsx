import React, { Component } from 'react'

export default class Header extends Component {
    render() {
        return (
            <div>
                <header>    
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark py-4">
                        <div>
                            <a href="#" className="navbar-brand mx-4">User Management App</a>
                        </div>
                    </nav>
                </header>
                
            </div>
        )
    }
}
