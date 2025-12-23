package com.sci.bpm.util;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Utility class for building dynamic JPA queries with conditional WHERE clauses.
 * Eliminates code duplication across DAO implementations.
 */
public class QueryBuilderUtil {

    /**
     * Builder class for constructing dynamic queries with parameters.
     */
    public static class DynamicQueryBuilder {
        private String baseQuery;
        private Map<String, Object> parameters;
        private StringBuilder whereClause;

        public DynamicQueryBuilder(String baseQuery) {
            this.baseQuery = baseQuery;
            this.parameters = new HashMap<>();
            this.whereClause = new StringBuilder();
        }

        /**
         * Add a WHERE condition to the query.
         * 
         * @param condition The condition string (e.g., " and m.field = :param")
         * @param paramName The parameter name
         * @param paramValue The parameter value
         * @return This builder instance for chaining
         */
        public DynamicQueryBuilder addCondition(String condition, String paramName, Object paramValue) {
            if (paramValue != null) {
                whereClause.append(condition);
                parameters.put(paramName, paramValue);
            }
            return this;
        }

        /**
         * Add a WHERE condition only if the value meets the condition check.
         * 
         * @param shouldAdd Whether to add the condition
         * @param condition The condition string
         * @param paramName The parameter name (can be null if no parameter needed)
         * @param paramValue The parameter value (can be null if no parameter needed)
         * @return This builder instance for chaining
         */
        public DynamicQueryBuilder addConditionIf(boolean shouldAdd, String condition, String paramName, Object paramValue) {
            if (shouldAdd) {
                whereClause.append(condition);
                if (paramName != null && paramValue != null) {
                    parameters.put(paramName, paramValue);
                }
            }
            return this;
        }

        /**
         * Add a WHERE condition only if the string value is not null or empty.
         * 
         * @param value The string value to check
         * @param condition The condition string
         * @param paramName The parameter name
         * @return This builder instance for chaining
         */
        public DynamicQueryBuilder addStringCondition(String value, String condition, String paramName) {
            if (value != null && !"".equals(value)) {
                whereClause.append(condition);
                parameters.put(paramName, value);
            }
            return this;
        }

        /**
         * Build the complete query string.
         * 
         * @return The complete query string with WHERE clause
         */
        public String buildQueryString() {
            if (whereClause.length() > 0) {
                // Use replace() instead of replaceAll() to avoid regex interpretation
                return baseQuery + whereClause.toString().replace("where and", "where");
            }
            return baseQuery;
        }

        /**
         * Build and create a JPA Query object with all parameters set.
         * 
         * @param em The EntityManager to create the query
         * @return The Query object with all parameters set
         */
        public Query buildQuery(EntityManager em) {
            String queryString = buildQueryString();
            Query query = em.createQuery(queryString);
            
            // Set all parameters on the query
            Iterator<String> keyset = parameters.keySet().iterator();
            while (keyset.hasNext()) {
                String key = keyset.next();
                query.setParameter(key, parameters.get(key));
            }
            
            return query;
        }

        /**
         * Build and create a native SQL Query object with all parameters set.
         * 
         * @param em The EntityManager to create the query
         * @return The native Query object with all parameters set
         */
        public Query buildNativeQuery(EntityManager em) {
            String queryString = buildQueryString();
            Query query = em.createNativeQuery(queryString);
            
            // Set all parameters on the query
            Iterator<String> keyset = parameters.keySet().iterator();
            while (keyset.hasNext()) {
                String key = keyset.next();
                query.setParameter(key, parameters.get(key));
            }
            
            return query;
        }

        /**
         * Get the parameters map.
         * 
         * @return The parameters map
         */
        public Map<String, Object> getParameters() {
            return parameters;
        }
    }

    /**
     * Create a new DynamicQueryBuilder instance.
     * 
     * @param baseQuery The base query string
     * @return A new DynamicQueryBuilder instance
     */
    public static DynamicQueryBuilder createBuilder(String baseQuery) {
        return new DynamicQueryBuilder(baseQuery);
    }

    /**
     * Set query parameters from a map.
     * 
     * @param query The Query object
     * @param parameters The parameters map
     */
    public static void setQueryParameters(Query query, Map<String, Object> parameters) {
        Iterator<String> keyset = parameters.keySet().iterator();
        while (keyset.hasNext()) {
            String key = keyset.next();
            query.setParameter(key, parameters.get(key));
        }
    }
}
