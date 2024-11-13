package com.mks.backendtest_bookingsystem.service;

import com.mks.backendtest_bookingsystem.entity.ClassSchedule;
import com.mks.backendtest_bookingsystem.entity.Response;

import java.util.List;

public interface BookingService {


    public Response checkIn(Long bookingId);

    public Response cancelBooking(Long bookingId);

    public Response bookClass(Long classId, String userEmail);

    public List<ClassSchedule> getClassesByCountry(String country);

    public Response getUserClasses(String email);
}
