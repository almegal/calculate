import java.util.Arrays;

class UserInput {
    String input;
    String v1;
    String v2;
    String operator;
    boolean v1IsRome;
    boolean v2IsRome;
    boolean isRome;
    boolean isValid;
    // задаем конструктор и сразу отрабатываем введенные данные
    UserInput (String input) throws Exception {
        // удаляем пробелы
        this.input = nonWhiteSpaces(input);
        // по умолчанию данные не валидные и не римскик
        this.isRome = false;
        this.isValid = false;
        //проверяем данные
        parseData();
    }

    private static String nonWhiteSpaces (String input) {
        return input.replaceAll(" ", "");
    }

    private void parseData() throws Exception {
        // определяем типа операции
        if (input.contains("+")) {
            operator = "\\+";
        } else if(input.contains("-")) {
            operator = "-";
        } else if(input.contains("/")) {
            operator = "/";
        } else if (input.contains("*")) {
            operator = "\\*";
        } else {
            throw new Exception("строка не является математической операцией");
        }
        // проверяем на соответствие с форматом данных
        if(input.split(operator).length > 2) {
            throw new Exception("//т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        } else if(input.split(operator).length < 1) {
            throw new Exception("throws Exception //т.к. строка не является математической операцией");
        }

        // записываем значения чисел
        v1 = input.split(operator)[0];
        v2 = input.split(operator)[1];
        // проверяем есть ли такие значения в енуме римских цифр
        for (Rome rome : Rome.values()) {
            if (rome.name().equalsIgnoreCase(v1)){
                v1IsRome = true;
                v1 = Rome.valueOf(v1).arabic;
                // если введены одинаковые цифры - присвоить одинаковые значения.если два операнда одинаковые второй условие не запускается
                if(input.split(operator)[0].equalsIgnoreCase(v2)) {v2IsRome = v1IsRome; v2 = v1;}
            } else if ( rome.name().equalsIgnoreCase(v2)) {
                v2 = Rome.valueOf(v2).arabic;
                v2IsRome = true;
            }
        }
        // проверяем ввод чисел не меньшке 0 и не больше 10
        if ((Integer.parseInt(v1) < 1 || Integer.parseInt(v1) > 10) || ((Integer.parseInt(v2) < 1 || Integer.parseInt(v2) > 10))) {
            throw new Exception("Введите число от 1 до 10 или от I до X");
        }
        // проверяем введенные данные согласно формату и задаем валидность данных
        if (((v2IsRome && v1IsRome) && (Integer.parseInt(v1) - Integer.parseInt(v2)) < 0) && operator == "-") {
            throw new Exception("в римской системе нет отрицательных чисел");
        } else if ( !v1IsRome && !v2IsRome) {
            isValid = true;
        } else if (  v2IsRome && v1IsRome){
            isValid = true;
            isRome = true;
        } else {
            throw new Exception("используются одновременно разные системы счисления");
        }
    }

    // проверка что строка число
    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
