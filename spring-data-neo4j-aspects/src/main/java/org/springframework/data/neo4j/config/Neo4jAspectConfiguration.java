/**
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.neo4j.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.support.node.Neo4jNodeBacking;
import org.springframework.data.neo4j.support.node.NodeEntityStateFactory;
import org.springframework.data.neo4j.support.relationship.Neo4jRelationshipBacking;

/**
 * @author mh
 * @since 30.09.11
 */
public class Neo4jAspectConfiguration extends Neo4jConfiguration {
    @Bean
    public Neo4jRelationshipBacking neo4jRelationshipBacking() throws Exception {
        Neo4jRelationshipBacking aspect = Neo4jRelationshipBacking.aspectOf();
        aspect.setGraphDatabaseContext(graphDatabaseContext());
aspect.setRelationshipEntityStateFactory(relationshipEntityStateFactory());
        return aspect;
    }

    @Bean
	public Neo4jNodeBacking neo4jNodeBacking() throws Exception {
		Neo4jNodeBacking aspect = Neo4jNodeBacking.aspectOf();
		aspect.setGraphDatabaseContext(graphDatabaseContext());
        NodeEntityStateFactory entityStateFactory = nodeEntityStateFactory();
		aspect.setNodeEntityStateFactory(entityStateFactory);
		return aspect;
	}
}
