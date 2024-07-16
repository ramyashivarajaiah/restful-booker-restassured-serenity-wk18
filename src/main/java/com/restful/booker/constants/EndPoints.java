package com.restful.booker.constants;

public class EndPoints {

    /**
     * this is for booking api endpoint
     */
    public static final String GET_ALL_BOOKING = "/booking";

    public static final String GET_SINGLE_BOOKING_BY_ID = Path.BOOKING + "/{bookingID}";
    public static final String PUT_UPDATE_SINGLE_BOOKING_BY_ID = Path.BOOKING + "/{bookingID}";
    public static final String PATCH_UPDATE_SINGLE_BOOKING_BY_ID = Path.BOOKING + "/{bookingID}";
    public static final String DELETE_SINGLE_BOOKING_BY_ID = Path.BOOKING + "/{bookingID}";
}