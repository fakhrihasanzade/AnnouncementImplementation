package service;

import model.entity.User;

import java.util.List;

public interface AdminService {


    List<User> getAllEmail(String email);

    void UpdateFullName(int id, String fullname);

    void UpdateEmail(int id, String email);

}
