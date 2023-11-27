package com.yrgo.services.calls;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.yrgo.domain.Action;
import com.yrgo.domain.Call;
import com.yrgo.services.customers.CustomerManagementService;
import com.yrgo.services.customers.CustomerNotFoundException;
import com.yrgo.services.diary.DiaryManagementService;

@Transactional
@Service("callService")
public class CallHandlingServiceImpl implements CallHandlingService {

    @Autowired
    private CustomerManagementService customerManagementService;

    @Autowired
    private DiaryManagementService diaryManagementService;

    @Override
    public void recordCall(String customerId, Call newCall, Collection<Action> actions)
            throws CustomerNotFoundException {
        customerManagementService.recordCall(customerId, newCall);
        for (Action action : actions) {
            diaryManagementService.recordAction(action);
        }
    }

}
