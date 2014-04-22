// Helper class; explicitly handles the queue

import java.util.Random;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StreamGenerator
{
   Queue patientQueue = new Queue();
   
   // method to make the queue
   public void createQueue()
   {
      // random number generators and their uses
      Random urgencyGenerator = new Random();
      int urgency;
      
      Random nameGenerator = new Random();
      int nameNum;
      
      Random surnameGenerator = new Random();
      int surnameNum;
      
      Random IDgenerator = new Random();
      int IDnum;
      
      // other patient info
      String name;
      String surname;
      
      Patient person = null;
      
      // creates 10 patients
      for (int i = 1; i <= 10; i++)
      {
         urgency = urgencyGenerator.nextInt(60) + 1; // assigns urgency of range 1 to 60
         nameNum = nameGenerator.nextInt(100); // picks an index from 0 to 99
         surnameNum = surnameGenerator.nextInt(100); // index 0 to 99
         IDnum = 10000 + IDgenerator.nextInt(90000); // 5 digit ID number
         
         /**
         Note: wrt to the urgency number choice - rather than inefficiently categorising and then sub-categorising
         I thought it would be more efficient to assign a wider range of numbers for varying degrees of urgency.
         An example: a brain haemorrhage could be like a 1 and a broken leg could be a 10. There's no arguing that
         both are urgent, i.e. "Red", but choosing a broader range of numbers for urgency assignment indicates urgency
         and deals with patient prioritisation in one number
         **/
         
         // searches in respective files at index randomly picked above for patient name / surname
         name = getName(nameNum, "names.txt");
         surname = getName(surnameNum, "surnames.txt");
         
         // creates instance of person
         person = new Patient(name, surname, IDnum, urgency);
         
         // inserts person into binary heap (queue)
         patientQueue.insertPatient(person);
      }
   }
   
   public void treatPatients()
   {
      // calls a "treatPatient()" method which deletes one singular patient
      // iterates deletes every person in the list
      for (int i = 0; i <= 9; i++)
      {
         patientQueue.treatPatient();
      }
   }
   
   public String getName(int index, String searchFile)
   {
      // method for fetching a random name from the file, given an index
      
      Scanner fileInput = null;
      
      try
      {
         fileInput = new Scanner(new FileInputStream(searchFile));
      }
      catch (FileNotFoundException e)
      {
         System.out.println("Oops! Sorry; something went wrong.");
      }
      
      String line = null;
      
      // iterates through every line in the list until it gets to the specified index
      for (int i = 0; i <= index; i++)
      {
         line = fileInput.nextLine().trim();
      }
      
      fileInput.close();
      
      return line;
   }
}