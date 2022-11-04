package com.test.nycschools.home.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.test.nycschools.data.model.School;

public class SchoolComparator extends DiffUtil.ItemCallback<School> {
    @Override
    public boolean areItemsTheSame(@NonNull School oldItem, @NonNull School newItem) {
        return oldItem.dbn.equals(newItem.dbn);
    }

    @Override
    public boolean areContentsTheSame(@NonNull School oldItem, @NonNull School newItem) {
        return oldItem.dbn.equals(newItem.dbn);
    }
}