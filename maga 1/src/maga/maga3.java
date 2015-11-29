package maga;

import java.io.Serializable;

public class maga3 implements Serializable {
    private UnitType uType;
    private String[] marking;
    private String[] value;
    private int index;

    /**
     * Конструктор елементу ЛСА.
     * @param uType   - тип елементу
     * @param marking - масив позначеннь елементу
     * @param value - масив сигналів елементу
     * @param idx  - індекс переходу або повернення
     */
    public maga3(UnitType uType, String[] marking, String[] value, int idx) {
        this.uType = uType;
        this.marking = marking;
        this.value = value;
        this.index = idx;
    }
    /**
     * @return тип елементу ЛСА
     */
    public UnitType getUnitType() {
        return this.uType;
    }
    /**
     * @return масив позначень елементу
     */
    public String[] getUnitMarking() {
        return this.marking;
    }
    /**
     * @param unitMarking - масив нових позначень даного елементу
     */
    public void setUnitMarking(String[] unitMarking) {
        this.marking = unitMarking;
    }
    /**
     * @return масив сигналів елементу
     */
    public String[] getUnitValue() {
        return this.value;
    }
    /**
     * @param unitValue - масив нових значень даного елементу
     */
    public void setUnitValue(String[] unitValue) {
        this.value = unitValue;
    }
    /**
     * @return індекс переходу або повернення
     */
    public int getIndex() {
        return this.index;
    }
    /**
     * @param index  -  новий індекс переходу або повернення
     */
    public void setIndex(int index) {
        this.index =index;
    }
}
