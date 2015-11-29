package maga;

import java.io.Serializable;

/**
 * ���� �������� ���.
 */
public enum maga4 implements Serializable {
    B, O, C, U, R, E;
}

package maga;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.*;

/**
 * ������ �����, ��� �������� ����������� ��������� ���������.
 */
enum TestType implements Serializable {
    IS_END, IS_RETURN_AFTER_END, IS_AFTER_END_UNITS_REACHABLE, DUPLICATE_RETURN_INDEX, CONDITION_INDEX_CORRESPONDS_RETURN_INDEX
}

public class MSA implements Serializable {
    private int[][] unitsUnion;
    private Map<Integer, Unit> unitSignals = new HashMap<Integer, Unit>();

    private int endIdx;

    /**
     * ����������� ���.
     * @param lsa
     */
    public MSA(LSA lsa) {
        createMSA(lsa);
    }

    /**
     * ������� ������� ������ ������ ���.
     * @return
     */
    public int[][] getUnitsUnion(){
        return unitsUnion;
    }

    /**
     * ������� ������� ����������� ������� ������� �� �����.
     * @return
     */
    public Map<Integer, Unit> getUnitSignals(){
        return unitSignals;
    }

    /**
     * ������� ��� �� ����� ���.
     * @param lsa
     */
    public void createMSA(LSA lsa) {
        unitsUnion = new int[getMatrixSize(lsa)][getMatrixSize(lsa)];
        for (int i = 0; i < unitsUnion.length; i++) {
            // ������� ����������: ����� ������� <-> ������
            unitSignals.put(i, getThisUnit(i, lsa));
            // ������� ��������
            maga4 currentUnitType = getThisUnit(i, lsa).getUnitType();
            switch (currentUnitType) {
                case O:
                    setOperationGoTo(i, lsa);
                    break;
                case C:
                    setConditionGoTo(i, lsa);
                    break;
            }
        }
    }

    /**
     * ���������� ������� ���������� ������� �� �������� ������� (� ���).
     * @param i
     */
    private void setOperationGoTo(int i, LSA lsa) {
        if (getNextUnit(getThisUnit(i, lsa), lsa) != (null)) {
            maga4 nextUnitType = getNextUnit(getThisUnit(i, lsa), lsa).getUnitType();
            switch (nextUnitType) {
                case O:
                case C:
                    setThisOne(i);
                    break;
                case U:
                    int rightPos = findNextUnitByIndex(getNextUnit(getThisUnit(i, lsa), lsa).getIndex(), lsa);
                    if (rightPos != -1) {
                        unitsUnion[i][rightPos] = 1;
                    }
                    break;
                case R:
                    int nextAbsPos = skipR(getAbsUnitPos(getThisUnit(i, lsa), lsa), lsa);
                    if (nextAbsPos != -1) {
                        unitsUnion[i][nextAbsPos] = 1;
                    }
                    break;
                case E:
            }
        }
    }
    /**
     * ���������� ������� ������ ������� �� �������� ������� (� ���).
     * @param i
     */
    private void setConditionGoTo(int i, LSA lsa) {
        setOperationGoTo(i, lsa);
        int secondPos = findNextUnitByIndex(getThisUnit(i, lsa).getIndex(), lsa);
        System.out.println("ni = " + secondPos);
        if (secondPos != -1) {
            unitsUnion[i][secondPos] = -1;
        }
    }

    /**
     * ������� ����� ������� ���.
     * @param lsa
     * @return
     */
    private int getMatrixSize(LSA lsa) {
        int count = 0;
        for (Unit unit : lsa.getLsaItems()) {
            if ((unit.getUnitType().equals(maga4.O)) || (unit.getUnitType().equals(maga4.C))) {
                count++;
            }
        }
        return count;
    }

    /**
     * ������� ������� � ��� �� ������� �������� ��������.
     * @param pos
     * @param lsa
     * @return
     */
    private Unit getThisUnit(int pos, LSA lsa) {
        int count = -1;
        for (Unit unit : lsa.getLsaItems()) {
            if ((unit.getUnitType().equals(maga4.O)) || (unit.getUnitType().equals(maga4.C))) {
                count++;
            }
            if (count == pos) {
                return unit;
            }
        }
        return null;
    }

    /**
     * ������� �������, ���� ������������� � ��� ������ �� ��������.
     * @param unit
     * @param lsa
     * @return
     */
    private Unit getNextUnit(Unit unit, LSA lsa) {
        ArrayList<Unit> list = lsa.getLsaItems();
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i).equals(unit)) {
                return list.get(i + 1);
            }
        }
        return null;
    }

    /**
     * ���� ������� ������� ���������� �������� �� �����, ���������� �������� ��������� �� ����������� ��������.
     * @param index
     * @return ������� ������� �������� ��������, ��� -1 � ��� ��������� ������
     */
    private int findNextUnitByIndex(int index, LSA lsa) {
        for (int i = 0; i < lsa.getLsaItems().size(); i++) {
            if ((lsa.getLsaItems().get(i).getUnitType().equals(maga4.R)) && (lsa.getLsaItems().get(i).getIndex() == index)) {
                return skipR(i, lsa);
            }
        }
        return -1;
    }
    /**
     * ���������� ������� �� ��������� ������� �������.
     * @param pos
     */
    private void setThisOne(int pos) {
        for (int i = 0; i < unitsUnion.length; i++) {
            unitsUnion[pos][i] = 0;
        }
        unitsUnion[pos][pos + 1] = 1;
    }
    /**
     * ������� ������� ������� ��������.
     * @param needUnit
     * @return
     */
    private int getIndexOfUnit(Unit needUnit, LSA lsa) {
        int count = 0;
        for (Unit unit : lsa.getLsaItems()) {
            if ((unit.getUnitType().equals(maga4.O)) || (unit.getUnitType().equals(maga4.C))) {
                if (unit.equals(needUnit)) {
                    break;
                }
                count++;
            }
        }
        return count;
    }
    /**
     * ������� ��������� ������� �������� � �����.
     * @param unit
     * @return
     */
    private int getAbsUnitPos(Unit unit, LSA lsa) {
        for (int i = 0; i < lsa.getLsaItems().size(); i++) {
            if (lsa.getLsaItems().get(i).equals(unit)) {
                return i;
            }
        }
        return -1;
    }
    /**
     * ������� ������� ������� �������� ���� ������ ��������, ����������
     * �� �������� ��������� �� ���������� ��������.
     * @param thisAbsPos
     * @return
     */
    private int skipR(int thisAbsPos, LSA lsa) {
        int needPos = -1;
        for (int i = thisAbsPos + 1; i < lsa.getLsaItems().size(); i++) {
            if (lsa.getLsaItems().get(i).getUnitType().equals(maga4.U)) {
                needPos = findNextUnitByIndex(lsa.getLsaItems().get(i).getIndex(), lsa);
                break;
            }
            if ((lsa.getLsaItems().get(i).getUnitType().equals(maga4.O)) || lsa.getLsaItems().get(i).getUnitType().equals(maga4.C)) {
                needPos = getIndexOfUnit(lsa.getLsaItems().get(i), lsa);
                break;
            }
            if (lsa.getLsaItems().get(i).getUnitType().equals(maga4.R)) {
                needPos = skipR(i, lsa);
                break;
            }
        }
        return needPos;
    }
    /**
     * ��������� � ������� ������� ���
     */
    public void printMSA() {
        System.out.println("----------MSA------------");
        for (int i = 0; i < unitsUnion.length; i++) {
            for (int j = 0; j < unitsUnion.length; j++) {
                out.print(unitsUnion[i][j] + " ");
            }
            out.println();
        }
    }
    /**
     * ��������� � ������� �������� ������ ���
     */
    public void printSignals() {
        for (int i = 0; i < unitSignals.size(); i++) {

            System.out.println(unitSignals.get(i).getUnitType() + " :");
            for (int j = 0; j < unitSignals.get(j).getUnitMarking().length; j++) {
                System.out.print("   " + unitSignals.get(j).getUnitMarking()[j] + " -> " + unitSignals.get(j).getUnitValue()[j]);
            }
            System.out.println(" idx = " + unitSignals.get(i).getIndex());
        }
    }
}
