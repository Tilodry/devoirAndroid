package yannroubeau.cgmatane.bibliotheque1.vue;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.HashMap;

import yannroubeau.cgmatane.bibliotheque1.R;
import yannroubeau.cgmatane.bibliotheque1.donnee.CoursDAO;
import yannroubeau.cgmatane.bibliotheque1.modele.Cours;

public class VueAjouterCours extends AppCompatActivity {

    protected EditText vueAjouterCoursChampTitre;
    protected TimePicker vueAjouterCoursChampHeure;
    protected CoursDAO coursDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_ajouter_cours);

        Button vueAjouterCoursActionAnnuler = (Button)findViewById(R.id.vueAjouterCoursActionAnnuler);

        vueAjouterCoursActionAnnuler.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View arg0)
                    {
                        naviguerRetourBibliotheque();
                    }
                }
        );

        vueAjouterCoursChampTitre = (EditText) findViewById(R.id.vueAjouterCoursChampTitre);
        vueAjouterCoursChampHeure = (TimePicker) findViewById(R.id.vueAjouterCoursChampAuteur);

        vueAjouterCoursChampHeure.setIs24HourView(true);

        Button vueAjouterCoursActionAjouter = (Button)findViewById(R.id.vueAjouterCoursActionAjouter);

        vueAjouterCoursActionAjouter.setOnClickListener(
                new View.OnClickListener()
                {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    public void onClick(View arg0)
                    {
                        enregistrerCours();
                        naviguerRetourBibliotheque();
                    }
                }
        );

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void enregistrerCours()
    {
        /*
        HashMap<String,String> cours;
        cours = new HashMap<String,String>();
        cours.put("titre", vueAjouterCoursChampTitre.getText().toString());
        cours.put("heure", vueAjouterCoursChampHeure.getHour() + "h" + vueAjouterCoursChampHeure.getMinute());
        coursDAO = CoursDAO.getInstance();
        coursDAO.ajouterCours(cours);

         */

        Cours cours = new Cours(vueAjouterCoursChampTitre.getText().toString(),
                vueAjouterCoursChampHeure.getHour() + "h" + vueAjouterCoursChampHeure.getMinute(),0);

        coursDAO = CoursDAO.getInstance();

        coursDAO.ajouterCours(cours);
    }

    public void naviguerRetourBibliotheque()
    {
        this.finish();
    }
}