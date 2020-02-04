import React, { Component } from 'react';
import './booking.scss';


class booking extends Component {
  render() {
    return (
      <div className="booking-box">
        <div className="app-booking">
          <div className="booking-date"></div>
        </div>
        <div id="selectedBox">

        </div>
        {/* <div className="booking-item">
            <img className="item-img" src="https://q-cf.bstatic.com/images/hotel/max1024x768/731/73118462.jpg" alt="room"></img>
              <div className="item-box">
                 
                <div className="item-boxText">
                    <div className="item-inboxText--booking">
                      <h3 className="item-name item-name--booking">Klasyczny pokój</h3>
                      <div className="item-ranking item-ranking--booking">
                        <span className="item-star fa fa-star checked"></span>
                        <span className="item-star fa fa-star checked"></span>
                        <span className="item-star fa fa-star checked"></span>
                        <span className="item-star fa fa-star checked"></span>
                        <span className="item-star fa fa-star checked"></span>
                        <p className="ranking-text"> 3 Recenzje </p>
                      </div>
                    </div>

                  <div className="booking-grid">  
                    <p className="item-p">Status:<span className="available">Dostępny</span></p>
                    <p className="item-p">Zaliczka:<span>Nie wymagana</span></p>
                    <p className="item-p">Łózka:<span>2</span></p>
                    <p className="item-p">Miejsca:<span>3</span></p>
                  </div>
                </div>
                  <div className="booking-price"><p>Cena:</p><p className="price">$200</p></div>

            </div>
          </div> */}



      </div>
    )
  }
}
export default booking;