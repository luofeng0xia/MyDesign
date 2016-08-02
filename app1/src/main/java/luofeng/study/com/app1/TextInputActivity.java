package luofeng.study.com.app1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by weixi on 2016/8/2.
 */
public class TextInputActivity extends AppCompatActivity implements TextWatcher {

    private TextInputLayout textInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_textinput);
        textInput = (TextInputLayout) findViewById(R.id.til);
        textInput.getEditText().addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable.length()<6) {
            textInput.setError("用户名不得少于6位");
            textInput.setErrorEnabled(true);
        }else {
            textInput.setErrorEnabled(false);
        }
    }
}
