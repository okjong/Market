package com.example.market;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.market.databinding.ActivityEditBinding;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EditActivity extends AppCompatActivity {

    ActivityEditBinding binding;

    String imgPath;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.userNickName.setText(G.userVo.name);
        userId=G.userVo.id;


    }

    public void clickSelectImage(View view){
        Intent intent= new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        resultLauncher.launch(intent);
    }

    ActivityResultLauncher<Intent> resultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode()!=RESULT_CANCELED){
                Intent intent=result.getData();
                Uri uri=intent.getData();
                Glide.with(EditActivity.this).load(uri).into(binding.pIv01);
                imgPath= getRealPathFromUri(uri);
                new AlertDialog.Builder(EditActivity.this).setMessage(imgPath).create().show();
            }
        }
    });

    String getRealPathFromUri(Uri uri){
        String[] proj= {MediaStore.Images.Media.DATA};
        CursorLoader loader= new CursorLoader(this,uri,proj,null,null,null);
        Cursor cursor=loader.loadInBackground();
        int column_index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result= cursor.getString(column_index);
        cursor.close();
        return result;
    }

   public void clickComplete(){

        String name=binding.pName01.getText().toString();
        String price=binding.pPrice01.getText().toString();
        String nickName=binding.userNickName.getText().toString();
        String id=userId;

       Retrofit retrofit=RetrofitHelper.getRetrofitInstanceGson();
       RetrofitService retrofitService=retrofit.create(RetrofitService.class);

       MultipartBody.Part filePart=null;
       if (imgPath!=null){
           File file= new File(imgPath);
           RequestBody requestBody= RequestBody.create(MediaType.parse("image/*"),file);
           filePart=MultipartBody.Part.createFormData("img",file.getName(),requestBody);
       }

       Map<String ,String > datePart = new HashMap<>();
       datePart.put("name",name);
       datePart.put("price",price);
       datePart.put("nickName",nickName);
       datePart.put("id",id);

       Call<String> call=retrofitService.postDataToServer(datePart,filePart);
       call.enqueue(new Callback<String>() {
           @Override
           public void onResponse(Call<String> call, Response<String> response) {
               String s=response.body();
               Toast.makeText(EditActivity.this, "응답"+s, Toast.LENGTH_SHORT).show();
           }

           @Override
           public void onFailure(Call<String> call, Throwable t) {
               Toast.makeText(EditActivity.this, "error"+t.getMessage(), Toast.LENGTH_SHORT).show();
           }
       });

       finish();
    }

}

















































