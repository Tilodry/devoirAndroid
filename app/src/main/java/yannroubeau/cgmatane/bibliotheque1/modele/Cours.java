package yannroubeau.cgmatane.bibliotheque1.modele;

import java.util.HashMap;

public class Cours {

    protected String titre;
    protected String heure;
    protected int id;

    public Cours(String titre, String heure, int id) {
        this.titre = titre;
        this.heure = heure;
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<String,String> obtenirCoursPourAfficher()
    {
        HashMap<String,String> coursPourAfficher = new HashMap<String,String>();
        coursPourAfficher.put("titre", this.titre);
        coursPourAfficher.put("heure", this.heure);
        coursPourAfficher.put("id", "" + this.id);
        return coursPourAfficher;
    }

}
