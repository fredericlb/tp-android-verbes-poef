package fr.epsi.verbes;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ConnexionActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_connexion);
  }

  public void onSubmit(View v) {
    Intent intent = new Intent();
    intent.putExtra("nom", "Langlade");
    intent.putExtra("prenom", "Frédéric");

    setResult(Activity.RESULT_OK, intent);
    finish();
  }
}
