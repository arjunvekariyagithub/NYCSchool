package com.test.nycschools.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.divider.MaterialDividerItemDecoration;
import com.test.nycschools.data.model.School;
import com.test.nycschools.home.adapter.SchoolAdapter;
import com.test.nycschools.home.adapter.SchoolComparator;
import com.test.nycschools.home.adapter.SchoolLoadStateAdapter;
import com.test.nycschools.home.databinding.FragmentHomeBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment implements SchoolAdapter.ItemClickListener {

    private HomeViewModel viewModel;
    private FragmentHomeBinding binding;
    private SchoolAdapter schoolAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        schoolAdapter = new SchoolAdapter(new SchoolComparator(), this);

        initRecyclerviewAndAdapter();

        viewModel.schoolPagingDataFlowable.subscribe(schoolPagingData -> {
            // submit new data to recyclerview adapter
            schoolAdapter.submitData(getLifecycle(), schoolPagingData);

            /* Given more time I would create empty view to represent empty state. */
        });
    }

    private void initRecyclerviewAndAdapter() {
        binding.schoolRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.schoolRecyclerView.addItemDecoration(
                new MaterialDividerItemDecoration(
                        requireContext(),
                        MaterialDividerItemDecoration.VERTICAL
                )
        );
        binding.schoolRecyclerView.setAdapter(
                // This will show end user a progress bar while pages are being requested from server
                schoolAdapter.withLoadStateFooter(
                        // When we will scroll down and next page request will be sent
                        // while we get response form server Progress bar will show to end user
                        new SchoolLoadStateAdapter(v -> schoolAdapter.retry())
                )
        );
    }

    @Override
    public void onSchoolItemClick(School school) {
        Bundle bundle = new Bundle();
        bundle.putString("schoolName", school.schoolName);
        bundle.putString("schoolDbn", school.dbn);
        NavHostFragment.findNavController(this)
                .navigate(R.id.action_homeFragment_to_schoolDetailsFragment, bundle);
    }
}