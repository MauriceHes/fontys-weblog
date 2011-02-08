/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.Posting;

/**
 *
 * @author Administrator
 */
public class WebLogDaoImp implements WebLogDao {
    
    private HashMap<Long, Posting> postings;
    private Long nextId;

    public WebLogDaoImp() {
        initWeblog();   
    }

    public void initWeblog() {
       
        postings = new HashMap<Long, Posting>();

        postings.put(1L,new Posting(1L,"Title 1", "Content 1"));
        postings.put(2L,new Posting(2L,"Title 2", "Content 2"));
        postings.put(3L,new Posting(3L,"Title 3", "Content 3"));
        nextId = 4L;
    }

    @Override
    public void savePosting(Posting p){
        if (p == null) {
            throw new IllegalArgumentException("Posting is null");
        }
        p.setId(nextId);
        postings.put(nextId++ , p);
    };

    @Override
    public List<Posting> listPostings(){
         return new ArrayList(postings.values());
    };

     @Override
    public Posting getPosting(Long id) {
            if (!postings.containsKey(id)) {
            throw new IllegalArgumentException("Id no found"+ id);
        }
        return postings.get(id);
    }
}
