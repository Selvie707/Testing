package com.example.testing;

import java.util.ArrayList;

public class Root{
    public int page;
    public ArrayList<Result> results;
    public int total_pages;
    public int total_results;

    public int getPage() {
        return page;
    }

    public ArrayList<Result> getResults() {
        return results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }
}
