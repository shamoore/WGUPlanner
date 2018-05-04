package com.smoo182.wguplanner.logic;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.smoo182.wguplanner.data.PlannerRepository;
import com.smoo182.wguplanner.view.activities.TermDetailActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CustomViewModelFactory implements ViewModelProvider.Factory {
    private TermListViewModel termListViewModel;
    private final PlannerRepository repository;

    @Inject
    public CustomViewModelFactory(PlannerRepository repository) {
        this.repository = repository;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(TermListViewModel.class))
            return (T) new TermListViewModel(repository);
        else if(modelClass.isAssignableFrom(TermDetailViewModel.class))
            return (T) new TermDetailViewModel(repository);
        else {
            throw new IllegalArgumentException("ViewModel Not Found");
        }
    }
}
