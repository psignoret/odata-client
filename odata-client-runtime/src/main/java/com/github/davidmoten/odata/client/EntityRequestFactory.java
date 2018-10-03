package com.github.davidmoten.odata.client;

public interface EntityRequestFactory<T extends ODataEntity, R extends EntityRequest<T>> {

    R create(ContextPath contextPath);

}
