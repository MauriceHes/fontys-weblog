/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.Comment;
import model.Posting;

/**
 *
 * @author Administrator
 */
public class WebLogDaoImp implements WebLogDao {
    
    private HashMap<Long, Posting> postings = new HashMap<Long, Posting>();
    private Posting p;
    private List<Comment> c = new ArrayList();
    private Long nextId;

    public WebLogDaoImp() {
        initWeblog();   
    }

    public void initWeblog() {
       
        //postings = new HashMap<Long, Posting>();
        //Posting p;
        //List<Comment> c;

        this.p = new Posting(1L,"My first post", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam pharetra pretium ante vitae sodales. In hac habitasse platea dictumst. Morbi lacus neque, placerat eu ultrices non, convallis ac ipsum. Fusce interdum tortor sit amet nisl vehicula et malesuada felis malesuada. Curabitur tempor, erat sed volutpat imperdiet, nibh eros convallis urna, quis viverra nunc libero egestas mi. Donec nulla nulla, auctor eleifend varius non, porttitor ac magna. Mauris in purus est, quis commodo nisl. Integer et ipsum felis. Nulla elit diam, fermentum hendrerit adipiscing et, faucibus vitae purus. Ut ligula elit, vulputate aliquam consectetur in, scelerisque id dui. Nulla facilisi. ");
        //c = new ArrayList();
        this.c.add(new Comment(this.p.getNextCommentID(), "Comment 1 bij post 1"));
        this.c.add(new Comment(this.p.getNextCommentID(), "Comment 2 bij post 1"));
        this.p.setComments(this.c);
        this.postings.put(1L, this.p);

        this.p = new Posting(2L,"My second post", "Cras sem nunc, dignissim ac tristique quis, ultrices at dui. Suspendisse potenti. Sed cursus massa sagittis justo faucibus porttitor. Curabitur vel nulla lectus, eget vulputate quam. Duis bibendum enim eu massa dapibus at imperdiet magna blandit. Mauris ut odio at lectus ultrices tempor. Cras sit amet erat orci, ut molestie ante. Suspendisse potenti. Nullam nisi metus, imperdiet vel molestie non, sodales eu nulla. Etiam et ligula justo, non porta nisl. Etiam vel facilisis velit. In hac habitasse platea dictumst. Vivamus ut felis justo, quis tincidunt nisi. Maecenas ut quam diam, a molestie lectus. Vivamus lobortis fermentum nibh feugiat gravida. Duis consectetur elementum massa nec malesuada. Mauris posuere aliquet porttitor. Etiam mi leo, feugiat in facilisis id, tempor mattis enim. ");
        this.c = new ArrayList();
        this.c.add(new Comment(this.p.getNextCommentID(), "Comment 1 bij post 2"));
        this.c.add(new Comment(this.p.getNextCommentID(), "Comment 2 bij post 2"));
        this.c.add(new Comment(this.p.getNextCommentID(), "Comment 3 bij post 2"));
        this.c.add(new Comment(this.p.getNextCommentID(), "Comment 4 bij post 2"));
        this.p.setComments(this.c);
        this.postings.put(2L, this.p);

        this.p = new Posting(3L,"Let's get started", "Morbi id neque leo. Donec erat nunc, iaculis vel semper ac, sodales ac est. Etiam a enim eget nisl luctus tincidunt at in dolor. Donec et metus id neque ultricies aliquam. Suspendisse ac sapien at ipsum fringilla pellentesque et sed libero. Vivamus in eros turpis. Phasellus orci neque, molestie sed varius ut, volutpat vitae nisl. Suspendisse varius metus ac sapien ultrices sit amet fringilla tortor pharetra. Vestibulum hendrerit ornare lorem, et malesuada neque pretium et. Curabitur bibendum, neque vel pharetra tincidunt, felis mauris elementum ligula, ut feugiat nisi turpis at nunc. Aenean faucibus sapien nec enim commodo quis elementum sapien placerat. Phasellus blandit nunc vitae ante rutrum ac posuere nulla tempus. Donec ipsum arcu, lacinia a ornare tristique, suscipit at sapien. Aenean dignissim, elit ut fermentum rutrum, neque ipsum scelerisque ligula, vitae consectetur justo magna lobortis nulla. Praesent nunc lacus, imperdiet et auctor ut, laoreet vel nisl. ");
        this.postings.put(3L, this.p);
        this.nextId = 4L;
    }

    @Override
    public void savePosting(Posting p){
        if (p == null) {
            throw new IllegalArgumentException("Posting is null");
        }
        p.setId(this.nextId);
        this.postings.put(this.nextId++ , p);
    };

    @Override
    public List<Posting> listPostings(){
         return new ArrayList(this.postings.values());
    };

    @Override
    public Posting getPosting(Long id) {
        if (!this.postings.containsKey(id)) {
            throw new IllegalArgumentException("Id no found"+ id);
        }
        return this.postings.get(id);
    }

}
