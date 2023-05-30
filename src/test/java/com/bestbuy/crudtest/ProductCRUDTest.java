package com.bestbuy.crudtest;

import com.bestbuy.model.ProductPojo;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductCRUDTest extends TestBase {

    static String name = "Duracell - AAA Batteries (4-Pack)" + TestUtils.getRandomValue();

    static String type = "HardGood" + TestUtils.getRandomValue();

    static Double price= 5.49;

    static String upc="041333424019";

    static double shipping = 0;

    static String description = "Compatible with select electronic devices";
    static String manufacturer = "Duracell";
    static String model = "MN2400B4Z";
    static String url = "http://www.bestbuy.com/site/duracell-aaa-batteries-4-pack/43900.p?id=1051384074145&skuId=43900&cmp=RMXCC";

    static String image= "http://img.bbystatic.com/BestBuy_US/images/products/4390/43900_sa.jpg";

    static int productId;

    //Get all products
    @Test
    public void test001()
    {
        Response response = given()
                .when()
                .get("/products");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    //Create product
    @Test
    public void test002()
    {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription(description);
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(productPojo)
                .when()
                .post("/products");
                response.then().log().all().statusCode(201);
              productId=  response.jsonPath().get("id");
                response.prettyPrint();
        System.out.println("Id : " + productId);


    }
    //get product by id
    @Test
    public void test003()
    {
        Response response = given()
                .when()
                .get("/products"+ "/" + productId);
        response.then().statusCode(200);
        response.prettyPrint();
    }
    //update the product
    @Test
    public void test004(){
        ProductPojo productPojo= new ProductPojo();
        productPojo.setName("Apple"+name);
        productPojo.setType(type);
        productPojo.setPrice(price);
        productPojo.setUpc(upc);
        productPojo.setShipping(shipping);
        productPojo.setDescription("Apple Product");
        productPojo.setManufacturer(manufacturer);
        productPojo.setModel(model);
        productPojo.setUrl(url);
        productPojo.setImage(image);
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(productPojo)
                .patch("/products" + "/" + productId);
        response.prettyPrint();
        response.then().log().all().statusCode(200);

        Response response1 = given()
                .when()
                .get("/products" + "/" +productId);
        response.then().statusCode(200);
        response.prettyPrint();
    }

    //delete the record
    @Test
    public void test005() {
        Response response = given()
                .when()
                .delete("/products/9999681");
        response.prettyPrint();
        response.then().log().all().statusCode(200);

        Response response1 = given()
                .when()
                .get("/products" + "/"+ productId);
        response.then().statusCode(200);
        response.prettyPrint();
    }




}
