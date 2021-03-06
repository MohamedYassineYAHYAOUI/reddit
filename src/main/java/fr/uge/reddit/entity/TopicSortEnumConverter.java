package fr.uge.reddit.entity;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TopicSortEnumConverter implements Converter<String, TopicSortEnum> {

    @Override
    public TopicSortEnum convert(String source) {
        if(source == null) {
            return null;
        }
        return TopicSortEnum.valueOf(source.toUpperCase());
    }
}
