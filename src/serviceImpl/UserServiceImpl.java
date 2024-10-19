package serviceImpl;

import model.entity.User;
import exception.MyException;
import model.enums.Position;
import service.CommonService;
import service.UserService;
import util.FileWriter;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements CommonService<User>, UserService {

    static ArrayList<User> userArrayList = new ArrayList<>();

    static {
        userArrayList.add(new User(1, "Bənövşə Əliyeva", "benovse@example.com", Position.USER, "parol1"));
        userArrayList.add(new User(2, "Ağa Məmmədov", "aga@example.com", Position.USER, "parol2"));
        userArrayList.add(new User(3, "Leyla Quliyeva", "leyla@example.com", Position.USER, "parol3"));
    }

    @Override
    public void add(User object) {
        userArrayList.add(object);
        FileWriter.writeFile("AddedUser.txt",object.getFullName());
    }

    @Override
    public List<User> getAll() {
        return userArrayList;
    }

    @Override
    public User getById(Integer id) {
        try {
            return userArrayList.stream().filter(a -> a.getId() == id).toList().get(0);
        } catch (Exception ex) {
            throw new MyException("Don't exist this user");
        }
    }

    @Override
    public void updateById(Integer id, String name) {
        try {
            for (User a : userArrayList) {
                if (a.getId() == id) {
                    a.setFullName(name);
                }
            }
        } catch (Exception ex) {
            throw new MyException("Don't exist this user");
        }

        FileWriter.writeFile("UpdatedUser.txt",name);
    }

    @Override
    public void deleteById(Integer id) {
        try {
            userArrayList.remove(id - 1);
        } catch (Exception ex) {
            throw new MyException("Don't exist this user");
        }
        FileWriter.writeFile("DeletedUser.txt",userArrayList.get(id-1).getFullName());
    }

    @Override
    public void updateFullName(int id, String fullname) {
        try {
            for (User a : userArrayList) {
                if (a.getId() == id) {
                    a.setFullName(fullname);
                }
            }
        } catch (Exception ex) {
            throw new MyException("Don't exist this user");
        }
        FileWriter.writeFile("UpdatedUser.txt",fullname);
    }

    @Override
    public void updateEmail(int id, String email) {
        String note="";
        try {
            for (User a : userArrayList) {
                if (a.getId() == id) {
                    a.setEmail(email);
                    note=a.getFullName();
                }
            }
        } catch (Exception ex) {
            throw new MyException("Don't exist this user");
        }
        FileWriter.writeFile("UpdatedUser.txt",note);
    }
}
