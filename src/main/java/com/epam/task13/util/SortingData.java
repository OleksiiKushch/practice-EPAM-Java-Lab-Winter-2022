package com.epam.task13.util;

public class SortingData {
    private String parameter;
    private String ordering;

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getOrdering() {
        return ordering;
    }

    public void setOrdering(String ordering) {
        this.ordering = ordering;
    }

    @Override
    public String toString() {
        return "SortingData{" +
                "parameter='" + parameter + '\'' +
                ", ordering='" + ordering + '\'' +
                '}';
    }
}
