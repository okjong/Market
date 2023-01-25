package com.example.market;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.market.databinding.ActivityLogin2Binding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class loginActivity extends AppCompatActivity {

    ActivityLogin2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLogin2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);

        binding.btnSignIn.setOnClickListener(v -> clickLogin());

    }

    void clickLogin(){
        String email = binding.etEmail.getText().toString();
        String password=binding.etPassword.getText().toString();

        FirebaseFirestore db=FirebaseFirestore.getInstance();
        db.collection("EmailUser")
                .whereEqualTo("email",email)
                .whereEqualTo("password",password)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots.getDocuments().size()>0){
                            String id = queryDocumentSnapshots.getDocuments().get(0).getId();
                            G2.userAccount=new UserAccount(id,email);

                            Intent intent= new Intent(loginActivity.this,Tap05_Login_Fragment.class);

                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);

                        }else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(loginActivity.this);
                            builder.setMessage("이메일과 비밀번호를 확인해주세요").show();
                            binding.etEmail.requestFocus();
                            binding.etEmail.selectAll();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(loginActivity.this, "실패"+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });




    }
}