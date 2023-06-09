package com.investree.demo.testing;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestingController {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void restTemplateSave() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept","*/*");
        headers.set("Content-Type","application/json");
        JSONObject bodyTesting = new JSONObject();
        JSONObject peminjam = new JSONObject();
        peminjam.put("id",1);
        bodyTesting.put("peminjam",peminjam);

        JSONObject meminjam = new JSONObject();
        meminjam.put("id",2);
        bodyTesting.put("meminjam",meminjam);

        bodyTesting.put("tenor",4);
        bodyTesting.put("totalPinjaman",1000000);
        bodyTesting.put("bungaPersen",50);
        bodyTesting.put("status","berjalan");
        System.out.println(bodyTesting.toString());
        HttpEntity<String> entity = new HttpEntity<String>(bodyTesting.toString(),headers);
        ResponseEntity<String> exchange = restTemplate.exchange("http://localhost:8081/api/v1/transaksi", HttpMethod.POST,entity,String.class);
        System.out.println("response = "+exchange.getBody());
        assertEquals(HttpStatus.OK,exchange.getStatusCode());
    }
}
