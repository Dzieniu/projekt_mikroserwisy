/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.zabek.app.rest;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import pl.zabek.app.Dao;
import pl.zabek.app.Image;
import pl.zabek.app.UserComment;

/**
 * REST Web Service
 *
 * @author zabek
 */
@Path("comments")
public class CommentsResource {
    
    @EJB
    private Dao dao;
    
    @GET
    @Path("for-room")
    public List<UserComment> getComments(@QueryParam("id") Integer id) {
        return dao.getRoomComents(id);
    }
    
    @POST
    @Path("create-comment")
    public void createComment(UserComment comment){
        comment.setId(null);
        dao.saveComment(comment);
    }
    
}
