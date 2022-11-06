package InputOutput;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.System.load;

public class Matrix {
    private double[][] data;

    public Matrix(String fileName) {

    }
    public Matrix(int x, int y) {
        this.data = new double[y][x];
    }

    public double getValue(int x, int y) {
        return this.data[y][x];
    }

    public void setValue(int x, int y, double value){
        this.data[y][x] = value;
    }

    public int getHeight() {
        return data.length;
    }

    public int getWidth() {
        return data[0].length;
    }

    public boolean saveFile(String fileName) {
        try {
            File file = new File(fileName);
            PrintWriter pw = new PrintWriter(file);

            for (double[] row : data) {
                for (int i = 0; i < row.length; i++) {
                    pw.print((i==0 ? "" : ",") + row[i]);
                }
                pw.print("");
            }
            pw.close();
            return true;
        } catch (Exception e) {
            return false;

        }
    }

    public boolean load(String fileName) {
        try (Scanner sc = new Scanner(new File(fileName))){
            ArrayList<String> lines = new ArrayList<>();
            while (sc.hasNext()) {
                lines.add(sc.nextLine());
            }

            int x = 0;
            int y = 0;
            try {
                data = new double[lines.size()][];
                for (y = 0; y < lines.size(); y++) {
                    String line = lines.get(y);
                    String[] cells = line.split(",");
                    data[y] = new double[cells.length];
                    for (x = 0; x < cells.length; x++) {
                        data[y][x] = Double.parseDouble(cells[x]);
                    }
                }
            } catch (Exception e) {
                return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        String m1FileName = "m1.csv";

        Matrix m1 = new Matrix(4, 6);
        m1.saveFile(m1FileName);

        m1.setValue(2, 5, 22.3);
        m1.saveFile(m1FileName);
    }
}
