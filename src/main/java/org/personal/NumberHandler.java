package org.personal;

abstract class NumberHandler {
    protected NumberHandler next;

    public void setNext(NumberHandler next) {
        this.next = next;
    }

    public abstract String handle(int number);
}

class UnitsHandler extends NumberHandler {

    private static final String[] units = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};

    @Override
    public String handle(int number) {
        if (number < 10) {
            return units[number];
        } else if (next != null) {
            return next.handle(number);
        }
        return "";
    }
}

class TensHandler extends NumberHandler {

    private static final String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    @Override
    public String handle(int number) {
        if (number < 10) {
            return next.handle(number);
        } else if (number < 20) {
            return teens[number - 10];
        } else if (number < 100) {
            return tens[number / 10] + ((number % 10 != 0) ? " " + next.handle(number % 10) : "");
        } else if (next != null) {
            return next.handle(number);
        }
        return "";
    }
}

class HundredsHandler extends NumberHandler {

    @Override
    public String handle(int number) {
        if (number < 100) {
            return next.handle(number);
        } else if (number < 1000) {
            return next.handle(number / 100) + " Hundred" + ((number % 100 != 0) ? " " + next.handle(number % 100) : "");
        } else if (next != null) {
            return next.handle(number);
        }
        return "";
    }
}

class ThousandsHandler extends NumberHandler {

    @Override
    public String handle(int number) {
        if (number < 1000) {
            return next.handle(number);
        } else if (number < 1000000) {
            return next.handle(number / 1000) + " Thousand" + ((number % 1000 != 0) ? " " + next.handle(number % 1000) : "");
        } else if (next != null) {
            return next.handle(number);
        }
        return "";
    }
}

class NumberToWordsConverter {

    private NumberHandler handler;

    public NumberToWordsConverter() {
        // Create the chain
        UnitsHandler unitsHandler = new UnitsHandler();
        TensHandler tensHandler = new TensHandler();
        HundredsHandler hundredsHandler = new HundredsHandler();
        ThousandsHandler thousandsHandler = new ThousandsHandler();

        // Set up chain
        thousandsHandler.setNext(hundredsHandler);
        hundredsHandler.setNext(tensHandler);
        tensHandler.setNext(unitsHandler);

        this.handler = thousandsHandler;
    }

    public String convert(int number) {
        return handler.handle(number).trim();
    }

    public static void main(String[] args) {
        NumberToWordsConverter converter = new NumberToWordsConverter();
        int number = 123456;
        System.out.println("In words: " + converter.convert(number));
    }
}
