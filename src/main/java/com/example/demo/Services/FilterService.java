package com.example.demo.Services;

import com.example.demo.Models.FilterParams;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FilterService {
    public FilterService() {
    }
    public FilterParams validateFilters(String status, String search, String preset, String from, String to){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fromDate = null;
        LocalDate toDate = null;
        if (from != ""){
            fromDate = LocalDate.parse(from, formatter);
        }
        if (to != ""){
            toDate = LocalDate.parse(to, formatter);
        }
        if (search == null){
            search = "";
        }
        if (preset == null){
            preset = "";
        }
        return new FilterParams(status, search.toLowerCase(Locale.ROOT), preset, fromDate, toDate);
    }
}
