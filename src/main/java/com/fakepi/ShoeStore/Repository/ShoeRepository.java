package com.fakepi.ShoeStore.Repository;

import com.fakepi.ShoeStore.Models.ShoeModel;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ShoeRepository extends CrudRepository<ShoeModel, Long>, JpaSpecificationExecutor<ShoeModel> {
}
