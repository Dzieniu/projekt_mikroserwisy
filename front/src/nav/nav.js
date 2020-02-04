/* eslint-disable eqeqeq */
import React, { Component } from 'react';
import './nav.scss';
import Axios from 'axios';
import account from './img/account.png'
import logo from './img/logo.png'
import apiUser from '../api/user.json'

let checkLogin = false;
let name = null;

const collapse = () => {
    if (checkLogin === false) {
        if (document.getElementById('signIn').style.display !== 'flex') {
            document.getElementById('signIn').style.display = "flex"
        } else if (document.getElementById('signIn').style.display === 'flex') {
            document.getElementById('signIn').style.display = "none"
        }
    } else if (checkLogin === true) {
        if (document.getElementById('account').style.display !== 'flex') {
            document.getElementById('account').style.display = "flex"
        } else if (document.getElementById('account').style.display === 'flex') {
            document.getElementById('account').style.display = "none"
        }
    }
}

const signIn = () => {

    const confimBox = document.querySelector('.confirmBox-grid')
    const confimNotes = document.querySelector('.confirmBox-notes')

    var msg = {
        username: document.getElementById('email').value.trim(),
        password: document.getElementById('password').value
    }

    Axios.post("http://localhost:8080/authenticate", msg)
        .then((res) => {
            Axios.get("http://localhost:8080/authenticate", { headers: { "Authorization": 'Bearer ' + res.data.token }}).then((userData) => {
                checkLogin = true;
                localStorage.setItem('id', userData.data.id);
                collapse()
                document.getElementById('signIn').style.display = "none"
                document.getElementById('nick').textContent = document.getElementById('email').value.trim()
                confimBox.style.display = 'none'
                confimNotes.style.display = 'none'
                
            }).catch((e) => {
                console.log(e);
                document.getElementById('email').style.borderBottom = "1px solid red"
                document.getElementById('email').style.color = "red"
                document.getElementById('password').style.borderBottom = "1px solid red"
                document.getElementById('password').style.color = "red"
            })


        })
        .catch((res) => {
            document.getElementById('email').style.borderBottom = "1px solid red"
            document.getElementById('email').style.color = "red"
            document.getElementById('password').style.borderBottom = "1px solid red"
            document.getElementById('password').style.color = "red"
        });


}

const log = () => {
    checkLogin = false;
    name = null;
    document.getElementById('account').style.display = "none"
    localStorage.removeItem("id");
}


class Nav extends Component {

    render() {

        return (
            <nav>
                <div className="nav">
                    <div className="nav-logo">
                        <h2> Hotels </h2>
                    </div>
                    <div onClick={collapse} className="nav-signIn btn">
                        <p>ZALOGUJ SIĘ</p>
                    </div>
                    <div id="signIn" className="nav-signInBox">
                        <img className="nav-icon" src={account} alt="account icon"></img>
                        <input id="email" className="nav-form-item nav--input" placeholder="login" />
                        <input id="password" className="nav-form-item nav--input" type="password" placeholder="hasło" />
                        <div onClick={signIn} className="nav-form-item signIn-btn btn ">Zaloguj się</div>
                        <p>Nie masz jeszcze konta? Zarejestruj się</p>
                    </div>
                    <div id="account" className="nav-account">
                        <div className="account-person">
                            <img className="account-logo" src={logo} alt="logo"></img>
                            <div className="person-box">
                                <h3 id='nick'>{name}</h3>
                            </div>
                        </div>
                        <div onClick={log} className="account-logout btn">Wyloguj się</div>
                    </div>
                </div>
            </nav>
        )
    }
}


export default Nav;
