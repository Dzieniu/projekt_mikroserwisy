import React, { Component } from 'react';
import './comfirmbox.scss'

const comfirm = () => {

    const confirmGrid = document.querySelector('.confirmBox-grid')
    const confirmBox = document.querySelectorAll('.app-confirmBox')
    const nick = document.getElementById('nick')
    const name = document.getElementById('Name')
    const nameIn = document.getElementById('nameInput')
    const lastnameIn = document.getElementById('lastnameInput')

    if (confirmGrid.style.display === 'none') {
        confirmBox[0].style.display = 'none'
        confirmBox[1].classList.remove('hidden')
        name.textContent = nick.textContent
    } else if (nameIn.value !== '' && lastnameIn.value !== '') {
        confirmBox[0].style.display = 'none'
        confirmBox[1].classList.remove('hidden')
        name.textContent = nameIn.value + '  ' + lastnameIn.value
    } else {
        nameIn.style.border = '1px solid red'
        lastnameIn.style.border = '1px solid red'
        nameIn.style.color = 'red'
        lastnameIn.style.color = 'red'
    }
}

class comfirmbox extends Component {
    render() {
        return (
            <div>
                <div className="app-confirmBox">
                    <div className="confirmBox-grid">
                        <input id='nameInput' type="text" placeholder="Imie"></input>
                        <input id='lastnameInput' type="text" placeholder="Nazwisko"></input>
                        <input type="text" placeholder="Email"></input>
                        <input type="text" placeholder="Numer telefonu"></input>
                    </div>
                    <textarea className="confirmBox-notes" placeholder="Uwagi"></textarea>
                    <div onClick={comfirm} className="app-confirm btn"><p>POTWIERDZ</p></div>
                </div>
                <div className="app-confirmBox hidden">
                    <h3 className="confirmBox-confirmText">Rezerwacja zostało pomyślnie zrealizowana na dane:</h3>
                    <h3 id='Name'>m</h3>
                </div>
            </div>
        )
    }
}
export default comfirmbox;