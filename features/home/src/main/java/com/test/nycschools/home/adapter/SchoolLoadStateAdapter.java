package com.test.nycschools.home.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.LoadState;
import androidx.paging.LoadStateAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.test.nycschools.home.R;
import com.test.nycschools.home.databinding.LoadStateItemBinding;

import org.jetbrains.annotations.NotNull;

public class SchoolLoadStateAdapter extends LoadStateAdapter<SchoolLoadStateAdapter.LoadStateViewHolder> {
    // Define Retry Callback
    private final View.OnClickListener retryCallback;

    public SchoolLoadStateAdapter(View.OnClickListener retryCallback) {
        // Init Retry Callback
        this.retryCallback = retryCallback;
    }

    @NotNull
    @Override
    public LoadStateViewHolder onCreateViewHolder(@NotNull ViewGroup parent,
                                                  @NotNull LoadState loadState) {
        // Return new LoadStateViewHolder object
        return new LoadStateViewHolder(parent, retryCallback);
    }

    @Override
    public void onBindViewHolder(@NotNull LoadStateViewHolder holder,
                                 @NotNull LoadState loadState) {
        // Call Bind Method to bind visibility  of views
        holder.bind(loadState);
    }

    public static class LoadStateViewHolder extends RecyclerView.ViewHolder {
        // Define Progress bar
        private final ProgressBar progressBar;
        // Define error TextView
        private final TextView errorMsg;
        // Define Retry button
        private final Button retry;

        LoadStateViewHolder(
                @NonNull ViewGroup parent,
                @NonNull View.OnClickListener retryCallback) {
            super(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.load_state_item, parent, false));
            LoadStateItemBinding binding = LoadStateItemBinding.bind(itemView);
            progressBar = binding.progressBar;
            errorMsg = binding.errorMsg;
            retry = binding.retryButton;
            retry.setOnClickListener(retryCallback);
        }

        public void bind(LoadState loadState) {
            // Check load state
            if (loadState instanceof LoadState.Error) {
                // Get the error
                LoadState.Error loadStateError = (LoadState.Error) loadState;
                // Set text of Error message
                errorMsg.setText(loadStateError.getError().getLocalizedMessage());
            }
            // set visibility of widgets based on LoadState
            progressBar.setVisibility(loadState instanceof LoadState.Loading
                    ? View.VISIBLE : View.GONE);
            retry.setVisibility(loadState instanceof LoadState.Error
                    ? View.VISIBLE : View.GONE);
            errorMsg.setVisibility(loadState instanceof LoadState.Error
                    ? View.VISIBLE : View.GONE);
        }
    }
}
