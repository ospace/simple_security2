package com.tistory.ospace.simplesecurity2.repository;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tistory.ospace.simplesecurity2.entity.Authority;
import com.tistory.ospace.simplesecurity2.entity.Search;


@Mapper
public interface AuthorityRepository {
	Integer countBy(@Param("search") Search search, @Param("entity") Authority entity);
	
	List<Authority> findAllBy(@Param("search") Search search, @Param("entity") Authority entity);
	
	List<Authority> findAllIn(@Param("ids") Collection<String> ids);
	
	Authority findById(String id);
	
	Authority findBy(Authority entity);
	
	void insert(Authority entity);
	
	void update(Authority entity);
	
	void deleteById(Integer id);

	
}
