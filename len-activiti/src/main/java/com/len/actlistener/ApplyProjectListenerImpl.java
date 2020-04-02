package com.len.actlistener;

import org.activiti.engine.delegate.DelegateTask;

public class ApplyProjectListenerImpl extends ActNodeListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("\nApplyProjectListenerImpl is working\n");
        super.notify(delegateTask);
    }
}
