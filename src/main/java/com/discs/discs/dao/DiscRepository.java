package com.discs.discs.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.discs.discs.model.Disc;

@Repository
public interface DiscRepository extends MongoRepository<Disc, Long>, DiscRepositoryCustom {
	
	List<Disc> findAll(Sort sortByNameAtAsc);
	
	Disc findById(long id);

		
}
