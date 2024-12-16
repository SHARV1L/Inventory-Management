package com.example.inventory_app.controller;

import com.example.inventory_app.model.Item;
import com.example.inventory_app.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<Item> getAllItems(){
        return itemService.getAllItems();
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
}
