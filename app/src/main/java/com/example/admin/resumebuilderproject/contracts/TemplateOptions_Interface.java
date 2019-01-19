package com.example.admin.resumebuilderproject.contracts;

import com.example.admin.resumebuilderproject.model.TemplateUsageBean;

import java.util.List;

public interface TemplateOptions_Interface {
    interface view{
        void setListOfTemplates(List<TemplateUsageBean> templateUsageBeans);
    }
    interface model{

    }
    interface presenter{
        void getListOfTemplates();
    }

}
