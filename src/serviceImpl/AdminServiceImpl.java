package serviceImpl;

import model.entity.User;
import exception.MyException;
import model.enums.Position;
import service.AdminService;
import service.CommonService;
import util.FileWriter;

import java.util.ArrayList;
import java.util.List;

public class AdminServiceImpl implements CommonService<User>, AdminService {

    static ArrayList<User> adminArrayList = new ArrayList<>();

    static {
        adminArrayList.add(new User(1, "Bənövşə Əliyeva", "benovse@example.com", Position.ADMIN, "parol1"));
        adminArrayList.add(new User(2, "Ağa Məmmədov", "aga@example.com", Position.ADMIN, "parol2"));
        adminArrayList.add(new User(3, "Leyla Quliyeva", "leyla@example.com", Position.ADMIN, "parol3"));
    }


    @Override
    public List<User> getAllEmail(String email) {
        return adminArrayList.parallelStream().filter(a -> a.getEmail().equals(email)).toList();
    }

    @Override
    public void UpdateFullName(int id, String fullname) {

        try {
            for (User u : adminArrayList) {
                if (u.getId() == id) {
                    u.setFullName(fullname);
                }
            }
        } catch (Exception ex) {
            throw new MyException("Don't exist this admin");
        }
        FileWriter.writeFile("UpdateAdmin.txt",fullname);
    }

    @Override
    public void UpdateEmail(int id, String email) {
        try {
            for (User u : adminArrayList) {
                if (u.getId() == id) {
                    u.setEmail(email);
                }
            }
        } catch (Exception ex) {
            throw new MyException("Don't exist this admin");
        }
        FileWriter.writeFile("UpdateEmail.txt",email);
    }

    @Override
    public void add(User object) {
        adminArrayList.add(object);
        String note=object.getFullName()+" "+object.getPosition();
        FileWriter.writeFile("AddAdmin.txt",note);
    }

    @Override
    public List<User> getAll() {
        return adminArrayList;
    }

    @Override
    public User getById(Integer id) {
        try {
            return adminArrayList.stream().filter(a -> a.getId() == id).toList().get(0);
        } catch (Exception ex) {
            throw new MyException("Don't exist this admin");
        }

    }

    @Override
    public void updateById(Integer id, String name) {
        try {
            for (User u : adminArrayList) {
                if (u.getId() == id) {
                    u.setFullName(name);
                }
            }
        } catch (Exception ex) {
            throw new MyException("Don't exist this admin");
        }
        FileWriter.writeFile("UpdateAdminName.txt",name);
    }

    @Override
    public void deleteById(Integer id) {
        try {
            adminArrayList.remove(id - 1);
        }catch(Exception ex){
            throw new MyException("Don't exist this admin");
        }
        String deletedAdmin=adminArrayList.get(id-1).getFullName();
        FileWriter.writeFile("DeletedAdmin.txt",deletedAdmin);
    }
}
