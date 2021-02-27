import java.util.*;

// AUTHOR:      SHARJEEL SOHAIL
// DATE:        06TH NOVEMBER 2020
// PROJECT:     JUICE COMPANY MANAGEMENT (WITH ARRAYS & FILES)
// ASSESSMENT:  SENG1110 ASSIGNMENT 2 
// FILE:        WAREHOUSE CLASS (03 OUT OF 03)

// PRPOSE OF THIS CLASS: 
// This warehouse class is used for factories to contain warehouses (Upto 3) and
// Set their Name, Fruit, and Capacity for each of the Warehouse
// Conditions of fruit and capacity are being applied in CompanyInterface class 

public class Warehouse 
{
    private String name;     // the name of the warehouse (Must not contain spaces)
    private String fruit;    // the fruit that can be stored in the warehouse. It can be only “Apple”, “Orange” or “Pear”
    private int maxCapacity; // the size of the warehouse. Represents units in tons (Must be positive int)
    
    //-----------------------------------------------------------------------------------------------------------------
    // Method:  setName(1 param)
    // Purpose: Public method to assign a name for Warehouse
    //-----------------------------------------------------------------------------------------------------------------
    
    public void SetName (String name){
        this.name = name;
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // Method:  getName()
    // Purpose: Public method to access name of the Warehouse
    //-----------------------------------------------------------------------------------------------------------------
    
    public String GetName(){
        return name;
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // Method:  setFruit(1 param)
    // Purpose: Public method to assign a fruit for Warehouse
    //-----------------------------------------------------------------------------------------------------------------
    
    public void SetFruit (String fruit){
        this.fruit = fruit;
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // Method:  getFruit()
    // Purpose: Public method to access fruit name of Warehouse 
    //-----------------------------------------------------------------------------------------------------------------
    
    public String GetFruit(){
        return fruit;
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // Method:  SetMaxCapacity(1 param)
    // Purpose: Public method to assign capacity of Warehouse 
    //-----------------------------------------------------------------------------------------------------------------
    
    public void SetMaxCapacity(int maxCapacity){
        this.maxCapacity = maxCapacity;
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // Method:  GetMaxCapacity()
    // Purpose: Public method to access capacity of Warehouse
    //-----------------------------------------------------------------------------------------------------------------
    
    public int GetMaxCapacity(){
        return maxCapacity;
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // END OF FILE
    //-----------------------------------------------------------------------------------------------------------------
}
