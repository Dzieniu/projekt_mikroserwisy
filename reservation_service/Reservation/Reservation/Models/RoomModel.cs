using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Reservations.Models
{
    public class RoomModel
    {
        public int? Id { get; set; }
        public String Description { get; set; }

        public int ReservationPrize { get; set; }
        
        public List<DateTime> Reservations { get; set; }
    }
}