package com.sys.volunteer.test;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.dao.CommonDao;
@Service
@Transactional
public class RandomServiceImpl extends CommonDao implements RandomService{

}
