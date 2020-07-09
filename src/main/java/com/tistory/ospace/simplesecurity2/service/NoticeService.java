package com.tistory.ospace.simplesecurity2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tistory.ospace.simplesecurity2.entity.Notice;
import com.tistory.ospace.simplesecurity2.entity.Search;
import com.tistory.ospace.simplesecurity2.repository.NoticeRepository;


@Service
public class NoticeService {
	@Autowired
	private NoticeRepository noticeRepo;
	

	public int count(Search search) {
		return noticeRepo.countBy(search, null);
	}

	public List<Notice> search(Search search) {
		List<Notice> ret = noticeRepo.findAllBy(search, null);
		return ret;
	}
}

