package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoreExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response= given()
                .when()
                .get("/stores")
                .then().statusCode(200);
        //response.log().all();

    }
    // 1) Extract the value of limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");

    }
    //2. Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");

    }

    //3. Extract the name of 5th store
    @Test
    public void test003() {
        String storeName = response.extract().path("data[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store name of 5th  is : " + storeName);
        System.out.println("------------------End of Test---------------------------");

    }
   // 4. Extract the names of all the store
   @Test
   public void test004() {
       List<String> storeNameList = response.extract().path("data.name");

       System.out.println("------------------StartingTest---------------------------");
       System.out.println("The List of all store names is : " + storeNameList);
       System.out.println("------------------End of Test---------------------------");

   }

   //5. Extract the storeId of all the store
   @Test
   public void test005() {
       List<Integer> storeIdList = response.extract().path("data.id");

       System.out.println("------------------StartingTest---------------------------");
       System.out.println("The List of all store Id is : " + storeIdList);
       System.out.println("------------------End of Test---------------------------");

   }
   //6. Print the size of the data list
   @Test
   public void test006() {
       List<?> list = response.extract().path("data");


       System.out.println("------------------StartingTest---------------------------");
       System.out.println("The size of the data list : " + list.size());
       System.out.println("------------------End of Test---------------------------");

   }
   //7. Get all the value of the store where store name = St Cloud
  @Test
   public void test007() {
       List<HashMap<String,?>> list = response.extract().path("data.findAll{it.name=='St Cloud'}");


       System.out.println("------------------StartingTest---------------------------");
       System.out.println("All the Values of the Store St Cloud  : " + list);
       System.out.println("------------------End of Test---------------------------");

   }
   //8.Get the address of the store where store name = Rochester
   @Test
   public void test008() {
       List<String> address = response.extract().path("data.findAll{it.name=='Rochester'}.address");


       System.out.println("------------------StartingTest---------------------------");
       System.out.println("Address of the Store Rochester  : " + address);
       System.out.println("------------------End of Test---------------------------");

   }

   //9. Get all the services of 8th store
   @Test
   public void test009() {
       List<HashMap<String,?>> services = response.extract().path("data[7].services");


       System.out.println("------------------StartingTest---------------------------");
       System.out.println("All the services of 8th store  : " + services);
       System.out.println("------------------End of Test---------------------------");

   }

   //10. Get storeservices of the store where service name = Windows Store
   @Test
   public void test010() {
       List<HashMap<String,?>> services = response.extract().path("data.findAll{it.name=='Windows Store'}.storeservices");


       System.out.println("------------------StartingTest---------------------------");
       System.out.println("The storeservices of service 'Windows Store' : " + services);
       System.out.println("------------------End of Test---------------------------");

   }

   //11. Get all the storeId of all the store
   @Test
   public void test011() {
       List<Integer> storeIdList = response.extract().path("data.services.storeservices.storeId");


       System.out.println("------------------StartingTest---------------------------");
       System.out.println("List of All Store id : " + storeIdList);
       System.out.println("------------------End of Test---------------------------");

   }
  // 12. Get id of all the store
  @Test
  public void test012() {
      List<Integer> idList = response.extract().path("data.id");


      System.out.println("------------------StartingTest---------------------------");
      System.out.println("List of id of all the stores : " + idList);
      System.out.println("------------------End of Test---------------------------");

  }
  //13. Find the store names Where state = ND
  @Test
  public void test013() {
      List<String> storeNameList = response.extract().path("data.findAll{it.state=='ND'}.name");


      System.out.println("------------------StartingTest---------------------------");
      System.out.println("List of stores of state 'ND' : " + storeNameList);
      System.out.println("------------------End of Test---------------------------");

  }
  //14. Find the Total number of services for the store where store name = Rochester
  @Test
  public void test014() {
      List<HashMap<String,?>> servicesList = response.extract().path("data.findAll{it.name=='Rochester'}.services");


      System.out.println("------------------StartingTest---------------------------");
      System.out.println("List of services of store Rochester : " + servicesList);
      System.out.println("------------------End of Test---------------------------");

  }

  //15. Find the createdAt for all services whose name = “Windows Store”
  @Test
  public void test015() {
      List<String> createdAtList = response.extract().path("data.services.storeservices.findAll{it.name=='Windows Store'}.createdAt");


      System.out.println("------------------StartingTest---------------------------");
      System.out.println("List of createdAt of services Windows Store : " + createdAtList);
      System.out.println("------------------End of Test---------------------------");

  }

  //16.Find the name of all services Where store name = “Fargo”

    @Test
    public void test016() {
        List<HashMap<String,?>> serviceNameList = response.extract().path("data.findAll{it.name=='Fargo'}.services.name");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of service name of  Store Fargo : " + serviceNameList);
        System.out.println("------------------End of Test---------------------------");

    }

    //17. Find the zip of all the store
    @Test
    public void test017() {
        List<String> zipList = response.extract().path("data.zip");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Zip for all stores : " + zipList);
        System.out.println("------------------End of Test---------------------------");

    }
    //18. Find the zip of store name = Roseville
    @Test
    public void test018() {
       List<String> zip = response.extract().path("data.findAll{it.name=='Roseville'}.zip");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Zip for all stores : " + zip);
        System.out.println("------------------End of Test---------------------------");

    }

    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019() {
        List<HashMap<String,?>> storeServiceList = response.extract().path("data.services.findAll{it.name=='Magnolia Home Theater'}.storeservices");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of storeServices of service 'Magnolia Home Theater' : " + storeServiceList);
        System.out.println("------------------End of Test---------------------------");

    }
    //20. Find the lat of all the stores
    @Test
    public void test020() {
        List<Double> latList = response.extract().path("data.lat");


        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of lat for all the stores : " + latList);
        System.out.println("------------------End of Test---------------------------");

    }
}
