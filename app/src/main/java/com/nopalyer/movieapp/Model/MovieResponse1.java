package com.nopalyer.movieapp.Model;

import java.util.List;

public class MovieResponse1 {

    private String page;

    private String total_pages;


    private List<Upcoming> results;

    private String total_results;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    public List<Upcoming> getResults() {
        return results;
    }

    public void setResults(List<Upcoming> results) {
        this.results = results;
    }

    public String getTotal_results() {
        return total_results;
    }

    public void setTotal_results(String total_results) {
        this.total_results = total_results;
    }
}
