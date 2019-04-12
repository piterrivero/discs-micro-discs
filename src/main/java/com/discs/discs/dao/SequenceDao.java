package com.discs.discs.dao;

import org.springframework.stereotype.Repository;

import com.discs.discs.exceptions.SequenceException;

@Repository
public interface SequenceDao {

	long getNextSequenceId(String key) throws SequenceException;
	
}
