package fr.uge.reddit.entity;

import java.util.Locale;

public enum TopicSortEnum {

    NEWEST("Newest"),
    BEST("Best"),
    WORST("Worst"),
    OLDEST("Oldest");

    private String sortType;


    private TopicSortEnum(String sortType){
        this.sortType = sortType;
    }

    public String getSortType() {
        return  sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }


}
