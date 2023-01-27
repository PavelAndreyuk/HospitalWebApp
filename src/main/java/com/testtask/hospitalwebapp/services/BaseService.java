package com.testtask.hospitalwebapp.services;

import java.util.List;

public interface BaseService<ROW> {

    ROW save(ROW doctor);

    void delete(ROW doctor);

    List<ROW> getAll();
}
