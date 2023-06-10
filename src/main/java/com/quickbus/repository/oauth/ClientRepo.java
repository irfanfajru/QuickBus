package com.quickbus.repository.oauth;

import com.quickbus.model.oauth.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientRepo extends PagingAndSortingRepository<Client, Long> {

    Client findOneByClientId(String clientId);

}

