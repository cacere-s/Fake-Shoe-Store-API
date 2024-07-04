package com.fakepi.ShoeStore.Service;

import com.fakepi.ShoeStore.Dtos.ShoeRequest;
import com.fakepi.ShoeStore.Dtos.ShoeUpdateRequest;
import com.fakepi.ShoeStore.Excepcions.CategoryNotFoundException;
import com.fakepi.ShoeStore.Excepcions.ShoeNotFoundException;
import com.fakepi.ShoeStore.Excepcions.UserNotFoundException;
import com.fakepi.ShoeStore.Models.ShoeCategoryModel;
import com.fakepi.ShoeStore.Models.ShoeModel;
import com.fakepi.ShoeStore.Models.UserModel;
import com.fakepi.ShoeStore.Repository.CategoryRepository;
import com.fakepi.ShoeStore.Repository.ShoeRepository;
import com.fakepi.ShoeStore.Repository.UserRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoeService {
  @Autowired
  private ShoeRepository shoeRepository;
  @Autowired
  private CategoryRepository categoryRepository;
  @Autowired
  private UserRepository userRepository;


  public List<ShoeModel> all(Integer min, Integer max, Long category, Boolean order) {
    Specification<ShoeModel> spec = buildSpecification(min, max, category, order);
    return shoeRepository.findAll(spec);
  }


  public ShoeModel byId(Long id) {
    return shoeRepository.findById(id).orElseThrow(() -> new ShoeNotFoundException("Not found shoe with ID: " + id));
  }

  public ShoeModel create(ShoeRequest request) {
    ShoeCategoryModel category = category(request.getCategory_id());
    ShoeModel shoe = ShoeModel.builder()
        .title(request.getTitle())
        .price(request.getPrice())
        .description(request.getDescription())
        .category(category)
        .user(user(username()))
        .image(request.getImage())
        .build();
    shoeRepository.save(shoe);
    return shoe;
  }

  public ShoeModel updateShoe(Long id, ShoeUpdateRequest request) {
    ShoeModel shoe = byId(id);
    shoe.setTitle(request.getTitle());
    shoe.setDescription(request.getDescription());
    shoe.setPrice(request.getPrice());
    return shoe;
  }

  public ShoeModel delete(Long id) {
    ShoeModel shoe = byId(id);
    shoeRepository.deleteById(id);
    return shoe;
  }

  private Specification<ShoeModel> buildSpecification(Integer min, Integer max, Long category, Boolean order) {
    return (root, query, cb) -> {
      List<Predicate> predicates = new ArrayList<>();

      if (min != null && !min.equals(0)) {
        predicates.add(cb.greaterThanOrEqualTo(root.get("price"), min));
      }
      if (max != null && !max.equals(0)) {
        predicates.add(cb.lessThanOrEqualTo(root.get("price"), max));
      }
      if (category != null) {
        predicates.add(cb.equal(root.get("category").get("id"), category));
      }
      if (order != null && order) {
        query.orderBy(cb.asc(root.get("price")));
      }

      return cb.and(predicates.toArray(new Predicate[0]));
    };
  }

  private ShoeCategoryModel category(Long id) {
    return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Not found category with ID: " + id));
  }

  private String username() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserDetails user = (UserDetails) auth.getPrincipal();
    return user.getUsername();
  }

  private UserModel user(String username) {
    return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("Not found user"));
  }


}
