package com.fakepi.ShoeStore.Controllers;

import com.fakepi.ShoeStore.Models.ShoeCategoryModel;
import com.fakepi.ShoeStore.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class CategoryController {
  private final CategoryService categoryService;

  @GetMapping("/categories")
  ResponseEntity<List<ShoeCategoryModel>> categories() {
    return ResponseEntity.ok(categoryService.all());
  }

  @GetMapping("/categories/{id}")
  ResponseEntity<ShoeCategoryModel> category(@PathVariable Long id) {
    return ResponseEntity.ok(categoryService.byId(id));
  }

}
