package com.lab.server_search.service;

import com.lab.server_search.dao.DocSearchDao;
import com.lab.server_search.document.Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class DocSearchService {

    @Autowired
    private DocSearchDao docSearchDao;

    public Page<Doc> findAll() {
        PageRequest pageRequest = PageRequest.of(0,4);
        return docSearchDao.findAll(pageRequest);
    }

    public void addDoc(Doc doc) {
        docSearchDao.save(doc);
    }

    public Page<Doc> findByKeyWord(String keyWord,int page,int size) {
        PageRequest pageRequest = PageRequest.of(page-1,size);
        return docSearchDao.findByTitleOrAuthorOrDescribe(keyWord,keyWord,keyWord,pageRequest);
    }
}
