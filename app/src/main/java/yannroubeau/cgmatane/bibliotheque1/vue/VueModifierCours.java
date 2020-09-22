package yannroubeau.cgmatane.bibliotheque1.vue;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import yannroubeau.cgmatane.bibliotheque1.R;
import yannroubeau.cgmatane.bibliotheque1.donnee.CoursDAO;
import yannroubeau.cgmatane.bibliotheque1.modele.Cours;

public class VueModifierCours extends AppCompatActivity {

    protected EditText vueModifierCoursChampTitre;
    protected TimePicker vueModifierCoursChampHeure;
    protected CoursDAO coursDAO;
    protected Cours cours;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_modifier_cours);

        Button vueModifierCoursActionAnnuler = (Button)findViewById(R.id.vueModifierCoursActionAnnuler);

        vueModifierCoursChampHeure = (TimePicker) findViewById(R.id.vueModifierCoursChampAuteur);
        vueModifierCoursChampHeure.setIs24HourView(true);

        vueModifierCoursActionAnnuler.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View arg0)
                    {
                        naviguerRetourBibliotheque();
                    }
                }
        );

        Bundle parametres = this.getIntent().getExtras();
        String idParametre = (String) parametres.get("id");
        int id = Integer.parseInt(idParametre);
        coursDAO = CoursDAO.getInstance();
        cours = coursDAO.chercherCoursParId(id);

        vueModifierCoursChampTitre = (EditText)findViewById(R.id.vueModifierCoursChampTitre);
        vueModifierCoursChampHeure = (TimePicker) findViewById(R.id.vueModifierCoursChampAuteur);
        vueModifierCoursChampTitre.setText(cours.getTitre());
        String heure;
        if(cours.getHeure().length() < 5 )
        {
            heure = "0" + cours.getHeure().substring(0,1);
        } else heure = cours.getHeure().substring(0,2);
        vueModifierCoursChampHeure.setHour(Integer.parseInt(heure));
        int i;
        try {
            i = Integer.parseInt(cours.getHeure().substring(cours.getHeure().length()-2));
        } catch( NumberFormatException e){
            i = Integer.parseInt(cours.getHeure().substring(cours.getHeure().length()-1));
        }
        vueModifierCoursChampHeure.setMinute(i);

        Button vueModifierCoursActionModifier = (Button)findViewById(R.id.vueModifierCoursActionModifier);

        vueModifierCoursActionModifier.setOnClickListener(

                new View.OnClickListener()
                {
                    public void onClick(View arg0)
                    {
                        enregistrerCours();
                        naviguerRetourBibliotheque();
                    }


                }
        );

        Button vueModifierCoursActionSupprimer = (Button)findViewById(R.id.vueModifierCoursActionSupprimer);

        vueModifierCoursActionSupprimer.setOnClickListener(

                new View.OnClickListener()
                {
                    public void onClick(View arg0)
                    {
                        coursDAO.supprimerCours(cours.getId());
                        naviguerRetourBibliotheque();
                    }


                }
        );
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void enregistrerCours()
    {
        /*Cours cours = new Cours(vueModifierCoursChampTitre.getText().toString(),
                vueModifierCoursChampHeure.getHour() + ":" + vueModifierCoursChampHeure.getMinute(),id);*/
        cours.setTitre(vueModifierCoursChampTitre.getText().toString());
        cours.setHeure(vueModifierCoursChampHeure.getHour() + ":" + vueModifierCoursChampHeure.getMinute());

        //coursDAO = CoursDAO.getInstance();

        coursDAO.modifierCours(cours);

    }

    public void naviguerRetourBibliotheque()
    {
        this.finish();
    }
}