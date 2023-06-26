package client;

import server.object.LabWork;

import java.io.*;
import java.util.HashSet;
import java.util.LinkedList;

public class SerializationManager {

    public static byte[] serialize(HashSet<?> collection) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);

        out.writeObject(collection);

        return bos.toByteArray();
    }


    public static byte[] serialize(LabWork labWork) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bos);

        out.writeObject(labWork);

        return bos.toByteArray();
    }


    public static LinkedList<LabWork> deserialize(byte[] byteArray) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(byteArray);
       try{
           ObjectInputStream ois = new ObjectInputStream(bis);
           return (LinkedList<LabWork>) ois.readObject();
       } catch (StreamCorruptedException e){
           System.out.println("Сказали же, сервер недоступен!");
       }
       return null;
    }
}
