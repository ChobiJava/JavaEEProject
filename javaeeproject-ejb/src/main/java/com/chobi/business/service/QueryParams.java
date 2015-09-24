package com.chobi.business.service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chobii on 24/09/15.
 */
public class QueryParams {

    private Map parameters = null;

    private QueryParams(String name, Object value) {
        this.parameters = new HashMap();
        this.parameters.put(name, value);
    }

    public static QueryParams with(String name, Object value) {
        return new QueryParams(name, value);
    }

    public QueryParams and(String name, Object value) {
        this.parameters.put(name, value);
        return this;
    }

    public Map parameters() {
        return this.parameters;
    }

}
