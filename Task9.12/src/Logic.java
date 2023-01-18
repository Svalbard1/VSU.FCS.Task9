import java.util.ArrayList;

public class Logic {

    public  ArrayList<Integer> process(ArrayList<Integer> list){
        boolean flagForList = true;//флаг, который отвечает за проверку наличия перестановок
        int len = list.size();
        while (flagForList){
            flagForList = false;
            for (int i = 0; i < len; i++){
                int number = list.get(i);
                if (number > 0){
                    //пробегать два раза, один возращает наличие перстановки, второй производит саму перестановку
                    //попробовать делать всё в одном
                    int indexNumber = i;
                    flagForList = permutationNumberCheck(number, indexNumber, list);            //проверили на наличие перестановки
                    permutationNumber(number, indexNumber, list);                               //Переставили
                }
            }
        }
        return list;
    }


    private  boolean permutationNumberCheck(int number, int indexNumber, ArrayList<Integer> list) {
        boolean flagForNumber = false;//Флаг для обозначения, что перестановка произошла
        int len = list.size();
        for (int i = 0; i < len; i++){
            int numberForPermutation = list.get(i);//Число, с которым мы будем "меняться местами"
            if (numberForPermutation > number){//Если число больше, то
                flagForNumber = true;//Обозначили, что перестановка произошла
                return flagForNumber;
            }
        }
        return flagForNumber;
    }

    //Метод, который осуществляет перестановку одного числа по возрастанию
    private  void permutationNumber(int number, int indexNumber, ArrayList<Integer> list) {
        int len = list.size();
        for (int i = 0; i < len; i++){
            int numberForPermutation = list.get(i);//Число, с которым мы будем "меняться местами"
            if (numberForPermutation > number){//Если число больше, то переставляем (т.е. сортируем)

                int indexForPermutation = i;
                list.remove(indexNumber);//Удалили число, которое передали в метод
                //(его мы, возможно переставим несколько раз)
                list.add(indexNumber, numberForPermutation);//Добавили на его место число, с которым мы "поменялись"
                indexNumber = i;//Запомнили новый индекс

                list.remove(indexForPermutation);
                list.add(indexForPermutation, number);
                return;
            }
        }
    }
}