package com.example.telassessment.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.telassessment.R;
import com.example.telassessment.adapter.CardListAdapter;
import com.example.telassessment.databinding.FragmentListCardBinding;
import com.example.telassessment.viewmodel.CardsListViewModel;

public class CardListFragment extends Fragment {

    private CardsListViewModel mViewModel;
    private FragmentListCardBinding binding;
    private CardListAdapter adapter;


    public static CardListFragment newInstance() {
        return new CardListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_list_card, container, false);

        adapter = new CardListAdapter();
        binding.itemList.setAdapter(adapter);
        binding.itemList.setLayoutManager(new LinearLayoutManager(getActivity()));

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(CardsListViewModel.class);
        binding.setViewModel(mViewModel);
        observeViewModel(mViewModel);
    }

    private void observeViewModel(final CardsListViewModel mViewModel) {
        // Update the list when the data changes
        mViewModel.getListObservable().observe(this, data -> {
            if (data != null) {

                // set list to recycler view adapter
                adapter.setList(data);

                // set action bar title
                if (!TextUtils.isEmpty(data.getTitle())) {
                    if (getActivity() instanceof CardsListActivity) {
                        ((CardsListActivity) getActivity()).getSupportActionBar().setTitle(data.getTitle());
                    }
                }

                // dismiss progress
                mViewModel.isLoading.set(false);
                mViewModel.isError.set(false);

            } else {
                mViewModel.isLoading.set(false);
                mViewModel.isError.set(true);
            }
        });
    }
}
