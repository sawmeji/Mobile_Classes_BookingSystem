package com.mks.backendtest_bookingsystem.service;

import com.mks.backendtest_bookingsystem.dto.ClassScheduleDto;
import com.mks.backendtest_bookingsystem.entity.Response;

public interface ClassScheduleService {

    public Response createClassSchedule(ClassScheduleDto classScheduleDTO);
}
