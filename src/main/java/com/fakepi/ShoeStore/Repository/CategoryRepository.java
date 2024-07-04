package com.fakepi.ShoeStore.Repository;

import com.fakepi.ShoeStore.Models.ShoeCategoryModel;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<ShoeCategoryModel, Long> {
}
