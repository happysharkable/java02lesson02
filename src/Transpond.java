public class Transpond {

    static String line = "10 3 1 2\n2 3 2 2\n5 6 7 1\n300 3 1 0";
    static String[][] convertedArray;

    static String[][] toArray (String string) {
        String lineSep = "\n";
        String rowSep = " ";

        // вычисляем размерность итогового массива
        int iLength = string.split(lineSep).length;
        int jLength = getMaxLength(string.split(lineSep));
        String[][] resultArray = new String[iLength][jLength];

        // заполняем массив
        for (int i = 0; i < iLength; i++) {
            resultArray[i] = string.split(lineSep)[i].split(rowSep);
        }

        return resultArray;
    }

    static void calcDimensions (String[][] array) throws ArrayIndexOutOfBoundsException {

        // Проверка размерности по i
        if (array.length != 4) {
            throw new ArrayIndexOutOfBoundsException("Array is not 4x4 array.");
        }

        // Проверка размерностей по j
        for (int i = 0; i < array.length; i++) {
            if (array[i].length != 4) {
                throw new ArrayIndexOutOfBoundsException("Array is not 4x4 array.");
            }
        }
    }

    static double evaluateSum (String[][] array) throws NumberFormatException {
        double sum = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (isNaN(array[i][j], i, j)) {
                    sum += Integer.parseInt(array[i][j]);
                }
            }
        }

        return sum/2;
    }

    static boolean isNaN (String string, int i, int j) throws NumberFormatException {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Element [" + i + "][" + j + "] is NaN");
        }
    }

    // вычисление количества столбцов для итогового массива
    static int getMaxLength (String[] array) {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            String[] tmpArray = array[i].split(" ");
            if (tmpArray.length > max) {
                max = tmpArray.length;
            }
        }
        return max;
    }

    public static void main(String[] args) {

        convertedArray = toArray(line);

        try {
            calcDimensions(convertedArray);
            System.out.println(evaluateSum(convertedArray));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}

//