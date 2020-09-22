package yannroubeau.cgmatane.bibliotheque1.donnee;

import android.app.AlarmManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import yannroubeau.cgmatane.bibliotheque1.modele.Cours;

public class CoursDAO {

    private static CoursDAO instance = null;
    private List<Cours> listeCours;

    private BaseDeDonnees baseDeDonnees;

    private CoursDAO()
    {
        this.baseDeDonnees = BaseDeDonnees.getInstance();
        listeCours = new ArrayList<Cours>();
    }

    public static CoursDAO getInstance()
    {
        if(null == instance)
        {
            instance = new CoursDAO();
        }

        return instance;
    }

    public List<Cours> listerCours()
    {
        String LISTER_COURS = "SELECT * FROM cours ORDER BY substr('00000'||cours.heure, -5, 5)";
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

    public void modifierCours (Cours cours)
    {
        SQLiteDatabase baseDeDonneesEcriture = baseDeDonnees.getWritableDatabase();

        baseDeDonneesEcriture.beginTransaction();
        try {
            ContentValues coursEnCleValeur = new ContentValues();
            coursEnCleValeur.put("heure", cours.getHeure());
            coursEnCleValeur.put("titre", cours.getTitre());
            baseDeDonneesEcriture.update("cours",coursEnCleValeur,"id="+cours.getId(), null );
            baseDeDonneesEcriture.setTransactionSuccessful();
        } catch(Exception e)
        {
            Log.d("CoursDAO", "Erreur en tentant de modifier un cours dans la base de donnée");
        } finally {
            baseDeDonneesEcriture.endTransaction();
        }
    }

    public void ajouterCours(Cours cours)
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

    public void supprimerCours(int id)
    {
        SQLiteDatabase baseDeDonneesEcriture = baseDeDonnees.getWritableDatabase();
        baseDeDonneesEcriture.beginTransaction();
        try {
            baseDeDonneesEcriture.delete("cours","id="+id,null);
            baseDeDonneesEcriture.setTransactionSuccessful();
        } catch(Exception e)
        {
            Log.d("CoursDAO", "Erreur en tentant de supprimer un cours dans la base de donnée");
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
