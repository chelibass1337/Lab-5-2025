import functions.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException, FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException, ClassNotFoundException {
        
        System.out.println("\n========== ТЕСТИРОВАНИЕ МЕТОДОВ toString(), equals(), hashCode(), clone() ==========\n");
        
        // Тестовые данные
        FunctionPoint[] points1 = {
            new FunctionPoint(0.0, 1.4),
            new FunctionPoint(1.0, 6.0),
            new FunctionPoint(2.0, 4.4)
        };

        FunctionPoint[] points2 = {
            new FunctionPoint(0.0, 1.4),
            new FunctionPoint(1.0, 6.0),
            new FunctionPoint(2.0, 4.4)
        };

        FunctionPoint[] points3 = {
            new FunctionPoint(0.0, 1.0),
            new FunctionPoint(1.0, 3.5),
            new FunctionPoint(2.0, 5.0)
        };

        // Создание объектов ArrayTabulatedFunction
        ArrayTabulatedFunction array1 = new ArrayTabulatedFunction(points1);
        ArrayTabulatedFunction array2 = new ArrayTabulatedFunction(points2);
        ArrayTabulatedFunction array3 = new ArrayTabulatedFunction(points3);

        // Создание объектов LinkedListTabulatedFunction
        LinkedListTabulatedFunction linked1 = new LinkedListTabulatedFunction(points1);
        LinkedListTabulatedFunction linked2 = new LinkedListTabulatedFunction(points2);
        LinkedListTabulatedFunction linked3 = new LinkedListTabulatedFunction(points3);

        // 1. Тестирование toString()
        System.out.println("1. МЕТОД toString():");
        System.out.println("   Array функции:");
        System.out.println("   - array1: " + array1);
        System.out.println("   - array2: " + array2);
        System.out.println("   - array3: " + array3);
        
        System.out.println("\n   LinkedList функции:");
        System.out.println("   - linked1: " + linked1);
        System.out.println("   - linked2: " + linked2);
        System.out.println("   - linked3: " + linked3);

        // 2. Тестирование equals()
        System.out.println("\n2. МЕТОД equals():");
        
        System.out.println("   Сравнение Array функций:");
        System.out.println("   - array1.equals(array2): " + array1.equals(array2) + " (должно быть true)");
        System.out.println("   - array1.equals(array3): " + array1.equals(array3) + " (должно быть false)");
        System.out.println("   - array1.equals(null):   " + array1.equals(null) + " (должно быть false)");
        
        System.out.println("\n   Сравнение LinkedList функций:");
        System.out.println("   - linked1.equals(linked2): " + linked1.equals(linked2) + " (должно быть true)");
        System.out.println("   - linked1.equals(linked3): " + linked1.equals(linked3) + " (должно быть false)");
        System.out.println("   - linked1.equals(null):    " + linked1.equals(null) + " (должно быть false)");
        
        System.out.println("\n   Сравнение Array и LinkedList:");
        System.out.println("   - array1.equals(linked2):  " + array1.equals(linked2) + " (должно быть true)");
        System.out.println("   - array1.equals(linked3):  " + array1.equals(linked3) + " (должно быть false)");

        // 3. Тестирование hashCode()
        System.out.println("\n3. МЕТОД hashCode():");
        
        System.out.println("   Array функции:");
        System.out.println("   - array1.hashCode(): " + array1.hashCode());
        System.out.println("   - array2.hashCode(): " + array2.hashCode());
        System.out.println("   - array3.hashCode(): " + array3.hashCode());
        System.out.println("   - array1.hashCode() == array2.hashCode(): " + (array1.hashCode() == array2.hashCode()) + " (должно быть true)");
        
        System.out.println("\n   LinkedList функции:");
        System.out.println("   - linked1.hashCode(): " + linked1.hashCode());
        System.out.println("   - linked2.hashCode(): " + linked2.hashCode());
        System.out.println("   - linked3.hashCode(): " + linked3.hashCode());
        System.out.println("   - linked1.hashCode() == linked2.hashCode(): " + (linked1.hashCode() == linked2.hashCode()) + " (должно быть true)");
        
        System.out.println("\n   Согласованность Array и LinkedList:");
        System.out.println("   - array1.hashCode() == linked2.hashCode(): " + (array1.hashCode() == linked2.hashCode()) + " (должно быть true)");

        // 4. Тестирование изменения и hashCode()
        System.out.println("\n4. ИЗМЕНЕНИЕ ОБЪЕКТА И ПРОВЕРКА hashCode():");
        
        int originalArrayHash = array1.hashCode();
        int originalLinkedHash = linked1.hashCode();
        
        System.out.println("   До изменения:");
        System.out.println("   - array1.hashCode():  " + originalArrayHash);
        System.out.println("   - linked1.hashCode(): " + originalLinkedHash);
        
        // Изменяем объекты
        array1.setPointY(2, 30.0);
        linked1.setPointY(1, 40.0);
        
        System.out.println("\n   После изменения:");
        System.out.println("   - array1.hashCode():  " + array1.hashCode());
        System.out.println("   - linked1.hashCode(): " + linked1.hashCode());
        System.out.println("   - Хэш изменился для array1:  " + (originalArrayHash != array1.hashCode()));
        System.out.println("   - Хэш изменился для linked1: " + (originalLinkedHash != linked1.hashCode()));

        // Восстанавливаем исходные значения
        array1.setPointY(2, 4.4);
        linked1.setPointY(1, 6.0);

        // 5. Тестирование clone()
        System.out.println("\n5. МЕТОД clone() (глубокое копирование):");
        
        System.out.println("   До клонирования:");
        System.out.println("   - array1:  " + array1);
        System.out.println("   - linked1: " + linked1);
        
        // Клонируем объекты
        ArrayTabulatedFunction arrayClone = (ArrayTabulatedFunction) array1.clone();
        LinkedListTabulatedFunction linkedClone = (LinkedListTabulatedFunction) linked1.clone();
        
        System.out.println("\n   После клонирования:");
        System.out.println("   - array1:  " + array1);
        System.out.println("   - arrayClone: " + arrayClone);
        System.out.println("   - linked1: " + linked1);
        System.out.println("   - linkedClone: " + linkedClone);
        
        System.out.println("\n   Проверка равенства до изменений:");
        System.out.println("   - array1.equals(arrayClone):  " + array1.equals(arrayClone) + " (должно быть true)");
        System.out.println("   - linked1.equals(linkedClone): " + linked1.equals(linkedClone) + " (должно быть true)");
        
        // Изменяем оригиналы
        array1.setPointY(2, 100.0);
        linked1.setPointY(1, 200.0);
        
        System.out.println("\n   После изменения оригиналов:");
        System.out.println("   - array1 (изменен):  " + array1);
        System.out.println("   - arrayClone: " + arrayClone + " (не должен измениться)");
        System.out.println("   - linked1 (изменен): " + linked1);
        System.out.println("   - linkedClone: " + linkedClone + " (не должен измениться)");
        
        System.out.println("\n   Проверка равенства после изменений:");
        System.out.println("   - array1.equals(arrayClone):  " + array1.equals(arrayClone) + " (должно быть false)");
        System.out.println("   - linked1.equals(linkedClone): " + linked1.equals(linkedClone) + " (должно быть false)");

        // 6. Тестирование FunctionPoint
        System.out.println("\n6. ТЕСТИРОВАНИЕ FunctionPoint:");
        
        FunctionPoint point1 = new FunctionPoint(10, 30);
        FunctionPoint point2 = new FunctionPoint(12, 31);
        FunctionPoint pointClone = (FunctionPoint) point1.clone();
        
        System.out.println("   - point1: " + point1);
        System.out.println("   - point2: " + point2);
        System.out.println("   - pointClone: " + pointClone);
        
        System.out.println("\n   Методы FunctionPoint:");
        System.out.println("   - point1.equals(pointClone): " + point1.equals(pointClone) + " (должно быть true)");
        System.out.println("   - point1.equals(point2):     " + point1.equals(point2) + " (должно быть false)");
        System.out.println("   - point1.hashCode() == pointClone.hashCode(): " + (point1.hashCode() == pointClone.hashCode()) + " (должно быть true)");
        System.out.println("   - point1.hashCode() == point2.hashCode():     " + (point1.hashCode() == point2.hashCode()) + " (должно быть false)");
    }
}