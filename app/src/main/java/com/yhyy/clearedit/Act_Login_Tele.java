package com.yhyy.clearedit;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;

import com.yhyy.clearedit.widget.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Act_Login_Tele extends BaseActivity {
    @BindView(R.id.et_account)
    ClearEditText etAccount;
    @BindView(R.id.et_sms)
    ClearEditText etSms;
    @BindView(R.id.tv_sms)
    TextView tvSms;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login_tele);
        ButterKnife.bind(this);
        initData();
        initToolBar();
        initListener();
    }

    //初始化
    private void initListener() {
        tvLogin.setEnabled(false);
        tvLogin.setBackgroundResource(R.drawable.round_gray_null_10);
        TextChange textChange = new TextChange();
        etAccount.addTextChangedListener(textChange);
        etSms.addTextChangedListener(textChange);
    }

    //标题
    private void initToolBar() {
    }

    private void initData() {
    }

    class TextChange implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (etAccount.getText().toString().length() == 11) {
                if (num == 0) {
                    tvSms.setEnabled(true);
                    tvSms.setBackgroundResource(R.drawable.btn_blue_null_10);
                } else {
                    tvSms.setEnabled(false);
                    tvSms.setBackgroundResource(R.drawable.round_gray_null_10);
                }
                if (etSms.getText().length() == 6) {
                    tvLogin.setEnabled(true);
                    tvLogin.setBackgroundResource(R.drawable.btn_blue_null_10);
                } else {
                    tvLogin.setEnabled(false);
                    tvLogin.setBackgroundResource(R.drawable.round_gray_null_10);
                }
            } else {
                tvSms.setEnabled(false);
                tvSms.setBackgroundResource(R.drawable.round_gray_null_10);
                tvLogin.setEnabled(false);
                tvLogin.setBackgroundResource(R.drawable.round_gray_null_10);
            }
        }
    }

    @OnClick({R.id.tv_sms, R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sms:
                num = 1;
                downtime();
                break;
            case R.id.tv_login:
                break;
        }
    }

    private void downtime() {
        /** 倒计时60秒，一次1秒 */
        new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                tvSms.setText("还剩" + millisUntilFinished / 1000 + "秒");
                tvSms.setEnabled(false);
                tvSms.setBackgroundResource(R.drawable.round_gray_null_10);
            }

            @Override
            public void onFinish() {
                tvSms.setText("获取验证码");
                num = 0;
                tvSms.setEnabled(true);
                tvSms.setBackgroundResource(R.drawable.btn_blue_null_10);
            }
        }.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //导入菜单布局
        getMenuInflater().inflate(R.menu.jumppswd, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //创建菜单项的点击事件
        switch (item.getItemId()) {
            case R.id.jumppswd:
                startActivity(new Intent(context, Act_Login_Pswd.class));
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
