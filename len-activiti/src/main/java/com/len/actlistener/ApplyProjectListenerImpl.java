package com.len.actlistener;

import org.activiti.engine.delegate.DelegateTask;

public class ApplyProjectListenerImpl extends ActNodeListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("\nApplyProjectListenerImpl is working\n");
        // vars 在PM提出申请任务完成时 就put了，也可在这完成
        super.notify(delegateTask);
    }
}
