package com.github.davidmoten.odata.client;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CollectionPageEntity<T extends ODataEntityType> implements Paged<T, CollectionPageEntity<T>> {

    private final Class<T> cls;
    private final List<T> list;
    private final Optional<String> nextLink;
    private final ContextPath contextPath;
    private final SchemaInfo schemaInfo;

    public CollectionPageEntity(Class<T> cls, List<T> list, Optional<String> nextLink, ContextPath contextPath,
            SchemaInfo schemaInfo) {
        this.cls = cls;
        this.list = list;
        this.nextLink = nextLink;
        this.contextPath = contextPath;
        this.schemaInfo = schemaInfo;
    }

    @Override
    public List<T> currentPage() {
        return list;
    }

    @Override
    public Optional<CollectionPageEntity<T>> nextPage() {
        if (nextLink.isPresent()) {
            // TODO add request headers used in initial call?
            // TODO handle relative nextLink?
            HttpResponse response = contextPath.context().service().get(nextLink.get(), Collections.emptyList());
            // odata 4 says the "value" element of the returned json is an array of
            // serialized T see example at
            // https://www.odata.org/getting-started/basic-tutorial/#entitySet
            return Optional.of(contextPath.context().serializer().deserializeCollectionPageEntity(response.getText(),
                    cls, contextPath, schemaInfo));
        } else {
            return Optional.empty();
        }
    }

}
