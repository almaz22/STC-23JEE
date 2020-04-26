package part1.lesson03.task03;

import java.util.Arrays;

/**
 * ObjectBox - класс, который хранит коллекцию Object
 * У класса есть метод addObject, добавляющий объект в коллекцию.
 * У класса есть метод deleteObject, проверяющий наличие объекта в коллекции и при наличии удаляющий его.
 * У класса есть метод dump, выводящий содержимое коллекции в строку.
 *
 * @author Almaz_Kamalov
 */
public class ObjectBox03 {

    private Object[] objects;

    public ObjectBox03() {
        this.objects = new Object[0];
    }

    /**
     * Метод, возвращаемый размер массива объектов
     * @return - размер массива
     */
    public int size() {
        return this.objects.length;
    }

    /**
     * Проверка пустоты массива
     * @return - возвращаем true - если массив пустой, false - в противном случае
     */
    public boolean isEmpty() {
        return this.size() != 0;
    }

    /**
     * Метод проверки элемента в массиве.
     * @param o - элемент поиска
     * @return - возвращаем true - если он есть в массиве, false - если его нет
     */
    public boolean contains(Object o) {
        if (this.isEmpty()) {
            for (Object object : this.objects) {
                if (object == o) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Метод копируем имеющий массив в новый массив и добавляет в конец новый элемент.
     * Старый массив присвает значение нового массива
     * @param obj - элемент вставки
     */
    public void addObject (Object obj) {
//        if (obj.getClass() != this.objects.getClass())
//            throw new RuntimeException("Объекты разные");
        int size = this.size();
        Object[] objects = new Object[size + 1];
        if (this.size() >= 0) System.arraycopy(this.objects, 0, objects, 0, this.size());
        objects[size] = obj;
        this.objects = objects;
    }

    /**
     * Метод проверяет наличие удаляемого элемента в массиве.
     * Если он есть, то создаем новый массив с размером ниже на единицу и без удаляемого элемента.
     * Проводим рекурсию метода для повторной проверки и удаления элемента из массива.
     * Старому массиву присваеваем новый массив без удаляемых элементов
     * @param o - удаляемый элемент
     */
    public void deleteObject(Object o) {
        if (this.isEmpty()) {
            if (this.contains(o)) {
                Object[] objects = new Object[this.size() - 1];
                int j = 0;
                boolean flag = true;
                for (int i = 0; i < this.size(); i++) {
                    if (flag) {
                        if (this.objects[i] != o) {
                            objects[j] = this.objects[i];
                            j++;
                        } else {
                            flag = false;
                        }
                    } else {
                        objects[j] = this.objects[i];
                        j++;
                    }
                }
                this.objects = objects;
                deleteObject(o);
            }
        }
    }

    /**
     * Метод вывода на экран массива объектов
     * @return - массив
     */
    public String dump () {
        return "ObjectBox{" +
                "objects=" + Arrays.toString(objects) +
                '}';
    }


}
