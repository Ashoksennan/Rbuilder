package com.example.admin.resumebuilderproject.view_holders;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;

import com.example.admin.resumebuilderproject.R;

import android.view.View;
import android.widget.TextView;

public class TemplateViewHolder extends RecyclerView.ViewHolder {
    public TextView tv_tot_downloads;
    public FloatingActionButton fab_preview_resume;
    public TemplateViewHolder(@NonNull View itemView) {
        super(itemView);
        tv_tot_downloads = itemView.findViewById(R.id.tv_tot_downloads);
        fab_preview_resume = itemView.findViewById(R.id.fab_preview_resume);
    }

}
