using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;

namespace Reservation.DAL.DAO
{
    public class RoomDto
    {
        [Key]
        public int Id { get; set; }
        public String Description { get; set; }

        public int ReservationPrize { get; set; }

        public virtual ICollection<ReservationDto> Reservation { get; set; }
     


      
    }
}