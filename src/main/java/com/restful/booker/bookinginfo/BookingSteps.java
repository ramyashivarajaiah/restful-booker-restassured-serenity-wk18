package com.restful.booker.bookinginfo;

import com.restful.booker.constants.EndPoints;
import com.restful.booker.constants.Path;
import com.restful.booker.models.BookingPojo;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

public class BookingSteps {

    @Step("Creating user with firstName : {0}, lastName : {1}, totalPrice :{2}, depositPaid : {3}, checkin : {4}, checkOut :{5}, additionalNeeds :{6}")
    public ValidatableResponse createBooking(String firstName, String lastName, int totalPrice, boolean depositPaid, String checkin, String checkout, String additionalNeeds) {

        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstName, lastName, totalPrice, depositPaid, checkin, checkout, additionalNeeds);

        return SerenityRest.given().
                header("Content-Type", "application/json")
                .when()
                .body(bookingPojo)
                .post(Path.BOOKING)
                .then();
    }

    @Step("Get booking information with bookingId :{0}")
    public ValidatableResponse getBookingInfoById(int bookingId) {
        return SerenityRest.given()
                .pathParam("bookingID", bookingId)
                .when()
                .get(EndPoints.GET_SINGLE_BOOKING_BY_ID)
                .then();
    }

    @Step("Updating booking info with bookingId :{0}, firstName : {1}, lastName : {2}, totalPrice :{3}, depositPaid : {4}, checkin : {5}, checkOut :{6}, additionalNeeds :{7}")
    public ValidatableResponse updateBookingInfoById(int bookingId, String firstName, String lastName, int totalPrice, boolean depositPaid, String checkin, String checkout, String additionalNeeds) {

        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstName, lastName, totalPrice, depositPaid, checkin, checkout, additionalNeeds);

        return SerenityRest.given()
                .pathParam("bookingID", bookingId)
                .auth().preemptive().basic("admin", "password123")
                .contentType("application/json")
                .accept("application/json")
                .when()
                .body(bookingPojo)
                .put(EndPoints.PUT_UPDATE_SINGLE_BOOKING_BY_ID)
                .then();
    }

    @Step("Partial Updating booking info with bookingId :{0}, firstName : {1}, lastName : {2}, totalPrice :{3}, depositPaid : {4}, checkin : {5}, checkOut :{6}, additionalNeeds :{7}")
    public ValidatableResponse partialUpdateBookingInfoById(int bookingId, String firstName, String lastName, int totalPrice, boolean depositPaid, String checkin, String checkout, String additionalNeeds) {

        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstName, lastName, totalPrice, depositPaid, checkin, checkout, additionalNeeds);

        return SerenityRest.given()
                .pathParam("bookingID", bookingId)
                .auth().preemptive().basic("admin", "password123")
                .contentType("application/json")
                .accept("application/json")
                .when()
                .body(bookingPojo)
                .patch(EndPoints.PATCH_UPDATE_SINGLE_BOOKING_BY_ID)
                .then();
    }


    @Step("Deleting booking information with bookingId :{0}")
    public ValidatableResponse deleteBooking(int bookingId) {
        return SerenityRest.given()
                .pathParam("bookingID", bookingId)
                .auth().preemptive().basic("admin", "password123")
                .header("Content-Type", "application/json")
                .when()
                .delete(EndPoints.DELETE_SINGLE_BOOKING_BY_ID)
                .then();
    }

}