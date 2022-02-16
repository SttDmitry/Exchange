package com.base.exchange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.base.exchange.adapter.ExchangeAdapter;
import com.base.exchange.entity.ExchangeInfo;
import com.base.exchange.entity.ExchangeRate;
import com.base.exchange.repo.ExchangeCallback;
import com.base.exchange.repo.ExchangeRepo;
import com.base.exchange.util.Utils;
import com.google.gson.Gson;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity implements ExchangeCallback {
    private Gson gson;
    private RecyclerView exchangeList;
    private ExchangeAdapter exchangeAdapter;
    private EditText amountOfRoubles;
    private TextView amountOfExchange;
    private Spinner spinnerExchange;
    private ArrayAdapter<String> spinnerExchangeAdapter;
    private ExchangeInfo exchangeInfo;
    private List<String> exchangeNames;
    private Button refreshButton;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = getSharedPreferences(getString(R.string.APP_PREFERENCES), Context.MODE_PRIVATE);
        exchangeList = findViewById(R.id.exchangeList);
        amountOfRoubles = (EditText) findViewById(R.id.amountOfRoubles);
        amountOfExchange = findViewById(R.id.amountOfExchange);
        spinnerExchange = findViewById(R.id.spinnerExchange);
        refreshButton = findViewById(R.id.refreshButton);
        exchangeList.setLayoutManager(new LinearLayoutManager(this));
        amountOfRoubles.setOnKeyListener((v, keyCode, event) -> {
            exchangeRateExecute();
            return false;
        });
        spinnerExchange.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                exchangeRateExecute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        refreshButton.setOnClickListener(v -> requestExchangeInfo());
        gson = Utils.getGson();
        String info = sharedPreferences.getString("info", "");
        if (!info.isEmpty()) {
            getExchangeInfo(info, true);
        } else {
            requestExchangeInfo();
        }

    }

    public void exchangeRateExecute() {
        if (!amountOfRoubles.getText().toString().isEmpty()) {
            amountOfExchange.setText(Utils.getExchange(exchangeInfo.getRateList().get((int) spinnerExchange.getSelectedItemId()), Double.valueOf(String.valueOf(amountOfRoubles.getText()))));
        }
    }

    public void requestExchangeInfo() {
        try {
            ExchangeRepo.getData(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getExchangeInfo(String info, Boolean b) {
        exchangeInfo = gson.fromJson(info, ExchangeInfo.class);
        exchangeInfo.setRateList();
        exchangeAdapter = new ExchangeAdapter(exchangeInfo.getRateList());
        exchangeNames = exchangeInfo.getRateList().stream().map(ExchangeRate::getName).collect(Collectors.toList());
        runOnUiThread(() -> {
            exchangeList.setAdapter(exchangeAdapter);
            spinnerExchangeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, exchangeNames);
            spinnerExchangeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerExchange.setAdapter(spinnerExchangeAdapter);
            if (b && Utils.zonedDateTimeDifference(exchangeInfo.getDate(), ChronoUnit.SECONDS)) {
                requestExchangeInfo();
            } else if (!b) {
                sharedPreferences.edit().putString("info", info).apply();
            }
        });
    }
}