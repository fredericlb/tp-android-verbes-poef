package fr.epsi.verbes;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class Revisions extends AppCompatActivity {

  private ArrayList<Verbe> verbes = new ArrayList<>();
/*
  {
    "baseVerbale": "abide",
    "preterit": "abode",
    "participePasse": "abode",
    "traduction": "demeurer"
  },
  {
    "baseVerbale": "arise",
    "preterit": "arose",
    "participePasse": "arisen",
    "traduction": "s'élever"
  },
  {
    "baseVerbale": "awake",
    "preterit": "awoke",
    "participePasse": "awaken",
    "traduction": "se réveiller"
  }
 */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_revisions);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
          .setAction("Action", null).show();
      }
    });

    RecyclerView list = findViewById(R.id.verbsList);

    verbes.add(new Verbe("abide", "demeurer", "abode", "abode"));
    verbes.add(new Verbe("arise", "s'élever", "arose", "arisen"));
    verbes.add(new Verbe("awake", "se reveiller", "awoke", "awaken"));
    list.setLayoutManager(new LinearLayoutManager(this));
    list.setAdapter(new VerbAdapter(verbes));
  }

}
