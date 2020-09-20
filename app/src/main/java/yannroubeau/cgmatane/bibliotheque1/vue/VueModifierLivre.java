package yannroubeau.cgmatane.bibliotheque1.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import yannroubeau.cgmatane.bibliotheque1.R;

public class VueModifierLivre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_modifier_livre);

        Button vueModifierLivreActionAnnuler = (Button)findViewById(R.id.vueModifierLivreActionAnnuler);

        vueModifierLivreActionAnnuler.setOnClickListener(
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
    }

    public void naviguerRetourBibliotheque()
    {
        this.finish();
    }
}