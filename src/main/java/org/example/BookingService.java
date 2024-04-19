package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class BookingService {

    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    private DatabaseService databaseService;

    public BookingService(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public boolean book(String userId, LocalDateTime from, LocalDateTime to) throws CantBookException {
        if (checkTimeInBD(from, to)) {
            return createBook(userId, from, to);
        }
        throw new CantBookException();
    }

    public boolean checkTimeInBD(LocalDateTime from, LocalDateTime to) {
        logger.info("Checking time in database for slot from {} to {}", from, to);
        return databaseService.checkAvailability(from, to);
    }

    public boolean createBook(String userId, LocalDateTime from, LocalDateTime to) {
        logger.info("Creating booking for user {} from {} to {}", userId, from, to);
        return databaseService.bookSlot(userId, from, to);
    }


}
