/*
 * Copyright (c)  [2011-2015] "Pivotal Software, Inc." / "Neo Technology" / "Graph Aware Ltd."
 *
 * This product is licensed to you under the Apache License, Version 2.0 (the "License").
 * You may not use this product except in compliance with the License.
 *
 * This product may include a number of subcomponents with
 * separate copyright notices and license terms. Your use of the source
 * code for these subcomponents is subject to the terms and
 * conditions of the subcomponent's license, as noted in the LICENSE file.
 */

package org.springframework.data.neo4j.integration.movies.repo;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.integration.movies.domain.User;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Michal Bachman
 */
@Repository
public interface UserRepository extends GraphRepository<User> {

    Collection<User> findByName(String name);

    Collection<User> findByMiddleName(String middleName);

    @Query("MATCH (user:User) RETURN COUNT(user)")
    int findTotalUsers();

    @Query("MATCH (user:User) RETURN user.id")
    List<Integer> getUserIds();

    @Query("MATCH (user:User) RETURN user.name, user.id")
    Iterable<Map<String,Object>> getUsersAsProperties();

    @Query("MATCH (user:User) RETURN user")
    Collection<User> getAllUsers();

    @Query("MATCH (m:Movie)<-[:ACTED_IN]-(a:User) RETURN m.title as movie, collect(a.name) as cast")
    List<Map<String, Object>> getGraph();

    @Query("MATCH (user:User{name:{name}}) RETURN user")
    User findUserByNameWithNamedParam(@Param("name") String name);

    @Query("MATCH (user:User{name:{0}}) RETURN user")
    User findUserByName(String name);
}
