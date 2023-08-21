package org.jesperancinha.matrixanywhere;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


public class MatrixActivity extends Activity {


    private TableLayout tableMatrix = null;

    private Button btnReturn;

    private Button buttonCalculate = null;

    private EditText[][] tableCalc = null;

    private Integer nRows = null;

    private Integer nColumns = null;

    private TextView textResult = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        setTitle(getString(R.string.matrix_calculation));


        tableMatrix = findViewById(R.id.tableMatrix);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nRows = extras.getInt("NROWS");
            nColumns = extras.getInt("NCOLUMNS");
            tableCalc = new EditText[nRows][nColumns];
            generateMatrix(nRows, nColumns);
        }
        btnReturn = findViewById(R.id.buttonReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MatrixActivity.this, MatrixAnywhereMainActivity.class);
                startActivity(i);
                finish();
            }
        });

        buttonCalculate = findViewById(R.id.buttonCalculate);
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double calculus = calculateDeterminant(tableCalc);
                textResult.setText(Double.toString(calculus));
            }
        });

        textResult = findViewById(R.id.textResult);
    }

    private double calculateDeterminant(EditText[][] tableCalc) {

        double[][] matrix = new double[tableCalc.length][tableCalc[0].length];
        for (int i = 0; i < tableCalc.length; i++) {
            for (int j = 0; j < tableCalc[0].length; j++) {
                if (tableCalc[i][j].getText() != null && !tableCalc[i][j].getText().toString().isEmpty()) {
                    matrix[i][j] = Double.parseDouble(tableCalc[i][j].getText().toString());
                } else {
                    matrix[i][j] = 0;
                }
            }
        }

        return getDeterminant(matrix);

    }


    public double getDeterminant(double[][] matrix) {
        double determinant = 0;

        if (matrix.length == 1) {
            determinant += matrix[0][0];
        } else if (matrix.length == 2) {
            determinant += matrix[0][0] * matrix[1][1] - matrix[0][1]
                    * matrix[1][0];
        } else {
            int multiplier = 1;
            for (double[] matrix1 : matrix) {
                for (int j = 0; j < matrix[0].length; j++) {

                    double[][] subMatrix = getSubMatrix(matrix, j);
                    determinant += multiplier * matrix1[j]
                            * getDeterminant(subMatrix);
                    multiplier *= -1;
                }

            }

        }
        return determinant;
    }

    private double[][] getSubMatrix(double[][] matrix, int c) {

        double[][] subTable = new double[matrix[0].length - 1][matrix.length - 1];
        int currentC = 0;
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (j != c) {
                    subTable[i - 1][currentC++] = matrix[i][j];
                }
            }
            currentC = 0;

        }
        return subTable;
    }

    private void generateMatrix(Integer nRows, Integer nColumns) {

        tableMatrix.setStretchAllColumns(true);
        tableMatrix.bringToFront();
        for (int i = 0; i < nRows; i++) {
            TableRow tr = new TableRow(this);
            for (int j = 0; j < nColumns; j++) {

                EditText c1 = new EditText(this);
                c1.setWidth(20);
                c1.setHeight(20);
                c1.setInputType(InputType.TYPE_CLASS_NUMBER);
                tr.addView(c1);
                c1.setBackgroundDrawable(getResources().getDrawable(R.drawable.edit_style));
                tableCalc[i][j] = c1;
            }
            tableMatrix.addView(tr);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.matrix, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
