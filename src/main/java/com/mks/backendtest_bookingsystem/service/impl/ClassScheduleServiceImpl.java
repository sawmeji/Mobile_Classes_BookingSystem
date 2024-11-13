package com.mks.backendtest_bookingsystem.service.impl;

import com.mks.backendtest_bookingsystem.dto.ClassScheduleDto;
import com.mks.backendtest_bookingsystem.entity.ClassSchedule;
import com.mks.backendtest_bookingsystem.entity.Response;
import com.mks.backendtest_bookingsystem.repository.ClassScheduleRepository;
import com.mks.backendtest_bookingsystem.service.ClassScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("classScheduleService")
public class ClassScheduleServiceImpl implements ClassScheduleService {

    @Autowired
    private ClassScheduleRepository classScheduleRepository;

    public void saveClass(ClassSchedule newSchedule){
        classScheduleRepository.save(newSchedule);
    }
    public Response createClassSchedule(ClassScheduleDto classScheduleDTO) {
        ClassSchedule newSchedule = new ClassSchedule();
        newSchedule.setClassName(classScheduleDTO.getClassName());
        newSchedule.setDescription(classScheduleDTO.getDescription());
        newSchedule.setStartTime(classScheduleDTO.getStartTime());
        newSchedule.setEndTime(classScheduleDTO.getEndTime());
        newSchedule.setMaxCapacity(classScheduleDTO.getCapacity());
        newSchedule.setCountry(classScheduleDTO.getCountry());

        saveClass(newSchedule);
        return new Response(true, "Create Class Successful!");
    }
}
