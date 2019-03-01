package com.example.telassessment.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.telassessment.R;
import com.example.telassessment.databinding.ItemListContentBinding;
import com.example.telassessment.model.DataModel;
import com.example.telassessment.model.Rows;

import java.util.List;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.ViewHolder> {

    private List<Rows> mValues;

    public CardListAdapter() {

    }

    public void setList(DataModel list) {
        this.mValues = list.getRows();
        this.notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ItemListContentBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(parent.getContext()), R.layout.item_list_content,
                        parent, false);
        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.binding.setData(mValues.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mValues == null ? 0 : mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        final ItemListContentBinding binding;

        public ViewHolder(ItemListContentBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
