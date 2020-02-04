using System;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;


namespace Reservation.DAL.DAO
{
    public class ReservationDto
    {
        [Key]
        public int ID { get; set; }
        public int UserId { get; set; }
        public virtual RoomDto Room { get; set; }
        public int RoomId { get; set; }

        [Column(TypeName = "Date")]
        public DateTime ReservationDate { get; set; }

    }
}