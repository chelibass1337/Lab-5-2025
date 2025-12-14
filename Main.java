import functions.*;
import functions.basic.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException, FunctionPointIndexOutOfBoundsException, InappropriateFunctionPointException, ClassNotFoundException {
        
        // ==================== ТЕСТИРОВАНИЕ ОСНОВНЫХ ФУНКЦИЙ ====================
        
        Function Func1 = new Cos();
        Function Func2 = new Sin();
        double pi = Math.PI;
        
        System.out.println("\n========== ЗНАЧЕНИЯ SIN И COS ==========\n");
        for (double i = 0; i <= pi; i += 0.1){
            System.out.printf("sin(%.2f) = %.6f \t cos(%.2f) = %.6f\n", i, Func2.getFunctionValue(i), i, Func1.getFunctionValue(i)); 
        }

        // Создаем табулированные функции
        TabulatedFunction TabulatedCos = TabulatedFunctions.tabulate(Func1, 0, pi, 10);
        TabulatedFunction TabulatedSin = TabulatedFunctions.tabulate(Func2, 0, pi, 10);
        
        System.out.println("\n========== ЗНАЧЕНИЯ ТАБУЛИРОВАННЫХ SIN И COS ==========\n");
        for (double i = 0; i <= pi; i += 0.1){
            System.out.printf("sin(%.2f) = %.6f \t cos(%.2f) = %.6f\n", i, TabulatedSin.getFunctionValue(i), i, TabulatedCos.getFunctionValue(i)); 
        }

        System.out.println("\n========== РАЗНОСТЬ МЕЖДУ ТАБУЛИРОВАННЫМИ И ИСХОДНЫМИ ЗНАЧЕНИЯМИ ==========\n");
        for (double i = 0; i <= pi; i += 0.1){
            double absSin = Math.abs(TabulatedSin.getFunctionValue(i) - Func2.getFunctionValue(i));
            double absCos = Math.abs(TabulatedCos.getFunctionValue(i) - Func1.getFunctionValue(i));
            System.out.printf("Δsin(%.2f) = %.6f \t Δcos(%.2f) = %.6f\n", i, absSin, i, absCos);
        }
    
        // Проверка тождества sin² + cos² = 1
        Function SumOfSquaresSinAndCos = Functions.sum(Functions.power(TabulatedSin, 2), Functions.power(TabulatedCos, 2));
        System.out.println("\n========== СУММА КВАДРАТОВ SIN И COS (sin² + cos² ≈ 1) ==========\n");
        for (double i = 0; i <= pi; i += 0.1) {
            System.out.printf("sin²(%.2f) + cos²(%.2f) = %.6f\n", i, i, SumOfSquaresSinAndCos.getFunctionValue(i));
        }

        // ==================== ТЕСТИРОВАНИЕ МЕТОДОВ ОБЪЕКТОВ ====================
        
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

        // ==================== ТЕСТИРОВАНИЕ СЕРИАЛИЗАЦИИ И ФАЙЛОВ ====================
        
        System.out.println("\n========== ТЕСТИРОВАНИЕ СЕРИАЛИЗАЦИИ И РАБОТЫ С ФАЙЛАМИ ==========\n");

        // Тестирование записи/чтения экспоненты
        TabulatedFunction Exp = TabulatedFunctions.tabulate(new Exp(), 0, 10, 11);
        File f = new File("exp.txt");
        FileWriter fw = new FileWriter(f);
        TabulatedFunctions.writeTabulatedFunction(Exp, fw);
        fw.close();
        FileReader fr = new FileReader(f);
        TabulatedFunction TabExp = TabulatedFunctions.readTabulatedFunction(fr);

        System.out.println("ЭКСПОНЕНТА (Exp):");
        for (int i = 0; i < 11; i++){
            System.out.printf("Exp(%d) = %.6f \t Из файла: %.6f\n", i, Exp.getFunctionValue(i), TabExp.getFunctionValue(i));
        }

        // Тестирование записи/чтения натурального логарифма
        TabulatedFunction ln = TabulatedFunctions.tabulate(new Log(Math.E), 1, 10, 11);
        File f2 = new File("ln.txt");
        FileOutputStream fw2 = new FileOutputStream(f2);
        TabulatedFunctions.outputTabulatedFunction(ln, fw2);
        fw2.close();
        FileInputStream fr2 = new FileInputStream(f2);
        TabulatedFunction TabLn = TabulatedFunctions.inputTabulatedFunction(fr2);

        System.out.println("\nНАТУРАЛЬНЫЙ ЛОГАРИФМ (ln):");
        for (int i = 1; i < 11; i++){
            System.out.printf("ln(%d) = %.6f \t Из файла: %.6f\n", i, ln.getFunctionValue(i), TabLn.getFunctionValue(i));
        }

        // Тестирование Externalizable
        TabulatedFunction Composition = TabulatedFunctions.tabulate(Functions.composition(new Exp(), new Log(Math.E)), 0, 10, 11);
        
        FileOutputStream fos = new FileOutputStream("Externalizable.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(Composition);
        oos.close();

        FileInputStream fis = new FileInputStream("Externalizable.txt");
        ObjectInputStream ois = new ObjectInputStream(fis);
        TabulatedFunction loadedComposition = (TabulatedFunction)ois.readObject();
        ois.close();

        System.out.println("\nКОМПОЗИЦИЯ exp(ln(x)) (должна быть равна x):");
        for (int i = 0; i < 11; i++) {
            System.out.printf("Оригинал: %.6f \t Из файла: %.6f\n", Composition.getFunctionValue(i), loadedComposition.getFunctionValue(i));
        }
        
        // Проверка методов для сериализованных объектов
        System.out.println("\nПРОВЕРКА МЕТОДОВ ДЛЯ СЕРИАЛИЗОВАННЫХ ОБЪЕКТОВ:");
        System.out.println("Composition.toString(): " + Composition);
        System.out.println("loadedComposition.toString(): " + loadedComposition);
        System.out.println("Composition.equals(loadedComposition): " + Composition.equals(loadedComposition));
        System.out.println("Composition.hashCode() == loadedComposition.hashCode(): " + (Composition.hashCode() == loadedComposition.hashCode()));
        
        System.out.println("\n========== ТЕСТИРОВАНИЕ ЗАВЕРШЕНО ==========\n");
    }
}