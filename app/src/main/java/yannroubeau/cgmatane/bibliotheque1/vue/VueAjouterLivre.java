package yannroubeau.cgmatane.bibliotheque1.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import yannroubeau.cgmatane.bibliotheque1.R;
import yannroubeau.cgmatane.bibliotheque1.donnee.LivreDAO;

public class VueAjouterLivre extends AppCompatActivity {

    protected EditText vueAjouterLivreChampTitre;
    protected EditText vueAjouterLivreChampAuteur;
    protected LivreDAO livreDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_ajouter_livre);

        Button vueAjouterLivreActionAnnuler = (Button)findViewById(R.id.vueAjouterLivreActionAnnuler);

        vueAjouterLivreActionAnnuler.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View arg0)
                    {
                         /*TODO Lancer activité
                        Toast message = Toast.makeText(
                                getApplicationContext(),
                                "Action Annuler !",
                                Toast.LENGTH_SHORT
                        );
                        message.show();*/

                        naviguerRetourBibliotheque();
                    }
                }
        );

        vueAjouterLivreChampTitre = (EditText) findViewById(R.id.vueAjouterLivreChampTitre);
        vueAjouterLivreChampAuteur = (EditText) findViewById(R.id.vueAjouterLivreChampAuteur);

        Button vueAjouterLivreActionAjouter = (Button)findViewById(R.id.vueAjouterLivreActionAjouter);

        vueAjouterLivreActionAjouter.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View arg0)
                    {
                         //TODO Lancer activité

                        /*Toast message = Toast.makeText(
                                getApplicationContext(),
                                "Champ titre: " + vueAjouterLivreChampTitre.getText().toString()+
                                        " Champ auteur: " + vueAjouterLivreChampAuteur.getText().toString(),
                                Toast.LENGTH_SHORT
                        );
                        message.show();*/

                        enregistrerLivre();
                        naviguerRetourBibliotheque();
                    }
                }
        );

    }

    private void enregistrerLivre()
    {
        HashMap<String,String> livre;
        livre = new HashMap<String,String>();
        livre.put("titre",vueAjouterLivreChampTitre.getText().toString());
        livre.put("auteur",vueAjouterLivreChampAuteur.getText().toString());

        livreDAO = LivreDAO.getInstance();
        livreDAO.ajouterLivre(livre);
    }

    public void naviguerRetourBibliotheque()
    {
        this.finish();
    }
}