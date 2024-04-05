import java.util.Scanner;

public class TicTacToe {
    Scanner scanner = new Scanner(System.in);
    final char[][] board = new char[3][3];
    Player player1;
    Player player2;

    public class Tableau {
        int x;
        int y;

        Tableau(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public class Player {
        String name;
        char sign;

        public Player(String name, char sign) {
            this.name = name;
            this.sign = sign;
        }

        public Tableau select() {
            int rowChoice, colChoice;
            while (true) {
                System.out.print(name + ", enter your row choice (0, 1, or 2): ");
                rowChoice = scanner.nextInt();
                if (rowChoice >= 0 && rowChoice < 3) {
                    break;
                } else {
                    System.out.println("Invalid row choice. Please enter a number between 0 and 2.");
                }
            }
            while (true) {
                System.out.print(name + ", enter your col choice (0, 1, or 2): ");
                colChoice = scanner.nextInt();
                if (colChoice >= 0 && colChoice < 3) {
                    break;
                } else {
                    System.out.println("Invalid col choice. Please enter a number between 0 and 2.");
                }
            }
            Tableau squareSelected = new Tableau(rowChoice, colChoice);
            return squareSelected;
        }
        

        public boolean iselected() {
            Tableau squareSelected = select();
            if (board[squareSelected.x][squareSelected.y] == '-') {
                board[squareSelected.x][squareSelected.y] = sign;
                return true;
            } else {
                System.out.println("This position is already occupied. Try again.");
                return false;
            }
        }
    }

    public void initializeGame() {
        System.out.println("Player1 Name:");
        String name = scanner.nextLine();
        player1 = new Player(name, 'X');
        System.out.println("Player2 Name:");
        name = scanner.nextLine();
        player2 = new Player(name, 'O');

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public static void printBoard(char[][] board) {
        System.out.println("Current state of the board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWin(char[][] board, Player player) {
        char playerSign = player.sign;
        String playerName = player.name;
    
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == playerSign && board[i][1] == playerSign && board[i][2] == playerSign) {
                System.out.println(playerName + " wins!");
                return true; // Row win
            }
            if (board[0][i] == playerSign && board[1][i] == playerSign && board[2][i] == playerSign) {
                System.out.println(playerName + " wins!");
                return true; // Column win
            }
        }
        // Check diagonals
        if (board[0][0] == playerSign && board[1][1] == playerSign && board[2][2] == playerSign) {
            System.out.println(playerName + " wins!");
            return true; // Main diagonal win
        }
        if (board[0][2] == playerSign && board[1][1] == playerSign && board[2][0] == playerSign) {
            System.out.println(playerName + " wins!");
            return true; // Secondary diagonal win
        }
        return false;
    }
    

    public void startGame() {
        initializeGame();
        printBoard(board);

        Player currentPlayer = player1;
        while (true) {
            if (currentPlayer.iselected()) {
                printBoard(board);
                if (checkWin(board, currentPlayer)) {
                    break;
                }
                if (isBoardFull(board)) {
                    System.out.println("It's a draw!");
                    break;
                }
                currentPlayer = (currentPlayer == player1) ? player2 : player1;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("It's a TicTacToe Game");
        System.out.println("Click P to Play");
        String choice = scanner.nextLine();
        while(choice.equals("P") || choice.equals("p")) {
            TicTacToe game = new TicTacToe();
            game.startGame();
            System.out.println("Do you want to play again? (Press P to play or any other key to quit)");
            choice = scanner.nextLine();
        }
        scanner.close(); 
    }
    
    }

