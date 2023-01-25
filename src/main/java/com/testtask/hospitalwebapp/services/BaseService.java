package com.testtask.hospitalwebapp.services;

import com.testtask.hospitalwebapp.models.Doctor;

import java.util.List;

public interface BaseService<ROW> {

    ROW save(ROW doctor);

    void delete(ROW doctor);

    List<ROW> getAll();
}
