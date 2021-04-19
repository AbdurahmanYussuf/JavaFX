/**
 * Abdurahman Yussuf 991625727
 * Mohammad Nasif Parvez 991582482
 * Final Project
 * April 18, 2021
 */

package content;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class EmployeeFile {

    public static void setList(LinkedList<Employee> employeeList) throws IOException {

        FileReader fr = new FileReader("Employee.data");
        BufferedReader br = new BufferedReader(fr);

        String line = br.readLine();

        while (line != null) {
            StringTokenizer tokenizer = new StringTokenizer(line, ",");
            Employee one = new Employee(tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken());
            employeeList.add(one);
            line = br.readLine();
        }

        br.close();
        fr.close();

    }

    public static void saveStatus(LinkedList<Employee> employeeList) throws IOException {

        FileWriter fw = new FileWriter("Employee.data");
        BufferedWriter bw = new BufferedWriter(fw);

        Iterator<Employee> itEmployee = employeeList.iterator();

        while (itEmployee.hasNext()) {
            Employee one = itEmployee.next();
            bw.write(one.getId() + "," + one.getName() + "," + one.getCity() + "," + one.getEmployee());
            bw.newLine();
        }
                    
        bw.flush();
        fw.close();
    }



    

}
