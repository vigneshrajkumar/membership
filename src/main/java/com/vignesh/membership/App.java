package com.vignesh.membership;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;




import org.apache.commons.codec.digest.DigestUtils;


// public class JavafxSample extends Application {  
//     @Override     
//     public void start(Stage primaryStage) throws Exception { 
//        /* 
//        Code for JavaFX application. 
//        (Stage, scene, scene graph) 
//        */       
//     }         
//     public static void main(String args[]){           
//        launch(args);      
//     } 
//  } 

public class App  {

    private static int id;

    private static int nextID(){
        return ++id;
    }

    /*
     * 
     * 0 : spouse
     * 1 : parent/child
     * 2 : sibling
     * 3 : inlaw
     * 4 : grandparent/ grandchild
     *  
     * */

    public static void main( String[] args ){
        // if (args.length < 1) {
        //     System.err.println("Please provide an input!");
        //     System.exit(0);
        // }
        // System.out.println(sha256hex(args[0]));

        
        ArrayList<Relationship> relationships = new ArrayList<Relationship>();
        List<Member> members = new ArrayList<Member>();

        Member paul = new Member(nextID(), "paul");
        Member grace = new Member(nextID(), "grace");
        
        
        relationships.add(new Relationship(nextID(), paul.getId(), grace.getId(), 0));

        Member sunil = new Member(nextID(), "sunil");

        relationships.add(new Relationship(nextID(), paul.getId(), sunil.getId(), 1));

        Member niti = new Member(nextID(), "niti");
        
        relationships.add(new Relationship(nextID(), niti.getId(), sunil.getId(), 0));


        members.add(paul);
        members.add(grace);
        members.add(sunil);
        members.add(niti);
        
        
        
        Database db = new Database();

        
        System.out.println(db.fetchAllMembers());

        db.addMembers(members);

        db.addRelationships(relationships);

        System.out.println(db.fetchAllMembers());

        // db.deleteByID(1);
        // db.deleteByID(2);
        // db.deleteByID(4);
        // db.deleteByID(5);


        for (Relationship rel : buildRelationships(relationships)) {
            System.out.println(rel);
        }

    }

    private static List<Relationship> buildRelationships(ArrayList<Relationship> existingRelationships){
        ArrayList<Relationship> identifiedRelationships = new ArrayList<Relationship>();

        for (Relationship rel : existingRelationships) {
            identifiedRelationships.add(rel);
        }

        return identifiedRelationships;
    } 

    public static String sha256hex(String input) {
        return DigestUtils.sha256Hex(input);
    }
}
