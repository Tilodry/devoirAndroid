package yannroubeau.cgmatane.bibliotheque1.donnee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDonnees extends SQLiteOpenHelper {

    private static BaseDeDonnees instance = null;

    public static synchronized BaseDeDonnees getInstance(Context contexte)
    {
        instance = new BaseDeDonnees(contexte);
        return instance;
    }

    public static BaseDeDonnees getInstance()
    {
        return instance;
    }

    public BaseDeDonnees(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context,name,factory,version);
    }

    public BaseDeDonnees(Context contexte)
    {
        super(contexte,"bibliotheque",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_TABLE = "create table cours(id INTEGER PRIMARY KEY, titre TEXT, heure TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onOpen(SQLiteDatabase db)
    {
        String DELETE = "delete from cours where 1 = 1";
        db.execSQL(DELETE);

        String INSERT_1 = "insert into cours(titre, heure) VALUES('Programmtion de monde virtuel', '8:00')";
        String INSERT_2 = "insert into cours(titre, heure) VALUES('Littérature québécoise', '12:35')";
        String INSERT_3 = "insert into cours(titre, heure) VALUES('Langue anglaise', '15:20')";

        db.execSQL(INSERT_1);
        db.execSQL(INSERT_2);
        db.execSQL(INSERT_3);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2)
    {
        String CREER_TABLE = "create table cours(id INTEGER PRIMARY KEY, titre TEXT, heure TEXT)";
        db.execSQL(CREER_TABLE);
    }

}
