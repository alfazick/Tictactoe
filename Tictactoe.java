import java.util.Scanner;

public class Tictactoe {
    public static void main(String[] args) {
        // write your code here
        
        //char[][] currentState = readInput();
        
        
        char[][] currentState = createGrid();
        ShowGrid(currentState);
        
        char mark = 'X';
        boolean GameOn = true;
        
        while(GameOn) {
            //ShowGrid(currentState);
            MakeMove(currentState,mark);
            ShowGrid(currentState);
            String result = checkGrid(currentState);
            if (!("Game not finished".equals(result))) {
                GameOn = false;
                System.out.print(result);
                break;
            }
            if (mark == 'X'){
                mark = 'O';
                continue;
            }
            
            if (mark == 'O') {
                mark = 'X';
                continue;
            }
            
            
        }
        //ShowGrid(currentState);
        //MakeMove(currentState);
        //checkGrid(currentState);
    }
    
    public static boolean check(String s) {
        if (s == null) {
        return false;
        }
        int len = s.length();
        for (int i = 0; i < len; i++) {
            // checks whether the character is not a digit and not a space
                if ((Character.isDigit(s.charAt(i)) == false) && (s.charAt(i) != ' ')) {
                    return false; // if it is not any of them then it returns false
            }
        }
        return true;
    }
    
    public static void MakeMove(char[][] matrix, char move) {
        boolean correctMove = false;
        int i;
        int j;
        while (!correctMove) {
            System.out.print("Enter the coordinates: ");
            Scanner scanner = new Scanner(System.in);
            String commands = scanner.nextLine();
            
            if (!(check(commands))) {
                System.out.println("You should enter numbers!");
                continue;
            }
            
            i = commands.charAt(0) - 49;
            j = commands.charAt(2) - 49;
            
            //System.out.println(i);
            //System.out.println(j);
            
            if (i < 0 || i > 2 || j < 0 || j > 2 ) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }
            
            if ((matrix[i][j] == 'X') || (matrix[i][j] == 'O')) {
                System.out.println("This cell is occupied! Choose another one!");
                continue;
            }
            else {
                correctMove = true;
                matrix[i][j] = move;
            }
            
            //matrix[i][j] = move;
            //ShowGrid(matrix);
            //correctMove = true;
        }
        
        //matrix[i][j] = move;
        //ShowGrid(matrix);
    }
    
    public static String checkGrid(char[][] matrix) {
        
        int xs = 0;
        int xsum = 0;
        int os = 0;
        int ysum = 0;
        
        int curSum = 0;
        boolean XWon = false;
        boolean OWon = false;
        String result = "Game not finished";
        
        
        int[][] diagonal = { {0,0}, {1,1}, {2,2}};
        int[][] antidiagonal = { {0,2}, {1,1}, {2,0}};
        
        int curSumdiag = 0;
        int curSumantidiag = 0;
        
        for (int coord = 0; coord < diagonal.length; coord++) {
            int i = diagonal[coord][0];
            int j = diagonal[coord][1];
            curSumdiag += (int) matrix[i][j];
            //System.out.println(curSumdiag);                
        }
        
        for (int coord = 0; coord < antidiagonal.length; coord++) {
            int i = antidiagonal[coord][0];
            int j = antidiagonal[coord][1];
            curSumantidiag += (int) matrix[i][j];
            //System.out.println(curSumantidiag);
        }
        //System.out.println(curSumantidiag);
        if (curSumdiag == 264 || curSumantidiag == 264)  {
            result = "X wins";
            XWon = true;
        }
        
        if (curSumdiag == 237 || curSumantidiag == 237 )  {
            result = "O wins";
            OWon = true;
        }

        
        
        for (int i = 0; i < matrix[0].length; i++) {
            int curSumbyrow = 0;
            int curSumbycolumn = 0;
            for (int j = 0; j < matrix.length; j++) {
                curSumbyrow += (int) matrix[i][j];
                curSumbycolumn += (int) matrix[j][i];
                
                if (matrix[i][j] == 'X') {
                    xs += 1;
                }
                
                if (matrix[i][j] == 'O') {
                    os += 1;
                }
                
                
                                
            }
            
            if (curSumbyrow == 264 || curSumbycolumn == 264)  {
                result = "X wins";
                XWon = true;
            }
            
            if (curSumbyrow == 237 || curSumbycolumn == 237)  {
                result = "O wins";
                OWon = true;
            }
                
        }
        
        //if (Math.abs(xs-os) > 1 || (XWon && OWon)) {
        //    result = "Impossible";
        //}
        
        //System.out.println(xs+os);
        if (xs+os == 9 && !(XWon || OWon)) {
            result = "Draw";
        }
        
        return result;
        
    }
    
    public static char[][] readInput() {
           
        char[][] grid = new char[3][3];
        Scanner scanner = new Scanner(System.in);
        String commands = scanner.nextLine();
        
        
        int idx = 0;
        
        
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = commands.charAt(idx);
                idx += 1;
            }   
        }
        
        return grid;
    }
    
    public static char[][] createGrid() {
           
        char[][] grid = new char[3][3];
        
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                grid[i][j] = '_';
            }   
        }
        
        return grid;
    }
    
    public static void ShowGrid(char[][] grid ) {
        System.out.println("---------");
        for (int i = 0; i < grid[0].length; i++) {
            System.out.print("| ");
            for (int j = 0; j < grid.length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
}