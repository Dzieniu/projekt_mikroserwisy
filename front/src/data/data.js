/* eslint-disable eqeqeq */
import React, { Component } from 'react';
import './data.scss'
import api from '../api/room.json'
import Axios from 'axios';


const sortRoom = () => {

  const room = document.getElementsByClassName('box-item')
  const dayIn = document.getElementById('dayIn')
  const monthIn = document.getElementById('monthIn')
  const dataIn = document.getElementById('dataIn')


  let monthInValue;
  if (monthIn.value == 'STY') { monthInValue = 1 }
  if (monthIn.value == 'LUTY') { monthInValue = 2 }
  if (monthIn.value == 'MAR') { monthInValue = 3 }
  if (monthIn.value == 'KWI') { monthInValue = 4 }
  if (monthIn.value == 'MAJ') { monthInValue = 5 }
  if (monthIn.value == 'CZE') { monthInValue = 6 }
  if (monthIn.value == 'LIP') { monthInValue = 7 }
  if (monthIn.value == 'SIE') { monthInValue = 8 }
  if (monthIn.value == 'WRZ') { monthInValue = 9 }
  if (monthIn.value == 'PAŻ') { monthInValue = 10 }
  if (monthIn.value == 'LIS') { monthInValue = 11 }
  if (monthIn.value == 'GRU') { monthInValue = 12 }


  // for(let i = 0;i < room.length;i++){

  //   if (api[i].dataInMonth <= monthInValue && monthInValue <= api[i].dataOutMonth) {
  //     room[i].style.display = ""
  //   }else{
  //     room[i].style.display = "none"
  //   }

  // }

  Axios.get('http://localhost:8080/reservations/getallrooms').then(res => {
    const persons = res.data;
    this.setState({ persons });
  })

  dataIn.textContent = dayIn.value + '/' + monthInValue + '/' + 2020
}

class data extends Component {


  render() {
    return (
      <div className="app-data">
        <div className="data-item">
          <p>ZAMEDLOWANIE</p>
          <select id="dayIn" className="data-select" name="check-in">
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
            <option>5</option>
            <option>6</option>
            <option>7</option>
            <option>8</option>
            <option>9</option>
            <option>10</option>
            <option>11</option>
            <option>12</option>
            <option>14</option>
            <option>15</option>
            <option>16</option>
            <option>17</option>
            <option>18</option>
            <option>19</option>
            <option>20</option>
            <option>21</option>
            <option>22</option>
            <option>23</option>
            <option>24</option>
            <option>25</option>
            <option>26</option>
            <option>27</option>
            <option>28</option>
            <option>29</option>
            <option>30</option>
            <option>31</option>
          </select>
          <span>/</span>
          <select id="monthIn" className="data-select--month" name="check in month">
            <option>STY</option>
            <option>LUTY</option>
            <option>MAR</option>
            <option>KWI</option>
            <option>MAJ</option>
            <option>CZE</option>
            <option>LIP</option>
            <option>SIE</option>
            <option>WRZ</option>
            <option>PAŻ</option>
            <option>LIS</option>
            <option>GRU</option>
          </select>
        </div>


        <div className="app-check">
          <div onClick={sortRoom} className="check-btn btn"><p>SPRAWDZ POKOJE</p></div>
        </div>
      </div>
    )
  }
}
export default data;