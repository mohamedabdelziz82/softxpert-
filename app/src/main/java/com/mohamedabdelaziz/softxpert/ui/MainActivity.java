package com.mohamedabdelaziz.softxpert.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.mohamedabdelaziz.softxpert.R;
import com.mohamedabdelaziz.softxpert.adapter.CarsAdapter;
import com.mohamedabdelaziz.softxpert.databinding.ActivityMainBinding;
import com.mohamedabdelaziz.softxpert.model.Cars;
import com.mohamedabdelaziz.softxpert.model.CarsResponse;
import com.mohamedabdelaziz.softxpert.viewmodel.CarsViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private CarsViewModel viewModel;
    private CarsAdapter adapter;
    private List<Cars> carsList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(CarsViewModel.class);
        binding.buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(binding.pageNum.getText())){
                    viewModel.getCarsData(Integer.parseInt(binding.pageNum.getText().toString()));
                }else binding.pageNum.setError("Please Enter Page Number");

            }
        });

        initRecyclerView();
        viewModel.getCarsList().observe(this, new Observer<CarsResponse>() {
            @Override
            public void onChanged(CarsResponse response) {
               carsList=   response.getData();
//                Toast.makeText(MainActivity.this, carsList.get(0).getBrand(), Toast.LENGTH_SHORT).show();
                adapter.updateList(carsList);
            }
        });

        viewModel.errorsLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if (s.equals("HTTP 500 Internal Server Error"))
                {
                    Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    private void initRecyclerView() {
        binding.carsRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new CarsAdapter(MainActivity.this,carsList);
        binding.carsRecyclerView.setAdapter(adapter);
    }
}