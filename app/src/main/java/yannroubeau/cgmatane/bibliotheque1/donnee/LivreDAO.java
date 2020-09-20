package yannroubeau.cgmatane.bibliotheque1.donnee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LivreDAO {

    private static LivreDAO instance = null;
    private List<HashMap<String,String>> listeLivre;

    private LivreDAO()
    {
        listeLivre = new ArrayList<HashMap<String, String>>();
        preparerListeLivre();
    }

    private void preparerListeLivre()
    {
        //List<HashMap<String,String>> listeLivre = new ArrayList<HashMap<String,String>>();

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

    }

    public static LivreDAO getInstance()
    {
        if(null == instance)
        {
            instance = new LivreDAO();
        }

        return instance;
    }
    public List<HashMap<String,String>> listerLivre()
    {
        return listeLivre;
    }

    public void ajouterLivre(HashMap<String,String> livre)
    {
        listeLivre.add(livre);
    }
}
