package com.example.inventory_app.controller;

import com.example.inventory_app.model.Item;
import com.example.inventory_app.service.ItemService;
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
    public ResponseEntity<List<Item>> getAllItems(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Item> pageResult = itemService.getAllItems(pageRequest);

        // Extract only the content (list of items) from the Page object
        List<Item> items = pageResult.getContent();
        return ResponseEntity.ok(items);
    }

//    @GetMapping("/{id}")
//    public Item getItemById(@PathVariable Long id){
//        return itemService.getItemById(id);
//    }
//
//    @PostMapping
//    public Item createItem(@RequestBody Item item){
//        return itemService.createItem(item);
//    }
//
//    @PutMapping("/{id}")
//    public Item updateItem(@PathVariable Long id, @RequestBody Item item) {
//        return itemService.updateItem(id, item);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteItem(@PathVariable Long id){
//        itemService.deleteItem(id);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id) {
        Item item = itemService.getItemById(id);
        return ResponseEntity.ok(item);
    }

    @PostMapping()
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item createdItem = itemService.createItem(item);
        return ResponseEntity.status(201).body(createdItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item item) {
        Item updatedItem = itemService.updateItem(id, item);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Item> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/barcode/{barcode}")
    public ResponseEntity<Item> getItemByBarcode(@PathVariable String barcode){
        Item item = itemService.getItemByBarcode(barcode);
        if(item != null){
            return ResponseEntity.ok(item);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/expiring")
    public ResponseEntity<List<Item>> getExpiringItems(@RequestParam int days){
        return ResponseEntity.ok(itemService.getExpiringItems(days));
    }
}
