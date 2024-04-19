package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BookingServiceTest {

    @Test
    public void testPositiveBooking() throws CantBookException {
            // Создаем мок объект DatabaseService
            DatabaseService mockDatabaseService = Mockito.mock(DatabaseService.class);
            Mockito.when(mockDatabaseService.checkAvailability(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(true);
            Mockito.when(mockDatabaseService.bookSlot(any(String.class), any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(true);

            // Создаем объект BookingService с моком DatabaseService
            BookingService bookingService = new BookingService(mockDatabaseService);

            // Вызываем метод book с доступным временным слотом
            boolean result = bookingService.book("user123", LocalDateTime.now(), LocalDateTime.now().plusHours(1));

            // Утверждаем, что бронирование прошло успешно
            assertTrue(result);
        }


    @Test
    public void testNegativeBooking() {
        // Arrange
        DatabaseService mockDatabaseService = Mockito.mock(DatabaseService.class);
        BookingService bookingService = new BookingService(mockDatabaseService);

        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = from.plusHours(2);

        when(mockDatabaseService.checkAvailability(any(LocalDateTime.class), any(LocalDateTime.class))).thenReturn(false);

        // Act & Assert
        assertThrows(CantBookException.class, () -> bookingService.book("user123", from, to));
    }
}