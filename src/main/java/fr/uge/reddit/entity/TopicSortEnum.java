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

/*
    public static TopicSortEnum fromValue(String value) {
        if(value == null)
            return null;
        var valueLc = value.toLowerCase();
        for(TopicSortEnum enumValue: values()){
            if(valueLc.equals(enumValue.getSortType())){
                return enumValue;
            }
        }
        throw new IllegalArgumentException("Invalid enum value type");
    }
*/

}
