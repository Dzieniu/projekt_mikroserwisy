/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.zabek.app.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import pl.zabek.app.Image;
import pl.zabek.app.RoomDetailTO;
import pl.zabek.app.UserComment;

/**
 *
 * @author zabek
 */
public class DataConverter {
    public static List<RoomDetailTO> getRoomDetails(List<Integer> ids, List<Image> images, List<UserComment> comments){
        List<RoomDetailTO> result = new ArrayList<>();
        
        Map<Integer, List<Image>> roomsImages = images.stream().collect(Collectors.groupingBy(i->i.getRoomId()));
        Map<Integer, List<UserComment>> roomsComments = comments.stream().collect(Collectors.groupingBy(i->i.getRoomId()));
        
        for (Integer id : ids) {
            RoomDetailTO det = new RoomDetailTO();
            if(roomsImages.containsKey(id)){
                det.setImage(roomsImages.get(id).get(0).getImage());
            }else{
                det.setImage(new byte[1]);
            }
            
            List<UserComment> rComent = roomsComments.getOrDefault(id, new ArrayList<>());
            det.setComments(rComent.stream().map(c->c.getComment()).collect(Collectors.toList()));
            det.setRate(rComent.stream().mapToInt(c->c.getRate()).average().orElse(0.0));
            result.add(det);
        }
        
        return result;
    }
}
