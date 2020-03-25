
/**
 * @author {Andreas}
 *
 */
public class Matrix {

 // Variables used throughout
 private double[][] elements;
 private int rows;
 private int columns;

 /**
 * @param rows
 * @param columns
 */
public Matrix(int rows, int columns) {
  elements = new double[rows][columns]; // create the right size matrix from input

  this.rows = rows;
  this.columns = columns;
 }


 /**
  * Add together two equally sized matrices
 * @param x first matrix
 * @param y second matrix
 * @return addition result matrix
 */
public static Matrix Add(Matrix x, Matrix y) {
  // Get all the values from provided matrices
  double element;
  int xRow, xCol, yRow, yCol;
  xRow = x.getRows();
  yRow = y.getRows();
  xCol = x.getColumns();
  yCol = y.getColumns();


  // addition only allowed for equally sized matrices
  if (xRow == yRow && xCol == yCol) {
   // create a new equally sized matrix for results
   Matrix resultMatrix = new Matrix(xRow, xCol);

   for (int i = 0; i < xRow; i++) {
    for (int j = 0; j < yCol; j++) {
     element = x.getElement(i, j) + y.getElement(i, j); // add the elements in corresponding positions
     resultMatrix.setElement(element, i, j); // set the new element in result matrix
    }
   }
   return resultMatrix;
  }

  // matrices are not compatible for addition
  else {

   System.out.println("Incompatible matrices, RETURNED NULL");

   return null;

  }
 }

 
 /**
 * cross multiply two compatible matrices
 * @param x first matrix
 * @param y second matrix
 * @return result multiplied matrix
 * 
 */
public static Matrix Multiply(Matrix x, Matrix y) {

  // Get all the values from provided matrices
  double element;
  int xRow, xCol, yRow, yCol;
  xRow = x.getRows();
  yRow = y.getRows();
  xCol = x.getColumns();
  yCol = y.getColumns();

  //You can only multiply two matrices if,
  //the number of columns in the first matrix equals the number of rows in the second.
  if (xCol == yRow) {
   // creates result matrix to return with new dimensions
   Matrix resultMatrix = new Matrix(xRow, yCol);

   for (int i = 0; i < xRow; i++) {
    for (int j = 0; j < yCol; j++) {
     element = 0;
     for (int k = 0; k < xCol; k++) {
      element = element + x.getElement(i, k) * y.getElement(k, j);
     }

     resultMatrix.setElement(element, i, j);
    }
   }
   return resultMatrix;
  } else {

   System.out.println("Incompatible matrices, RETURNED NULL");

   return null;

  }
 }


 /**
 * @param value is set to given position i j
 * @param i
 * @param j
 */
public void setElement(double value, int i, int j) {

  elements[i][j] = value;

 }


 public int getRows() {

  return rows;

 }

 public int getColumns() {

  return columns;

 }

 public double getElement(int rows, int columns) {

  return elements[rows][columns]; // returns a double

 }
 

}