package org.jesperancinha.matrixanywhere;

import org.junit.jupiter.api.Test;

class MatrixActivityTest {

    @Test
    void getDeterminant() {
        final MatrixActivity matrixActivity = new MatrixActivity();

        double determinant = matrixActivity.getDeterminant(new double[][]{
                {1, 1},
                {2, 1}
        });

        assert determinant == -1.0;

    }
}