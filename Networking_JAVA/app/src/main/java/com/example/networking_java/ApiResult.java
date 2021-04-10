package com.example.networking_java;

import java.util.ArrayList;

public class ApiResult {
    int total_count;
    boolean incomplete_results;
    ArrayList<GitHubUser> items;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public boolean isIncomplete_results() {
        return incomplete_results;
    }

    public void setIncomplete_results(boolean incomplete_results) {
        this.incomplete_results = incomplete_results;
    }

    public ArrayList<GitHubUser> getItems() {
        return items;
    }

    public void setItems(ArrayList<GitHubUser> items) {
        this.items = items;
    }
}
