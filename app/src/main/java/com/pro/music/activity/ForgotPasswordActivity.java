package com.pro.music.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.pro.music.R;
import com.pro.music.databinding.ActivityForgotPasswordBinding;
import com.pro.music.utils.StringUtil;
import com.pro.music.validator.ForgotPasswordValidator;

public class ForgotPasswordActivity extends BaseActivity {

    private ActivityForgotPasswordBinding mActivityForgotPasswordBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityForgotPasswordBinding = ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(mActivityForgotPasswordBinding.getRoot());

        initListener();
    }

    private void initListener() {
        mActivityForgotPasswordBinding.imgBack.setOnClickListener(v -> onBackPressed());
        mActivityForgotPasswordBinding.btnResetPassword.setOnClickListener(v -> onClickValidateResetPassword());
    }

    private void onClickValidateResetPassword() {
        String strEmail = mActivityForgotPasswordBinding.edtEmail.getText().toString().trim();

        ForgotPasswordValidator.ResetPasswordError error =
                ForgotPasswordValidator.validateEmail(strEmail);

        switch (error) {
            case EMAIL_REQUIRED:
                Toast.makeText(this, getString(R.string.msg_email_require), Toast.LENGTH_SHORT).show();
                return;
            case EMAIL_INVALID:
                Toast.makeText(this, getString(R.string.msg_email_invalid), Toast.LENGTH_SHORT).show();
                return;
            case OK:
                resetPassword(strEmail);
        }
    }

    private void resetPassword(String email) {
        showProgressDialog(true);
        FirebaseAuth auth = FirebaseAuth.getInstance();

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    showProgressDialog(false);
                    if (task.isSuccessful()) {
                        Toast.makeText(ForgotPasswordActivity.this,
                                getString(R.string.msg_reset_password_successfully),
                                Toast.LENGTH_SHORT).show();
                        mActivityForgotPasswordBinding.edtEmail.setText("");
                    }
                });
    }
}