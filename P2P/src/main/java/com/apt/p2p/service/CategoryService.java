package com.apt.p2p.service;

import com.apt.p2p.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category findById(int categoryId);

    Category updateName(int id, String name);

    Category save(Category category);
}
