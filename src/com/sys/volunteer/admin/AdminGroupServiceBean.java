package com.sys.volunteer.admin;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sys.volunteer.dao.CommonDao;

@Service
@Transactional
public class AdminGroupServiceBean extends CommonDao implements AdminGroupService {

}
