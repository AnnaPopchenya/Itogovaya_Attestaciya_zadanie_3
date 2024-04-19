package org.example;

import java.time.LocalDateTime;

public class DatabaseService {
    public boolean checkAvailability(LocalDateTime from, LocalDateTime to) {
        return false;
    }

    public boolean bookSlot(String userId, LocalDateTime from, LocalDateTime to) {
           return true;
    }
}