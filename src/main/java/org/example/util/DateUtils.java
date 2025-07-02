package org.example.util;

import org.example.entity.JoursFeries;
import org.example.repository.JoursFeriesRepository;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Component
public class DateUtils {

    private final JoursFeriesRepository joursFeriesRepository;

    public DateUtils(JoursFeriesRepository joursFeriesRepository) {
        this.joursFeriesRepository = joursFeriesRepository;
    }

    public LocalDate adjustReturnDate(LocalDate date, String regleAjustement) {
        if (date == null) {
            return null;
        }

        List<JoursFeries> joursFeries = joursFeriesRepository.findAll();
        LocalDate adjustedDate = date;

        // Check if the date falls on a weekend or holiday
        while (isNonWorkingDay(adjustedDate, joursFeries)) {
            if ("NEXT".equalsIgnoreCase(regleAjustement)) {
                adjustedDate = adjustedDate.plusDays(1);
            } else if ("PREVIOUS".equalsIgnoreCase(regleAjustement)) {
                adjustedDate = adjustedDate.minusDays(1);
            } else {
                throw new IllegalArgumentException("Invalid adjustment rule: " + regleAjustement);
            }
        }

        return adjustedDate;
    }

    private boolean isNonWorkingDay(LocalDate date, List<JoursFeries> joursFeries) {
        // Check if the date is a weekend (Saturday or Sunday)
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return true;
        }

        // Check if the date is a holiday
        return joursFeries.stream().anyMatch(jour -> jour.getDate().equals(date));
    }
}
