import java.util.Random;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class StreamGenerator
{
   Queue patientQueue = new Queue();
   
   public void createQueue()
   {
      Random urgencyGenerator = new Random();
      int urgency;
      
      Random nameGenerator = new Random();
      int nameNum;
      
      Random surnameGenerator = new Random();
      int surnameNum;
      
      Random IDgenerator = new Random();
      int IDnum;
      
      String name;
      String surname;
      
      Patient person = null;
      
      for (int i = 1; i <= 100; i++)
      {
         urgency = urgencyGenerator.nextInt(50) + 1; // change in pain ranking
         nameNum = nameGenerator.nextInt(100);
         surnameNum = surnameGenerator.nextInt(100);
         IDnum = 10000 + IDgenerator.nextInt(90000);
         
         name = getName(nameNum, "names.txt");
         surname = getName(surnameNum, "surnames.txt");
         
         person = new Patient(name, surname, IDnum, urgency);
         patientQueue.insertPatient(person);
      }
   }
   
   public String getName(int index, String searchFile)
   {
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
      
      for (int i = 0; i <= index; i++)
      {
         line = fileInput.nextLine().trim();
      }
      
      fileInput.close();
      
      return line;
   }
   
   public Queue getQueue()
   {
      return patientQueue;
   }
}