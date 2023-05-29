package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    // 21) Extract the value of limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");
    }
    // 22) Extract the value of total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");
    }
    // 23) Extract the name of the 5Th product
    @Test
    public void test003() {
        String productName = response.extract().path("data[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of 5Th product : " + productName);
        System.out.println("------------------End of Test---------------------------");
    }
    // 24) Extract the name of the all product
    @Test
    public void test004() {
        List< String> productNameList = response.extract().path("data.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the name of all product : " + productNameList);
        System.out.println("------------------End of Test---------------------------");
    }
    // 25) Extract the productId of the all product
    @Test
    public void test005() {
        List<Integer> productIdList = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The list of productid is : " + productIdList);
        System.out.println("------------------End of Test---------------------------");
    }

    //26. Print the size of the data list
    @Test
    public void test006() {
        List<Integer> productList = response.extract().path("data");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of product  list is : " + productList.size());
        System.out.println("------------------End of Test---------------------------");
    }

    //27. Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)

    @Test
    public void test007() {
        List<HashMap<String,?>> value  = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get the value of product 'Energizer - MAX Batteries AA (4-Pack)' is : " + value);
        System.out.println("------------------End of Test---------------------------");
    }

    //28. Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test008() {
        List<HashMap<String,?>> value  = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Get the model of product 'Energizer - N Cell E90 Batteries (2-Pack)' is : " + value);
        System.out.println("------------------End of Test---------------------------");
    }

    //29. Get all the categories of 8th products

    @Test
    public void test009() {
        List<HashMap<String,?>> categories  = response.extract().path("data[7].categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the Categories for 8th product are  : " + categories);
        System.out.println("------------------End of Test---------------------------");
    }
    //30. Get categories of the store where product id = 150115
    @Test
    public void test010() {
        List<HashMap<String,?>> categories  = response.extract().path("data.findAll{it.id==150115}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All the Categories for product id '150115'  : " + categories);
        System.out.println("------------------End of Test---------------------------");
    }
    //31. Get all the descriptions of all the products
    @Test
    public void test011() {
        List<HashMap<String,?>> data  = response.extract().path("data.description");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of All the description  : " + data);
        System.out.println("------------------End of Test---------------------------");
    }
    //32. Get id of all the all categories of all the products
    @Test
    public void test012() {
        List<String> cIdList  = response.extract().path("data.categories.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of All the categories Ids  : " + cIdList);
        System.out.println("------------------End of Test---------------------------");
    }

    //33. Find the product names Where type = HardGood
    @Test
    public void test013() {
        List<String> type  = response.extract().path("data.findAll{it.type=='HardGood'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of All the products of type 'HardGood'  : " + type);
        System.out.println("------------------End of Test---------------------------");
    }
    //34. Find the Total number of categories for the product where product name = Duracell - AA1.5V CopperTop Batteries (4-Pack)

    @Test
    public void test014() {
        List<HashMap<String,?>> list  = response.extract().path("data.findAll{it.name=='Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Total of categories of product 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'   : " + list.size());
        System.out.println("------------------End of Test---------------------------");
    }
    //35)  Find the createdAt for all products whose price < 5.49
    @Test
    public void test015() {
        List< String> createdAtSize = response.extract().path("data.findAll{it.price< 5.49}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Find the createdAt for all products whose price < 5.49 " + createdAtSize);
        System.out.println("------------------End of Test---------------------------");
    }
    //36)  Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-
    //Pack)”
    @Test
    public void test016() {
        List< String> categoriesName = response.extract().path("data.findAll{it.name =='Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Find the name of all categories Where product name " + categoriesName);
        System.out.println("------------------End of Test---------------------------");
    }
    //37)  Find the manufacturer of all the products
    @Test
    public void test017() {
        List< String> manufactureName = response.extract().path("data.manufacturer");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Find the manufacturer of all the products " +manufactureName);
        System.out.println("------------------End of Test---------------------------");
    }
    //38. Find the imge of products whose manufacturer is = Energizer
    @Test
    public void test018() {
        List< String> image = response.extract().path("data.findAll{it.manufacturer =='Energizer'}.image");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Image of Product of manufacturer 'Energizer' " + image);
        System.out.println("------------------End of Test---------------------------");
    }
    //39)  Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test019() {
        List< String> priceGreater = response.extract().path("data.findAll{it.price>5.49}.createdAt");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" Find the createdAt for all categories products whose price > 5.99 " + priceGreater);
        System.out.println("------------------End of Test---------------------------");
    }
    //40. Find the uri of all the products
    @Test
    public void test020() {
        List< String> uri = response.extract().path("data.url");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println(" URI of all products " + uri);
        System.out.println("------------------End of Test---------------------------");
    }

}
