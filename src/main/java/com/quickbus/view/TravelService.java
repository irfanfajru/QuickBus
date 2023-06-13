package com.quickbus.view;

import com.quickbus.model.Travel;
import com.quickbus.response.ResponseMap;

import java.util.UUID;

public interface TravelService {

    public ResponseMap save(Travel travel);
    public ResponseMap update(Travel travel, UUID travelId);
    public ResponseMap delete(UUID travelId);
    public ResponseMap getById(UUID travelId);
}
