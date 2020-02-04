/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.zabek.app;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author zabek
 */
@Stateless
public class Dao {
    @PersistenceContext(unitName="persistence_unit")
    protected EntityManager em;
    
    public List<UserComment> getRoomComents(int roomId){
        return em.createQuery("SELECT c FROM UserComment c where c.roomId = :rId")
                .setParameter("rId", roomId)
                .getResultList();
    }
    
    public List<UserComment> getRoomsComents(List<Integer> rooms){
        if(rooms.isEmpty())
            return new ArrayList<>();
        
        return em.createQuery("SELECT c FROM UserComment c where c.roomId in :rooms")
                .setParameter("rId", rooms)
                .getResultList();
    }
    
    public void saveComment(UserComment uc){
        em.persist(uc);
    }
    
    public void saveImage(Image i){
        List<Image> images = em.createQuery("SELECT i FROM Image i WHERE i.roomId = :id")
                .setParameter("id", i.getRoomId())
                .getResultList();
        images.forEach(im->em.remove(im));
        
        em.persist(i);
    }
    
    public List<Image> getRoomsImages(List<Integer> rooms){
        return em.createQuery("SELECT i FROM Image i WHERE i.roomId in :rooms")
                .setParameter("rooms", rooms)
                .getResultList();
    }
    
    public List<Image> getAllImages(){
        return em.createQuery("SELECT i FROM Image i")
                .getResultList();
    }
    
    public Image getRoomImage(Integer id){
        return em.createQuery("SELECT i FROM Image i WHERE i.roomId = :id", Image.class)
                .setParameter("id", id)
                .getResultList().stream().findAny().orElse(null);
    }
}
