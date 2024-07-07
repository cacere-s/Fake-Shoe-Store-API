package com.fakepi.ShoeStore.Service;

import com.fakepi.ShoeStore.Excepcions.CategoryNotFoundException;
import com.fakepi.ShoeStore.Models.ShoeCategoryModel;
import com.fakepi.ShoeStore.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
  @Autowired
  CategoryRepository categoryRepository;

  public List<ShoeCategoryModel> all() {
    return (List<ShoeCategoryModel>) categoryRepository.findAll();
  }

  public ShoeCategoryModel byId(Long id) {
    return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Not found category with ID: " + id));
  }

}
