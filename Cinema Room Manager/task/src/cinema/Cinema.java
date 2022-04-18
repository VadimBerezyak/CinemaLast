package cinema;

import java.util.Arrays;
import java.util.Scanner;

class Cinema {
    public static Scanner sc = new Scanner(System.in);
    public static int countSoldSeats = 0;
    public static int totalIncome = 0;
    public static int currentIncome = 0;
    public static void main(String[] args) {


        System.out.print("Enter the number of rows:\n" + "> ");
        int rows = sc.nextInt();
        System.out.print("Enter the number of seats in each row:\n" + "> ");
        int seats = sc.nextInt();

        String[][] room = new String[rows][seats + 1];
        makeArraySeats(rows, seats, room);
        showMenu(rows, seats, room);
    }

    public static void makeArraySeats(int rows, int seats, String[][] room) {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats + 1; j++) {
                if (j == 0) {
                    room[i][j] = Integer.toString(i + 1);
                } else {
                    room[i][j] = "S";
                }
            }

        }
    }

    public static void showMenu(int rows, int seats, String[][] room) {
        System.out.println();
        System.out.print("1. Show the seats\n" +
                "2. Buy a ticket\n" +
                "3. Statistics\n" +
                "0. Exit\n" + "> ");
        Scanner sc = new Scanner(System.in);

        int userChoise = sc.nextInt();
        switch (userChoise) {
            case 1:
                System.out.println();
                showSeats(rows, seats, room);
                showMenu(rows, seats, room);
                break;
            case 2:
                System.out.println();
                showAndByTicket(rows, seats, room);
                System.out.println();
                break;
            case 3:
                System.out.println();
                showStatistics(rows, seats, room);
                System.out.println();
                showMenu(rows, seats, room);
                break;
            case 0:
                return;
            default:
                System.out.println("Please, try again!");
        }

    }

    private static void showStatistics(int rows, int seats, String[][] room) {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats+1; j++) {
                if (room[i][j] == "B") {
                    countSoldSeats++;
                }
        }
        }
        totalIncome = rows/2 * seats * 10 + (rows - rows/2)*seats*8;
        float percentage = (float) countSoldSeats / (float) (rows*seats)*100;
        System.out.printf("Number of purchased tickets: %d%n  Percentage:%.2f%%nCurrent income: $%d%nTotal income: $%d", countSoldSeats, percentage, currentIncome, totalIncome);
    }


    private static void showAndByTicket(int rows, int seats, String[][] room) {
        System.out.print("Enter a row number:\n" + "> ");
        int userRow = sc.nextInt();
        System.out.print("Enter a seat number in that row:\n" + "> ");
        int userSeat = sc.nextInt();

        System.out.print("Ticket price: ");
        ticketPrice(rows, seats, userRow, userSeat, room);
    }

    private static void ticketPrice(int rows, int seats, int userRow, int userSeats, String[][] room) {
        if (room[userRow - 1][userSeats] == "B") {
            System.out.println("That ticket has already been purchased!");
            System.out.println();
            showAndByTicket(rows, seats, room);
        } else {
            if (rows * seats <= 60 || userRow <= rows / 2) {
                System.out.print("$10\n");
                currentIncome += 10;
                totalIncome = rows * seats * 10;
            } else {
                System.out.print("$8\n");
                currentIncome +=8;

            }
            room[userRow - 1][userSeats] = "B";
            showMenu(rows, seats, room);
        }

        }

    public static void showSeats(int rows, int seats, String[][] room) {
        System.out.print("Cinema:\n" + "  ");
        for (int i = 1; i < seats + 1; i++) {
            if (i != seats) {
                System.out.print(i + " ");
            } else {
                System.out.println(i);
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats + 1; j++) {
                if (j != seats) {
                    System.out.print(room[i][j] + " ");
                } else {
                    System.out.print(room[i][j] + "\n");
                }

            }
        }
    }
}

