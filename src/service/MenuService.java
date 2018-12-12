package service;

import domain.Plate;
import repository.MenuRepository;

import java.util.List;

public class MenuService {

    private MenuRepository menuRepository = new MenuRepository();

    private void validatePlateId(Plate plate) throws Exception {
        if (false) {
            throw new Exception("The plate cannot be added due wrong passed id");
        }
    }

    public void addPlate(Plate plate) throws Exception {
        validatePlateId(plate);
        menuRepository.addPlate(plate);
    }

    public void modifyPlate(Plate plate) throws Exception {
        validatePlateId(plate);
        Plate plateDB = findPlateById(plate.getPlateId());
        if (plateDB != null) {
            menuRepository.modifyPlate(plate);
        }
    }

    public void removePlate(Plate plate) throws Exception {
        Plate plateDB = findPlateById(plate.getPlateId());
        if (plateDB != null) {
            menuRepository.removePlate(plate);
        }
    }

    public Plate findPlateById(int id) {
        return menuRepository.findPlateById(id);
    }

    public List<Plate> findAll() {
        return menuRepository.findAll();
    }


}