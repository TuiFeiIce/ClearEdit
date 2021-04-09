package com.yhyy.clearedit;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;

import com.yhyy.clearedit.widget.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Act_Login_Pswd extends BaseActivity {
    @BindView(R.id.et_account)
    ClearEditText etAccount;
    @BindView(R.id.et_pswd)
    ClearEditText etPswd;
    @BindView(R.id.iv_see)
    ImageView ivSee;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    int status = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login_pswd);
        ButterKnife.bind(this);
        initData();
        initToolBar();
        initListener();
    }

    private void initListener() {
        tvLogin.setEnabled(false);
        tvLogin.setBackgroundResource(R.drawable.round_gray_null_10);
        TextChange textChange = new TextChange();
        etAccount.addTextChangedListener(textChange);
        etPswd.addTextChangedListener(textChange);
    }

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
            if (etAccount.getText().toString().length() > 0 && etPswd.getText().toString().length() > 0) {
                tvLogin.setEnabled(true);
                tvLogin.setBackgroundResource(R.drawable.btn_blue_null_10);
            } else {
                tvLogin.setEnabled(false);
                tvLogin.setBackgroundResource(R.drawable.round_gray_null_10);
            }
        }
    }

    @OnClick({R.id.iv_see, R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_see:
                if (status == 0) {
                    etPswd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    etPswd.setSelection(etPswd.getText().length());
                    ivSee.setImageResource(R.drawable.edit_see);
                    status = 1;
                } else {
                    etPswd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    etPswd.setSelection(etPswd.getText().length());
                    ivSee.setImageResource(R.drawable.edit_unsee);
                    status = 0;
                }
                break;
            case R.id.tv_login:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //导入菜单布局
        getMenuInflater().inflate(R.menu.jumptele, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //创建菜单项的点击事件
        switch (item.getItemId()) {
            case R.id.jumptele:
                startActivity(new Intent(context, Act_Login_Tele.class));
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
