/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.zabek.app.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import pl.zabek.app.Dao;
import pl.zabek.app.Image;
import pl.zabek.app.util.ImageCompressor;

/**
 *
 * @author zabek
 */
@Path("image")
public class ImageResource {
    @EJB
    Dao dao;
    
    @POST
    @Path("save")
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveImage(String s) throws IOException{
        ImageTO iTO = new ObjectMapper().readValue(s, ImageTO.class);
        Image newi = new Image();
        newi.setImageName(iTO.getImageName());
        newi.setRoomId(iTO.getRoomId());
        newi.setImage(javax.xml.bind.DatatypeConverter.parseBase64Binary(iTO.getImage().split(",")[1]));
        ImageCompressor.rescaleImage(newi);
        dao.saveImage(newi);
    }
    
    @GET
    @Path("get-rooms-images")
    public List<Image> getRoomsImages(@QueryParam("room") List<Integer> roomIds) throws IOException {
        if (roomIds.isEmpty()) {
            return dao.getAllImages();
        }

        return dao.getRoomsImages(roomIds);
    }
    
    @GET
    @Path("room/{id}")
    public byte[] getRoomImage(@PathParam("id") Integer roomId){
        Image i = dao.getRoomImage(roomId);
        if(i == null)
            return new byte[0];
        
        return i.getImage();
    }
    
    
}
