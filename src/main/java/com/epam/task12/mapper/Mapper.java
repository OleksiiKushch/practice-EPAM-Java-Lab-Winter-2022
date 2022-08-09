package com.epam.task12.mapper;

/**
 * Convert one object to another according to the logic in the method {@link #map(Object, Object)},
 * takes data from source object and populate (fills) target object.
 *
 * @param <S> source
 * @param <T> target
 *
 * @author Oleksii Kushch
 */
public interface Mapper<S, T> {
    void map(S source, T target) throws MapException;
}
