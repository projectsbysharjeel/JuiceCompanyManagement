import java.util.*;
import java.io.*;

// AUTHOR:      SHARJEEL SOHAIL
// DATE:        06TH NOVEMBER 2020
// PROJECT:     JUICE COMPANY MANAGEMENT (WITH ARRAYS & FILES)
// ASSESSMENT:  SENG1110 ASSIGNMENT 2 
// FILE:        COMPANYINTERFACE CLASS (01 OUT OF 03)

// PURPOSE OF THIS CLASS:
// This is the main class which has all the tasks that are required
// The UI is incharge of getting input from the user and running 
// functions that are being asked, it also connects with the Factory class

public class CompanyInterface 
{
    Scanner keyboard = new Scanner(System.in);
    Factory[] factory = new Factory[6]; // Ignoring 0 index and going upto 5
    int factoryCount = 1;
    String fileName = "ReginaCompany.txt";
    
    //-----------------------------------------------------------------------------------------------------------------
    // METHOD:  run()
    // PURPOSE: To control the structure of the data
    //-----------------------------------------------------------------------------------------------------------------

    private void run() 
    {
        System.out.println("--------------------------------------------");
        System.out.println("   WELCOME TO JUICE COMPANY MANAGEMENT");    

        // Recalls specific methods based on user input 
        // Until user decided to exit the program (by selecting 0)
        int optionSelected;
        do 
        {
            optionSelected = chooseOption();
            switch (optionSelected)
            {
                case 1: AddFactory();
                        break;
                case 2: DeleteFactory();
                        break;
                case 3: ListOfFactories();
                        break;
                case 4: DisplayListOfWarehouses();
                        break;
                case 5: ListWarehouseByFruit(inputFruit());
                        break;
                case 6: // Find out what juices our company can make
                        break;
                case 7: InputDataFromFile();
                        break;
                case 8: OutputDataToFile();
                        break;
            }
        }
        while (optionSelected != 0); // Exit the program
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // METHOD:  main(1 param)
    // PURPOSE: To control the structure of the data 
    //-----------------------------------------------------------------------------------------------------------------
    
    public static void main(String[] args)
    {
        CompanyInterface company = new CompanyInterface();
        company.run();
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // METHOD:  chooseOption()
    // PURPOSE: Displays the User Interface and asks user for an option to select 
    //-----------------------------------------------------------------------------------------------------------------
    
    private int chooseOption()
    {
        System.out.println("--------------------------------------------");
        System.out.println("        Select any option below: ");
        System.out.println("--------------------------------------------");
        System.out.println("(1) Create a new factory");
        System.out.println("(2) Delete a factory");
        System.out.println("(3) List of all factories");
        System.out.println("(4) List of all warehouses");
        System.out.println("(5) List of warehouses with a particular fruit");
        System.out.println("(6) Find out what juices our company can make");
        System.out.println("(7) Input factories & warehouses from an external file");
        System.out.println("(8) Output current factories & warehouses to an external file");
        System.out.println("(0) Exit the program");
        System.out.println("--------------------------------------------");
        int userResponse = keyboard.nextInt();
        return (userResponse); // Returns integer input by user
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // TASK 1 
    // METHOD:  AddFactory()
    // PURPOSE: Creates a factory and its warehouses all at once with some conditions 
    //-----------------------------------------------------------------------------------------------------------------
    
    public void AddFactory()
    {
        Boolean isFull = false;   // Control the bounds
        int maxWarehouse = 0;     // Maximum warehouses a factory can store
        Boolean isFound = false;  // Checks if factory name already exists 
    
        // Check is factory limit has reached 5, If no, return true 
        for(int i=1; i < factory.length; i++)
        {
            if (factory[i] == null) isFull = true;
        }
        
        // if still have space for factory, create
        if (isFull == true)
        {
            for (int i=1; i < factory.length; i++)
            {
                if (factory[i] == null) 
                {
                    factory[i] = new Factory();         // Creates a factory with default constructor 
        
                    System.out.print("Enter factory name: ");
                    String name = keyboard.next();
                    isFound = checkFactoryName(name);   // Name exists already?
        
                    while (isFound)                     // If exists, ask again
                    {
                        System.out.print("This name already exists. Type again: ");
                        name = keyboard.next();
                        isFound = checkFactoryName(name);
                    }
                    factory[i].SetName(name);           // Set factory name when unique
                    
                    System.out.print("Add size for this factory: ");
                    factory[i].SetSize(checkSizeOfFactory(keyboard.next())); // User another function to implement conditions on size
                    
                    maxWarehouse = factory[i].getMaxWarehouses(); // Gets the maximum no. of warehouses it can store by checking size 
                
                    // Creates warehouses depending on the size, Asks for input for each attribute
                    for (int j = 1; j <= maxWarehouse; j++)
                    {
                        factory[i].addWarehouse(inputName(j), inputFruit(), inputCapacity(), maxWarehouse);
                    }
                    System.out.println("Factory " + i + " Successfully added");
                    break;
                }
            }
        }
        // No space to add new factory
        else    System.out.print("No more space for new factories\n");
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // TASK 2 
    // METHOD:  DeleteFactory()
    // PURPOSE: Asks the user to enter fatory name & deletes the factory entered 
    //-----------------------------------------------------------------------------------------------------------------
    
    public void DeleteFactory()
    {
        Boolean factoryFound = false; 
        System.out.print("Enter factory name that you like to remove: ");
        String factoryName = keyboard.next(); 
        for (int i=1; i < factory.length; i++)
        {
            if (factory[i].GetName().equalsIgnoreCase(factoryName)) // Checks if that factory exists 
            {
                factory[i] = null;                                  // If yes, make it null
                System.out.println("Successfully deleted");
                factoryFound = true;
                break;
            }
        }
        if (!factoryFound)                                          // If not found, return a message 
        {
            System.out.println(factoryName + " not found as it does not exist yet \n");
        }
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // TASK 3 
    // METHOD:  ListOfFactories()
    // PURPOSE: Prints the list of factories that are being created  
    //-----------------------------------------------------------------------------------------------------------------
    
    public void ListOfFactories()
    {
        Boolean isFound = checkIfFactoryNull();
        if (isFound) DisplayListOfFactories(factory);   // If even a single factory found, print the list
        else System.out.println("No factories yet");
    }
        
    //-----------------------------------------------------------------------------------------------------------------
    // TASK 4 
    // METHOD:  ListOfWarehouses()
    // PURPOSE: Prints the list of warehouses and their attributes and which factory they belong to
    //-----------------------------------------------------------------------------------------------------------------
    
    private void DisplayListOfWarehouses()
    {
        Boolean isFound = checkIfFactoryNull();
        if (isFound)
        {
            String message = "";
            for (int i=1; i < factory.length; i++)
            {
                if (factory[i] != null)
                {
                    int max = factory[i].getMaxWarehouses();
                    message += factory[i].ListWarehouses(max, 2);
                }
            }
            System.out.println(message);
        }
        else System.out.println("No warehouses yet");
    }
       
    //-----------------------------------------------------------------------------------------------------------------
    // TASK 5 
    // METHOD:  ListOfWarehousesByFruit()
    // PURPOSE: Prints only the list of warehouses which have the same fruit   
    //-----------------------------------------------------------------------------------------------------------------
    
    private void ListWarehouseByFruit(String fruit)
    {
        String message = "";
        for (int i=1; i <= 5; i++)
        {
            if (factory[i] != null)
            {
                message += factory[i].ListByFruit(fruit, factory[i].getMaxWarehouses(), 2);
            }
        }
        System.out.println(message);
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // TASK 7 
    // METHOD:  InputDataFromFile()
    // PURPOSE: Loads the file and shows the data that's in it    
    //-----------------------------------------------------------------------------------------------------------------
    
    private void InputDataFromFile()
    {
        String line;
        Scanner fis = null; // Initialize 
        try
        {
            fis = new Scanner (new File(fileName)); // Opens file
        }
        catch (Exception e)
        {
            System.out.println("Error opening file \n");
        }
        while(fis.hasNextLine())  // Until the end of the file 
        {
            
            line = fis.nextLine(); 
            System.out.println(line);
        }
        fis.close(); // Closes the file
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // TASK 8 
    // METHOD:  OutputDataToFile()
    // PURPOSE: Exports the data added so far to the file     
    //-----------------------------------------------------------------------------------------------------------------
    
    private void OutputDataToFile()
    {
        int maximumWarehouses = 0; 
        PrintWriter outputstream = null; // Initializes the file 
        try
        {
            outputstream = new PrintWriter(fileName); // Instantiate the file with fileName that's provided at the top
        }
        catch (FileNotFoundException e) // If file not found, display message  
        {
            System.out.println("Error opening file " + fileName);
        }
        
        // Go through each factory & its data to print everything in the file
        for(int i = 0; i < factory.length; i++) 
        {
            if (factory[i] != null)
            {
                outputstream.println("Factory \nName " + factory[i].GetName() + 
                "\nSize " + factory[i].GetSize());
                maximumWarehouses = factory[i].getMaxWarehouses();
                outputstream.println("\nWarehouses");
                outputstream.println(factory[i].ReturnWarehouseData(maximumWarehouses)); // Using another function to get warehouse data 
            }
        }
        outputstream.close(); // Closes the file 
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // OTHER FUNCTIONS USED 
    //-----------------------------------------------------------------------------------------------------------------
    
    //-----------------------------------------------------------------------------------------------------------------
    // METHOD:  DisplayListOfFactories()
    // PURPOSE: Displays the list of factories with factory name & size and its warehouses     
    //-----------------------------------------------------------------------------------------------------------------
    private void DisplayListOfFactories(Factory[] f)
    {
        String fMessage;
        String wMessage;
        for(int i=1; i < f.length; i++)
        {
            if (f[i] != null)
            {
                fMessage = "Factory " + f[i].GetName() + " has size " + f[i].GetSize();
                System.out.println(fMessage);
                int max = f[i].getMaxWarehouses();
                wMessage = f[i].ListWarehouses(max, 1);
                System.out.println(wMessage);
            }
        }
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // METHOD: inputName()
    // PURPOSE: Asks user to enter Warehouse name
    //-----------------------------------------------------------------------------------------------------------------
    private String inputName(int i)
    {
        System.out.print("\nEnter name for warehouse " + i + ": ");
        String name = keyboard.next();
        return(name);
    }

    //-----------------------------------------------------------------------------------------------------------------
    // METHOD: inputFruit()
    // PURPOSE: Asks user to enter Fruit for Warehouse
    //-----------------------------------------------------------------------------------------------------------------
    private String inputFruit()
    {
        System.out.print("Enter fruit: ");
        String fruit = keyboard.next();
        while (!(fruit.equalsIgnoreCase("Apple") || fruit.equalsIgnoreCase("Orange") || fruit.equalsIgnoreCase("Pear")))
        {
            System.out.print("It can only be 'Apple', 'Orange', or 'Pear' \nEnter again: "); // Display message if not correct
            fruit = keyboard.next();
        }
        return(fruit);
    }

    //-----------------------------------------------------------------------------------------------------------------
    // METHOD: inputCapacity 
    // PURPOSE: Asks user to enter capacity for warehouse
    //-----------------------------------------------------------------------------------------------------------------
    private int inputCapacity()
    {
        System.out.print("Enter maximum capacity: ");
        int capacity = keyboard.nextInt();
        while (capacity < 0)
        {
            System.out.print("Should be a positive number \n Enter again: ");
            capacity = keyboard.nextInt();
        }
        return(capacity);
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // METHOD: checkSizeOfFactory() 
    // PURPOSE: Checks the size of the warehouse by restricting it to 'S', 'M', 'L'
    //-----------------------------------------------------------------------------------------------------------------
    
    private String checkSizeOfFactory(String size)
    {
        while (!(size.equalsIgnoreCase("S") || size.equalsIgnoreCase("M") || size.equalsIgnoreCase("L")))        
        {
            System.out.print("Size can only be one of 'S', 'M', or 'L' \nEnter again: ");
            size = keyboard.next();
        }
        return (size);
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // METHOD: checkIfFactoryNull()
    // PURPOSE: Checks if factory array is null, return bool true/false
    //-----------------------------------------------------------------------------------------------------------------
    private Boolean checkIfFactoryNull()
    {
        Boolean isFound = false;
        for (int i=1; i < factory.length; i++)          // Check if there is any factory added yet
        {
            if (factory[i] != null)
            {
                isFound = true;
            }
        }
        return(isFound);
    }
    
    //-----------------------------------------------------------------------------------------------------------------
    // METHOD: checkFactoryName()
    // PURPOSE: Checks if the factory name has already been added before
    //-----------------------------------------------------------------------------------------------------------------
    private Boolean checkFactoryName(String factoryName)
    {
        Boolean isFound = false;
        
        for(int i=1; i <= 5; i++)
        {
            if(factory[i] != null)
            {
                if(factory[i].GetName().equalsIgnoreCase(factoryName)) isFound = true;
            }
        }
        return(isFound);
    }
}
