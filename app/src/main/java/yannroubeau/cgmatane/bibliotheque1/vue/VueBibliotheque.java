package yannroubeau.cgmatane.bibliotheque1.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import yannroubeau.cgmatane.bibliotheque1.R;
import yannroubeau.cgmatane.bibliotheque1.donnee.BaseDeDonnees;
import yannroubeau.cgmatane.bibliotheque1.donnee.CoursDAO;
import yannroubeau.cgmatane.bibliotheque1.modele.Cours;

public class VueBibliotheque extends AppCompatActivity {

    protected ListView vueBibliothequeListeCours;
    protected List<Cours> listeCours;
    protected CoursDAO coursDAO;

    protected Intent intentionNaviguerAjouterCours;
    protected Intent intentionNaviguerModifierCours;

    static final public int ACTIVITE_AJOUTER_COURS = 1;
    static final public int ACTIVITE_MODIFIER_COURS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_bibliotheque);
        vueBibliothequeListeCours = (ListView)findViewById(R.id.vueBibliothequeListeCours);


        BaseDeDonnees.getInstance(getApplicationContext());
        coursDAO = CoursDAO.getInstance();

        afficherListeCours();

        Button vueBibliothequeListeCoursActionAjouterCours = (Button)findViewById(R.id.vueBibliothequeListeCoursActionAjouterCours);

        intentionNaviguerAjouterCours = new Intent(this, VueAjouterCours.class);

        vueBibliothequeListeCoursActionAjouterCours.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View arg0)
                    {
                        startActivityForResult(intentionNaviguerAjouterCours, ACTIVITE_AJOUTER_COURS);
                    }
                }
        );

        intentionNaviguerModifierCours = new Intent(this, VueModifierCours.class);


        vueBibliothequeListeCours.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View vue,
                                            int positionDansAdapteur, long positionItem) {

                        ListView vueListeCours = (ListView)vue.getParent();

                        @SuppressWarnings("unchecked")
                        HashMap<String,String> cours =
                                (HashMap<String,String>)
                                        vueListeCours.getItemAtPosition((int)positionItem);
                        intentionNaviguerModifierCours.putExtra("id", cours.get("id"));
                        startActivityForResult(intentionNaviguerModifierCours,ACTIVITE_MODIFIER_COURS);

                    }
                }
        );
    }

    protected void onActivityResult(int activite, int resultat, Intent donnees)
    {
        super.onActivityResult(activite, resultat, donnees);
        switch(activite)
        {
            case ACTIVITE_AJOUTER_COURS:
                afficherListeCours();
                break;

            case ACTIVITE_MODIFIER_COURS:
                afficherListeCours();
                break;

        }
    }

    public void afficherListeCours()
    {
        listeCours = coursDAO.listerCours();

        List<HashMap<String,String>> listeCoursPourAfficher =
                new ArrayList<HashMap<String,String>>();

        for(Cours cours:listeCours)
        {
            listeCoursPourAfficher.add(cours.obtenirCoursPourAfficher());
        }

        SimpleAdapter adapteur = new SimpleAdapter(
                this,
                listeCoursPourAfficher,
                android.R.layout.two_line_list_item,
                new String[]{"titre","heure"},
                new int[] {android.R.id.text1, android.R.id.text2});

        vueBibliothequeListeCours.setAdapter(adapteur);
    }
}