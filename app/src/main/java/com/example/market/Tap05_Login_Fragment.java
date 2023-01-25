package com.example.market;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

public class Tap05_Login_Fragment extends Fragment {

    TextView nickName;
    CircleImageView ivProfile;
    Button kakaoBtn;

    Button logBtn;
    Button logOut;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_5_login,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logBtn = view.findViewById(R.id.logBtn);

        nickName = view.findViewById(R.id.nickName);
        ivProfile = view.findViewById(R.id.ivProfile);
        kakaoBtn = view.findViewById(R.id.kakaoBtn);
        logOut = view.findViewById(R.id.logOut);

//        logBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(getContext(),)
//            }
//        });

        kakaoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserApiClient.getInstance().loginWithKakaoAccount(getContext(), new Function2<OAuthToken, Throwable, Unit>() {
                    @Override
                    public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                        if (oAuthToken!=null){
                            Toast.makeText(getActivity(), "로그인성공", Toast.LENGTH_SHORT).show();

                            UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                                @Override
                                public Unit invoke(User user, Throwable throwable) {
                                    if (user!= null){
                                        String id=String.valueOf(user.getId());

                                        String nickname = user.getKakaoAccount().getProfile().getNickname();
                                        String profileImage= user.getKakaoAccount().getProfile().getThumbnailImageUrl();

                                        String email = user.getKakaoAccount().getEmail();

                                        nickName.setText(nickname);

                                        G.userVo.name=nickname;
                                        G.userVo.id=id;
                                        G.userVo.imgUrl=profileImage;
                                        FirebaseDatabase.getInstance().getReference("user").child(id).setValue(nickname).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(getContext(), "OK", Toast.LENGTH_SHORT).show();
                                                Glide.with(Tap05_Login_Fragment.this).load(profileImage).into(ivProfile);
                                            }
                                        });


                                    }else {
                                        Toast.makeText(getContext(), "실패", Toast.LENGTH_SHORT).show();
                                    }

                                    return null;
                                }
                            });
                        }

                        return null;
                    }
                });
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserApiClient.getInstance().logout(new Function1<Throwable, Unit>() {
                    @Override
                    public Unit invoke(Throwable throwable) {
                        if (throwable!=null){
                            Toast.makeText(getContext(), "logout실패", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getContext(), "로그아웃", Toast.LENGTH_SHORT).show();

                            nickName.setText("닉네임");
                            Glide.with(Tap05_Login_Fragment.this).load(R.drawable.profile_image).into(ivProfile);
                        }

                        return null;
                    }
                });
            }
        });


    }
}
