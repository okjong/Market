package com.example.market;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.market.databinding.ActivityDetailBinding;
import com.example.market.databinding.ActivityEditBinding;

public class DetailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;

    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.detailToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Intent intent=getIntent();

        String title=intent.getStringExtra("title");
        String price=intent.getStringExtra("price");
        String img=intent.getStringExtra("img");
        String nick=intent.getStringExtra("nick");
        userId=intent.getStringExtra("userId");

        binding.detailTitle.setText(G.selectedItem.name);
        binding.detailTitle02.setText(title);
        binding.detailPrice.setText(price);
        binding.detailUserNick.setText(nick);
        //detail_userId.setText(userId);

        Glide.with(this).load(img).into(binding.detailImg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topmenu_detail,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.detail_cart){
            Toast.makeText(this, "만들 예정", Toast.LENGTH_SHORT).show();
        }

        if(id==R.id.detail_chat){

            if(userId.equals(G.userVo.id)){
                Intent intent= new Intent(this,ChattingListActivity.class);
                startActivity(intent);
            }else{
                Intent intent= new Intent(this,ChattingActivity.class);
                startActivity(intent);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}