package com.example.demo.Models;

import java.io.Serializable;
import java.time.LocalDate;

public class FilterParams implements Serializable {
    private String status;
    private String search;
    private String preset;
    private LocalDate from;
    private LocalDate to;
    public FilterParams(){
    }

    public FilterParams(String status, String search, String preset, LocalDate from, LocalDate to) {
        this.status = status;
        this.search = search;
        this.preset = preset;
        this.from = from;
        this.to = to;
    }

    public String getStatus() {
        return status;
    }

    public String getSearch() {
        return search;
    }

    public String getPreset() {
        return preset;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

}
