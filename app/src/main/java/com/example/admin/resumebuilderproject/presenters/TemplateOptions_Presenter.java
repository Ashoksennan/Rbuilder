package com.example.admin.resumebuilderproject.presenters;

import com.example.admin.resumebuilderproject.contracts.TemplateOptions_Interface;
import com.example.admin.resumebuilderproject.model.TemplateUsageBean;

import java.util.ArrayList;
import java.util.List;

public class TemplateOptions_Presenter implements TemplateOptions_Interface.presenter {
    TemplateOptions_Interface.view view;

    public TemplateOptions_Presenter(TemplateOptions_Interface.view view) {
        this.view = view;
    }


    @Override
    public void getListOfTemplates() {
        List<TemplateUsageBean> templateUsageBeans = new ArrayList<>();
        for(int i=0;i<8;i++){
            templateUsageBeans.add(new TemplateUsageBean(String.valueOf(i)));
        }
        view.setListOfTemplates(templateUsageBeans);
    }
}
