package fr.epsi.verbes;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InscriptionActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_inscription);
  }

  public void onSubmit(View v) {
    String prenom = ((EditText)findViewById(R.id.inscriptionFirstName)).getText().toString();
    String nom = ((EditText)findViewById(R.id.inscriptionLastName)).getText().toString();
    String mail = ((EditText)findViewById(R.id.inscriptionMail)).getText().toString();
    String password = ((EditText)findViewById(R.id.inscriptionPassword)).getText().toString();
    String niveau = ((EditText)findViewById(R.id.inscriptionNiveau)).getText().toString();

    Intent intent = new Intent();
    intent.putExtra("prenom", prenom);
    intent.putExtra("nom", nom);
    intent.putExtra("mail", mail);
    intent.putExtra("password", password);
    intent.putExtra("niveau", niveau);

    setResult(Activity.RESULT_OK, intent);
    finish();
  }
}
