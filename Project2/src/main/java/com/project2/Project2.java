package com.project2;

import java.io.*;
import java.util.*;

public class Project2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean repeat = true;
        int size_number = 0;
        String initial = "";
        while (repeat) {
            boolean error = true;
            while (error) // error or not ?
            {
                try {
                    System.out.println("Enter number of rows square grid = "); // size of grid
                    size_number = Integer.parseInt(scan.nextLine()); // size

                    if (size_number <= 1) {
                        System.out.println("Number of rows square grid must be more than 1");
                    } else {
                        error = false;
                    }

                } catch (InputMismatchException e) {
                    System.out.println(e);
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            }

            boolean checkinitialinput = false;
            while (!checkinitialinput) {
                try {
                    System.out.printf("Enter initial states (%d bits, left to right, line by line) = \n",
                            size_number * size_number);
                    initial = scan.nextLine();

                    for (int i = 0; i < initial.length(); i++) {
                        if (initial.charAt(i) != '0' && initial.charAt(i) != '1') {
                            System.out.println("Invalid initial state input !!");
                            System.out.println("Please insert only 0 or 1");

                            checkinitialinput = false;
                        }
                    }
                    checkinitialinput = true;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(e);
                }
            }
            LightSolver_String LS = new LightSolver_String(initial, size_number);
            LS.printSolution(LS.Solve());

            /*LightSolver LS = new LightSolver( initial, size_number); //roonpee's code
        if (LS.getstartState().hashCode() == -1)
        {
            System.out.println("Can't find solution ");
        }*/
            repeat = false;
        }
    }
}
