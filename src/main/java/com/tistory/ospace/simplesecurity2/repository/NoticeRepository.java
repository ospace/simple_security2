package com.tistory.ospace.simplesecurity2.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

import com.tistory.ospace.simplesecurity2.entity.Notice;
import com.tistory.ospace.simplesecurity2.entity.Search;

//@PreAuthorize("hasPermission(#id, 'com.test.Foo', read) or hasPermission(#id, 'com.test.Foo', admin)")
//@PreAuthorize("hasRole('ROLE_USER')")
//@PostFilter("hasPermission(filterObject, read) or hasPermission(filterObject, admin)")
//@PreAuthorize("hasPermission(#report, write) or hasPermission(#report, admin)")
@Mapper
public interface NoticeRepository {
	Integer countBy(@Param("search") Search search, @Param("entity") Notice entity);
	
	
	@PostFilter("hasPermission(filterObject, 'READ')")
	List<Notice> findAllBy(@Param("search") Search search, @Param("entity") Notice entity);
	
	@PostAuthorize("hasPermission(returnObject, 'READ')")
	Notice findById(Integer id);
	
	@PreAuthorize("hasPermission(#entity, 'WRITE')")
	void insert(@Param("entity")Notice entity);
}
