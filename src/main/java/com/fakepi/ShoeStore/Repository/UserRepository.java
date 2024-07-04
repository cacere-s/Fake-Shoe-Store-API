package com.fakepi.ShoeStore.Repository;

import com.fakepi.ShoeStore.Models.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Long> {
  Optional<UserModel> findByUsername(String username);
}
