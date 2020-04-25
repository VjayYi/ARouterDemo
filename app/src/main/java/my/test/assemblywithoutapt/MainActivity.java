package my.test.assemblywithoutapt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import my.test.annotation.BindView;
import my.test.arount.Arouter;

@BindView("main/mainActivity")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Arouter.getInstance().jumpActivity("login/LoginActivity",null);

            }
        });
    }
}
