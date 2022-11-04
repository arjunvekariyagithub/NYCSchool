package com.test.nycschools.home.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.test.nycschools.data.model.School;
import com.test.nycschools.home.R;
import com.test.nycschools.home.databinding.SchoolItemBinding;

import org.jetbrains.annotations.NotNull;

public class SchoolAdapter extends PagingDataAdapter<School, SchoolAdapter.SchoolViewHolder> {
    // Define Loading ViewType
    public static final int LOADING_ITEM = 0;
    // Define Movie ViewType
    public static final int SCHOOL_ITEM = 1;
    private final ItemClickListener itemClickListener;

    public SchoolAdapter(
            @NotNull DiffUtil.ItemCallback<School> diffCallback,
            ItemClickListener schoolItemClickListener
    ) {
        super(diffCallback);
        this.itemClickListener = schoolItemClickListener;
    }

    @NonNull
    @Override
    public SchoolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Return MovieViewHolder
        return new SchoolViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolViewHolder holder, int position) {
        School currentSchool = getItem(position);
        // Check for null
        if (currentSchool != null) {
            holder.schoolItemBinding.schoolName.setText(currentSchool.schoolName);
            holder.schoolItemBinding.getRoot().setOnClickListener(v -> {
                itemClickListener.onSchoolItemClick(currentSchool);
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        // set ViewType
        return position == getItemCount() ? SCHOOL_ITEM : LOADING_ITEM;
    }



    public static class SchoolViewHolder extends RecyclerView.ViewHolder {
        // Define movie_item layout view binding
        SchoolItemBinding schoolItemBinding;


        public SchoolViewHolder(@NonNull ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.school_item, parent, false));
            this.schoolItemBinding = SchoolItemBinding.bind(itemView);
        }
    }

    public interface ItemClickListener {
        void onSchoolItemClick(School school);
    }
}
