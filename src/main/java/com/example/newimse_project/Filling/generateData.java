package com.example.newimse_project.Filling;

import com.example.newimse_project.Model.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class generateData {

     private static Book b_1,b_2,b_3,b_4;
     private static Publisher p_1,p_2,p_3,p_4;
    private static Ncategory  nc_1,nc_2;
    public  static List<User> generateUser() {
        User u_1 = new User("BobAlavi", "Bob@gmail.com","+436656606482","Austria,Graz-1010 straBe");
        User u_2 = new User("AliMaxi", "AliMaxi@gmail.com","+436656719481","Austria,Vienna-1110 straBe");
        User u_3 = new User("CameronJannin", "Cameron@gmail.com","+436656122089","Italy,Milan");
        User u_4 = new User("DavidJannin", "David@gmail.com","+436656122089","Italy,Milan");
        return Arrays.asList(u_1,u_2,u_3,u_4);
    }
    public  static List<Book> generateBook() {
          b_1 = new Book("c","http://infolab.stanford.edu/pub/papers/google.pdf","JanMickle");
          b_2 = new Book( "kotlin","http://infolab.stanford.edu/pub/papers/google.pdf","YaniOZA");
          b_3 = new Book( "java","http://infolab.stanford.edu/pub/papers/google.pdf","NaserAhmed");
          b_4 = new Book( "sql","http://infolab.stanford.edu/pub/papers/google.pdf","MalekJasem");
        return Arrays.asList(b_1, b_2, b_3, b_4);
    }


    public static List<Chapters> generatechapters()
    {


        Chapters chapters1 = new Chapters("chapter1",1L,b_1);
        Chapters chapters2 = new Chapters("chapter2",2L,b_1);
        Chapters chapters3 = new Chapters("chapter3",3L,b_1);
        Chapters chapters4 = new Chapters("chapter4",4L,b_1);

        return Arrays.asList(chapters1,chapters2,chapters3,chapters4);
    }

    public  static List<Publisher> generatePublisher() {
          p_1 = new Publisher("learningInstitute","learningInstitute_contact@gmail.com");
          p_2 = new Publisher( "KotlinInstitute","KotlinInstitute_contact@gmail.com");
          p_3 = new Publisher( "JavaInstitute","JavaInstitute_contact@gmail.com");
          p_4 = new Publisher( "SqlInstitute","SqlInstitute_contact@gmail.com");
        return Arrays.asList(p_1, p_2, p_3, p_4);
    }

    public  static List<Book_Publisher> generateBook_publishers() {
        String date_1 = "16/07/2007";
        String date_2 = "26/02/2017";
        String date_3 = "06/11/2012";
        String date_4 = "06/09/2021";
        DateTimeFormatter f = DateTimeFormatter.ofPattern( "dd/MM/yyyy" );
        LocalDate lddate_1 = LocalDate.parse( date_1 , f );
        LocalDate lddate_2 = LocalDate.parse( date_2, f );
        LocalDate lddate_3 = LocalDate.parse( date_3, f );
        LocalDate lddate_4 = LocalDate.parse( date_4, f );

        Book_Publisher  bp_1 = new Book_Publisher(b_1,p_1,lddate_1);
        Book_Publisher  bp_2 = new Book_Publisher(b_2,p_2, lddate_2);
        Book_Publisher  bp_3 = new Book_Publisher(b_3,p_3,lddate_3);
        Book_Publisher  bp_4 = new Book_Publisher(b_4,p_4, lddate_4);
        Book_Publisher  bp_5 = new Book_Publisher(b_1,p_4, lddate_4);
        return Arrays.asList(bp_1,bp_2,bp_3,bp_4,bp_5);
    }

    public  static List<Ncategory> generateNcategory() {
          nc_1 = new Ncategory("DeepThinking","developer learn anything");
          nc_2 = new Ncategory("FunCoding","programming is fun");
        return Arrays.asList(nc_1,nc_2);
    }
    public  static List<Book_Ncategory> geneBook_ncategories() {
        Book_Ncategory  bp_1 = new Book_Ncategory(b_1,nc_1);
        Book_Ncategory  bp_2 = new Book_Ncategory(b_2,nc_2);
        Book_Ncategory  bp_3 = new Book_Ncategory(b_3,nc_1);
        Book_Ncategory  bp_4 = new Book_Ncategory(b_4,nc_1);
        Book_Ncategory  bp_5 = new Book_Ncategory(b_1,nc_2);
        return Arrays.asList(bp_1,bp_2,bp_3,bp_4,bp_5);
    }



    public static LocalDate get_date()
    {

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dateobj = new Date();

        DateTimeFormatter f = DateTimeFormatter.ofPattern( "dd/MM/yyyy" );
        LocalDate ld = LocalDate.parse( df.format(dateobj) , f );

        return ld;
    }
}
