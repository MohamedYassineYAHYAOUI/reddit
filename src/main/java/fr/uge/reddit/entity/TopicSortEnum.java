package fr.uge.reddit.entity;

public enum TopicSortEnum {

    BEST("best"),
    WORST("worst"),
    OLDEST("oldest"),
    NEWEST("newest");

    private String sortType;


    TopicSortEnum(String sortType){
        this.sortType = sortType;
    }

    public String getSortType() {
        return sortType;
    }

}
