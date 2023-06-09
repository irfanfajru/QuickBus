package com.investree.demo.repository.oauth;

import com.investree.demo.model.oauth.Client;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientRepo extends PagingAndSortingRepository<Client, Long> {

    Client findOneByClientId(String clientId);

}

