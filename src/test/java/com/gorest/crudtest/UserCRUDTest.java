package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {


   static String email =  "mani12@gmail.com";
    static String name =  "Mani meghji";
    UserPojo userPojo = new UserPojo();
    int userId;

    @Test
    public void verifyUserCreatedSuccessfully(){

        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender("male");
        userPojo.setStatus("active");

        Response response = given()
                .header("Authorization","Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .contentType(ContentType.JSON)
                .when()
                .body(userPojo)
                .post("/users");
        response.then().log().all().statusCode(201);
       int userId= response.body().jsonPath().getInt("id");
        System.out.println(userId);
    }
    @Test
    public void getUserid(){
        Response response = given()
                .header("Authorization","Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .when()
                .get("/users/4110686");
        response.then().log().all().statusCode(200);
    }
    @Test
    public void verifyUserUpdateSuccessfully(){
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender("male");
        userPojo.setStatus("inactive");
        Response response = given()
                .header("Authorization","Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .contentType(ContentType.JSON)
                .when()
                .body(userPojo)
                .patch("/users/4110686");
        response.then().log().all().statusCode(200);
    }
    @Test
    public void delete() {
    /*curl --location -g --request DELETE 'https://gorest.co.in/public/v2/users/id=2730' \
            --header 'Authorization: Bearer {{token}}*/
        Response response = given()
                .header("Authorization","Bearer 72d70b64d5532cce2c74a8198603ebe97bf36b9002a3b6addb576cb98809967c")
                .when()
                .delete("/users/4110686");
        response.then().log().all().statusCode(404);
        response.prettyPrint();

    }
}
