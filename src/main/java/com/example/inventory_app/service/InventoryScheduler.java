package com.example.inventory_app.service;

import com.example.inventory_app.model.Item;
import com.example.inventory_app.repository.ItemRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InventoryScheduler {

    private final ItemRepository itemRepository;

    public InventoryScheduler(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    // The below annotation ensure the code check for expiring items every night.
//    @Scheduled(cron = "0 0 * * * ?")
//    public void checkForExpiringItems() {
//        List<Item> expiringItems = itemRepository.findAll().stream()
//                .filter(item -> item.getExpirationDate().isBefore(LocalDate.now().plusDays(3)))
//                .toList();
//
//
//        // Log for the expiring Items
//        if(!expiringItems.isEmpty()) {
//            System.out.println("Expiring Items: " + expiringItems);
//
//        }
//    }

    @Scheduled(cron = "0 0 * * * ?")
    public void notifyExpiringItems() {
        List<Item> expiringItems = itemRepository.findAll()
                .stream()
                .filter(item -> item.getExpirationDate().isBefore(LocalDate.now().plusDays(2)))
                .toList();

        if (!expiringItems.isEmpty()) {
            expiringItems.forEach(item -> System.out.println("Reminder: Item " + item.getName() + " is expiring soon!"));
            // Later, we will integrate an email or notification service here.
        }
    }

}
