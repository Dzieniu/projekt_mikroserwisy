import React, { Component } from 'react';
import './App.scss';

import Navi from './nav/nav.js'
import Footer from './footer/footer.js'
import Item from './item/item.js'
import Booking from './booking/booking.js'

class App extends Component {

  render() {
    localStorage.removeItem("id");
    return (
      <div className="App">
        <Navi />
        <div>
          {/* <h2 className="app-resTittle">Rezewacje</h2>
              <h3 className="app-MakeRes">Zrób Rezewacje</h3> */}
          {/* <Data monthIn='' monthOut='' /> */}
          <h3 className="app-roomsTittle">Pokoje dostępne dla ciebie</h3>
          <div className="app-box">
            <Item />

            
            {/* <p className="app-roomsTittle">Potwierdz Rezewacje</p>
            <Confirmbox/> */}
          </div>
        </div>
        <Footer />
      </div>
    );
  }
}

export default App;
