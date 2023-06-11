package com.quickbus.view;

import com.quickbus.model.Bus;
import com.quickbus.response.ResponseMap;

import java.util.Map;
import java.util.UUID;

public interface BusService {
    public ResponseMap save(Bus bus);
    public ResponseMap update(Bus bus, UUID busId);
}
