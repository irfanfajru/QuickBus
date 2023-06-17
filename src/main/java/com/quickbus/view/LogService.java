package com.quickbus.view;

import com.quickbus.model.Log;

public interface LogService {
    public void save(Log log);
    public void createActivity(String desc);
    public void updateActivity(String desc);
    public void deleteActivity(String desc);
}
