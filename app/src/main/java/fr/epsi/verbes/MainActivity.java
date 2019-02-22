package fr.epsi.verbes;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    TextView welcomeLine = (TextView) findViewById(R.id.welcomeLine);
    welcomeLine.setVisibility(View.INVISIBLE);
  }

  public void onInscriptionBtnClicked(View v) {
    Intent intent = new Intent(this, InscriptionActivity.class);
    startActivityForResult(intent, 1);
  }

  public void onConnexionBtnClicked(View v) {
    Intent intent = new Intent(this, ConnexionActivity.class);
    startActivityForResult(intent, 2);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == 1 || requestCode == 2) {
      String nom = data.getStringExtra("nom");
      String prenom = data.getStringExtra("prenom");
      String joueurId = data.getStringExtra("joueurId");
      Log.w("verbesLog", nom);
      Log.w("verbesLog", prenom);
      Log.w("verbesLog/joueurId", joueurId);
      TextView welcomeLine = (TextView) findViewById(R.id.welcomeLine);
      welcomeLine.setVisibility(View.VISIBLE);
      welcomeLine.setText("Bienvenue " + prenom + " " + nom);
    }
  }
}
