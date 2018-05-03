package com.hengkai.officeautomationsystem.function.login;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatCheckBox;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hengkai.officeautomationsystem.R;
import com.hengkai.officeautomationsystem.base.BaseActivity;
import com.hengkai.officeautomationsystem.final_constant.NetworkTagFinal;
import com.hengkai.officeautomationsystem.function.MainActivity;
import com.hengkai.officeautomationsystem.utils.EditTextFilterUtil;
import com.hengkai.officeautomationsystem.utils.ToastUtil;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2018/4/26.
 * 登录页面
 */
public class LoginActivity extends BaseActivity<LoginPresenter> {

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.check_box)
    AppCompatCheckBox checkBox;
    @BindView(R.id.tv_forget_password)
    TextView tvForgetPassword;

    @Override
    protected int setupView() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        //设置沉浸式状态栏, 参数2: 颜色, 参数3: 透明度(0-255, 0表示透明, 255不透明)
        StatusBarUtil.setColor(this, getResources().getColor(R.color.transparent), 0);
        ButterKnife.bind(this);

        initText();


    }

    /**
     * 初始化TextView和EditText
     */
    private void initText() {
        tvForgetPassword.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);    //下划线
        etAccount.setFilters(EditTextFilterUtil.addSpaceFiltering());
        etPassword.setFilters(EditTextFilterUtil.addSpaceFiltering());
    }

    @Override
    protected ArrayList<String> cancelNetWork() {
        ArrayList<String> tags = new ArrayList<>();
        tags.add(NetworkTagFinal.LOGIN_ACTIVITY_LOGIN);
        return tags;
    }

    @Override
    protected LoginPresenter bindPresenter() {
        return new LoginPresenter();
    }

    @OnClick({R.id.btn_login, R.id.tv_forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                goToLogin();
                break;
            case R.id.tv_forget_password:

                break;
        }
    }

    /**
     * 登录
     */
    private void goToLogin() {
        String account = etAccount.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            ToastUtil.showToast("账号不能为空");
            return;
        }
        String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            ToastUtil.showToast("密码不能为空");
            return;
        }
        mPresenter.login(account, password);
    }

    /**
     * 登录成功
     */
    public void loginSuccess() {
        ToastUtil.showToast("登录成功");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 登录失败
     */
    public void loginFailure() {

    }


}
