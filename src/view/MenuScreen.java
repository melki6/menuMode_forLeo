package view;

import domain.Plate;
import service.MenuService;

import java.util.List;
import java.util.Scanner;

public class MenuScreen {

    static MenuService menuService = new MenuService();

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args){
        int plateId;
        String name;
        String type;
        String drink;
        int idListIngridients;

        System.out.print("plate id: ");
        plateId = scanner.nextInt();

        System.out.println();

        System.out.print("Name: ");
        name = scanner.next();

        System.out.println();

        System.out.print("Type: ");
        type = scanner.next();

        System.out.println();

        System.out.print("Drink: ");
        drink = scanner.next();

        System.out.println();

        System.out.print("id of the ingridients list: ");
        idListIngridients = scanner.nextInt();

        System.out.println();

        System.out.println();

        Plate plate = new Plate(plateId, name, type,  drink, idListIngridients);

        try {
            menuService.addPlate(plate);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        List<Plate> menu = menuService.findAll();

        System.out.println("Listing menu");

        for (Plate tempPlate:menu) {

            System.out.println("Plate Id: "+ tempPlate.getPlateId());
            System.out.println(plate.getName());
            System.out.println(plate.getType());
            System.out.println(plate.getDrink());
            System.out.println(plate.getIdListIngridients());
        }

        Plate plate2 = new Plate(plate.getPlateId(),
                plate.getName(), plate.getType(), "tea", plate.getIdListIngridients());

        try {
            menuService.modifyPlate(plate2);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("Listing menu");

        for (Plate tempPlate:menu) {

            System.out.println("Plate Id: "+ tempPlate.getPlateId());
            System.out.println(plate.getName());
            System.out.println(plate.getType());
            System.out.println(plate.getDrink());
            System.out.println(plate.getIdListIngridients());
        }

        try {
            menuService.removePlate(plate);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("Listing menu");

        for (Plate tempPlate:menu) {

            System.out.println("Plate Id: "+ tempPlate.getPlateId());
            System.out.println(plate.getName());
            System.out.println(plate.getType());
            System.out.println(plate.getDrink());
            System.out.println(plate.getIdListIngridients());
        }

    }
}