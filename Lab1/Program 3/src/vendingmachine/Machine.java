/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachine;

import static vendingmachine.VendingMachine.*;
import java.awt.Button;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;


/**
 *
 * @author Junaid
 */
public class Machine{
    
    private final String[] outputArray ={
      "Invalid Input","Select Drink",
        "Rs 20","Rs 30","Rs 45",
        "Change: 5","Change: 10","Change: 50",
        "Tea","Coffee","Cold Drink",
        "Proceed Furthur: yes/No","Insufficient Money"
    };
    private final String[] Alphabet = {
        "Tea","Coffee","Cold Drink",
        "Rs 5","Rs 10","Rs 50",
        "Yes","No"
    };
    
    private HashMap<String,Integer> map = new HashMap<>();
    
    private Button bTea,bCoffee,bDrink,b5,b10,b50,bYes,bNo;
    private TextField Display;
    private Panel inputPanel;
    private JFrame frame;
    State q0,q1,q2,q3,q4,q5,q6;
    
    boolean buttonPressed = false;

    public Machine() {
        
        int money,i = 0;
        ArrayList change;
        
        
        intializeFrame();
        initializeStates();
        
        /*---------State 0------------*/
        displayOutput(q0.getOutput(),1);
        
        if(inputString.equals(Alphabet[0])){
            q1.setOutput(outputArray[2]);
            q1.setName(Alphabet[0]);
        }
        else if(inputString.equals(Alphabet[1])){
            q1.setOutput(outputArray[3]);
            q1.setName(Alphabet[1]);
        }
        else if(inputString.equals(Alphabet[2])){
            q1.setOutput(outputArray[4]);
            q1.setName(Alphabet[2]);
        }
        else{
           invalidInputState();
        }
        
        
        /*-------------State 1-------------*/
        
        displayOutput(q1.getOutput(),1);
        while(true){
            money = Integer.parseInt(inputString.substring(3));
            if(money < map.get(q1.getName())){
                /*---------State 2--------------*/
                q2.setOutput(outputArray[12]);
                displayOutput(q2.getOutput(),1);
            }
            else if(money > map.get(q1.getName())){
                /*-----------State 3-------------*/
                change = calculateChange(money,map.get(q1.getName()));
                while(i < change.size()){
                    q3.setOutput("Change: " + String.valueOf(change.get(i)));
                    displayOutput(q3.getOutput(),0);
                    i++;
                }
                break;
                
            }
        }
        
        /*---------------State 4---------------*/
        displayOutput(q4.getOutput(),1);
        i = 0;
        if(inputString.equals(Alphabet[7])){
            change = calculateChange(map.get(q1.getName()),0);
                while(i < change.size()){
                    q3.setOutput("Money Back: " + String.valueOf(change.get(i)));
                    displayOutput(q3.getOutput(),0);
                    i++;
                }
        
                 System.exit(0);
        }
        
        /*---------------State 5 ----------------*/
        q5.setOutput(q1.getName());
        displayOutput("Enjoy your " + q5.getOutput(),0);
        System.exit(0);
        
        
        
        
            
        
        
        
       
        
        
        
        
        
    }
    
    private void displayOutput(String s,int flag){
     
     if(flag == 0){
         try {
             Display.setText(s);
             Thread.sleep(2000);
             return;
         } catch (InterruptedException ex) {
             Logger.getLogger(Machine.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
     Display.setText(s); 
     sleep();
     buttonPressed = false;
        
    }
    private ArrayList calculateChange(int money,int req){
        
        ArrayList change = new ArrayList();
        while(req != money ){
            if(money%10 == 0 && money - 10>=req){
                money = money - 10;
                change.add(10);
            }
            else if(money % 5 == 0){
                money = money - 5;
                change.add(5);
            }
                
        }
        
        return change;
    }
    
    private void invalidInputState(){
        
         Display.setText(q6.getOutput());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Machine.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);
    }
    
    private void sleep(){
        
        while(!buttonPressed){
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Machine.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void initializeStates(){
        
         q0 = new State("q0",outputArray[1]); //Intial
         q1 = new State("q1"); //money
         q2 = new State("q2");  //more money
         q3 = new State("q3"); //change
         q4 = new State("q4",outputArray[11]); //proceed
         q5 = new State("q5"); //drink
         q6 = new State("q6",outputArray[0]); //wrong input
         
         map.put(Alphabet[0], 20);
         map.put(Alphabet[1], 30);
         map.put(Alphabet[2], 45);
    }
    
    private void intializeFrame(){
        frame = new JFrame("Drink Vending Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLayout(new GridLayout(2,0));
        
        frame.setLocationRelativeTo(null);
        frame.setSize(400,200);
        initializeOutput();
        frame.add(Display);
        
        intializeInput();
        frame.add(inputPanel);
    }
    
    private void initializeOutput(){
        
        Display = new TextField();
        
        Display.setEditable(false);
       
    }

    private void intializeInput() {
       
        inputPanel = new Panel(new GridLayout(3,3));
        
        bTea = new Button(Alphabet[0]);
        bTea.addActionListener(new Listener(bTea.getLabel()));
        
        bCoffee = new Button(Alphabet[1]);
        bCoffee.addActionListener(new Listener(bCoffee.getLabel()));
        
        bDrink = new Button(Alphabet[2]);
        bDrink.addActionListener(new Listener(bDrink.getLabel()));;
        
        b5 = new Button(Alphabet[3]);
        b5.addActionListener(new Listener(b5.getLabel()));
        
        b10 = new Button(Alphabet[4]);
        b10.addActionListener(new Listener(b10.getLabel()));
        
        
        b50 = new Button(Alphabet[5]);
        b50.addActionListener(new Listener(b50.getLabel()));
        
        bYes = new Button(Alphabet[6]);
        bYes.addActionListener(new Listener(bYes.getLabel()));
        
        bNo = new Button(Alphabet[7]);
        bNo.addActionListener(new Listener(bNo.getLabel()));
        
        inputPanel.add(bTea);
        inputPanel.add(bCoffee);
        inputPanel.add(bDrink);
        
        inputPanel.add(b5);
        inputPanel.add(b10);
        inputPanel.add(b50);
        
        inputPanel.add(bYes);
        inputPanel.add(new Panel());
        inputPanel.add(bNo);
        
    }
    
    private class Listener implements ActionListener{
        
        String name;
        Listener(String s){
            name = s;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            inputString = name;
            buttonPressed = true;
        }
        
    }
    
    
    
}
