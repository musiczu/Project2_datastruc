package com.project2;

import java.io.*;
import java.util.*;

public class Project2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean repeat = true;
        int size_number = 0;
        String input = "";
        while (repeat) {
            boolean error = true;
            while (error) // error or not ?
            {
                try {
                    System.out.println("Enter number of rows square grid = "); // size of grid
                    input = scan.nextLine();
                    size_number = scan.nextInt(); // size

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
        }
        boolean checkinitialinput = false;
        while (!checkinitialinput){
            try {
                System.out.printf("Enter initial states (%d bits, left to right, line by = ",
                        size_number * size_number);
                String initial = scan.nextLine();

                // check 01
                for (int i = 0; i < initial.length(); i++) {
                    if (initial.charAt(i) != '0' && initial.charAt(i) != '1') {
                        System.out.println("Invalid initial state input !!");
                        System.out.println("Please insert only 0 or 1");

                        checkinitialinput = false;
                    }
                }
                checkinitialinput = true;
            } catch (NumberFormatException e) {
                // NumberFormatException
                System.out.println(e);
            }catch (StringIndexOutOfBoundsException e) {
                // NumberFormatException
                System.out.println(e);
            }
        }  
        LightSolver LS = new LightSolver( input, size_number);
        if (LS.getstartState().hashCode() == -1)
        {
            System.out.println("Can't find solution ");
        }
        else {
            
        }
        
        repeat = false;

        
        /*
         * int i = 0;
         * while (initial != "0000" || initial != "0001" || initial != "0010" || initial
         * != "0011" || initial != "0100"
         * || initial != "0101" || initial != "0110" || initial != "0111" || initial !=
         * "1000" || initial != "1001"
         * || initial != "1010" || initial != "1011" || initial != "1100" || initial !=
         * "1101" || initial != "1110"
         * || initial != "1111")
         * {
         * if (i != 0)
         * {
         * System.out.println("Wrong Initial state");
         * }
         * initial = scan.nextLine();
         * }
         */

    }

}
