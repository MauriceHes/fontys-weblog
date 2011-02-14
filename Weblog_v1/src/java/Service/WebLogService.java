/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import dao.WebLogDao;
import dao.WebLogDaoImp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.*;

public class WebLogService {

    private WebLogDao webLogDao;

    public WebLogService() {
        webLogDao = new WebLogDaoImp();
    }

    public void addPosting(Posting p) {
        webLogDao.savePosting(p);
    }

    public List<Posting> getPostings() {
        return webLogDao.listPostings();
    }

    public Posting getPost(long id) {
        return webLogDao.getPosting(id);
    }

    ;
}
