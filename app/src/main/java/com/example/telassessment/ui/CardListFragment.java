package com.example.telassessment.ui;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.telassessment.R;
import com.example.telassessment.adapter.CardListAdapter;
import com.example.telassessment.databinding.FragmentListCardBinding;
import com.example.telassessment.model.DataModel;
import com.example.telassessment.viewmodel.CardsListViewModel;

import java.util.List;

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
        observeViewModel(mViewModel);
    }

    private void observeViewModel(CardsListViewModel mViewModel) {
        // Update the list when the data changes
        mViewModel.getListObservable().observe(this, new Observer<DataModel>() {
            @Override
            public void onChanged(@Nullable DataModel data) {
                if (data != null) {
                    adapter.setList(data);
                }
            }
        });
    }

}
