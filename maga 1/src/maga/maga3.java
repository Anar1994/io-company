package maga;

import java.io.Serializable;

public class maga3 implements Serializable {
    private UnitType uType;
    private String[] marking;
    private String[] value;
    private int index;

    /**
     * ����������� �������� ���.
     * @param uType   - ��� ��������
     * @param marking - ����� ���������� ��������
     * @param value - ����� ������� ��������
     * @param idx  - ������ �������� ��� ����������
     */
    public maga3(UnitType uType, String[] marking, String[] value, int idx) {
        this.uType = uType;
        this.marking = marking;
        this.value = value;
        this.index = idx;
    }
    /**
     * @return ��� �������� ���
     */
    public UnitType getUnitType() {
        return this.uType;
    }
    /**
     * @return ����� ��������� ��������
     */
    public String[] getUnitMarking() {
        return this.marking;
    }
    /**
     * @param unitMarking - ����� ����� ��������� ������ ��������
     */
    public void setUnitMarking(String[] unitMarking) {
        this.marking = unitMarking;
    }
    /**
     * @return ����� ������� ��������
     */
    public String[] getUnitValue() {
        return this.value;
    }
    /**
     * @param unitValue - ����� ����� ������� ������ ��������
     */
    public void setUnitValue(String[] unitValue) {
        this.value = unitValue;
    }
    /**
     * @return ������ �������� ��� ����������
     */
    public int getIndex() {
        return this.index;
    }
    /**
     * @param index  -  ����� ������ �������� ��� ����������
     */
    public void setIndex(int index) {
        this.index =index;
    }
}
