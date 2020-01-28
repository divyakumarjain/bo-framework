package org.divy.bo.demos.spring.mvc.cards;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.divy.bo.demos.spring.mvc.SpringMVCDemoApplication;
import org.divy.bo.demos.domain.greetings.Greeting;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.with;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = SpringMVCDemoApplication.class)
public class GreetingTest
{
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void initialiseRestAssuredMockMvcWebApplicationContext() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
        RestAssured.port = port;
    }

    @LocalServerPort
    int port;

    @Test
    public void simpleEntity() {
        Greeting greeting = new Greeting();
        greeting.setGreetingMessage( "Hello World" );

        String location = with().body( greeting )
              .when()
              .contentType( ContentType.JSON )
              .accept( ContentType.JSON )
              .request("POST", "/api/greeting")
              .then()
              .log().body()
              .statusCode(201)
              .header( "Location", containsString( "/api/greeting/" ) )
        .extract().header( "Location" );

        with().get(location)
              .then()
              .contentType( ContentType.JSON )
              .statusCode( 200 )
              .body( "greetingMessage",  is( equalTo( "Hello World" ) ));

        greeting.setGreetingMessage( "Hello World from Rest" );
        with().body( greeting )
              .when()
              .contentType( ContentType.JSON )
              .accept( ContentType.JSON )
              .request("PUT", location)
              .then()
              .log().body()
              .statusCode(204);

        with().get(location)
              .then()
              .contentType( ContentType.JSON )
              .statusCode( 200 )
              .body( "greetingMessage",  is( equalTo( "Hello World from Rest" ) ));

        with().delete(location)
              .then()
              .statusCode( allOf( greaterThanOrEqualTo( 200 ), lessThanOrEqualTo( 204 )) );

        with().get(location)
              .then()
              .statusCode( 404 );

    }
}
