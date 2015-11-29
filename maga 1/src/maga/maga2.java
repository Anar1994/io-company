package maga;

import lab1.gui.MainFrame;

import java.util.ArrayList;

public class maga2 {
    private ArrayList<Unit> lsaItems = new ArrayList<Unit>();

    /**
     * Конструктор ЛСА без параметрів(створюється нова ЛСА).
     */
    public maga2() {
    }

    /**
     * Створення ЛСА з раніше створеним набором елементів.
     *
     * @param lsaItems
     */
    public maga2(ArrayList<Unit> lsaItems) {
        this.lsaItems = lsaItems;
    }

    /**
     * Повертає нововстворений обєкт МСА на основі даної ЛСА.
     *
     * @return
     */
    public MSA getMsa() {
        return (new MSA(this));
    }

    /**
     * Додає новий елемент в дану ЛСА.
     *
     * @param unit
     */
    public void addItem(Unit unit) {
        lsaItems.add(unit);
    }

    //------------------Operation -------------------------

    /**
     * Повертає  ArrayList оперторних вершин даного ЛСА.
     *
     * @return
     */
    private ArrayList<Unit> getOperationUnits() {
        ArrayList<Unit> operationUnits = new ArrayList<Unit>();
        for (Unit unit : lsaItems) {
            if (unit.getUnitType().equals(UnitType.O)) {
                operationUnits.add(unit);
            }
        }
        return operationUnits;
    }

    /**
     * Повертає масив опертаторних вершин даного ЛСА.
     *
     * @return
     */
    public String[][] getOperationUnitsArray() {
        ArrayList<Unit> operationUnits = getOperationUnits();
        int size = operationUnits.size();
        ArrayList<String> operMarks = new ArrayList<String>();
        ArrayList<String> operSignals = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            int count = 0;
            for (String s : operationUnits.get(i).getUnitMarking()) {
                if (checkOperationSignal(operMarks, s)) {
                    operMarks.add(s);
                    operSignals.add(operationUnits.get(i).getUnitValue()[count]);
                }
                count++;
            }
        }
        String[][] operUnits = new String[2][operMarks.size()];
        for (int i = 0; i < operMarks.size(); i++) {
            operUnits[0][i] = operMarks.get(i);
            operUnits[1][i] = operSignals.get(i);
        }
        return operUnits;
    }

    /**
     * Змінює поля операторної вершини ЛСА з позначенням marking.
     *
     * @param marking
     * @param newValue
     * @param changeMarking
     */
    public void changeOperationUnit(String marking, String newValue, boolean changeMarking) {
        ArrayList<Unit> operationUnits = getOperationUnits();
        for (Unit unit : operationUnits) {
            for (int i = 0; i < unit.getUnitMarking().length; i++) {
                if (unit.getUnitMarking()[i].equals(marking)) {
                    if (changeMarking) {
                        unit.getUnitMarking()[i] = newValue;
                    } else {
                        unit.getUnitValue()[i] = newValue;
                    }
                }
            }
        }
    }

    //----------------------------Conditional ----------------------------------

    /**
     * Повертає  ArrayList умовних вершин ЛСА.
     *
     * @return
     */
    private ArrayList<Unit> getConditionUnits() {
        ArrayList<Unit> conditionUnits = new ArrayList<Unit>();
        for (Unit unit : lsaItems) {
            if (unit.getUnitType().equals(UnitType.C)) {
                conditionUnits.add(unit);
            }
        }
        return conditionUnits;
    }

    /**
     * Повертає масив умовних вершин ЛСА,
     *
     * @return
     */
    public String[][] getConditionUnitsArray() {
        ArrayList<Unit> conditionUnits = getConditionUnits();
        int size = conditionUnits.size();
        ArrayList<String> condMarks = new ArrayList<String>();
        ArrayList<String> condSignals = new ArrayList<String>();
        for (int i = 0; i < size; i++) {
            int count = 0;
            for (String s : conditionUnits.get(i).getUnitMarking()) {
                if (checkOperationSignal(condMarks, s)) {
                    condMarks.add(s);
                    condSignals.add(conditionUnits.get(i).getUnitValue()[count]);
                }
                count++;
            }
        }
        String[][] condUnits = new String[2][condMarks.size()];
        for (int i = 0; i < condMarks.size(); i++) {
            condUnits[0][i] = condMarks.get(i);
            condUnits[1][i] = condSignals.get(i);
        }
        return condUnits;
    }

    /**
     * Змінює поля умовної вершини ЛСА з позначенням marking.
     *
     * @param marking
     * @param newValue
     * @param changeMarking
     */
    public void changeConditionUnit(String marking, String newValue, boolean changeMarking) {
        ArrayList<Unit> conditionUnits = getConditionUnits();
        for (Unit unit : conditionUnits) {
            if (unit.getUnitMarking()[0].equals(marking)) {
                if (changeMarking) {
                    unit.getUnitMarking()[0] = newValue;
                } else {
                    unit.getUnitValue()[0] = newValue;
                }
            }
        }
    }

    /**
     * Перевірка, чи є елемент з позначенням mark в масиві arrayOfMarks.
     *
     * @param arrayOfMarks
     * @param mark
     * @return
     */
    private boolean checkOperationSignal(ArrayList<String> arrayOfMarks, String mark) {
        boolean flag = true;
        for (String s : arrayOfMarks) {
            if (mark.equals(s)) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * Повертає масив елементів ЛСА.
     *
     * @return
     */
    public ArrayList<Unit> getLsaItems() {
        return lsaItems;
    }

    /**
     * Виводить значення елементів ЛСА в стандартний потік виводу.
     */
    public void printLSA() {
        for (Unit unit : lsaItems) {
            System.out.println(unit.getUnitType() + " :");
            for (int i = 0; i < unit.getUnitMarking().length; i++) {
                System.out.print("   " + unit.getUnitMarking()[i] + " -> " + unit.getUnitValue()[i]);
            }
            System.out.println(" idx = " + unit.getIndex());
        }
    }
}
