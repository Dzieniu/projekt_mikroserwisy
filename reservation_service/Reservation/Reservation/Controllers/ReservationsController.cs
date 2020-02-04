using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Reservation.DAL;
using Reservation.DAL.DAO;
using Reservations.Models;

namespace Reservation.Controllers
{
    [Route("[controller]/[action]")]
    [ApiController]
    public class ReservationsController : ControllerBase
    {
        private readonly ReservationContext _db;
        public ReservationsController(ReservationContext db)
        {
            _db = db;
        }

        [HttpGet]
            public IActionResult test()
        {
            return Ok("ok");
        }
           

        [HttpGet]

        public IActionResult GetAllRooms()
        {
            try
            {
                var query = _db.Rooms.ToList();
                List<RoomModel> rooms = new List<RoomModel>();
                foreach (var item in query)
                {
                    rooms.Add(new RoomModel
                    {
                        Id = item.Id,
                        Description = item.Description,
                        ReservationPrize = item.ReservationPrize

                    });

                }
                return Ok(rooms);
            }
            catch (Exception ex)
            {
                return BadRequest(ex);
            }
        }

        [HttpGet]
        public IActionResult GetFreeRooms(DateTime date)
        {

            try
            {
                var query = _db.Reservations.Where(x => x.ReservationDate == date);
                var rooms = query.Select(x => x.Room.Id).ToList();
                List<int> freerooms = _db.Rooms.Select(x => x.Id).ToList();
                foreach (var item in rooms)
                {
                    freerooms.Remove(item);
                }
                return Ok(freerooms);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }



        }

        [HttpGet]
        public IActionResult GetUserReservations(int id)
        {

            try
            {
                List<ReservationModel> list = new List<ReservationModel>();
                var query = _db.Reservations.Where(x => x.UserId == id);
                foreach (var item in query)
                {
                    list.Add(new ReservationModel
                    {
                        ID = item.ID,
                        RoomID = item.Room.Id,
                        UserId = item.UserId,
                        ReservationDate = item.ReservationDate
                    });

                };
                return Ok(list);
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }

        }

        [HttpGet]


        public IActionResult GetRoom(int id)
        {
            try
            {
                var query = _db.Rooms.Find(id);

                if (query != null)
                {
                    RoomModel room = new RoomModel
                    {
                        Id = query.Id,
                        Description = query.Description,
                        ReservationPrize = query.ReservationPrize,
                        Reservations = query.Reservation.Select(item => item.ReservationDate).ToList()


                    };

                    //foreach (var item in query1)
                    //{
                    //    room.Reservations.Add(item.ReservationDate);
                    //        }
                    return Ok(room);
                }
                else
                    return NotFound();
            }catch (Exception ex)
            {
                return BadRequest(ex);
            }
        }

        [HttpGet]

        public IActionResult GetAllReservations()
        {
            
                try
                {
                    var query = _db.Reservations.ToList();
                    if (query != null)
                    {
                        List<ReservationModel> reservations = new List<ReservationModel>();
                        foreach (var item in query)
                        {
                            reservations.Add(new ReservationModel
                            {
                                ID = item.ID,
                                RoomID = item.RoomId,
                                UserId = item.UserId,
                                ReservationDate = item.ReservationDate
                            });

                        };
                        return Ok(reservations);
                    }
                    else
                        return NotFound();
                }
                catch (Exception ex)
                {
                    return BadRequest(ex.Message);
                }
            }

        [HttpPost]
        public IActionResult AddRoom([FromBody] RoomModel room)
        {

            try
            {
                if (room == null)
                {
                    return BadRequest("Empty body request");
                }
                else
                {
                    RoomDto room1 = new RoomDto
                    {
                        //Id = room.Id,
                        Description = room.Description,
                        ReservationPrize = room.ReservationPrize

                    };
                    _db.Rooms.Add(room1);
                    _db.SaveChanges();
                    return Created("Room Added", room1);
                }
            }
            catch (Exception ex)
            {
                return BadRequest("Bład przy dodawaniu pokoju do bazy /n"+ex.Message);
            }
        }
        [HttpPost]
        public IActionResult AddReservation([FromBody] ReservationModel reservation)
        {

            try
            {
                if (reservation != null)
                {
                    var query = _db.Reservations.Where(x => (x.ReservationDate == reservation.ReservationDate.Date) && (x.RoomId == reservation.RoomID)).FirstOrDefault();
                    if (query == null)
                    {
                        var obiekt = new ReservationDto
                        {
                            ReservationDate = reservation.ReservationDate.Date,
                            RoomId = reservation.RoomID,
                            UserId = reservation.UserId
                        };
                       
                        _db.Reservations.Add(obiekt);
                        _db.SaveChanges();
                        return Created("Reservation Added", obiekt);
                    }
                    else
                    {
                        return BadRequest("Can not reserve room in this day");
                    }


                }
                else
                    return BadRequest("Empty body request");
            }
            catch (Exception ex)
            {
                return BadRequest(ex.Message);
            }

        }

        [HttpDelete]
        public IActionResult DeleteRoom(int id)
        {
            
                try
                {
                    var room = _db.Rooms.Find(id);
                    _db.Rooms.Remove(room);
                    _db.SaveChanges();
                    return Ok("Room deleted");
                }
                catch (Exception ex)
                {
                    return BadRequest(ex.Message);
                }
            
        }
        [HttpDelete]
        public IActionResult DeleteReservation(int id)
        {
           
                try
                {
                    var reservation = _db.Reservations.Find(id);
                    _db.Reservations.Remove(reservation);
                    _db.SaveChanges();
                    return Ok("Reservation deleted");
                }
                catch (Exception ex)
                {
                    return BadRequest(ex.Message);
                }
            
        }
    }





}




