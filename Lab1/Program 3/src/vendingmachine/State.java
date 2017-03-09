/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachine;

/**
 *
 * @author Junaid
 */
public class State {
    
    private String Name;
    private String Output;
    
    public State(String s) {
        Name = s;
    }

    
    /*----------------------Getters and Setters---------------------------*/
    public State(String Name, String Output) {
        this.Name = Name;
        this.Output = Output;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getOutput() {
        return Output;
    }

    public void setOutput(String Output) {
        this.Output = Output;
    }


    
}
