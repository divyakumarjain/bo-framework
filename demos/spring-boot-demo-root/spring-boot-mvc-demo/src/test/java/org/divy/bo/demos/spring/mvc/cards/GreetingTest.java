package org.divy.bo.demos.spring.mvc.cards;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.divy.bo.demos.spring.mvc.SpringMVCDemoApplication;
import org.divy.bo.demos.domain.greetings.Greeting;
import org.divy.common.bo.spring.mvc.rest.config.WebEndpointConfig;
import org.divy.common.bo.spring.repository.RepositoryConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.test.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.with;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {SpringMVCDemoApplication.class, RepositoryConfig.class, WebEndpointConfig.class},
      properties = {"bo-framework.endpoint.mvc.enable-hateoas-api=false"} )
public class GreetingTest
{
    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
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
