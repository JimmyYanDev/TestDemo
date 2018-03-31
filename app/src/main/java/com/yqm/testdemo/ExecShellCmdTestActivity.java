package com.yqm.testdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * 执行shell命令测试Activity
 *
 * @author michealyan
 * email: yanqinming@hymost.com
 * created at 2018/3/31 11:03
 */
public class ExecShellCmdTestActivity extends AppCompatActivity {

    private EditText mCmdEditText;
    private Button mExecButton;
    private TextView mResultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exec_shell_cmd_test);

        mCmdEditText = findViewById(R.id.et_cmd);
        mExecButton = findViewById(R.id.btn_exec);
        mResultText = findViewById(R.id.tv_result);

        mExecButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exec();
            }
        });

        mCmdEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // 这两个条件必须同时成立，如果仅仅用了enter判断，就会执行两次
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    // 执行发送消息等操作
                    exec();
                    return true;
                }
                return false;
            }
        });
    }

    private void exec() {
        String cmd;
        String result;
        cmd = mCmdEditText.getText().toString();
        result = "执行结果如下：\n" + ExecShellCmdUtil.execShellCmd(cmd);
        mResultText.setText("");
        mResultText.setText(result);
    }
}
