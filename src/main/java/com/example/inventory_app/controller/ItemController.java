package com.example.inventory_app.controller;

import com.example.inventory_app.model.Item;
import com.example.inventory_app.service.ItemService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

//    @GetMapping
//    public List<Item> getAllItems(){
//        return itemService.getAllItems();
//    }

    @GetMapping
    public ResponseEntity<Page<Item>> getAllItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.ok(itemService.getAllItems(pageRequest));
    }

    @GetMapping("/{id}")
    public Item getItemById(@PathVariable Long id){
        return itemService.getItemById(id);
    }

    @PostMapping
    public Item createItem(@RequestBody Item item){
        return itemService.createItem(item);
    }

    @PutMapping("/{id}")
    public Item updateItem(@PathVariable Long id, @RequestBody Item item) {
        return itemService.updateItem(id, item);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id){
        itemService.deleteItem(id);
    }

    public ResponseEntity<Item> getItemByBarcode(@PathVariable String barcode){
        Item item = itemService.getItemByBarcode(barcode);
        if(item != null){
            return ResponseEntity.ok(item);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<List<Item>> getExpiringItems(@RequestParam int days){
        return ResponseEntity.ok(itemService.getExpiringItems(days));
    }
}
