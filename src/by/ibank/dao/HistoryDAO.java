package by.ibank.dao;

import by.ibank.entity.History;

import java.time.LocalDate;

public interface HistoryDAO {
    void addToHistory(int card_number, LocalDate date,int amount) throws ClassNotFoundException;
}
