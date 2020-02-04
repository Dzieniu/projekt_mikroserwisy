using Microsoft.EntityFrameworkCore;
using Reservation.DAL.DAO;


namespace Reservation.DAL
{
    public class ReservationContext: DbContext
    {
    
        public ReservationContext(DbContextOptions<ReservationContext> options) : base(options)
        { }
       
        public virtual DbSet<RoomDto> Rooms { get; set; }
        public virtual DbSet<ReservationDto> Reservations { get; set; }
    }
}
