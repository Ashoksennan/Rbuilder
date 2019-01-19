package com.example.admin.resumebuilderproject.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.admin.resumebuilderproject.R;
import com.example.admin.resumebuilderproject.model.TemplateUsageBean;
import com.example.admin.resumebuilderproject.view_holders.TemplateViewHolder;

import java.util.ArrayList;
import java.util.List;

public class TemplateListAdapter extends RecyclerView.Adapter<TemplateViewHolder> {
    List<TemplateUsageBean> templateUsageBeans;
    ItemClickInterface itemClickInterface;

    public TemplateListAdapter(List<TemplateUsageBean> templateUsageBeans,ItemClickInterface itemClickInterface) {
        this.templateUsageBeans = templateUsageBeans;
        this.itemClickInterface = itemClickInterface;
    }

    @NonNull
    @Override
    public TemplateViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new TemplateViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_template_row,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TemplateViewHolder templateViewHolder, final int i) {
        final TemplateUsageBean templateUsageBean = templateUsageBeans.get(i);
        templateViewHolder.tv_tot_downloads.setText(templateUsageBean.getUsageCount()+" Downloads");
        templateViewHolder.fab_preview_resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickInterface.clickItem(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return templateUsageBeans.size();
    }

    public interface ItemClickInterface{
        public void clickItem(int templateId);
    }
}
