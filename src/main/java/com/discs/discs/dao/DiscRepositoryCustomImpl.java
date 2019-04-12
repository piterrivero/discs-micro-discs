package com.discs.discs.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DiscRepositoryCustomImpl implements DiscRepositoryCustom {

	@Autowired
    MongoTemplate mongoTemplate;

}
