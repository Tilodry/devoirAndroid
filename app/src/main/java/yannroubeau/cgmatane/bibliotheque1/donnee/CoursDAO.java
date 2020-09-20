package yannroubeau.cgmatane.bibliotheque1.donnee;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import yannroubeau.cgmatane.bibliotheque1.modele.Cours;

public class CoursDAO {

    private static CoursDAO instance = null;
    //private List<HashMap<String,String>> listeCours;
    private List<Cours> listeCours;

    private BaseDeDonnees baseDeDonnees;

    private CoursDAO()
    {
        this.baseDeDonnees = BaseDeDonnees.getInstance();
        //listeCours = new ArrayList<HashMap<String, String>>();
        listeCours = new ArrayList<Cours>();
        //preparerListeCours();
    }

    private void preparerListeCours()
    {
        //List<HashMap<String,String>> listeLivre = new ArrayList<HashMap<String,String>>();

        /*HashMap<String,String> cours;

        cours = new HashMap<String,String>();
        cours.put("titre","Android pour les nuls");
        cours.put("heure","Département d'informatique");
        listeCours.add(cours);

        cours = new HashMap<String,String>();
        cours.put("titre","The Hobbit");
        cours.put("heure","Tolkien");
        listeCours.add(cours);

        cours = new HashMap<String,String>();
        cours.put("titre","Harry Potter");
        cours.put("heure","J.K.Rowling");
        listeCours.add(cours);*/

        listeCours.add(new Cours("Android pour les nuls", "Départements informatique", 0));
        listeCours.add(new Cours("The Hobbit", "Tolkien", 1));
        listeCours.add(new Cours("Harry Potter", "J.K.Rowling", 2));



    }

    public static CoursDAO getInstance()
    {
        if(null == instance)
        {
            instance = new CoursDAO();
        }

        return instance;
    }
    /*
    public List<Cours> listerCours()
    {
        return listeCours;
    }
    */

    public List<Cours> listerCours()
    {
        String LISTER_COURS = "SELECT * FROM cours";
        Cursor curseur = baseDeDonnees.getReadableDatabase().rawQuery(LISTER_COURS,null);
        this.listeCours.clear();
        Cours cours;

        int indexId = curseur.getColumnIndex("id");
        int indexHeure = curseur.getColumnIndex("heure");
        int indexTitre = curseur.getColumnIndex("titre");

        for(curseur.moveToFirst();!curseur.isAfterLast();curseur.moveToNext())
        {
            int id = curseur.getInt(indexId);
            String heure = curseur.getString(indexHeure);
            String titre = curseur.getString(indexTitre);
            cours = new Cours(titre, heure, id);
            this.listeCours.add(cours);
        }

        return listeCours;
    }
    /*
    public void ajouterCours(HashMap<String,String> cours)
    {
        //listeCours.add(cours);
    }

     */

    public void ajouterCours (Cours cours)
    {
        SQLiteDatabase baseDeDonneesEcriture = baseDeDonnees.getWritableDatabase();

        baseDeDonneesEcriture.beginTransaction();
        try {
            ContentValues coursEnCleValeur = new ContentValues();
            coursEnCleValeur.put("heure", cours.getHeure());
            coursEnCleValeur.put("titre", cours.getTitre());

            baseDeDonneesEcriture.insertOrThrow("cours",null,coursEnCleValeur);
            baseDeDonneesEcriture.setTransactionSuccessful();
        } catch(Exception e)
        {
            Log.d("CoursDAO", "Erreur en tentant d'ajouter un cours dans la base de donnée");
        } finally {
            baseDeDonneesEcriture.endTransaction();
        }
    }

    public Cours chercherCoursParId(int id)
    {
        listerCours();
        for(Cours coursRecherche : this.listeCours)
        {
            if(coursRecherche.getId() == id) return coursRecherche;
        }
        return null;
    }
}
