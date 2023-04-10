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
            Game GM = new Game(initial, size_number);
            
            boolean checkbrokeninput = false;
            while (!checkbrokeninput) {
                try {
                    System.out.printf("\nSet broken light (Y/N) ?\n");
                    String Y_N = scan.nextLine();
                    if (Y_N.equals("Y") || Y_N.equals("y")) {
                        int rowbroke = 0, colbroke = 0;
                        System.out.printf("Enter row of broken light (0 - %d) = \n", size_number - 1);
                        boolean checkrow = false;
                        while (!checkrow) {
                            rowbroke = Integer.parseInt(scan.nextLine());
                            if (rowbroke < 0 || rowbroke > size_number - 1) {
                                System.out.printf("Please insert (0 - %d) = \n", size_number - 1);
                            } else {
                                checkrow = true;
                            }
                        }
                        System.out.printf("Enter col of broken light (0 - %d) = \n", size_number - 1);
                        boolean checkcol = false;
                        while (!checkcol) {
                            colbroke = Integer.parseInt(scan.nextLine());
                            if (colbroke < 0 || colbroke > size_number - 1) {
                                System.out.printf("Please insert (0 - %d) = \n", size_number - 1);
                            } else {
                                checkcol = true;
                            }
                        }
                        GM.addbroken(rowbroke, colbroke);
                        checkbrokeninput = true;
                    } else if (Y_N.equals("N") || Y_N.equals("n")) {
                        checkbrokeninput = true;
                    } else {
                        System.out.println("Input (Y/N)");
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(e);
                }
            }
            GM.Start();
            repeat = false;
        }
    }
}
