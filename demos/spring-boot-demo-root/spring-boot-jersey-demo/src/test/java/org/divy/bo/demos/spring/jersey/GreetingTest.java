package org.divy.bo.demos.spring.jersey;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.divy.bo.demos.domain.greetings.Greeting;
import org.divy.common.bo.spring.jersey.rest.JerseyEndpointConfig;
import org.divy.common.bo.spring.repository.RepositoryConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;

import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {SpringJerseyApplication.class, JerseyEndpointConfig.class, RepositoryConfig.class},
      properties = {"bo-framework.endpoint.jersey.enable-hateoas-api=true"} )
public class GreetingTest
{
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Value("${local.server.port}")
    private int port;

    @BeforeEach
    public void initialiseRestAssuredMockMvcWebApplicationContext() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
        RestAssured.port = port;
    }
    @Test
    public void simpleEntity() {
        Greeting greeting = new Greeting();
        greeting.setGreetingMessage( "Hello World" );
        HashMap<String, Greeting> body = new HashMap<>();

        body.put( "data", greeting );

        String location = with().body( body )
              .when()
              .contentType( ContentType.JSON )
              .accept( ContentType.JSON )
              .request("POST", "/hatoas/greeting")
              .then()
              .log().body()
              .statusCode(201)
              .header( "Location", containsString( "/hatoas/greeting/" ) )
        .extract().header( "Location" );

        with().get(location)
              .then()
              .contentType( ContentType.JSON )
              .statusCode( 200 )
              .log().body()
              .body( "data.greetingMessage",  is( equalTo( "Hello World" ) ));

        greeting.setGreetingMessage( "Hello World from Rest" );
        with().body( body )
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
              .body( "data.greetingMessage",  is( equalTo( "Hello World from Rest" ) ));

        with().delete(location)
              .then()
              .statusCode( allOf( greaterThanOrEqualTo( 200 ), lessThanOrEqualTo( 204 )) );

        with().get(location)
              .then()
              .statusCode( 404 );
    }
}
