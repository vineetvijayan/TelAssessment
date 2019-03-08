package com.example.telassessment.ui;

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
import android.widget.Toast;

import com.example.telassessment.R;
import com.example.telassessment.adapter.CardListAdapter;
import com.example.telassessment.databinding.FragmentListCardBinding;
import com.example.telassessment.viewmodel.CardsListViewModel;

import java.util.Objects;

public class CardListFragment extends Fragment {

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
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initBinding();
        setAdapter();
    }

    private void setAdapter() {
        adapter = new CardListAdapter();
        binding.itemList.setAdapter(adapter);
        binding.itemList.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void initBinding() {
        CardsListViewModel mViewModel = new CardsListViewModel();
        binding.setViewModel(mViewModel);
        callApi(mViewModel);
        observeViewModel(mViewModel);
    }

    private void callApi(CardsListViewModel mViewModel) {
        // fetch list to be displayed
        mViewModel.fetchList();
    }

    private void observeViewModel(final CardsListViewModel mViewModel) {
        // Update the list when the data changes
        mViewModel.getListObservable().observe(this, data -> {
            if (data != null && data.getDataModel() != null) {

                // set list to recycler view adapter
                adapter.setList(data.getDataModel());

                // set action bar title
                if (!TextUtils.isEmpty(data.getDataModel().getTitle())) {
                    if (getActivity() instanceof CardsListActivity &&
                            getActivity().getActionBar() != null) {
                        Objects.requireNonNull(((CardsListActivity) getActivity()).getSupportActionBar()).setTitle(data.getDataModel().getTitle());
                    }
                }

                // dismiss progress
                mViewModel.isLoading.set(false);
                mViewModel.isError.set(false);

            } else {
                mViewModel.isLoading.set(false);
                mViewModel.isError.set(true);

                if (data != null && data.getThrowable() != null && !TextUtils.isEmpty(data.getThrowable().getMessage())) {
                    Toast.makeText(getActivity(), data.getThrowable().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
