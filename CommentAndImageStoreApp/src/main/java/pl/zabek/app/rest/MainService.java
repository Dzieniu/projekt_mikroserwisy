/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.zabek.app.rest;

import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import pl.zabek.app.Dao;
import pl.zabek.app.RoomDetailTO;
import pl.zabek.app.util.DataConverter;

/**
 *
 * @author zabek
 */
@Path("main")
public class MainService {
    @EJB
    Dao dao;
    
    @GET
    @Path("rooms-info")
    public List<RoomDetailTO> getRoomsInfo(@QueryParam("id") List<Integer> rooms){
        return DataConverter.getRoomDetails(rooms, dao.getRoomsImages(rooms), dao.getRoomsComents(rooms));
    }
}
