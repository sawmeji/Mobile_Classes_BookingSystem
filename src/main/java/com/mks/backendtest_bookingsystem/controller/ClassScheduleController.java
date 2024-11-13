package com.mks.backendtest_bookingsystem.controller;

import com.mks.backendtest_bookingsystem.dto.ClassScheduleDto;
import com.mks.backendtest_bookingsystem.entity.Response;
import com.mks.backendtest_bookingsystem.service.ClassScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/schedules")
public class ClassScheduleController {
    @Autowired
    private ClassScheduleService classScheduleService;

    @PostMapping("/admin/create")
    public Response createClassSchedule(@RequestBody ClassScheduleDto classScheduleDTO) {
        return classScheduleService.createClassSchedule(classScheduleDTO);
    }
}
