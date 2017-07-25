package com.teamtreehouse;

import java.io.*;

public class Treets{
    //serializarea
    public static void save(Treet[] treets){
        try( //folosirea try(){}.. pentru excluderea etapei de finally{obk.close;}
            FileOutputStream fos = new FileOutputStream("treets.sr");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(treets);
        } catch (IOException e) {
            System.out.println("Problem saving Treets");
            e.printStackTrace();
        }
    }
    //deserializarea
    public static Treet[] load()  {
        Treet[] treets = new Treet[0]; //initializam ca fenomen, fara obiecte in interior
        try(
                FileInputStream fis = new FileInputStream("treets.sr");
                ObjectInputStream ois = new ObjectInputStream(fis);
                ) {
            treets = (Treet[]) ois.readObject();
        }catch (IOException e){
            System.out.println("Problem reading Treets");
            e.printStackTrace();
        }catch (ClassNotFoundException cnfe){
            System.out.println("Problem loading Treets");
            cnfe.printStackTrace();

        }
        return treets;
    }


}
