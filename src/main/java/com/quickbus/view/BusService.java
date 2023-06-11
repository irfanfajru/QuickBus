package com.quickbus.view;

import com.quickbus.model.Bus;
import com.quickbus.response.ResponseMap;
import org.springframework.data.domain.Page;

import java.util.Map;
import java.util.UUID;

public interface BusService {
    public ResponseMap getById(UUID busId);
    public ResponseMap save(Bus bus);
    public ResponseMap update(Bus bus, UUID busId);
    public ResponseMap delete(UUID busId);
}
