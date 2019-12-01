package com.mrcodekiddie.tobuytoday;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;



public class ShopOrderActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    List<Order> itemList;
    List<ItemData> itemDataList;

    private Toolbar toolbar;
    ItemMasterAdapter itemMasterAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_order);
        toolbar = findViewById(R.id.app_bar_order);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("KID");
        itemList = new ArrayList<Order>();

        itemList.add(new Order("001", "burger"));
        itemList.add(new Order("002", "pizza"));
        itemList.add(new Order("003", "sandwich"));
        itemList.add(new Order("004", "lemonade"));
        itemList.add(new Order("005", "cold coffee"));
        itemList.add(new Order("006", "hot coffee"));
        itemList.add(new Order("007", "black coffee"));
        itemList.add(new Order("008", "green tea"));
        itemList.add(new Order("010", "Idly"));
        itemList.add(new Order("011", "Dhosai"));
        itemList.add(new Order("012", "Pongal"));
        itemList.add(new Order("013", "Poori"));
        itemList.add(new Order("014", "Ooothappam"));
        itemList.add(new Order("015", "Vadai"));
        itemList.add(new Order("016", "Parotta"));
        itemList.add(new Order("017", "pani poori"));
        itemList.add(new Order("018", "bele poori"));
        itemList.add(new Order("019", "Omlete"));
        itemList.add(new Order("020", "kalakki"));
        itemList.add(new Order("021", "Half Boil"));
        itemList.add(new Order("022", "Full Boil"));


        itemDataList = new ArrayList<>();
        for (Order item : itemList) {
            itemDataList.add(new ItemData(item,new EditModel()));
        }


        mRecyclerView = findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(false);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(ShopOrderActivity.this));

        itemMasterAdapter = new ItemMasterAdapter(this,
                itemDataList);
        mRecyclerView.setAdapter(itemMasterAdapter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        itemList = new ArrayList<Order>();

    }

    private ArrayList<EditModel> populateList() {
        ArrayList<EditModel> list = new ArrayList<>();

        for (int i = 0; i < itemList.size(); i++) {
            EditModel editModel = new EditModel();
            editModel.setEditTextValue("");
            list.add(editModel);
        }
        return list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                itemMasterAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}
