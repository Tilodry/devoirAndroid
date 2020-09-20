package yannroubeau.cgmatane.bibliotheque1.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import yannroubeau.cgmatane.bibliotheque1.R;

public class VueModifierCours extends AppCompatActivity {

    protected TimePicker vueModifierCoursChampHeure;

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
    }

    public void naviguerRetourBibliotheque()
    {
        this.finish();
    }
}