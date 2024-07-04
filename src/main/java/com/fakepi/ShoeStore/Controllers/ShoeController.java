package com.fakepi.ShoeStore.Controllers;

import com.fakepi.ShoeStore.Dtos.ShoeUpdateRequest;
import com.fakepi.ShoeStore.Models.ShoeModel;
import com.fakepi.ShoeStore.Dtos.ShoeRequest;
import com.fakepi.ShoeStore.Service.ShoeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ShoeController {
  private final ShoeService shoeService;

  @GetMapping("/all")
  ResponseEntity<List<ShoeModel>> allShoe(
      @RequestParam(required = false) Integer price_min,
      @RequestParam(required = false) Integer price_max,
      @RequestParam(required = false) Long category_id,
      @RequestParam(required = false) Boolean order
  ) {
    if (price_min != null && price_max != null) {
      if (price_min > price_max) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
    }
    return ResponseEntity.ok(shoeService.all(price_min, price_max, category_id, order));
  }
  @GetMapping("/{id}")
  ResponseEntity<ShoeModel> shoeById(@PathVariable Long id) {
    return ResponseEntity.ok(shoeService.byId(id));
  }

  @PostMapping("/create")
  ResponseEntity<ShoeModel> createShoe(@RequestBody ShoeRequest request) {
    return ResponseEntity.ok(shoeService.create(request));
  }

  @PutMapping("/{id}")
  ResponseEntity<ShoeModel> updateShoe(
      @PathVariable Long id,
      @RequestBody ShoeUpdateRequest request) {
    return ResponseEntity.ok(shoeService.updateShoe(id, request));
  }

  @DeleteMapping("/{id}")
  ResponseEntity<ShoeModel> deleteShoe(@PathVariable Long id) {
    return ResponseEntity.ok(shoeService.delete(id));
  }

}
