/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author tea
 */
public class tictactoe {
    
    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> computerPositions = new ArrayList<Integer>();
    
     public static void main(String[] args){
         char[][] gameBoard = {{' ','|',' ','|',' '},
                               {'-','+','-','+','-'},
                               {' ','|',' ','|',' '},
                               {'-','+','-','+','-'},
                               {' ','|',' ','|',' '}};
         
         printGameBoard(gameBoard);
         
         while(true){
             Scanner scan = new Scanner(System.in);
             System.out.println("pick an index (1-9):");
             int playerPos = scan.nextInt();
             while(playerPositions.contains(playerPos) || computerPositions.contains(playerPos)){
                 System.out.println("enter a valid index:");
                 playerPos = scan.nextInt();
             }

             placePiece(gameBoard, playerPos, "player");
             
             String result = checkWinner();
             if(result.length() > 0){
                 System.out.println(result);
                 break;
             }
             
             Random rnd = new Random();
             int compPos = rnd.nextInt(9) + 1;
             while(playerPositions.contains(compPos) || computerPositions.contains(compPos)){
                 compPos = rnd.nextInt(9) + 1;
             }
             placePiece(gameBoard, compPos, "computer");
             
             printGameBoard(gameBoard);
             
             result = checkWinner();
             if(result.length() > 0){
                 System.out.println(result);
                 break;
             }
             
         }
         
     }
     
     public static void printGameBoard(char[][] gameBoard){
         for (char[] row : gameBoard) {
             for (char c : row) {
                 System.out.print(c);
             }
             System.out.println();
         }
     }
     
     public static void placePiece(char[][] gameBoard, int pos, String user){
         char symbol = ' ';
         if(user.equals("player")){
             symbol = 'x';
             playerPositions.add(pos);
         }
         else if(user.equals("computer")){
             symbol = 'o';
             computerPositions.add(pos);
         }
         
         switch (pos) {
             case 1:
                 gameBoard[0][0] = symbol;
                 break;
             case 2:
                 gameBoard[0][2] = symbol;
                 break;
             case 3:
                 gameBoard[0][4] = symbol;
                 break;
             case 4:
                 gameBoard[2][0] = symbol;
                 break;
             case 5:
                 gameBoard[2][2] = symbol;
                 break;
             case 6:
                 gameBoard[2][4] = symbol;
                 break;
             case 7:
                 gameBoard[4][0] = symbol;
                 break;
             case 8:
                 gameBoard[4][2] = symbol;
                 break;
             case 9:
                 gameBoard[4][4] = symbol;
                 break;
             default:
                 break;
         }
     }
     
     public static String checkWinner(){
         List topRow = Arrays.asList(1,2,3);
         List midRow = Arrays.asList(4,5,6);
         List botRow = Arrays.asList(7,8,9);
         List leftCol = Arrays.asList(1,4,7);
         List midCol = Arrays.asList(2,5,8);
         List rightCol = Arrays.asList(3,6,9);
         List cross1 = Arrays.asList(1,5,9);
         List cross2 = Arrays.asList(7,5,3);
         
         List<List> conditions = new ArrayList<List>();
         conditions.add(topRow);
         conditions.add(midRow);
         conditions.add(botRow);
         conditions.add(leftCol);
         conditions.add(midCol);
         conditions.add(rightCol);
         conditions.add(cross1);
         conditions.add(cross2);
         
         for(List l : conditions){
             if(playerPositions.containsAll(l)){
                 return "player wins";
             }
             else if(computerPositions.contains(l)){
                 return "computer wins";
             }
         }
         if(playerPositions.size() + computerPositions.size() == 9){
             return "-end-";
         }
         
         return "";
     }
}
