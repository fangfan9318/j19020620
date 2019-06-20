package com.qf.j1902.service.impl;

import com.qf.j1902.mapper.RepayMapper;
import com.qf.j1902.pojo.Repay;
import com.qf.j1902.service.RepayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by Administrator on 2019/6/2.
 */
@Service
public class RepayServiceImpl implements RepayService {
    @Resource
    private RepayMapper repayMapper;
    @Override
    public boolean addRepay(String repaytype, String jine, String neirong, String picture, String repaycount, String xiangou, String yunfei, String fapiao, String repaytime, int projectid) {
        return repayMapper.addRepay(repaytype,jine,neirong,picture,repaycount,xiangou,yunfei,fapiao,repaytime,projectid);
    }

    @Override
    public ArrayList<Repay> getRepayById(int projectid) {
        return repayMapper.getRepayById(projectid);
    }
}
