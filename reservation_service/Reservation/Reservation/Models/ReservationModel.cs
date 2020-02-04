using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace Reservations.Models
{
    public class ReservationModel
    {
        public int ID { get; set; }
        public int UserId { get; set; }
       
        public int RoomID { get; set; }

        
        public DateTime ReservationDate { get; set; }
    }
}