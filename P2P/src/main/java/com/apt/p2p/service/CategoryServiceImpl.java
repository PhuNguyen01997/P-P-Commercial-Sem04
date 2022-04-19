package com.apt.p2p.service;

import com.apt.p2p.entity.Category;
import com.apt.p2p.model.view.ToastResponse;
import com.apt.p2p.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(int categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public Category updateName(int id, String name) {
        Category category = categoryRepository.findById(id).get();
        category.setName(name);
        category.setUpdatedAt(new Date());

        categoryRepository.save(category);
        return category;
    }

    @Override
    public Category save(Category category) {
        categoryRepository.save(category);
        return category;
    }

    @Override
    public void deleteById(int id) throws Exception {
        Category category = categoryRepository.findById(id).get();
        if(category.getProducts().size() > 0){
            throw new Exception("Error occur when delete category, category only delete if it does't have any products");
        }
        categoryRepository.deleteById(id);
    }
}
