import java.util.Scanner;

public class TicTacToe {

    private static char[][] board = new char[3][3];  // 3x3 Tic-Tac-Toe board
    private static char currentPlayer = 'X';  // Player 1 starts with 'X'

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while (playAgain) {
            resetBoard();  // Reset the board for a new game
            boolean gameEnded = false;

            while (!gameEnded) {
                printBoard();
                playerMove();
                gameEnded = checkWinner() || isBoardFull();

                if (gameEnded) {
                    printBoard();
                    if (checkWinner()) {
                        System.out.println("Player " + currentPlayer + " wins!");
                    } else {
                        System.out.println("It's a draw!");
                    }
                }

                // Switch player turns after every move
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }

            // Ask if players want to play again
            System.out.println("Do you want to play again? (yes/no)");
            playAgain = scanner.nextLine().equalsIgnoreCase("yes");
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }

    // Method to initialize or reset the board
    private static void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';  // Empty board spaces
            }
        }
    }

    // Method to display the board
    private static void printBoard() {
        System.out.println("Current board:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Method to handle the player's move
    private static void playerMove() {
        Scanner scanner = new Scanner(System.in);
        int row, col;

        while (true) {
            System.out.println("Player " + currentPlayer + ", enter your move (row [1-3] and column [1-3]): ");
            row = scanner.nextInt() - 1;  // Convert to 0-indexed
            col = scanner.nextInt() - 1;

            // Validate the move
            if (row < 0 || col < 0 || row > 2 || col > 2) {
                System.out.println("This position is off the board. Try again.");
            } else if (board[row][col] != '-') {
                System.out.println("This position is already taken. Try again.");
            } else {
                break;
            }
        }

        board[row][col] = currentPlayer;  // Place the current player's symbol
    }

    // Method to check if the board is full (a draw)
    private static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;  // No empty spaces
    }

    // Method to check if there's a winner
    private static boolean checkWinner() {
        // Check rows, columns, and diagonals
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    // Check all rows for a winning condition
    private static boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    // Check all columns for a winning condition
    private static boolean checkColumns() {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }
        return false;
    }

    // Check both diagonals for a winning condition
    private static boolean checkDiagonals() {
        return ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer));
    }
}

