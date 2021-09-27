import java.util.Arrays;

public class TheGameOfLife {


    public int[][] startSimulation(int[][] matrix, int iteration){
        int height = matrix.length;
        int width = matrix[0].length;

        int[][] result = new int[height][width];
        for(int i = 0; i < iteration; i++){
            simulate(matrix, result);
            //swap
            int[][] temp = matrix.clone();
            matrix = result.clone();
            result = temp.clone();
//            swap(matrix, result);
        }
        return matrix;
    }

    public void simulate(int[][] matrix, int[][] result){
        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                //live or die
                if(cellCanLive(matrix, i, j)){
                    result[i][j] = 1;
                }else{
                    result[i][j] = 0;
                }
            }
        }
    }

    public boolean cellCanLive(int[][] matrix, int i, int j){
        //get neighbors
        int aliveNeighbors = countAliveNeighbors(matrix, i, j);

        if(aliveNeighbors == 3)
            return true;
        return matrix[i][j] == 1 && aliveNeighbors == 2;
    }

    public int countAliveNeighbors(int[][] matrix, int i, int j){
        int count = 0;
        for(int h = i-1; h <= i+1; h++){
            for(int v = j-1; v <= j+1; v++){
                //out of bound
                if(h < 0 || h >= matrix.length || v < 0 || v >= matrix[h].length) continue;
                //current cell
                if(h == i && v == j) continue;
                //is alive
                if(matrix[h][v] == 1) count++;
            }
        }
        return count;
    }

    public void swap(int[][] matrix, int[][] result){
        int[][] temp = matrix.clone();
        matrix = result.clone();
        result = temp.clone();
    }


    public void printMatrix(int[][] matrix){
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[i].length; j++){
                System.out.print(matrix[i][j] + " ");
                if(j == matrix[i].length - 1){
                    System.out.println();
                }
            }
        }
    }

    public static void main(String args[]){
        TheGameOfLife runner = new TheGameOfLife();
        int[][] matrix = {
                {1, 0, 1, 0, 1},
                {0, 0, 1, 0, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 1, 0, 0},
                {1, 0, 1, 0, 1},
        };
        int[][] result = runner.startSimulation(matrix, 3);
//        int[][] result = {
//                {0, 0, 0, 0, 0},
//                {0, 0, 1, 0, 0},
//                {0, 1, 1, 1, 0},
//                {0, 0, 1, 0, 0},
//                {0, 0, 0, 0, 0},
//        };
//        runner.swapMatrix(matrix, result, 5, 5);
        runner.printMatrix(result);
//        System.out.println(runner.countAliveNeighbors(testkernel1, 0, 1));
    }
}
