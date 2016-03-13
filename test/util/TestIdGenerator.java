package util;

import src.util.IdGenorator;

public class TestIdGenerator {
    public static void main(String args[]){
        IdGenorator genorator = IdGenorator.getInstance();

        System.out.println(genorator.getNewId());
        System.out.println(genorator.getNewId());
        System.out.println(genorator.getNewId());
        System.out.println(genorator.getNewId());

        genorator = IdGenorator.getInstance();
        System.out.println(genorator.getNewId());
    }

}
