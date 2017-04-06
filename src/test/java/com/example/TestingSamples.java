package com.example;

import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestingSamples {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void test_with_test_rest_template() {
        String forObject = testRestTemplate.getForObject("https://api.github.com/users/arturbdr/repos", String.class);
        List<String> ip = JsonPath.read(forObject, "$..name");
        assertEquals(30, ip.size());
    }

    @Test
    public void test_with_rest_assured() {
        RestAssured.when()
                .get("https://api.github.com/users/arturbdr/repos")
                .then()
                .body("id", hasSize(30));
    }
}
