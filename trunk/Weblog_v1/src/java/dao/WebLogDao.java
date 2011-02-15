/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.util.List;
import model.Comment;
import model.Posting;

public interface WebLogDao {
    void savePosting(Posting p);
    List<Posting> listPostings();
    Posting getPosting(Long id);

    
}
