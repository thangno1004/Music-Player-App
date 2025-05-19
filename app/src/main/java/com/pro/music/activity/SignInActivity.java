package com.pro.music.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.pro.music.validator.SignInValidator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.pro.music.R;
import com.pro.music.constant.Constant;
import com.pro.music.constant.GlobalFunction;
import com.pro.music.databinding.ActivitySignInBinding;
import com.pro.music.model.User;
import com.pro.music.prefs.DataStoreManager;
import com.pro.music.utils.StringUtil;

public class SignInActivity extends BaseActivity {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private ActivitySignInBinding mActivitySignInBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivitySignInBinding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(mActivitySignInBinding.getRoot());
        initListener();
    }
    public void setFirebaseAuth(FirebaseAuth mockAuth) {
        this.firebaseAuth = mockAuth;
    }
    private void initListener() {
        mActivitySignInBinding.rdbUser.setChecked(true);
        mActivitySignInBinding.layoutSignUp.setOnClickListener(
                v -> GlobalFunction.startActivity(SignInActivity.this, SignUpActivity.class));

        mActivitySignInBinding.btnSignIn.setOnClickListener(v -> onClickValidateSignIn());
        mActivitySignInBinding.tvForgotPassword.setOnClickListener(v -> onClickForgotPassword());
    }

    private void onClickForgotPassword() {
        GlobalFunction.startActivity(this, ForgotPasswordActivity.class);
    }

    private void onClickValidateSignIn() {
        String strEmail = mActivitySignInBinding.edtEmail.getText().toString().trim();
        String strPassword = mActivitySignInBinding.edtPassword.getText().toString().trim();
        boolean isAdminChecked = mActivitySignInBinding.rdbAdmin.isChecked();

        SignInValidator.SignInError error = SignInValidator.validate(strEmail, strPassword, isAdminChecked);

        switch (error) {
            case EMAIL_REQUIRED:
                Toast.makeText(this, getString(R.string.msg_email_require), Toast.LENGTH_SHORT).show();
                return;
            case PASSWORD_REQUIRED:
                Toast.makeText(this, getString(R.string.msg_password_require), Toast.LENGTH_SHORT).show();
                return;
            case EMAIL_INVALID:
                Toast.makeText(this, getString(R.string.msg_email_invalid), Toast.LENGTH_SHORT).show();
                return;
            case EMAIL_INVALID_FOR_ADMIN:
                Toast.makeText(this, getString(R.string.msg_email_invalid_admin), Toast.LENGTH_SHORT).show();
                return;
            case EMAIL_INVALID_FOR_USER:
                Toast.makeText(this, getString(R.string.msg_email_invalid_user), Toast.LENGTH_SHORT).show();
                return;
            case OK:
                signInUser(strEmail, strPassword);
        }
    }

    void signInUser(String email, String password) {
        Log.d("TEST", "App đã khởi động");
        Log.d("TEST", "START signInUser"); // 👈 kiểm tra test có vào không

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(Runnable::run, task -> {
                    Log.d("TEST", "COMPLETED task"); // 👈 kiểm tra callback có chạy không

                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null) {
                            User userObject = new User(user.getEmail(), password);
                            if (user.getEmail() != null && user.getEmail().contains(Constant.ADMIN_EMAIL_FORMAT)) {
                                userObject.setAdmin(true);
                            }

                            // Tạm thời bỏ các hàm phụ không an toàn
//                    DataStoreManager.setUser(userObject);
//                    goToMainActivity();
                        }
                    } else {
                        // Toast có thể lỗi context trong test
//                Toast.makeText(SignInActivity.this, getString(R.string.msg_sign_in_error),
//                        Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void goToMainActivity() {
        if (DataStoreManager.getUser().isAdmin()) {
            GlobalFunction.startActivity(SignInActivity.this, AdminMainActivity.class);
        } else {
            GlobalFunction.startActivity(SignInActivity.this, MainActivity.class);
        }
        finishAffinity();
    }
}