import java.util.*;

// AUTHOR:      SHARJEEL SOHAIL
// DATE:        06TH NOVEMBER 2020
// PROJECT:     JUICE COMPANY MANAGEMENT (WITH ARRAYS & FILES)
// ASSESSMENT:  SENG1110 ASSIGNMENT 2 
// FILE:        FACTORY CLASS (02 OUT OF 03)

// PURPOSE OF THIS CLASS: 
// This class is used to create a Factory and (possibly) a warehouse too by accessing Warehouse class
// It sets the name and size of the factory and have few methods to deal with their respective warehouses
// To complete the tasks required in the assignment specs

public class Factory 
{
    private String name;                        // the name of the factory. Must not contain spaces.
    private String size;                        // the size of the factory (it can be “S”, “M” or “L”)
    Warehouse[] warehouse = new Warehouse[4];   // Ignoring 0 and going upto 3 [Reason for setting up array as 4]
    
    //-----------------------------------------------------------------------------------------------------------------
    // CONSTRUCTOR: Factory()
    // PURPOSE:     Sets initial name and size to null
    //-----------------------------------------------------------------------------------------------------------------
    
    public Factory(){
        name = "";
        size = "";
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // METHOD:  setName(1 param)
    // PURPOSE: Public method to assign a name to Factory
    //-----------------------------------------------------------------------------------------------------------------
    
    public void SetName(String name){
        this.name = name;
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // METHOD:  getName()
    // PURPOSE: Public method to print name of the Factory
    //-----------------------------------------------------------------------------------------------------------------

    public String GetName(){
        return name;
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // METHOD:  setSize(1 param)
    // PURPOSE: Public method to assign a size to a Factory 
    //-----------------------------------------------------------------------------------------------------------------

    public void SetSize(String size){
        this.size = size;
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // METHOD:  getSize()
    // PURPOSE: Public method to print size of the Factory 
    //-----------------------------------------------------------------------------------------------------------------

    public String GetSize(){
       return size;
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // METHOD:  getMaxWarehouses()
    // PURPOSE: Returns the maximum no. of warehouses a factory can have (depending on size)
    //-----------------------------------------------------------------------------------------------------------------
    
    public int getMaxWarehouses()
    {
        if(size.equalsIgnoreCase("s")) return 1;
        else if(size.equalsIgnoreCase("m")) return 2;
        else return 3;
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // METHOD:  addWarehouse(4 params)
    // PURPOSE: Creates a new warehouse and uses SetData function to initialise name, fruit, and quantity
    //-----------------------------------------------------------------------------------------------------------------
    
    public void addWarehouse(String newName, String newFruit, int newCapacity, int newWarehouseQty)
    {
        // checks the maximum no. of warehouses can store, whichever's empty,
        // creates a warehouse in the position
        for (int i = 1; i <= newWarehouseQty; i++)
        {
            if (warehouse[i] == null)
            {
                warehouse[i] = new Warehouse();
                SetData(warehouse[i], newName, newFruit, newCapacity); // Using another function
                break;
            }
        }
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // METHOD:  SetData(4 params)
    // PURPOSE: Sets the data into Warehouse by accessing their public methods 
    //-----------------------------------------------------------------------------------------------------------------
    
    private void SetData(Warehouse w, String name, String fruit, int capacity)
    {
        // Accessing public methods of attributes to fill in data 
        w.SetName(name);
        w.SetFruit(fruit);
        w.SetMaxCapacity(capacity);
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // METHOD:  ListWarehouse(2 params)
    // PURPOSE: Used to list warehouses by also using PrintWarehouse function
    //-----------------------------------------------------------------------------------------------------------------
    
    public String ListWarehouses(int maxWare, int outputNo)
    {
        // Prints all the warehouses until maximum no. reached 
        String output = "";
        for(int i=1; i <= maxWare; i++)
        {
            output += PrintWarehouse(warehouse[i], outputNo); // Using another function
        }
        return(output);
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // METHOD:  ListByFruit(3 params)
    // PURPOSE: Used to return list of warehouses of same fruit by also using PrintWarehouse function
    //-----------------------------------------------------------------------------------------------------------------
    
    public String ListByFruit(String fruitName, int maxWare, int outputNo)
    {
        // Compares input fruit name with all warehouses' fruit names
        // and prints out data of those warehouses 
        String output = "";
        for (int i=1; i <= maxWare; i++)
        {
            if (fruitName.equalsIgnoreCase(warehouse[i].GetFruit()))
            {
                output += PrintWarehouse(warehouse[i], outputNo);
            }
        }
        return (output);
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // METHOD:  PrintWarehouse(2 params)
    // PURPOSE: Used to print warehouse information as required
    //-----------------------------------------------------------------------------------------------------------------
    
    private String PrintWarehouse(Warehouse w, int outputNo)
    {
        // Return a message displaying the details of the warehouses 
        String message = "";
        if (outputNo == 1)          // This is for displaying with factories (task3)
            message = "  Warehouse " + w.GetName() + " has fruit " + w.GetFruit() + " \n";
            
        else if (outputNo == 2)     // This is for displaying only warehouses (task4)
            message = "Warehouse " + w.GetName() + " has fruit " + w.GetFruit() + 
            " and max capacity " + w.GetMaxCapacity() + " tonnes \n";
        
        return(message);
    } 
    
    //-----------------------------------------------------------------------------------------------------------------
    // METHOD:  ReturnWarehouseData(1 param)
    // PURPOSE: Used to print warehouse data formatted in a file (task8) 
    //-----------------------------------------------------------------------------------------------------------------
    
    public String ReturnWarehouseData(int maxWare)
    {
        // Prints warehouses' data in a format that is required to fill the file 
        String message = "";
        for (int i = 1; i <= maxWare; i++)
        {
            message += "\nName " + warehouse[i].GetName() + "\nFruit " + warehouse[i].GetFruit() + "\nMaxCapacity " 
                + warehouse[i].GetMaxCapacity() + "\n";
        }
        return (message); 
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // END OF FILE 
    //-----------------------------------------------------------------------------------------------------------------
}
