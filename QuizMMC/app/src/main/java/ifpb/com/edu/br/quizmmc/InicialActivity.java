package ifpb.com.edu.br.quizmmc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

public class InicialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
    }
    public void telaquiz(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void telavideo(View view) {

        Intent intent = new Intent(this, VideoActivity.class);
        startActivity(intent);
    }
}
