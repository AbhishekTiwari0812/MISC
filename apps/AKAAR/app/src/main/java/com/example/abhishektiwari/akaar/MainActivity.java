package com.example.abhishektiwari.akaar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity  {

    public static String[] StudentNames = {"Etha Shellman", "Breana Bowens ", "Suzette Keo", "Shaquita Heid ", "Loria Jong ", "Otha Tirrell ", "Chiquita Shawn ", "Gertie Langenfeld ", "Annie Wainscott ", "Gisela Goggans ", "Antonietta Edelman ", "Dustin Songer ", "Elfriede Mar ", "Lyndia Just ", "Karena Decamp ", "Jo Iversen ", "Hipolito Posner ", "Letha Lugar ", "Akiko Cable ", "Kathline Woodberry ", "Kenyetta Martyn ", "Cristie Boone ", "Mark Knapper ", "Diann Calle ", "Madie Heuser ", "Lakeshia Degroot ", "Barbara Ables ", "Lynetta Mayo ", "Andree Egbert ", "Jody Dement ", "Eustolia Aldrete ", "Dreama Blue ", "Leta Stockbridge ", "Tory Fabiano ", "Yoko Kiser ", "Natashia Steffan ", "Wynell Mcie ", "Wilmer Hussain ", "Georgann Conboy ", "Belle Leonardi ", "Socorro Crossley ", "Basil Meredith ", "Bradley Westhoff ", "Nakisha Batt ", "Junita Seder ", "Efrain Arenas ", "Anita Schultze ", "Velda Ramon ", "Octavia Blackston ", "Shan Myers", "Albina", "Trimpe", "Brandee", "Billman", "Jacques", "Fazio", "Peggy", "Meaux", "Racquel", "Moline", "Barry", "Obannon", "Mary", "Wingerter", "Retha", "Lastinger", "Mayra", "Pass", "Jannet", "Mckim", "Dionna", "Cortese", "Lee", "Schmid", "Bertha", "Bunner", "Tawny", "Bowerman", "Ricarda", "Lones", "Rodger", "Sly", "Norberto", "Currington", "Kasie", "Giorgi", "Lorrie", "Neel", "Ian", "Kellison", "Lanelle", "Mclain", "Barbera", "Glasser", "Allegra", "Clemens", "Kelly", "Miles", "Pablo", "Upshaw", "Jenice", "Hehn", "Alline", "Malbon", "Brynn", "Abele", "Moira", "Spafford", "Adam", "Bergeson", "Von", "Cocklin", "Luna", "Victor", "Audra", "Swartwood", "Leatha", "Kurland", "Leif", "Colorado", "Kay", "Burchill", "Gerard", "Victory", "Meryl", "Laine", "Clarine", "Strope", "Tonda", "Macke", "Cristobal", "Ricken", "Candyce", "Hutter", "Keli", "Eisert", "Fidela", "Eakes", "Maritza", "Schult", "Jerri", "Rozelle", "Lavon", "Summerfield", "Rebecca", "Grier", "Bryan", "Collum", "Ron", "Squillante", "Misty", "Lien", "Elouise", "Arehart", "Edwina", "Borey", "Hoa", "Aubrey", "Annamarie", "Tignor", "Waltraud", "Emberton", "Tracee", "Riegel", "Yvette", "Peters", "Willetta", "Brehm", "Janis", "Chattin", "Manual", "Shaeffer", "Georgeanna", "Baez", "Johnathan", "Ahmad", "Ivana", "Horwitz", "Tennie", "Fetterolf", "Ezequiel", "Rabalais", "Angelique", "Halladay", "Gil", "Rhoten", "Tamra", "Devries", "Ivelisse", "Figaro", "Verna", "Eden", "Katina", "Edgecomb", "Gustavo", "Claypool", "Hildegarde", "Nickols", "Ka", "Herdon", "Dani", "Bullen", "Joycelyn", "Windholz", "Shirely", "Rueda", "Jayme", "Paulus", "Rosalia", "Stickler", "Keisha", "Payan", "Tabatha", "Yandell", "Pearline", "Detwiler", "Shea", "Ratti", "Joella", "Kari", "Artie", "Vroman", "Kenny", "Shive", "Robyn", "Villanueva", "Dorthy", "Wollman", "Emerita", "Laporta", "Lorri", "Grass", "Tamara", "Hennessee", "Ada", "Lacroix", "Fern", "Frazee", "Latia", "Winkle", "Enoch", "Conwell", "Glennis", "Monnin", "Narcisa", "Wilcoxson", "Zachary", "Vanhook", "Li", "Throop", "Karyn", "Sica", "Nannie", "Lizarraga", "Setsuko", "Bellew", "Maude", "Lenton", "Marylou", "Truby", "Kayleigh", "Schlicher", "Doretta", "Malecha", "Jeannine", "Streeter", "Beatris", "Schleusner", "Valorie", "Terrel", "Ben", "Storms", "Philip", "Marcelino", "Agustina", "Hunley", "Cathrine", "Obrian", "Johnette", "Hollar", "Riva", "Farr", "Nida", "Wyer", "Mirna", "Dozal", "Clarinda", "Procter", "Roland", "Gover", "Malcom", "Hagar", "Josette", "Schober", "Shirely", "Debelak", "Stephenie", "Dickman", "Karren", "Malcom", "Roman", "Lamacchia", "Veta", "Costanza", "Katie", "Daigneault", "Imogene", "Schlabach", "Shavon", "Blaylock", "Melani", "Hudkins", "Aletha", "Strum", "Alfonso", "Vanwinkle", "Jason", "Funnell", "Rosalinda", "Nourse", "Antione", "Staple", "Maurine", "Powers", "Christena", "Lukasik", "Christinia", "Conely", "Ismael", "Moyle", "Alanna", "Dower", "Stephen", "Lawton", "Kelley", "Schaefer", "Yu", "Jernigan", "Deonna", "Dewey", "Alexa", "Hagemann", "Kiley", "Cockrum", "Elden", "Godsey", "Ronda", "Rahe", "Kristan", "Higby"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);
        Thread mySplashActivity = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent SetHomePage=new Intent(MainActivity.this,HomePage.class);
                    startActivity(SetHomePage);
                    finish();
                }
            }
        };
        mySplashActivity.start();






    }


}