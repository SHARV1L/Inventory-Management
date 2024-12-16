package com.example.inventory_app.service;

import com.example.inventory_app.model.Item;
import com.example.inventory_app.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item createItem(Item item){
        return itemRepository.save(item);
    }

    public Item getItemById(Long id){
        return itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item with the Id: " + id + " not found!"));
    }

    public Item updateItem(Long id, Item item){
        //Item existingItem = getItemById(id);
        Item existingItem = itemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Item Not Found"));

        existingItem.setName(item.getName());
        existingItem.setQuantity(item.getQuantity());
        existingItem.setExpirationDate(item.getExpirationDate());
        return itemRepository.save(existingItem);
    }

    public void deleteItem(Long id){
        itemRepository.deleteById(id);
    }

    public Item getItemByBarcode(String barcode) {
        return itemRepository.findByBarcode(barcode);
    }
}
