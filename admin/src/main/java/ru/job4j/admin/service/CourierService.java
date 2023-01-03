package ru.job4j.admin.service;

import ru.job4j.domain.model.Courier;

import java.util.List;

public interface CourierService {

    Courier add(Courier courier);

    boolean dismissById(int id);

    boolean hireById(int id);

    List<Courier> findAll();
}