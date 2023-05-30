package com.bestbuy.crudtest;

import com.bestbuy.model.StorePojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class StoresCRUDTest extends TestBase {
    static String name = "Tester" + TestUtils.getRandomValue();
    static String type = "BigBox" + TestUtils.getRandomValue();
    static String address =  TestUtils.getRandomValue() + " ,Fashion Street" ;
    static String address2 = "Grove Crescent";
    static String City = "London" ;
    static String state = "England" ;
    static String zip = "Nw" + TestUtils.getRandomValue();
    static double lat = 44.23569;
    static double lng = -97.66;
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";

    static int storeId;


    //Create store
    @Test
    public void test001() {
        StorePojo storePojo = new StorePojo();
        storePojo.setName(name);
        storePojo.setType(type);
        storePojo.setAddress(address);
        storePojo.setAddress2(address2);
        storePojo.setCity(City);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(storePojo)
                .when()
                .post("/stores");
        response.then().log().all().statusCode(201);
        storeId = response.jsonPath().get("id");
        System.out.println("store id : " + storeId);
    }



    //Get store by id
    @Test
    public void test002() {
        Response response = given()
                .when()
                .get("/stores" + "/" +storeId);
        response.then().statusCode(200);
        response.prettyPrint();
    }
    //Update store using put
    @Test
    public void test003() {
        StorePojo storePojo = new StorePojo();
        storePojo.setName("Prime" + name + "Store");
        storePojo.setType(type);
        storePojo.setAddress("Prime"+address);
        storePojo.setAddress2(address2);
        storePojo.setCity(City);
        storePojo.setState(state);
        storePojo.setZip(zip);
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        storePojo.setHours(hours);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(storePojo)
                .when()
                .put("/stores" + "/" +storeId);
        response.then().log().all().statusCode(200);

    }
    //Update store using patch

    @Test
    public void test004() {
        StorePojo storePojo = new StorePojo();
        storePojo.setHours("Mon: 10-10; Tue: 10-8; Wed: 10-7; Thurs: 10-6; Fri: 10-5; Sat: 10-9; Sun: 10-8");
        storePojo.setName("Tesco"+name);
        storePojo.setType(type);
        storePojo.setZip(zip+"LP");
        storePojo.setLat(lat);
        storePojo.setLng(lng);
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(storePojo)
                .patch("/stores" + "/" +storeId);
        response.prettyPrint();
        response.then().log().all().statusCode(200);
    }
//Delete record
    @Test
    public void test005() {
        Response response = given()
                .when()
                .delete("/stores" + "/" +storeId);
        response.prettyPrint();
        response.then().log().all().statusCode(200);


    }
    //Get whole store list
    @Test
    public void test006() {
        Response response = given()
                .when()
                .get("/stores");
        response.then().statusCode(200);
        response.prettyPrint();
    }

}
