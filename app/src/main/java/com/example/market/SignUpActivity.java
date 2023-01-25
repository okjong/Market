package com.example.market;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.example.market.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);

        binding.btnSignup.setOnClickListener(v -> clickSignup());

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    void clickSignup(){
        String email = binding.etEmail.getText().toString();
        String password=binding.etPassword.getText().toString();
        String passwordConfirm=binding.etPasswordConfirm.getText().toString();

//        if (password!=passwordConfirm){
//            Toast.makeText(this, "일치하지 않습니다.", Toast.LENGTH_SHORT).show();
////            AlertDialog.Builder alertDialog= new AlertDialog.Builder(SignUpActivity.this);
////            alertDialog.setMessage("비밀번호가 일치하지 않습니다.").show();
//            binding.etPasswordConfirm.selectAll();
//            return;
//        }

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("EmailUser")
                .whereEqualTo("email",email)
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (queryDocumentSnapshots.getDocuments().size()>0){
                            AlertDialog.Builder builder=new AlertDialog.Builder(SignUpActivity.this);
                            builder.setMessage("문제가 있습니다.").show();
                            binding.etEmail.requestFocus();
                            binding.etEmail.selectAll();
                        }else {
                            HashMap<String,String> user= new HashMap<>();
                            user.put("email",email);
                            user.put("password",password);

                            db.collection("EmailUser").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    AlertDialog.Builder builder=new AlertDialog.Builder(SignUpActivity.this);
                                    builder.setMessage("회원가입이 완료되었습니다.")
                                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    finish();
                                                }
                                            }).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(SignUpActivity.this, "회원가입 실패"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    }
                });






    }
}





































































