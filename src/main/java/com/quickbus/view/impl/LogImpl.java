package com.quickbus.view.impl;

import com.quickbus.model.Log;
import com.quickbus.model.oauth.User;
import com.quickbus.repository.LogRepo;
import com.quickbus.repository.oauth.UserRepo;
import com.quickbus.view.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LogImpl implements LogService {
    @Autowired
    public LogRepo logRepo;

    @Autowired
    public UserRepo userRepo;

    public String createDesc = "CREATE";
    public String updateDesc = "UPDATE";
    public String deleteDesc = "DELETE";

    @Override
    public void save(Log log){
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepo.findOneByUsername(auth.getName());
            log.setUser(user);
            logRepo.save(log);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void createActivity(String desc){
        try{
            Log log = new Log();
            log.setActivity(createDesc);
            log.setDescription(desc);
            this.save(log);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateActivity(String desc){
        try{
            Log log = new Log();
            log.setActivity(updateDesc);
            log.setDescription(desc);
            this.save(log);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteActivity(String desc){
        try{
            Log log = new Log();
            log.setActivity(deleteDesc);
            log.setDescription(desc);
            this.save(log);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
