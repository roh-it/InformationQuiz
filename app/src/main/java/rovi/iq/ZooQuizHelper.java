package rovi.iq;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rrohi on 12-02-2018.
 */

public class ZooQuizHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DB_NAME = "Zoo.db";
    public ZooQuizHelper zooQuizHelper;


    //If you want to add more questions or wanna update table values
    //or any kind of modification in db just increment version no.
    private static final int DB_VERSION = 2;
    //Table name
    private static final String TABLE_NAME_ZOO = "TQ";
    //Id of question
    private static final String UID = "_UID";
    //Question
    private static final String QUESTION = "QUESTION";
    //Option A
    private static final String OPTA = "OPTA";
    //Option B
    private static final String OPTB = "OPTB";
    //Option C
    private static final String OPTC = "OPTC";
    //Option D
    private static final String OPTD = "OPTD";
    //Answer
    private static final String ANSWER = "ANSWER";
    //So basically we are now creating table with first column-id , sec column-question , third column -option A, fourth column -option B , Fifth column -option C , sixth column -option D , seventh column - answer(i.e ans of  question)
    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME_ZOO + " ( " + UID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + QUESTION + " VARCHAR(255), " + OPTA + " VARCHAR(255), " + OPTB + " VARCHAR(255), " + OPTC + " VARCHAR(255), " + OPTD + " VARCHAR(255), " + ANSWER + " VARCHAR(255));";
    //Drop table query
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME_ZOO;

    private static final String DROP_DB="DROP DATABASE "+DB_NAME;

    ZooQuizHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //OnCreate is called only once
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //OnUpgrade is called when ever we upgrade or increment our database version no
        sqLiteDatabase.execSQL(DROP_TABLE);
        sqLiteDatabase.execSQL(DROP_DB);

        zooQuizHelper = new ZooQuizHelper(context);
        //(sqLiteDatabase);
    }

    void allQuestion() {
        ArrayList<TriviaQuestion> arraylist = new ArrayList<>();

        arraylist.add(new TriviaQuestion("Which phylum is characterized by choanocytes that create a current to pull in food?", "Cnidaria", "Urochordata", "Platyhelminthes", "Porifera", "Porifera"));

        arraylist.add(new TriviaQuestion("Which class of organisms can eviscerate to escape predators and survive?", "Cephalopoda", "Holothuroidea", "Asteroidea", "Chelicerata", "Holothuroidea"));

        arraylist.add(new TriviaQuestion("On which organism below might you find setae?", "Earthworm", "Sea Star", "Flat worm", "Horseshoe Crab", "Earthworm"));

        arraylist.add(new TriviaQuestion("Which phylum is characterized by pentameral symmetry?", "Mollusca", "Ctenophora", "Echinodermata", "Chaetognatha", "Echinodermata"));

        arraylist.add(new TriviaQuestion("Which of the following worm phyla has a tough outer cuticle that undergoes ecdysis?", "Nematoda", "Annelida", "Platyhelminthes", "Nemertea", "Nematoda"));

        arraylist.add(new TriviaQuestion("On which of the following organisms might you find cnidae?", "Comb Jelly", "Sponge", "Brittle Star", "Coral polyp", "Coral polyp"));

        arraylist.add(new TriviaQuestion("Which of the following is not required to be a chordate?", "Notochord", "Dorsal Hollow Nerve Cord", "Calcified spinal cord", "Muscular Postanal", "Calcified spinal cord"));

        arraylist.add(new TriviaQuestion("Phylogenetics is the study of:", "DNA", "Relationships between groups of organisms", "Interactions between organisms", "Interactions between living and nonliving things", "Relationships between groups of organisms"));

        arraylist.add(new TriviaQuestion("Which phylum includes colonial (and clonal) organisms that encrust on surfaces?", "Bryozoa", "Arthropoda", "Nematoda", "Porifera", "Bryozoa"));

        arraylist.add(new TriviaQuestion("What is the name for reproduction in which males and females both emit gametes (sex cells) into the water and egg and sperm meet in the water column?", "Hermaphroditism", "External fertilization", "Broadcast spawning", "Cross-pollination", "Broadcast spawning"));


        this.addAllQuestions(arraylist);

    }


    private void addAllQuestions(ArrayList<TriviaQuestion> allQuestions) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            for (TriviaQuestion question : allQuestions) {
                values.put(QUESTION, question.getQuestion());
                values.put(OPTA, question.getOptA());
                values.put(OPTB, question.getOptB());
                values.put(OPTC, question.getOptC());
                values.put(OPTD, question.getOptD());
                values.put(ANSWER, question.getAnswer());
                db.insert(TABLE_NAME_ZOO, null, values);
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }
    }


    List<TriviaQuestion> getAllOfTheQuestions() {

        List<TriviaQuestion> questionsList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        db.beginTransaction();
        String coloumn[] = {UID, QUESTION, OPTA, OPTB, OPTC, OPTD, ANSWER};
        Cursor cursor = db.query(TABLE_NAME_ZOO, coloumn, null, null, null, null, null);


        while (cursor.moveToNext()) {
            TriviaQuestion question = new TriviaQuestion();
            question.setId(cursor.getInt(0));
            question.setQuestion(cursor.getString(1));
            question.setOptA(cursor.getString(2));
            question.setOptB(cursor.getString(3));
            question.setOptC(cursor.getString(4));
            question.setOptD(cursor.getString(5));
            question.setAnswer(cursor.getString(6));
            questionsList.add(question);
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        cursor.close();
        db.close();
        return questionsList;
    }
}
