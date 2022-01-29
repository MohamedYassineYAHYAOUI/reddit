package fr.uge.reddit.entity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class TopicSortEnumConverter implements Converter<String, TopicSortEnum> {

    @Override
    public TopicSortEnum convert(String source) {
        return TopicSortEnum.valueOf(source.toUpperCase());
    }
}
