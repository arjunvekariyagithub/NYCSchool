package com.test.nycschools.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.test.nycschools.home.databinding.FragmentSchoolDetailsBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SchoolDetailsFragment extends Fragment {

    private SchoolDetailsViewModel viewModel;
    private FragmentSchoolDetailsBinding binding;
    private Bundle args = getArguments();


    public static SchoolDetailsFragment newInstance() {
        return new SchoolDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentSchoolDetailsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(SchoolDetailsViewModel.class);
        viewModel.getSchoolDetails(getArguments().getString("schoolDbn"));
        viewModel.schoolDetails.observe(getViewLifecycleOwner(), schoolDetails -> {
            binding.noOfTestTakers.setText(
                    getString(R.string.number_of_test_takers, schoolDetails.numberOfTestTakers)
            );
            binding.mathScore.setText(
                    getString(R.string.math_scores, schoolDetails.mathAvgScore)
            );
            binding.readingScore.setText(
                    getString(R.string.reading_scores, schoolDetails.criticalReadingAvgScore)
            );
            binding.writingScore.setText(
                    getString(R.string.writing_scores, schoolDetails.writingAvgScore)
            );
        });

        viewModel.error.observe(getViewLifecycleOwner(), error -> {
            Snackbar.make(binding.getRoot(), "Something went wrong!", Snackbar.LENGTH_LONG).show();
        });
    }
}