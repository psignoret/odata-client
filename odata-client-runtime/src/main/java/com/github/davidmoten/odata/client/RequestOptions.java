package com.github.davidmoten.odata.client;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface RequestOptions {

    List<RequestHeader> getRequestHeaders();

    Map<String, String> getQueries();

    public static final RequestOptions EMPTY = new RequestOptions() {

        @Override
        public List<RequestHeader> getRequestHeaders() {
            return Collections.emptyList();
        }

        @Override
        public Map<String, String> getQueries() {
            return Collections.emptyMap();
        }

    };

}
