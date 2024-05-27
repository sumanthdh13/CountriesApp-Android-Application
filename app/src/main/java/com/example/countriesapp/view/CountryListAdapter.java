package com.example.countriesapp.view;

import com.example.countriesapp.R;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.countriesapp.databinding.ItemCountryBinding;
import com.example.countriesapp.model.CountryModel;
import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {

    private List<CountryModel> countries;

    public CountryListAdapter(List<CountryModel> countries) {
        this.countries = countries;
    }

    public void updateCountries(List<CountryModel> newCountries) {
        countries.clear();
        countries.addAll(newCountries);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        CountryModel country = countries.get(position);
        holder.countryBinding.name.setText(country.getCountryName());
        holder.countryBinding.capital.setText(country.getCapital());
        Util.loadImage(holder.countryBinding.imageView, country.getFlag(), Util.getProgressDrawable(holder.itemView.getContext()));
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    @SuppressLint("NonConstantResourceId")
    class CountryViewHolder extends RecyclerView.ViewHolder {
        ItemCountryBinding countryBinding;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            countryBinding = ItemCountryBinding.bind(itemView);
        }
    }
}
