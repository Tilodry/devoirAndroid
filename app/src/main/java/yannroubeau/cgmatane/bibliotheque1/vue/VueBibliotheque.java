package yannroubeau.cgmatane.bibliotheque1.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import yannroubeau.cgmatane.bibliotheque1.R;
import yannroubeau.cgmatane.bibliotheque1.donnee.LivreDAO;

public class VueBibliotheque extends AppCompatActivity {

    protected ListView vueBibliothequeListeLivre;
    protected List<HashMap<String,String>> listeLivre;
    protected LivreDAO livreDAO;

    protected Intent intentionNaviguerAjouterLivre;
    protected Intent intentionNaviguerModifierLivre;

    static final public int ACTIVITE_AJOUTER_LIVRE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_bibliotheque);
        vueBibliothequeListeLivre = (ListView)findViewById(R.id.vueBibliothequeListeLivre);

        livreDAO = LivreDAO.getInstance();
        /*listeLivre = livreDAO.listerLivre();

        SimpleAdapter adapteur = new SimpleAdapter(
                this,
                listeLivre,
                android.R.layout.two_line_list_item,
                new String[]{"titre","auteur"},
                new int[] {android.R.id.text1, android.R.id.text2});

        vueBibliothequeListeLivre.setAdapter(adapteur);*/

        afficherListeLivre();

        Button vueBibliothequeListeLivreActionAjouterLivre = (Button)findViewById(R.id.vueBibliothequeListeLivreActionAjouterLivre);

        intentionNaviguerAjouterLivre = new Intent(this, VueAjouterLivre.class);

        vueBibliothequeListeLivreActionAjouterLivre.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View arg0)
                    {
                        /* TODO Lancer activité
                        Toast message = Toast.makeText(
                                getApplicationContext(),
                                "Action ajouter livre!",
                                Toast.LENGTH_SHORT
                        );
                        message.show();
*/
                        startActivityForResult(intentionNaviguerAjouterLivre, ACTIVITE_AJOUTER_LIVRE);
                    }
                }
        );

        intentionNaviguerModifierLivre = new Intent(this, VueModifierLivre.class);


        vueBibliothequeListeLivre.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View vue,
                                            int positionDansAdapteur, long positionItem) {

                        ListView vueListeLivres = (ListView)vue.getParent();

                        @SuppressWarnings("unchecked")
                        HashMap<String,String> livre =
                                (HashMap<String,String>)
                                        vueListeLivres.getItemAtPosition((int)positionItem);

                        /*Toast message  = Toast.makeText(getApplicationContext(),
                                "Position "+positionItem + " titre "+livre.get("titre"),
                                Toast.LENGTH_SHORT);
                        message.show();*/

                        startActivity(intentionNaviguerModifierLivre);

                    }
                }
        );
    }

    protected void onActivityResult(int activite, int resultat, Intent donnees)
    {
        super.onActivityResult(activite, resultat, donnees);
        switch(activite)
        {
            case ACTIVITE_AJOUTER_LIVRE:
                afficherListeLivre();
                break;

        }
    }

    public void afficherListeLivre()
    {
        listeLivre = livreDAO.listerLivre();

        SimpleAdapter adapteur = new SimpleAdapter(
                this,
                listeLivre,
                android.R.layout.two_line_list_item,
                new String[]{"titre","auteur"},
                new int[] {android.R.id.text1, android.R.id.text2});

        vueBibliothequeListeLivre.setAdapter(adapteur);
    }

    /*public List<HashMap<String,String>> prepareListeLivre()
    {
        List<HashMap<String,String>> listeLivre = new ArrayList<HashMap<String,String>>();

        HashMap<String,String> livre;

        livre = new HashMap<String,String>();
        livre.put("titre","Android pour les nuls");
        livre.put("auteur","Département d'informatique");
        listeLivre.add(livre);

        livre = new HashMap<String,String>();
        livre.put("titre","The Hobbit");
        livre.put("auteur","Tolkien");
        listeLivre.add(livre);

        livre = new HashMap<String,String>();
        livre.put("titre","Harry Potter");
        livre.put("auteur","J.K.Rowling");
        listeLivre.add(livre);

        return listeLivre;
    }*/
}