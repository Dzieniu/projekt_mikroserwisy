/* eslint-disable eqeqeq */
import React, { Component } from 'react';
import axios from 'axios';
import './item.scss';
import Axios from 'axios';
import Calendar from 'react-calendar'
import ReactFileReader from 'react-file-reader';
import api from '../api/room.json'

function checkOption(e) {

  if (e.target.parentElement.style.opacity == '') {
    e.target.parentElement.style.opacity = '1'
  } else {
    e.target.parentElement.style.opacity = ''
  }

}

let commId = 0;

const open = (e) => {

  const urlRoom = document.getElementById('imgRoom')
  const nameRoom = document.getElementById('tittleRoom')
  const descRoom = document.getElementById('descRoom')


  for (let i = 0; i < api.length; i++) {

    if (e.target.textContent == api[i].name) {
      commId = i;
      urlRoom.src = api[i].url;
      nameRoom.textContent = api[i].name;
      descRoom.textContent = api[i].description;
      const genComm = api[commId].reviews.map((rev) => {
        return `<div class="opinion-comment"><div class="opinion-autor"> <h3>${rev.autor}</h3> <div class="item-ranking">
              <span class="item-star fa fa-star checked"></span>
              <span class="item-star fa fa-star checked"></span>
              <span class="item-star fa fa-star checked"></span>
              <span class="item-star fa fa-star checked"></span>
              <span class="item-star fa fa-star checked"></span>
              </div></div><p>${rev.content}</p></div></div>`
      }
      )

    }

  }
  let openItem = document.getElementsByClassName(e.target.id)
  openItem[0].style.display = 'block'
}

const openCrateDialog = (e) => {
  if (!isUserLogged)
    return;

  let openItem = document.getElementsByClassName('opinion-box')
  openItem[0].style.display = 'block'
}


function close() {
  const closeItem = document.getElementsByClassName('opinion-box')
  closeItem[0].style.display = 'none'
}



function isUserLogged() {
  var id = localStorage.getItem('id');
  if (id) {
    return true;
  }
  return false;
}


class select extends Component {
  constructor(props) {
    super(props);
    this.state = {
      date: new Date(),
      allRooms: [],
      selected: [],
      file: ""
    };
  }

  handleFiles = files => {

    this.setState({
      file: files
    })

  }

  GetRoomsURL = "http://localhost";
  componentDidMount() {

    Axios.get(this.GetRoomsURL + ':8080/reservations/getAllRooms').then(res => {
      this.setState({
        allRooms: res.data
      })
      // console.log(this.state.allRooms)
    }).catch(err => { console.error(err) });


    this.getRoomsData();
  }

  getRoomsData() {
    Axios.get(this.GetRoomsURL + ':8080/reservations/getfreerooms?date=' + this.state.date.toISOString().slice(0, 10)).then(res => {
      this.setState({
        selected: res.data
      })
      // console.log(this.state.allRooms)
    }).catch(err => { console.error(err) });
  }

  // onChange = data => this.setState({date: data});
  onChange = date => this.refreschByDate(date);

  refreschByDate(date) {

    this.setState({ date });
    this.getRoomsData();
  }

  render() {
    return (
      <div>
        <div className="app-data">

          <Calendar
            onChange={this.onChange}

            value={this.state.date}
          />



        </div>

        <div onClick={openCrateDialog} id="openBtn" className="nav-form-item close-btn btn ">Dodaj pokój</div>

        {this.genRooms(this.state.allRooms, this.state.selected)}


        <div className="opinion-box">
          <div className="opinion-content">
            <button onClick={close} id="opinion-box" className="opinion-close"></button>
            <div className="input-room">
              <input id="name" className="nav-form-item nav--input" placeholder="nazwa" />
              <input id="price" className="nav-form-item nav--input" type="number" placeholder="cena" />
              {/* <input type="file" id="avatar" name="avatar" accept="image/png, image/jpeg" className="nav-form-item" /> */}
              <ReactFileReader handleFiles={this.handleFiles} className="nav-form-item btn image-btn" base64={true}>
                <button className='nav-form-item btn image-btn'>Wybierz plik</button>
              </ReactFileReader>
              <div onClick={() => this.createRoom()} id="exit-btn" className="nav-form-item close-btn btn ">Zapisz</div>

            </div>
          </div>
        </div>
      </div>
    )
  }


  genRooms(rooms, selected) {
    var filtered = rooms.filter((f) => selected.includes(f.id));

    return filtered.map((room) =>

      <div id={room.id} className="box-item">
        <img className="item-img" src={"http://localhost:38080/CommentAndImageStoreApp-0.1/resources/image/room/" + room.id} alt="room"></img>
        <div className="item-box">
          <div className="item-boxText">
            <h3 id="opinion-box" className="item-name">{room.description}</h3>
            <div className="item-ranking">
              <span className="item-star fa fa-star checked"></span>
              <span className="item-star fa fa-star checked"></span>
              <span className="item-star fa fa-star checked"></span>
              <span className="item-star fa fa-star checked"></span>
              <span className="item-star fa fa-star checked"></span>
              {/* <p className="ranking-text"> {room.reviews.length} Opinie </p> */}
            </div>
            <p className="item-p">Status:<span className="available">{'wolny'}</span></p>
            <p className="item-p">Cena:<span>{room.reservationPrize + ' zł za dzień'}</span></p>
          </div>
          <div className="item-option">
            {/* <div className="option-box">
            <div className="item-checkbox">
              <input onClick={checkOption} type="checkbox"></input> <p >Sniadanie od  </p><p><span className="item-checkbox--red"> {room.breakfastPrice}</span> / Noc</p>
            </div>
          </div>*/
              <div className="item-selectBox">
                {/* <p>Pokoje</p><input className="selectBox-input" type="number" min="1" max="6" placeholder="1"></input> */}
                <div id={room.id} onClick={() => this.selectR(room)} className="selectBox-btn btn"> <p id={room.id} className="btn-text">Zarezerwuj</p> </div>
              </div>}
          </div>
        </div>
      </div>
    )
  }

  selectR(room) {
    if (!isUserLogged()) {
      return;
    }

    var msg = {
      RoomID: room.id,
      UserId: parseInt(localStorage.getItem("id")),
      ReservationDate: this.state.date.toISOString().slice(0, 10)
    };

    Axios.post(this.GetRoomsURL + ':8080/reservations/addreservation', msg).then((res) => { this.getRoomsData(); })
      .catch((e) => {
        console.log(e);
      })


  }

  createRoom() {
    console.log(this.state);
    if (!isUserLogged()) {
      return;
    }

    var msg = {
      Description: document.getElementById("name").value,
      ReservationPrize: parseInt(document.getElementById("price").value)
    }



    Axios.post(this.GetRoomsURL + ':8080/reservations/addroom', msg).then((res) => {
      var imageMsg = {
        image: this.state.file.base64,
        roomId: res.data.id,
        imageName: 'plik.jpg'
      }
      console.log(imageMsg)
      Axios.post('http://localhost:8080/CommentAndImageStoreApp-0.1/resources/image/save', imageMsg).then((res2) => {

        this.getRoomsData();
        close();
      })
        .catch((e) => {
          console.log(e);
        })

        Axios.get(this.GetRoomsURL + ':8080/reservations/getAllRooms').then(res => {
          this.setState({
            allRooms: res.data
          })
          this.getRoomsData();
          close();
          // console.log(this.state.allRooms)
        }).catch(err => { console.error(err) });
    })
      .catch((e) => {
        console.log(e);
      })


  }

}
export default select;