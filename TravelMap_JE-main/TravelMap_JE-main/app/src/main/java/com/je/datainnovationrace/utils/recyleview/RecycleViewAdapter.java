package com.je.datainnovationrace.utils.recyleview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.je.datainnovationrace.R;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private final List<String> data;
    private int layoutId;

    public RecycleViewAdapter(List<String> data, int layoutId) {
        this.data = data;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}


