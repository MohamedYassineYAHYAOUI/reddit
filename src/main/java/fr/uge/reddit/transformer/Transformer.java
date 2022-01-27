package fr.uge.reddit.transformer;

import java.util.Collection;
import java.util.List;

interface Transformer<D,M>{

    //M dtoToModel(D dto);

    D modelToDto(M model);

    //List<M> dtoToModel(Collection<D> dto);

    List<D> modelToDto(Collection<M> model);
}
