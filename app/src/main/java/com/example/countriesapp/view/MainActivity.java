package com.example.countriesapp.view;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.view.View;
import com.example.countriesapp.databinding.ActivityMainBinding;
import com.example.countriesapp.viewmodel.ListViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    private ListViewModel viewModel;
    private CountryListAdapter adapter = new CountryListAdapter(new ArrayList<>());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        viewModel = new ViewModelProvider(this).get(ListViewModel.class);
        viewModel.refresh();
        mainBinding.countriesList.setLayoutManager(new LinearLayoutManager(this));
        mainBinding.countriesList.setAdapter(adapter);
        mainBinding.swiperefreshlayout.setOnRefreshListener(() -> {
            viewModel.refresh();
            mainBinding.swiperefreshlayout.setRefreshing(false);

        });
        observeViewModel();
    }

        private void observeViewModel() {
            viewModel.countries.observe(this, countryModels -> {
                if(countryModels != null){
                    mainBinding.countriesList.setVisibility(View.VISIBLE);
                    adapter.updateCountries(countryModels);
                }
            });
            viewModel.countryLoadError.observe(this, isError ->{
                if (isError != null){
                    mainBinding.listError.setVisibility(isError ? View.VISIBLE : View.GONE);
                }
            });
            viewModel.loading.observe(this,isLoading ->{
                if(isLoading != null){
                    mainBinding.loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                    if (isLoading){
                        mainBinding.listError.setVisibility(View.GONE);
                        mainBinding.countriesList.setVisibility(View.GONE);
                    }
                }
            });
        }
    }