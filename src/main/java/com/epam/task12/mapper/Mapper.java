package com.epam.task12.mapper;

public interface Mapper<S, T> {
    void map(S source, T target) throws MapException;
}
