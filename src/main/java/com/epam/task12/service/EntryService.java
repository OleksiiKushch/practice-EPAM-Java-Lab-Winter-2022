package com.epam.task12.service;

public interface EntryService {
    default boolean isAddEnyRecordsToDb(int numOfAddedRecords) {
        return numOfAddedRecords > 0;
    }
}
