package fr.uge.reddit.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public class PageDTO<E>{

    private final int maxPage;
    private final long resultSize;
    private final int pageSize;
    private final int currentPage;
    private final List<E> content;

    private PageDTO(int maxPage, long resultSize, int pageSize, int currentPage, List<E> content){
        this.maxPage = maxPage;
        this.resultSize = resultSize;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.content = content;
    }

    public static <F> PageDTO<F> fromPage(Page<F> page){
        System.out.println(">>> page : " + page.getContent() + " ::: " + page.getTotalPages());
        return new PageDTO<>(page.getTotalPages(), page.getTotalElements(), page.getSize(), page.getNumber(), page.getContent());
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public List<E> getContent() {
        return content;
    }

    public long getResultSize() {
        return resultSize;
    }
}
