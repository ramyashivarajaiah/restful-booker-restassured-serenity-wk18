package com.restful.booker.crudtest;

import com.restful.booker.bookinginfo.BookingSteps;
import com.restful.booker.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;

@RunWith(SerenityRunner.class)
public class BookingCrudTest extends TestBase {

    static String firstName = "Tim";
    static String lastName = "White";
    static int totalPrice = 111;
    static boolean depositPaid = true;
    static String checkin = "2023-09-12";
    static String checkout = "2024-01-11";
    static String additionalNeeds = "Breakfast";

    static int bookingId;

    @Steps
    BookingSteps bookingSteps;


    @Title("Test001-This will create a new Booking")
    @Test
    public void test001() {

        ValidatableResponse response = bookingSteps.createBooking(firstName, lastName, totalPrice, depositPaid, checkin, checkout, additionalNeeds);

        response.statusCode(200);
        response.log().body();

        bookingId = response.extract().path("bookingid");
        System.out.println(bookingId);
    }

    @Title("Test002-Verify booking details was added successfully")
    @Test
    public void test002() {
        ValidatableResponse response = bookingSteps.getBookingInfoById(bookingId);
        response.statusCode(200);
        System.out.println("firstName is: " + response.extract().path("firstname"));
    }


    @Title("Test003-PUT - Update booking info and verify updated details ")
    @Test
    public void test003() {
        String ufirstName = firstName + "Updated";
        String ucheckin = "2023-10-20";
        String ucheckout = "2024-02-28";

        ValidatableResponse response = bookingSteps.updateBookingInfoById(bookingId, ufirstName, lastName, totalPrice, depositPaid, ucheckin, ucheckout, additionalNeeds);
        response.statusCode(200);
        System.out.println("firstName is : " + response.extract().path("firstname"));
        System.out.println("checkin date is :" + response.extract().path("bookingdates.checkin"));
    }


    @Title("Test004-Patch - Partial update booking info and verify updated details ")
    @Test
    public void test004() {
        int utotalPrice = 500;
        String uadditionalNeeds = "Dinner";
        String ucheckin = "2024-01-20";
        String ucheckout = "2024-02-28";

        ValidatableResponse response = bookingSteps.partialUpdateBookingInfoById(bookingId, firstName, lastName, utotalPrice, depositPaid, ucheckin, ucheckout, uadditionalNeeds);
        response.statusCode(200);
        System.out.println("total price :" + response.extract().path("totalprice"));
        response.body("additionalneeds", equalTo("Dinner"));
    }

    @Title("Test005-Delete booking and verify if booking is deleted")
    @Test
    public void test005() {
        bookingSteps.deleteBooking(bookingId).statusCode(201);
        bookingSteps.getBookingInfoById(bookingId).statusCode(404);

    }

}
